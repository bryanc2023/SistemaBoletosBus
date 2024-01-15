package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Detalle;
import com.nuevo.springboot.reservas.app.models.service.IDetalleService;


@Controller
public class DetalleController {
	@Autowired
	private IDetalleService detalleService;
	
	@GetMapping("/detalle/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE DETALLES");
	    model.addAttribute("detalles", detalleService.findAll());
	    return "listarDetalle";
	}
	
	
	

	@GetMapping("/detalle/form")
	public String crear(Model model) {
	    Detalle detalle = new Detalle();
	    model.addAttribute("detalle", detalle);
	    model.addAttribute("titulo", "Ingrese la detalle");
	    return "formDetalle";
	}
	
	@GetMapping("/detalle/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		Detalle detalle = null;
		if(id > 0) {
			detalle = detalleService.findOne(id);
			if(detalle == null) {
				flash.addFlashAttribute("error", "El id del detalle no existe en la base de datos!");
				return "redirect:listar";
			}
		}else {
		flash.addFlashAttribute("error", "El id del detalle no puede ser 0!");
		return "redirect:listar";
		}
		model.addAttribute("detalle", detalle);
	    model.addAttribute("titulo", "Editar detalle");
		return "formDetalle";
	}
	
	@GetMapping("/detalle/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			detalleService.delete(id);
			flash.addFlashAttribute("success", "Detalle eliminado con exito!");
		}
		return "redirect:/detalle/listar";
	}

	@PostMapping("/detalle/form")
	public String guardar(Detalle detalle, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash = (detalle.getId() != null)? "Detalle editado con exito!" : "Detalle creado con exito!";
		detalleService.save(detalle);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
	    return "redirect:listar";
	}
	
	
}
