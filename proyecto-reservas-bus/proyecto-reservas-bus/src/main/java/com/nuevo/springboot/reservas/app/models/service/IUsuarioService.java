package com.nuevo.springboot.reservas.app.models.service;
import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;


public interface IUsuarioService extends UserDetailsService{

	
	public void guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public Usuario guardarUsuarioPersonal(UsuarioRegistroDTO registroDTO);

	List<Usuario> findByRole(String rol);
	Usuario findByEmail(String email);

	public void save(Usuario entity);
    public Usuario findOne(Long id);
    public void delete(Long id);
    public List<Usuario> findAll();
	public Usuario save1(Usuario entity);
	public Usuario findById(Long id);
	public Usuario get(Long id);
	public void delete1(Long id);

	public Long obtenerIdUsuarioPorEmail(String email);
	public Long obtenerIdUsuarioLogueado();

}
