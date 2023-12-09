package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.nuevo.springboot.reservas.app.models.dao.IConsolidatedDao;
import com.nuevo.springboot.reservas.app.models.entity.Administrador;

@Controller
public class AdministradorController {

	@Autowired
	private IConsolidatedDao<Administrador> administradorDao;
	
	@GetMapping("/administrador/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE ADMINISTRADORES");
	    model.addAttribute("administradors", administradorDao.findAll());
	    return "listarAdministrador";
	}
	
	@GetMapping("/administrador/form")
	public String crear(Model model) {
	    Administrador administrador= new Administrador();
	    model.addAttribute("administrador", administrador);
	    model.addAttribute("titulo", "Ingrese el administrador");
	    return "formAdministrador";
	}
	
	@GetMapping("/administrador/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Administrador administrador = null;
		if(id > 0) {
			administrador = administradorDao.findOne(id);
		}else {
		return "redirect:/administrador/listar";
		}
		model.addAttribute("administrador", administrador);
	    model.addAttribute("titulo", "Editar administrador");
		return "formAdministrador";
	}
	
	@GetMapping("/administrador/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			administradorDao.delete(id);
		}
		return "redirect:/administrador/listar";
	}
	
	@PostMapping("/administrador/form")
	public String guardar(Administrador administrador) {
	    administradorDao.save(administrador);
	    return "redirect:listar";
	}
	
}
