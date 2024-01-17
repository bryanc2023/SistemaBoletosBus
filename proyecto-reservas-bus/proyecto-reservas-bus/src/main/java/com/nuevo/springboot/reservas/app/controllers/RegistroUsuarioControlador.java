package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.braintreegateway.util.Http.RequestMethod;
import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
import com.nuevo.springboot.reservas.app.models.dao.IConfirmationTokenDao;
import com.nuevo.springboot.reservas.app.models.entity.ConfirmationToken;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.UsuarioService;
import com.nuevo.springboot.reservas.app.models.service.EmailServiceSender;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	    private UsuarioService usuarioServicio;
	
	    @Autowired
	    private IConfirmationTokenDao confirmationTokenRepository;

	   

	public RegistroUsuarioControlador(UsuarioService usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "login";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
		
		
		usuarioServicio.guardar(registroDTO);
		return "redirect:/login?exito";
	}
	
	
	
    
	
}
