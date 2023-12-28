package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.GenericDataService;


@Controller
public class UsuarioController {

	@Autowired
	private  GenericDataService <Usuario> usuarioService;
	
	@GetMapping("/usuario/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE USUARIOS");
	    model.addAttribute("usuarios", usuarioService.findAll());
	    return "listarUsuario";
	}
	
	@GetMapping("/usuario/form")
	public String crear(Model model) {
	    Usuario usuario= new Usuario();
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("titulo", "Ingrese el usuario");
	    return "formUsuario";
	}
	
	@GetMapping("/usuario/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Usuario usuario = null;
		if(id > 0) {
			usuario = usuarioService.findOne(id);
		}else {
		return "redirect:/personal/listar";
		}
		model.addAttribute("usuario", usuario);
	    model.addAttribute("titulo", "Editar usuario");
		return "formUsuario";
	}
	
	@GetMapping("/usuario/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			usuarioService.delete(id);
		}
		return "redirect:/usuario/listar";
	}
	
	@PostMapping("/usuario/form")
	public String guardar(Usuario usuario) {
		usuarioService.save(usuario);
	    return "redirect:listar";
	}
	
}
