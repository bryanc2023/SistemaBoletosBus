package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.UsuarioService;


@Controller
@RequestMapping("/personal/form")
public class RegistroPersonalController {

	private UsuarioService usuarioServicio;

	public RegistroPersonalController(UsuarioService usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "administrador/registroPersonal";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, RedirectAttributes flash, SessionStatus status) {
	    try {
	        usuarioServicio.guardarUsuarioPersonal(registroDTO);
	        status.setComplete();
	        flash.addFlashAttribute("success", "Usuario registrado con éxito!");
	        return "redirect:/personal/listar";
	    } catch (Exception ex) {
	        // Puedes manejar otras excepciones específicas aquí si es necesario
	        flash.addFlashAttribute("error", "Error al registrar el usuario.");
	        return "redirect:/personal/listar"; // O a otra página en caso de error.
	    }
	}

    
	
}
