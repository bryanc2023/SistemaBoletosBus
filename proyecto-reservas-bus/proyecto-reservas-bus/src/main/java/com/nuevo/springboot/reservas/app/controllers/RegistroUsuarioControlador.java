package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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


	@PostMapping("/registro")
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO,Model model) {
		try {
		usuarioServicio.guardar(registroDTO);
		 model.addAttribute("message", "Se ha enviado un correo electrónico para confirmar la identidad. Verifique su cuenta para iniciar seción");
		return "emailen";
		} catch (DataIntegrityViolationException e) {
		    // Manejar la excepción y enviar un mensaje amigable al usuario.
		    model.addAttribute("message", "El correo electrónico que intenta ingresar , ya está registrado.");
		    return "error2";
		}
	}
	
	

	
    
	
}
