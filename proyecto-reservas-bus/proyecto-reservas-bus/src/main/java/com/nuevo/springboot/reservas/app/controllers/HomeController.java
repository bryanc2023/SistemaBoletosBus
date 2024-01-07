package com.nuevo.springboot.reservas.app.controllers;





import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.service.ICronogramaService;
import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;


@Controller
public class HomeController {
	

	@Autowired
	private  IRutaService rutaService;
	
	@Autowired
	private  IUnidadService unidadService;
	
	@Autowired
	private  ICronogramaService cronogramaService;
	
	@Autowired
	private  IUsuarioService usuarioService;
	
	@GetMapping("/")
	public String mostrarHome(Authentication authentication,Model model) {
		  Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		    if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
		        // Lógica para mostrar contenido específico para el rol de admin
		        model.addAttribute("mensaje", "Bienvenido ADMIN");
		        // Agrega más atributos al modelo si es necesario para el rol de admin
		        return "administrador/home"; // Vista para el rol de admin
		    } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
		        // Lógica para mostrar contenido específico para el rol de usuario
		        model.addAttribute("mensaje", "Bienvenido USER");
		        // Agrega más atributos al modelo si es necesario para el rol de usuario
		        
		        model.addAttribute("unidades", unidadService.findAll());
		        return "pasajero/home"; // Vista para el rol de usuario
		    } else {
		        // Manejar otras opciones si es necesario
		        return "redirect:/login?error";
		    }
	

	}
	
	
	
	
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		//List<Unidad> unidad= unidadService.findAll().stream().filter( p -> p.getCooperativa().contains(nombre)).collect(Collectors.toList());
		//model.addAttribute("unidades", unidad);		
		return "pasajero/home";
	}

	 }
