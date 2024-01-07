package com.nuevo.springboot.reservas.app.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;

import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.UploadFileService;



	@Controller
	public class RutaController {
		
		@Autowired
		private  IRutaService rutaService;
		
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
		public String guardar(Ruta ruta, Model model) {
			 try {
		            rutaService.save(ruta);
		            return "redirect:/ruta/listar";
		        } catch (DataIntegrityViolationException ex) {
		            String errorMessage = "Error: Ruta duplicada. Esta ruta ya existe.";
		            model.addAttribute("mensaje", errorMessage);
		            return "formRuta"; // Redirige de vuelta al formulario con el mensaje de error.
		        }
			
		}
		
	
		
		
		

	}


