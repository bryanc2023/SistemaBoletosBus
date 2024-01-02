package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;
import com.nuevo.springboot.reservas.app.models.service.IBoletoService;



@Controller
public class BoletoController {

	@Autowired
	private IBoletoService boletoService;
	
	@GetMapping("/boleto/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE BOLETOS");
	    model.addAttribute("boletos", boletoService.findAll());
	    return "listarBoleto";
	}

	@GetMapping("/boleto/form")
	public String crear(Model model) {
	    Boleto boleto = new Boleto();
	    model.addAttribute("boleto", boleto);
	    model.addAttribute("titulo", "Ingrese el boleto");
	    return "formBoleto";
	}
	
	@GetMapping("/boleto/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		Boleto boleto = null;
		if(id > 0) {
			boleto = boletoService.findOne(id);
			if(boleto == null) {
				flash.addFlashAttribute("error", "El id del boleto no existe en la base de datos!");
				return "redirect:listar";
			}
		}else {
		flash.addFlashAttribute("error", "El id del boleto no puede ser 0!");
		return "redirect:listar";
		}
		model.addAttribute("boleto", boleto);
	    model.addAttribute("titulo", "Editar boleto");
		return "formBoleto";
	}
	
	@GetMapping("/boleto/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			boletoService.delete(id);
			flash.addFlashAttribute("success", "Boleto eliminado con exito!");
		}
		return "redirect:/boleto/listar";
	}

	@PostMapping("/boleto/form")
	public String guardar( Boleto boleto, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash = (boleto.getId() != null)? "Boleto editado con exito!" : "Boleto creado con exito!";
		boletoService.save(boleto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
	    return "redirect:listar";
	}
	
}
