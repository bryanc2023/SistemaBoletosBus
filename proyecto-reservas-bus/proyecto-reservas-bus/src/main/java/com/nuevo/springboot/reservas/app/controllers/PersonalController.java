package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Personal;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;

@Controller
public class PersonalController {

	@Autowired
	private GenericDataService<Personal> personalService;

	@GetMapping("/personal/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "LISTA DE PERSONAL");
		model.addAttribute("personals", personalService.findAll());
		return "listarPersonal";
	}

	@GetMapping("/personal/form")
	public String crear(Model model) {
		Personal personal = new Personal();
		model.addAttribute("personal", personal);
		model.addAttribute("titulo", "Ingrese el personal");
		return "formPersonal";
	}

	@GetMapping("/personal/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Personal personal = null;
		if (id > 0) {
			personal = personalService.findOne(id);
			if(personal == null) {
				flash.addFlashAttribute("error", "El id del personal no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id del personal no puede ser 0!");
			return "redirect:/personal/listar";
		}
		model.addAttribute("personal", personal);
		model.addAttribute("titulo", "Editar personal");
		return "formPersonal";
	}

	@GetMapping("/personal/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {
		if (id > 0) {
			personalService.delete(id);
			flash.addFlashAttribute("success", "Personal eliminado con exito!");
		}
		return "redirect:/personal/listar";
	}

	@PostMapping("/personal/form")
	public String guardar(Personal personal, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash = (personal.getId() != null) ? "Personal editado con exito!" : "Personal creado con exito!";
		personalService.save(personal);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
}
