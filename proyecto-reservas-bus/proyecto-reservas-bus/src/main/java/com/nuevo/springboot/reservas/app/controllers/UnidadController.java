package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;


@Controller
public class UnidadController {

	@Autowired
	private  GenericDataService <Unidad> unidadService;
	
	@GetMapping("/unidad/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE UNIDADES");
	    model.addAttribute("unidades", unidadService.findAll());
	    return "listarUnidad";
	}

	@GetMapping("/unidad/form")
	public String crear(Model model) {
	    Unidad unidad = new Unidad();
	    model.addAttribute("unidad", unidad);
	    model.addAttribute("titulo", "Ingrese la unidad");
	    return "formUnidad";
	}
	
	@GetMapping("/unidad/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Unidad unidad = null;
		if(id > 0) {
			unidad = unidadService.findOne(id);
		}else {
		return "redirect:listar";
		}
		model.addAttribute("unidad", unidad);
	    model.addAttribute("titulo", "Editar cliente");
		return "formUnidad";
	}
	
	@GetMapping("/unidad/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			unidadService.delete(id);
		}
		return "redirect:/unidad/listar";
	}

	@PostMapping("/unidad/form")
	public String guardar(Unidad unidad) {
		unidadService.save(unidad);
	    return "redirect:/unidad/listar";
	}
	
}
