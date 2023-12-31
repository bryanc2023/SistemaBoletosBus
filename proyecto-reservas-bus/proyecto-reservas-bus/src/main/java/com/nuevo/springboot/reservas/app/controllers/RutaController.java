package com.nuevo.springboot.reservas.app.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Ruta;

import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.UploadFileService;

@Controller
public class RutaController {

	@Autowired
	private IRutaService rutaService;

	@GetMapping("/ruta/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "LISTA DE RUTAS");
		model.addAttribute("rutas", rutaService.findAll());
		return "listarRuta";
	}
	@Autowired
	private UploadFileService upload;
	
	
	@GetMapping("/ruta/form")
	public String crear(Model model) {
		Ruta ruta = new Ruta();
		model.addAttribute("ruta", ruta);
		model.addAttribute("titulo", "Ingrese la ruta");
		return "formRuta";
	}

	@GetMapping("/ruta/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Ruta ruta = null;
		if (id > 0) {
			ruta = rutaService.findOne(id);
			if (ruta == null) {
				flash.addFlashAttribute("error", "El id de la ruta no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id de la ruta no puede ser 0!");
			return "redirect:/ruta/listar";
		}
		model.addAttribute("ruta", ruta);
		model.addAttribute("titulo", "Editar ruta");
		return "formRuta";
	}

	@GetMapping("/ruta/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {
		if (id > 0) {
			rutaService.delete(id);
			flash.addFlashAttribute("success", "Ruta eliminada con exito!");
		}
		return "redirect:/ruta/listar";
	}

	@PostMapping("/ruta/form")
	public String guardar(Ruta ruta, RedirectAttributes flash, SessionStatus status,@RequestParam("img") MultipartFile file) throws IOException {
		String mensajeFlash = (ruta.getId() != null) ? "Ruta editada con exito!" : "Ruta creada con exito!";
		//imagen
		
		if(ruta.getId()==null) { //cuando se crea un producto
			String nombreImagen = upload.saveImage(file);
			ruta.setImagen(nombreImagen);
		}else {
			if(file.isEmpty()) {
				Ruta r = new Ruta();
				r=rutaService.get(ruta.getId()).get();
				ruta.setImagen(r.getImagen());
			}else {
				String nombreImagen = upload.saveImage(file);
				ruta.setImagen(nombreImagen);
			}
		}
		
		rutaService.save(ruta);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

}
