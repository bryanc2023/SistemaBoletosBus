package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Administrador;
import com.nuevo.springboot.reservas.app.models.service.IAdministradorService;


@Controller
public class AdministradorController {

	@Autowired
	private IAdministradorService administradorService;
	
	@GetMapping("/administrador/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE ADMINISTRADORES");
	    model.addAttribute("administradors", administradorService.findAll());
	    return "listarAdministrador";
	}
	
	@GetMapping("/administrador/form")
	public String crear(Model model) {
	    Administrador administrador= new Administrador();
	    model.addAttribute("administrador", administrador);
	    model.addAttribute("titulo", "Ingrese el administrador");
	    return "formAdministrador";
	}
	
	@GetMapping("/administrador/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		Administrador administrador = null;
		if(id > 0) {
			administrador = administradorService.findOne(id);
			if(administrador == null) {
				flash.addFlashAttribute("error", "El id del administrador no existe en la base de datos!");
				return "redirect:listar";
			}
		}else {
		flash.addFlashAttribute("error", "El id del administrador no puede ser 0!");
		return "redirect:/administrador/listar";
		}
		model.addAttribute("administrador", administrador);
	    model.addAttribute("titulo", "Editar administrador");
		return "formAdministrador";
	}
	
	@GetMapping("/administrador/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			administradorService.delete(id);
			flash.addFlashAttribute("success", "Administrador eliminado con exito!");
		}
		return "redirect:/administrador/listar";
	}
	
	@PostMapping("/administrador/form")
	public String guardar(Administrador administrador, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash = (administrador.getId() != null)? "Administrador editado con exito!" : "Administrador creado con exito!";
		administradorService.save(administrador);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
	    return "redirect:listar";
	}
	
}
