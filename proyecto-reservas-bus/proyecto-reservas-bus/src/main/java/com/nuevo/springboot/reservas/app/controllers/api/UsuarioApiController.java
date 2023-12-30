package com.nuevo.springboot.reservas.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;

@RestController
@RequestMapping("/api")
public class UsuarioApiController {

	@Autowired
	private GenericDataService<Usuario> usuarioService;
	
	@GetMapping("/usuario")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario show(@PathVariable Integer id) {
		return usuarioService.findById(id);
	}
	
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {
	   return usuarioService.save1(usuario);
	}
	
	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		Usuario usuarioActual = usuarioService.findById(id);

		usuarioActual.setContraseñaUsuario(usuario.getContraseñaUsuario());
		usuarioActual.setCorreoUsuario(usuario.getCorreoUsuario());
		usuarioActual.setEstadoActividad(usuario.getEstadoActividad());
		usuarioActual.setId(usuario.getId());
		usuarioActual.setNombreUsuario(usuario.getNombreUsuario());
		usuarioActual.setContraseñaUsuario(usuario.getContraseñaUsuario());
		
		return usuarioService.save1(usuarioActual);
	}
	
	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		usuarioService.delete(id);
	}
}
