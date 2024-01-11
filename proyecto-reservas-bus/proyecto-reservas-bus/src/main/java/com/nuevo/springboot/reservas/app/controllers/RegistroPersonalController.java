package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
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
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
		usuarioServicio.guardarUsuarioPersonal(registroDTO);
		return "redirect:/personal/listar";
	}
	

    
	
}
