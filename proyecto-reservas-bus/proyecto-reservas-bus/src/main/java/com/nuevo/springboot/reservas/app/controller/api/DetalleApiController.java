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


import com.nuevo.springboot.reservas.app.models.entity.Detalle;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;

@RestController
@RequestMapping("/api")
public class DetalleApiController {

	@Autowired
	private GenericDataService<Detalle> detalleService;
	
	@GetMapping("/detalle")
	public List<Detalle> index(){
		return detalleService.findAll();
	}
	
	@GetMapping("/detalle/{id}")
	public Detalle show(@PathVariable Integer id) {
		return detalleService.findById(id);
	}
	
	@PostMapping("/detalle")
	@ResponseStatus(HttpStatus.CREATED)
	public Detalle create(@RequestBody Detalle detalle) {
	   return detalleService.save1(detalle);
	}
	
	@PutMapping("/detalle/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Detalle update(@RequestBody Detalle detalle, @PathVariable Integer id) {
		Detalle detalleActual = detalleService.findById(id);
		
		detalleActual.setDescripcion(detalle.getDescripcion());
		detalleActual.setId(detalle.getId());
	
		
		return detalleService.save1(detalleActual);
	}
	
	@DeleteMapping("/detalle/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		detalleService.delete(id);
	}
}
