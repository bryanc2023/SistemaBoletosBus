package com.nuevo.springboot.reservas.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.service.ICooperativaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;

@Controller
public class UnidadController {

	@Autowired
	private  IUnidadService unidadService;
	
	@Autowired
	private  ICooperativaService cooperativaService;
	
	@GetMapping("/unidad/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE UNIDADES");
	    model.addAttribute("unidades", unidadService.findAll());
	    model.addAttribute("cooperativa", cooperativaService.findAll());
	    return "listarUnidad";
	}

	@GetMapping("/unidad/form")
	public String crear(Model model) {
	    Unidad unidad = new Unidad();
	    model.addAttribute("unidad", unidad);
	    model.addAttribute("titulo", "Ingrese la unidad");
	    model.addAttribute("cooperativas", cooperativaService.findAll());
	    return "formUnidad";
	}
	
	@GetMapping("/unidad/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Unidad unidad = null;
		if (id > 0) {
			unidad = unidadService.findOne(id);
			if (unidad == null) {
				flash.addFlashAttribute("error", "El id de la unidad no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id de la unidad no puede ser 0!");
			return "redirect:listar";
		}
		model.addAttribute("unidad", unidad);
		model.addAttribute("cooperativas", cooperativaService.findAll());
		model.addAttribute("titulo", "Editar cliente");
		return "formUnidad";
	}
	
	@GetMapping("/unidad/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			unidadService.delete(id);
			flash.addFlashAttribute("success", "Unidad eliminada con exito!");
		}
		return "redirect:/unidad/listar";
	}

	@PostMapping("/unidad/form")
	public String guardar(Unidad unidad, RedirectAttributes flash, SessionStatus status, Model model) {
	    try {
	        String mensajeFlash = (unidad.getId() != null) ? "Unidad editada con éxito!" : "Unidad creada con éxito!";

	        // Verificar si ya existe una unidad con el mismo número para la misma cooperativa
	        if (unidadService.existeUnidadConNumeroYCooperativa(unidad.getNumero(), unidad.getCooperativa())) {
	            String errorMessage = "Error: Unidad duplicada. Esta unidad ya existe con ese número y misma cooperativa.";
	            model.addAttribute("mensaje", errorMessage);
	            model.addAttribute("cooperativas", cooperativaService.findAll());
	            return "formUnidad"; // Redirige de vuelta al formulario con el mensaje de error.
	        }

	        
	        // Si no existe una unidad con el mismo número para la misma cooperativa, se guarda
	        unidadService.save(unidad);
	        status.setComplete();
	        flash.addFlashAttribute("success", mensajeFlash);
	        return "redirect:/unidad/listar";
	    } catch (DataIntegrityViolationException ex) {
	        String errorMessage = "Error: Ocurrió un problema al guardar la unidad.";
	        model.addAttribute("mensaje", errorMessage);
	        model.addAttribute("cooperativas", cooperativaService.findAll());
	        return "formUnidad"; // Redirige de vuelta al formulario con el mensaje de error.
	    }
	}
	

}
