package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.nuevo.springboot.reservas.app.models.entity.Pasajero;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;

@Controller
public class PasajeroController {
		
	
	@Autowired
	private  GenericDataService <Pasajero> pasajeroService;
	
	@GetMapping("/pasajero/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE PASAJEROS");
	    model.addAttribute("pasajeros", pasajeroService.findAll());
	    return "listarPasajero";
	}
	
	@GetMapping("/pasajero/form")
	public String crear(Model model) {
	    Pasajero pasajero = new Pasajero();
	    model.addAttribute("pasajero", pasajero);
	    model.addAttribute("titulo", "Ingrese el pasajero");
	    return "formPasajero";
	}
	
	@GetMapping("/pasajero/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Pasajero pasajero = null;
		if(id > 0) {
			pasajero = pasajeroService.findOne(id);
		}else {
		return "redirect:listar";
		}
		model.addAttribute("pasajero", pasajero);
	    model.addAttribute("titulo", "Editar pasajero");
		return "formPasajero";
	}
	
	@GetMapping("/pasajero/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			pasajeroService.delete(id);
		}
		return "redirect:/pasajero/listar";
	}

	@PostMapping("/pasajero/form")
	public String guardar(Pasajero pasajero) {
		pasajeroService.save(pasajero);
	    return "redirect:listar";
	}
	
}
