package com.nuevo.springboot.reservas.app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Configuracion;

import com.nuevo.springboot.reservas.app.models.service.ConfiguracionService;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {
	
	@Autowired
	private ConfiguracionService configuracionService;
	
	@GetMapping("/listar")
	public String listar(Model model ) {
	    model.addAttribute("titulo", "LISTA DE CONFIGURACION");
	    model.addAttribute("configuraciones", configuracionService.findAll());
	    return "listarConf";
	}
	@GetMapping("/form")
	public String crear(Model model) {
	    Configuracion configuracion = new Configuracion();
	    configuracion.setFechaConfiguracion(new Date());
	    model.addAttribute("configuracion", configuracion);
	    model.addAttribute("titulo", "Ingrese la configuracion");
	    return "formConfiguracion";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
	    Configuracion configuracion = null;

	    if (id > 0) {
	        configuracion = configuracionService.findOne(id);

	        if (configuracion == null) {
	            flash.addFlashAttribute("error", "El id del detalle no existe en la base de datos!");
	            return "redirect:listarConf";
	        }
	    } else {
	        flash.addFlashAttribute("error", "El id del detalle no puede ser 0!");
	        return "redirect:listar";
	    }

	    // If the edited configuration is being set to "activo," deactivate other configurations
	    if ("activo".equals(configuracion.getEstado())) {
	        List<Configuracion> configuracionesActivas = configuracionService.findAllConfiguracionesActivas();

	        for (Configuracion activa : configuracionesActivas) {
	            if (!activa.getId().equals(configuracion.getId())) {
	                activa.setEstado("inactivo");
	                configuracionService.save(activa);
	            }
	        }
	    }

	    // Desactivar la configuración actualmente activa
	    Configuracion configuracionActiva = configuracionService.findConfiguracionActiva();

	    if (configuracionActiva != null && !configuracionActiva.getId().equals(configuracion.getId())) {
	        configuracionActiva.setEstado("inactivo");
	        configuracionService.save(configuracionActiva);
	    }

	    // Activar la configuración que estás editando
	    configuracion.setEstado("activo");
	    configuracionService.save(configuracion);

	    model.addAttribute("configuracion", configuracion);
	    model.addAttribute("titulo", "Editar configuracion");
	    return "formConfiguracion";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			configuracionService.delete(id);
			flash.addFlashAttribute("success", "Configuracion eliminada con exito!");
		}
		return "redirect:/listar";
	}
	
	@PostMapping("/form")
	public String guardar(Configuracion configuracion, RedirectAttributes flash, SessionStatus status) {
	    // Find the currently active configuration
	    Configuracion configuracionActiva = configuracionService.findConfiguracionActiva();

	    // If there is an active configuration and it's different from the new one
	    if (configuracionActiva != null && !configuracionActiva.getId().equals(configuracion.getId())) {
	        configuracionActiva.setEstado("inactivo");
	        configuracionService.save(configuracionActiva);
	    }

	    // Set the new configuration as active
	    configuracion.setEstado("activo");
	    configuracionService.save(configuracion);

	    // Set flash attributes and complete the session
	    String mensajeFlash = (configuracion.getId() != null) ? "Configuracion editada con exito!" : "Configuracion creada con exito!";
	    status.setComplete();
	    flash.addFlashAttribute("success", mensajeFlash);

	    return "redirect:listar";
	}
}
	

