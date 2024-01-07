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


@Controller
public class HomeController {
	

	@Autowired
	private  IRutaService rutaService;
	
	@Autowired
	private  IUnidadService unidadService;
	
	@Autowired
	private  ICronogramaService cronogramaService;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		
		model.addAttribute("titulo", "Pagina de inicio");
		model.addAttribute("mensaje", "Bienvenido a Reserva de boletos");
		model.addAttribute("cronogramas", cronogramaService.findAll());
		model.addAttribute("unidades", unidadService.findAll());
		return "home";
	

	}
	
	@GetMapping("/redirectByRole")
    public String redirectByRole(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/unidad/listar";
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return "redirect:/";
        } else {
            // Manejar otras opciones si es necesario
            return "redirect:/login?error";
        }
    }
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		//List<Unidad> unidad= unidadService.findAll().stream().filter( p -> p.getCooperativa().contains(nombre)).collect(Collectors.toList());
		//model.addAttribute("unidades", unidad);		
		return "home";
	}

	 }
