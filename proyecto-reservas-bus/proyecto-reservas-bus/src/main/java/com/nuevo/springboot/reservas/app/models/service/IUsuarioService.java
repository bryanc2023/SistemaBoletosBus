package com.nuevo.springboot.reservas.app.models.service;
import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;


public interface IUsuarioService extends UserDetailsService{

	
	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;



	
}
