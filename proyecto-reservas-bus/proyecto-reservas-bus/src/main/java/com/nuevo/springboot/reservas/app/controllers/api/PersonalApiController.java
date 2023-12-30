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

import com.nuevo.springboot.reservas.app.models.entity.Personal;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;

@RestController
@RequestMapping("/api")
public class PersonalApiController {

	@Autowired
	private GenericDataService<Personal> personalService;
	
	@GetMapping("/personal")
	public List<Personal> index(){
		return personalService.findAll();
	}
	
	@GetMapping("/personal/{id}")
	public Personal show(@PathVariable Integer id) {
		return personalService.findById(id);
	}
	
	@PostMapping("/personal")
	@ResponseStatus(HttpStatus.CREATED)
	public Personal create(@RequestBody Personal personal) {
	   return personalService.save1(personal);
	}
	
	@PutMapping("/personal/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Personal update(@RequestBody Personal personal, @PathVariable Integer id) {
		Personal personalActual = personalService.findById(id);
		
		personalActual.setApellido(personal.getApellido());
		personalActual.setBoletos(personal.getBoletos());
		personalActual.setFechaIngreso(personal.getFechaIngreso());
		personalActual.setGenero(personal.getGenero());
		personalActual.setId(personalActual.getId());
		personalActual.setNombre(personal.getNombre());
		personalActual.setNroPuesto(personal.getNroPuesto());
		personalActual.setUsuario(personal.getUsuario());
	
		
		
		return personalService.save1(personalActual);
	}
	
	@DeleteMapping("/personal/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		personalService.delete(id);
	}
}
