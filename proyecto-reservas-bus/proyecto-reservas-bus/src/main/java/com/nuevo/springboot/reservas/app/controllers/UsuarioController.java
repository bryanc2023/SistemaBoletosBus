package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuario/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "LISTA DE USUARIOS");
		model.addAttribute("usuarios", usuarioService.findAll());
		return "listarUsuario";
	}

	@GetMapping("/usuario/form")
	public String crear(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Ingrese el usuario");
		return "formUsuario";
	}

	@GetMapping("/usuario/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Usuario usuario = null;
		if (id > 0) { 
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El id del usuario no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id del boleto no puede ser 0!");
			return "redirect:/personal/listar";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Editar usuario");
		return "formUsuario";
	}

	@GetMapping("/usuario/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {
		if (id > 0) {
			usuarioService.delete(id);
			flash.addFlashAttribute("success", "Usuario eliminado con exito!");
		}
		return "redirect:/usuario/listar";
	}

	@PostMapping("/usuario/form")
	public String guardar(Usuario usuario, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash = (usuario.getId() != null) ? "Usuario editado con exito!" : "Usuario creado con exito!";
		usuarioService.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

}
