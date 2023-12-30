package com.nuevo.springboot.reservas.app.controller.api;

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


import com.nuevo.springboot.reservas.app.models.entity.Pasajero;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;


@RestController
@RequestMapping("/api")
public class PasajeroApiController {

	@Autowired
	private GenericDataService<Pasajero> pasajeroService;
	
	@GetMapping("/pasajero")
	public List<Pasajero> index(){
		return pasajeroService.findAll();
	}
	
	@GetMapping("/pasajero/{id}")
	public Pasajero show(@PathVariable Integer id) {
		return pasajeroService.findById(id);
	}
	
	@PostMapping("/pasajero")
	@ResponseStatus(HttpStatus.CREATED)
	public Pasajero create(@RequestBody Pasajero pasajero) {
	   return pasajeroService.save1(pasajero);
	}
	
	@PutMapping("/pasajero/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Pasajero update(@RequestBody Pasajero pasajero, @PathVariable Integer id) {
		Pasajero pasajeroActual = pasajeroService.findById(id);
		
		pasajeroActual.setApellido(pasajero.getApellido());
		pasajeroActual.setBoletos(pasajero.getBoletos());
		pasajeroActual.setDireccion(pasajero.getDireccion());
		pasajeroActual.setEdad(pasajero.getEdad());
		pasajeroActual.setGenero(pasajero.getGenero());
		pasajeroActual.setId(pasajero.getId());
		pasajeroActual.setNombre(pasajero.getNombre());
		pasajeroActual.setTelefono(pasajero.getTelefono());
		pasajeroActual.setUsuario(pasajero.getUsuario());
		
		
		return pasajeroService.save1(pasajeroActual);
	}
	
	@DeleteMapping("/pasajero/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		pasajeroService.delete(id);
	}
}
