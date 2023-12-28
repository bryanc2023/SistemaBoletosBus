package com.nuevo.springboot.reservas.app.controllers;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		
		model.addAttribute("titulo", "Pagina de inicio");
		model.addAttribute("mensaje", "Bienvenido a Reserva de boletos");
		return "home";

	
	}
}