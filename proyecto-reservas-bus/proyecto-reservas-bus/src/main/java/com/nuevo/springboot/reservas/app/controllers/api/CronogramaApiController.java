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

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;

@RestController
@RequestMapping("/api")
public class CronogramaApiController {

	@Autowired
	private GenericDataService<Cronograma> cronogramaService;
	
	@GetMapping("/cronograma")
	public List<Cronograma> index(){
		return cronogramaService.findAll();
	}
	
	@GetMapping("/cronograma/{id}")
	public Cronograma show(@PathVariable Integer id) {
		return cronogramaService.findById(id);
	}
	
	@PostMapping("/cronograma")
	@ResponseStatus(HttpStatus.CREATED)
	public Cronograma create(@RequestBody Cronograma cronograma) {
	   return cronogramaService.save1(cronograma);
	}
	
	@PutMapping("/cronograma/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cronograma update(@RequestBody Cronograma cronograma, @PathVariable Integer id) {
		Cronograma cronogramaActual = cronogramaService.findById(id);
		
		cronogramaActual.setAnio(cronograma.getAnio());
		cronogramaActual.setDescripcion(cronograma.getDescripcion());
		cronogramaActual.setDia(cronograma.getDia());
		cronogramaActual.setFecha(cronograma.getFecha());
		cronogramaActual.setId(cronograma.getId());
		cronogramaActual.setMes(cronograma.getMes());
		
		return cronogramaService.save1(cronogramaActual);
	}
	
	@DeleteMapping("/cronograma/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		cronogramaService.delete(id);
	}
	
}
