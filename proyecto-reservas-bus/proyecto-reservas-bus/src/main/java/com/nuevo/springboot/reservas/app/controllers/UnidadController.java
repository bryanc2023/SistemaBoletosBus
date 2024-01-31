package com.nuevo.springboot.reservas.app.controllers;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.service.ICooperativaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.UploadFileService;

@Controller
public class UnidadController {

	@Autowired
	private  IUnidadService unidadService;
	
	@Autowired
	private  ICooperativaService cooperativaService;
	
	@Autowired
	private UploadFileService upload;
	
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
		return "formUnidadEdi";
	}
	
	@GetMapping("/unidad/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		
		if(id > 0) {
			Unidad u=new Unidad();
			u=unidadService.get(id).get();
			//eliminar cuando no sea la imagen por defecto
			if(u.getImagen().equals("default.jpg")) {
				upload.deleteImage(u.getImagen());
			}
			
		if(u.tieneCronogramas()==true) {
			return "redirect:/confirmarEliminar/" + id;
		}else {
			unidadService.delete(id);
			flash.addFlashAttribute("success", "Unidad eliminada con exito!");
			return "redirect:/unidad/listar";
		}
		}
		return "redirect:/unidad/listar";
		
	}
	
	
	@GetMapping("/unidad/eliminarf/{id}")
	public String eliminarf(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		
		
			Unidad u=new Unidad();
			u=unidadService.get(id).get();
			//eliminar cuando no sea la imagen por defecto
			if(u.getImagen().equals("default.jpg")) {
				upload.deleteImage(u.getImagen());
			}
			
		
			unidadService.delete(id);
			flash.addFlashAttribute("success", "Unidad eliminada con exito!");
			
		
		
		return "redirect:/unidad/listar";
		
	}
	
	@GetMapping("/confirmarEliminar/{id}")
	public String confirmarEliminar(@PathVariable(value = "id") Integer id, Model model) {
	    model.addAttribute("id", id);
	    return "confirmarEliminar";
	}

	@PostMapping("/unidad/form")
	public String guardar(Unidad unidad,@RequestParam("img") MultipartFile file, RedirectAttributes flash, SessionStatus status, Model model) throws IOException {
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
	        //imagen
	        if(unidad.getId()==null){ //cuando se crea una unidad
	        	String nombreImagen= upload.saveImage(file);
	        	unidad.setImagen(nombreImagen);
	        }
	        unidad.setEstadoActividad("Activo");
	        unidad.setStockBoletos(true);
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
	
	@PostMapping("/update")
	public String update(Unidad unidad, @RequestParam("img") MultipartFile file) throws IOException {
		if(file.isEmpty()) {//Se edita el producto pero no se cambia la imagen
    		Unidad u=new Unidad();
    		u= unidadService.get(unidad.getId()).get();
    		unidad.setImagen(u.getImagen());
    	}else {//cuando se edita la imagen
    		Unidad u=new Unidad();
			u=unidadService.get(unidad.getId()).get();
			//eliminar cuando no sea la imagen por defecto
			if(u.getImagen().equals("default.jpg")) {
				upload.deleteImage(u.getImagen());
			}
    		String nombreImagen= upload.saveImage(file);
        	unidad.setImagen(nombreImagen);
        	
    	}
		unidadService.update(unidad);
		return "redirect:/unidad/listar";
	}
	

}
