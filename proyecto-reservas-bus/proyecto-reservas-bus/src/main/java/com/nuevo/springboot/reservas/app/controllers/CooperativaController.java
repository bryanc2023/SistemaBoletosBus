package com.nuevo.springboot.reservas.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.service.ICooperativaService;


@Controller
public class CooperativaController {

	@Autowired
	private  ICooperativaService cooperativaService;
	
	@GetMapping("/cooperativa/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE COOPERATIVAS");
	    model.addAttribute("cooperativas", cooperativaService.findAll());
	    return "listarCooperativa";
	}

	@GetMapping("/cooperativa/form")
	public String crear(Model model) {
	    Cooperativa cooperativa = new Cooperativa();
	    model.addAttribute("cooperativa", cooperativa);
	    model.addAttribute("titulo", "Ingrese la cooperativa");
	    return "formCooperativa";
	}
	
	@GetMapping("/cooperativa/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Cooperativa cooperativa = null;
		if (id > 0) {
			cooperativa = cooperativaService.findOne(id);
			if (cooperativa == null) {
				flash.addFlashAttribute("error", "El id de la cooperativa no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id de la cooperativa no puede ser 0!");
			return "redirect:listar";
		}
		model.addAttribute("cooperativa", cooperativa);
		model.addAttribute("titulo", "Editar cooperativa");
		return "formCooperativa";
	}
	
	@GetMapping("/cooperativa/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			cooperativaService.delete(id);
			flash.addFlashAttribute("success", "Cooperativa eliminada con exito!");
		}
		return "redirect:/cooperativa/listar";
	}

	@PostMapping("/cooperativa/form")
	public String guardar(Cooperativa cooperativa, RedirectAttributes flash, SessionStatus status,Model model) {
		try {
		String mensajeFlash = (cooperativa.getId() != null)? "Cooperativa editada con exito!" : "Cooperativa creada con exito!";
		cooperativaService.save(cooperativa);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
	    return "redirect:/cooperativa/listar";
	  } catch (DataIntegrityViolationException ex) {
          String errorMessage = "Error: Cooperativa duplicada. Esta cooperativa ya existe.";
          model.addAttribute("mensaje", errorMessage);
          return "formCooperativa"; // Redirige de vuelta al formulario con el mensaje de error.
      }
	}
	

}
