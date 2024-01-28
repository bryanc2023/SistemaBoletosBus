package com.nuevo.springboot.reservas.app.models.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
   
	@Autowired
	private JavaMailSender mailSender;
	
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
                registroDTO.getApellido(), registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")));

        usuarioRepositorio.save(usuario);
        ConfirmationToken confirmationToken = new ConfirmationToken(usuario);
        Resource resource1 = new ClassPathResource("static/images/image-1.png");
        String imageSrc1 = "cid:image-1";
        Resource resource2 = new ClassPathResource("static/images/image-2.png");
        String imageSrc2 = "cid:image-2";
        confirmationTokenRepository.save(confirmationToken);
        String emailTemplate = readHtmlFile("templates/correo.html");
        // Construir el cuerpo del correo con HTML
        String verificationLink = "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken();
        String nombreCompleto = registroDTO.getNombre() + " " + registroDTO.getApellido();
        emailTemplate = emailTemplate.replace("[NOMBRE_COMPLETO]", nombreCompleto);
        emailTemplate = emailTemplate.replace("[VERIFICATION_LINK]", verificationLink);
        emailTemplate = emailTemplate.replace("[IMAGE_SRC1]", imageSrc1);
        emailTemplate = emailTemplate.replace("[IMAGE_SRC2]", imageSrc2);
        final String finalEmailTemplate = emailTemplate;
        // Configurar el mensaje de correo
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            // Utilizar constructor con 'true' para indicar que el contenido es multipart
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(usuario.getEmail());
            messageHelper.setSubject("Completa tu registro!");
            messageHelper.setFrom("tikers@gmail.com");
            messageHelper.setText(finalEmailTemplate, true);
            messageHelper.addInline("image-1", resource1);
            messageHelper.addInline("image-2", resource2);// true indica que el contenido es HTML
        };

        // Enviar el correo
        emailSenderService.sendHtmlEmail(messagePreparator);
       
    }

	

	private String readHtmlFile(String filePath) {
	    try {
	        Resource resource = new ClassPathResource(filePath);
	        byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
	        return new String(fileData, StandardCharsets.UTF_8);
	    } catch (IOException e) {
	        // Manejar la excepción según tus necesidades
	        e.printStackTrace();
	        return ""; // O devolver un valor predeterminado
	    }
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
	
	public void sendEmail2(String recipientEmail, String link)
	        throws MessagingException, UnsupportedEncodingException {
		 MimeMessage message = mailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message, true);

		    helper.setTo(recipientEmail);
		    helper.setSubject("Recupera tu contraseña!");

		    // Construir el cuerpo del correo con HTML
		    Resource resource1 = new ClassPathResource("static/images/image-1.png");
		    String imageSrc1 = "cid:image-1";
		    Resource resource2 = new ClassPathResource("static/images/image-3.png");
		    String imageSrc2 = "cid:image-2";

		    String emailTemplate = readHtmlFile("templates/correo2.html");
		    
		 
		    emailTemplate = emailTemplate.replace("[VERIFICATION_LINK]", link);
		    emailTemplate = emailTemplate.replace("[IMAGE_SRC1]", imageSrc1);
		    emailTemplate = emailTemplate.replace("[IMAGE_SRC2]", imageSrc2);

		    // Configurar el mensaje de correo
		    helper.setText(emailTemplate, true);
		    helper.addInline("image-1", resource1);
		    helper.addInline("image-2", resource2);

		    mailSender.send(message);
	}
	@Override
	public void incrementarIntentosFallidos(String email) {
        Usuario usuario = findByEmail(email);
        if (usuario != null) {
            usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
            usuarioRepositorio.save(usuario);
        }
    }
	@Override
    public void reiniciarIntentosFallidos(String email) {
        Usuario usuario = findByEmail(email);
        if (usuario != null) {
            usuario.setIntentosFallidos(0);
            usuarioRepositorio.save(usuario);
        }
    }
	
	
	}
