package com.nuevo.springboot.reservas.app.controllers;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nuevo.springboot.reservas.app.models.service.UsuarioService;


@Controller
public class RegistroControlador {
	 
	@Autowired
	private UsuarioService servicio;
	
     
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	
}
