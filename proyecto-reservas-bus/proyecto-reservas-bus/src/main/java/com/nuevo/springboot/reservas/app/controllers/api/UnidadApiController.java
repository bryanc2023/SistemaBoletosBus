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

import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;

@RestController
@RequestMapping("/api")
public class UnidadApiController {

	@Autowired
	private IUnidadService unidadService;
	
	@GetMapping("/unidad")
	public List<Unidad> index(){
		return unidadService.findAll();
	}
	
	@GetMapping("/unidad/{id}")
	public Unidad show(@PathVariable Integer id) {
		return unidadService.findById(id);
	}
	
	@PostMapping("/unidad")
	@ResponseStatus(HttpStatus.CREATED)
	public Unidad create(@RequestBody Unidad unidad) {
	   return unidadService.save1(unidad);
	}
	
	@PutMapping("/unidad/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Unidad update(@RequestBody Unidad unidad, @PathVariable Integer id) {
		Unidad unidadActual = unidadService.findOne(id);
		
		unidadActual.setCantidadAsientos(unidad.getCantidadAsientos());
		unidadActual.setCooperativa(unidad.getCooperativa());
		unidadActual.setEstadoActividad(unidad.getEstadoActividad());
		unidadActual.setId(unidad.getId());
		unidadActual.setNumero(unidad.getNumero());
		return unidadService.save1(unidadActual);
	}
	
	@DeleteMapping("/unidad/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		unidadService.delete(id);
	}
}
