package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;






@Controller
public class RutaController {
	
	@Autowired
	private  GenericDataService <Ruta> rutaService;
	
	@GetMapping("/ruta/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE RUTAS");
	    model.addAttribute("rutas", rutaService.findAll());
	    return "listarRuta";
	}
	
	@GetMapping("/ruta/form")
	public String crear(Model model) {
	    Ruta ruta = new Ruta();
	    model.addAttribute("ruta", ruta);
	    model.addAttribute("titulo", "Ingrese la ruta");
	    return "formRuta";
	}
	
	@GetMapping("/ruta/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Ruta ruta = null;
		if(id > 0) {
			ruta = rutaService.findOne(id);
		}else {
		return "redirect:/ruta/listar";
		}
		model.addAttribute("ruta", ruta);
	    model.addAttribute("titulo", "Editar ruta");
		return "formRuta";
	}
	
	@GetMapping("/ruta/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			rutaService.delete(id);
		}
		return "redirect:/ruta/listar";
	}
	
	@PostMapping("/ruta/form")
	public String guardar(Ruta ruta) {
		rutaService.save(ruta);
	    return "redirect:listar";
	}

}
