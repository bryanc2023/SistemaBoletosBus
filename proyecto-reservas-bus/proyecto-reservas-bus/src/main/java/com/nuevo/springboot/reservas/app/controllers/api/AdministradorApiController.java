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

import com.nuevo.springboot.reservas.app.models.entity.Administrador;
import com.nuevo.springboot.reservas.app.models.service.IAdministradorService;

@RestController
@RequestMapping("/api")
public class AdministradorApiController {

	@Autowired
	private IAdministradorService administradorService;
	
	@GetMapping("/administrador")
	public List<Administrador> index(){
		return administradorService.findAll();
	}
	
	@GetMapping("/administrador/{id}")
	public Administrador show(@PathVariable Integer id) {
		return administradorService.findById(id);
	}
	
	@PostMapping("/administrador")
	@ResponseStatus(HttpStatus.CREATED)
	public Administrador create(@RequestBody Administrador administrador) {
	   return administradorService.save1(administrador);
	}
	
	@PutMapping("/administrador/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Administrador update(@RequestBody Administrador administrador, @PathVariable Integer id) {
		Administrador administradorActual = administradorService.findById(id);
		administradorActual.setApellido(administrador.getApellido());
		administradorActual.setFechaIngreso(administrador.getFechaIngreso());
		administradorActual.setGenero(administrador.getGenero());
		administradorActual.setId(administrador.getId());
		administradorActual.setNombre(administrador.getNombre());
		administradorActual.setRutas(administrador.getRutas());
		administradorActual.setUsuario(administrador.getUsuario());
		
		return administradorService.save1(administradorActual);
	}
	
	@DeleteMapping("/administrador/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		administradorService.delete(id);
	}
}
