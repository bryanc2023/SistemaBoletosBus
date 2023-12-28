package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;






@Controller
public class CronogramaController {
	@Autowired
	private GenericDataService <Cronograma> cronogramaService;
	
	@GetMapping("/cronograma/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE CRONOGRAMAS");
	    model.addAttribute("cronogramas", cronogramaService.findAll());
	    return "listarCro";
	}
	
	@GetMapping("/cronograma/form")
	public String crear(Model model) {
	    Cronograma cronograma = new Cronograma();
	    model.addAttribute("cronograma", cronograma);
	    model.addAttribute("titulo", "Ingrese el cronograma");
	    return "formCro";
	}
	
	@GetMapping("/cronograma/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		Cronograma cronograma = null;
		if(id > 0) {
			cronograma = cronogramaService.findOne(id);
			if(cronograma == null) {
				flash.addFlashAttribute("error", "El id del cronograma no existe en la base de datos!");
				return "redirect:listar";
			}
		}else {
		flash.addFlashAttribute("error", "El id del cronograma no puede ser 0!");
		
		return "redirect:/cronograma/listar";
		}
		model.addAttribute("cronograma", cronograma);
	    model.addAttribute("titulo", "Editar cronograma");
		return "formCro";
	}
	
	@GetMapping("/cronograma/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			cronogramaService.delete(id);
			flash.addFlashAttribute("success", "Cronograma eliminado con exito!");
		}
		return "redirect:/cronograma/listar";
	}
	
	@PostMapping("/cronograma/form")
	public String guardar(Cronograma cronograma, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash = (cronograma.getId() != null)? "Cronograma editado con exito!" : "Cronograma creado con exito!";
		cronogramaService.save(cronograma);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
	    return "redirect:listar";
	}
	
	
}
