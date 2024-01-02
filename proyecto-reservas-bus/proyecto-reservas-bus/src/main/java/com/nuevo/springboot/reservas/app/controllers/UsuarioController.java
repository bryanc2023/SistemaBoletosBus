package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private  IUsuarioService usuarioService;
	
	@GetMapping("/usuario/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE USUARIOS");
	    model.addAttribute("unidades", usuarioService.listarUsuarios());
	    return "listarUsuario";
	}

	@GetMapping("/usuario/form")
	public String crear(Model model) {
	    Usuario usuario = new Usuario();
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("titulo", "Ingrese el usuario");
	    return "formUsuario";
	}
	


}
