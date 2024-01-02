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

import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.service.IRutaService;

@RestController
@RequestMapping("/api")
public class RutaApiController {

	@Autowired
	private IRutaService rutaService;
	
	@GetMapping("/ruta")
	public List<Ruta> index(){
		return rutaService.findAll();
	}
	
	@GetMapping("/ruta/{id}")
	public Ruta show(@PathVariable Integer id) {
		return rutaService.findById(id);
	}
	
	@PostMapping("/ruta")
	@ResponseStatus(HttpStatus.CREATED)
	public Ruta create(@RequestBody Ruta ruta) {
	   return rutaService.save1(ruta);
	}
	
	@PutMapping("/ruta/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ruta update(@RequestBody Ruta ruta, @PathVariable Integer id) {
		Ruta rutaActual = rutaService.findById(id);
		
		rutaActual.setAdministrador(ruta.getAdministrador());
		rutaActual.setCostoRuta(ruta.getCostoRuta());
		rutaActual.setId(ruta.getId());
		rutaActual.setRutaDestino(ruta.getRutaDestino());
		rutaActual.setRutaOrigen(ruta.getRutaOrigen());
		
		
		return rutaService.save1(rutaActual);
	}
	
	@DeleteMapping("/ruta/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		rutaService.delete(id);
	}
}
