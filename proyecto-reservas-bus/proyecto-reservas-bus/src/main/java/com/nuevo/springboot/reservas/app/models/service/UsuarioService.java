package com.nuevo.springboot.reservas.app.models.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.entity.ConfirmationToken;
import com.nuevo.springboot.reservas.app.models.entity.Rol;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.dao.IConfirmationTokenDao;
import com.nuevo.springboot.reservas.app.models.dao.IDetalleDao;
import com.nuevo.springboot.reservas.app.models.dao.IUsuarioDao;



@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
    private IConfirmationTokenDao confirmationTokenRepository;

   @Autowired
    private EmailServiceSender emailSenderService;
   
	
	private IUsuarioDao usuarioRepositorio;
	 @Autowired
	    public UsuarioService(IUsuarioDao usuarioRepository) {
	        this.passwordEncoder = new BCryptPasswordEncoder();
			this.usuarioRepositorio = usuarioRepository;
	    }

	private final BCryptPasswordEncoder passwordEncoder;

   
    public UsuarioService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    

	
	@Override
	public void guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(), 
				registroDTO.getApellido(),registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),Arrays.asList(new Rol("ROLE_USER")));
		
		usuarioRepositorio.save(usuario);
		ConfirmationToken confirmationToken = new ConfirmationToken(usuario);

        confirmationTokenRepository.save(confirmationToken);
        
         // Construir el cuerpo del correo con HTML
        String verificationLink = "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken();
        String nombreCompleto = registroDTO.getNombre() + " " + registroDTO.getApellido();
        String emailBody = "<div style='text-align: center;'><h2>Complete Registration</h2>"
        		+ "<p>Estimado " + nombreCompleto + ",</p>"
                + "<p>Para confirmar tu cuenta da click en el siguiente enlace:</p>"
                + "<a href='" + verificationLink + "' style='"
                + "background-color: #4CAF50; /* Green */"
                + "border: none;"
                + "color: white;"
                + "padding: 15px 32px;"
                + "text-align: center;"
                + "text-decoration: none;"
                + "display: inline-block;"
                + "font-size: 16px;'>Verificar Cuenta</a></div>";

        // Configurar el mensaje de correo
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(usuario.getEmail());
        mailMessage.setSubject("Completa tu registro!");
        mailMessage.setFrom("chand312902@gmail.com");
        mailMessage.setText("To confirm your account, please click here: " + verificationLink);

        // Configurar el contenido HTML del correo
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mailMessage.getTo());
            messageHelper.setSubject(mailMessage.getSubject());
            messageHelper.setFrom(mailMessage.getFrom());
            messageHelper.setText(emailBody, true); // true indica que el contenido es HTML
        };

        // Enviar el correo
        emailSenderService.sendHtmlEmail(messagePreparator);
		
		
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	 public Long obtenerIdUsuarioPorEmail(String email) {
	        Usuario usuario = usuarioRepositorio.findByEmail(email);
	        return usuario != null ? usuario.getId() : null;
	    }
	 
	 

    public Long obtenerIdUsuarioLogueado() {
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String email = authentication.getUsername();
            return obtenerIdUsuarioPorEmail(email);
        }
        return null;
    }
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario guardarUsuarioPersonal(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(), registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_PERSONAL")));
        return usuarioRepositorio.save(usuario);
    }



	@Override
	public void save(Usuario usuario) {
		usuarioRepositorio.save(usuario);
	}



	@Override
	public Usuario findOne(Long id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}



	@Override
	public void delete(Long id) {
		usuarioRepositorio.deleteById(id);
		
	}



	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepositorio.findAll();
	}



	@Override
	public Usuario save1(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}



	@Override
	public Usuario findById(Long id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}



	@Override
	public Usuario get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete1(Long id) {
		// TODO Auto-generated method stub
		usuarioRepositorio.deleteById(id);
	}



	@Override
	public List<Usuario> findByRole(String rol) {
		return usuarioRepositorio.findByRolesNombre(rol);

	}



	@Override
	public Usuario findByEmail(String email) {
		
		return usuarioRepositorio.findByEmail(email);
	}



	public void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException {
		Usuario usuario=usuarioRepositorio.findByEmail(email);
		if(usuario != null) {
			usuario.setResetPasswordToken(token);
			usuarioRepositorio.save(usuario);
		}else {
			throw new CustomerNotFoundException("No se pudo encontrar ningun usuario con el email" + email);
		}
		
	}

	public Usuario get(String resetPasswordToken) {
		return usuarioRepositorio.findByResetPasswordToken(resetPasswordToken);
	}
	
	public void updatePassword(Usuario usuario, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		
		usuario.setPassword(encodedPassword);
		usuario.setResetPasswordToken(null);
		
		usuarioRepositorio.save(usuario);
	}
	}
