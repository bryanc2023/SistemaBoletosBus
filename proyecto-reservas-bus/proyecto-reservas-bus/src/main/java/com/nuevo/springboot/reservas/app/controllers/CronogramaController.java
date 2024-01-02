package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;
import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.service.ICronogramaService;
import com.nuevo.springboot.reservas.app.models.service.RutaService;
import com.nuevo.springboot.reservas.app.models.service.UnidadService;

@Controller
public class CronogramaController {
	@Autowired
	private ICronogramaService cronogramaService;

	@Autowired
	private UnidadService unidadService;

	@Autowired
	private RutaService rutaService;

	@GetMapping("/cronograma/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "LISTA DE CRONOGRAMAS");
		model.addAttribute("cronogramas", cronogramaService.findAll());
		model.addAttribute("unidad", unidadService.findAll());
		model.addAttribute("ruta", rutaService.findAll());
		return "listarCro";
	}

	@GetMapping("/cronograma/form")
	public String crear(Model model) {
		Cronograma cronograma = new Cronograma();
		model.addAttribute("cronograma", cronograma);
		model.addAttribute("titulo", "Ingrese el cronograma");
		model.addAttribute("unidades", unidadService.findAll());
		model.addAttribute("rutas", rutaService.findAll());
		return "formCro";
	}

	@GetMapping("/cronograma/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Cronograma cronograma = null;
		if (id > 0) {
			cronograma = cronogramaService.findOne(id);
			if (cronograma == null) {
				flash.addFlashAttribute("error", "El id del cronograma no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id del cronograma no puede ser 0!");

			return "redirect:/cronograma/listar";
		}
		model.addAttribute("cronograma", cronograma);
		model.addAttribute("titulo", "Editar cronograma");
		return "formCro";
	}

	@PostMapping("/cronograma/form")
	public String guardar(@ModelAttribute("cronograma") Cronograma cronograma, @ModelAttribute("ruta.id") Integer rutaId,
	        @ModelAttribute("unidad.id") Integer unidadId, RedirectAttributes flash, SessionStatus status) {

		String mensajeFlash = (cronograma.getId() != null)? "Cronograma editado con exito!" : "Cronograma creado con exito!";
	    // Obtener la instancia de Ruta y Unidad utilizando los IDs
	    Ruta rutaSeleccionada = rutaService.findById(rutaId);
	    Unidad unidadSeleccionada = unidadService.findById(unidadId);

	    // Asignar las instancias de Ruta y Unidad al cronograma
	    cronograma.setRuta(rutaSeleccionada);
	    cronograma.setUnidad(unidadSeleccionada);

	    // Guardar el cronograma con las relaciones establecidas
	    cronogramaService.save(cronograma);
	    status.setComplete();

	   
	    flash.addFlashAttribute("success", mensajeFlash);

	    return "redirect:listar";
	}


}
