package com.nuevo.springboot.reservas.app.controllers;





import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
		  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		    if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
		        // Lógica para mostrar contenido específico para el rol de admin
		        model.addAttribute("mensaje", "Bienvenido ADMIN");
		        // Agrega más atributos al modelo si es necesario para el rol de admin
		        return "administrador/home"; // Vista para el rol de admin
		    } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
		    	 
		    	        String email = ((UserDetails) principal).getUsername();
		    	        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
		    	        model.addAttribute("id", idUsuario);
		        List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
		        model.addAttribute("resultados", resultados);
		        return "pasajero/home"; 
		       
		     
		    } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_PERSONAL"))) {
		    	
		        return "personal/home";
		    } else {
		    	 return "redirect:/login?error";
		    }
	

	}
	
	

	
	
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam("fecha") String fecha, Model model) {
		
        List<Object[]> unidades = unidadService.findByCronogramaFecha(fecha);
        model.addAttribute("resultados", unidades);
        return "pasajero/home";
	}

	 }
