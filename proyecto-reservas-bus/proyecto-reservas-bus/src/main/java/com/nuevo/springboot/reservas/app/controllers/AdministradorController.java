package com.nuevo.springboot.reservas.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;
import com.nuevo.springboot.reservas.app.models.service.UsuarioService;


@Controller
public class AdministradorController {
	
	@Autowired
	private  IUsuarioService usuarioServicio;

	
	@GetMapping("/personal/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE PERSONAL");
	    model.addAttribute("usuarios", usuarioServicio.findByRole("ROLE_PERSONAL"));
	    return "administrador/listarPersonal";
	}
	
	@GetMapping("/personal/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Model model) {
		return null;
		
	}
	
	@GetMapping("/personal/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			usuarioServicio.delete(id);
		}
		return "redirect:/personal/listar";
	}
	

	
}
