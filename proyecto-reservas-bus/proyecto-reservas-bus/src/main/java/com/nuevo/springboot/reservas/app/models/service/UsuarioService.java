package com.nuevo.springboot.reservas.app.models.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
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
import com.nuevo.springboot.reservas.app.models.entity.Rol;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.dao.IDetalleDao;
import com.nuevo.springboot.reservas.app.models.dao.IUsuarioDao;



@Service
public class UsuarioService implements IUsuarioService{

	
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
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(), 
				registroDTO.getApellido(),registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),Arrays.asList(new Rol("ROLE_USER")));
		return usuarioRepositorio.save(usuario);
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

}
