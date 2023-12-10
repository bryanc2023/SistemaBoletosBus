package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nuevo.springboot.reservas.app.models.dao.IConsolidatedDao;

import com.nuevo.springboot.reservas.app.models.entity.Detalle;


@Controller
public class DetalleController {
	@Autowired
	private IConsolidatedDao<Detalle> detalleDao;
	
	@GetMapping("/detalle/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE DETALLES");
	    model.addAttribute("detalles", detalleDao.findAll());
	    return "listarDetalle";
	}
	
	
	

	@GetMapping("/detalle/form")
	public String crear(Model model) {
	    Detalle detalle = new Detalle();
	    model.addAttribute("detalle", detalle);
	    model.addAttribute("titulo", "Ingrese la detalle");
	    return "formDetalle";
	}
	
	@GetMapping("/detalle/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Detalle detalle = null;
		if(id > 0) {
			detalle = detalleDao.findOne(id);
		}else {
		return "redirect:listar";
		}
		model.addAttribute("detalle", detalle);
	    model.addAttribute("titulo", "Editar detalle");
		return "formDetalle";
	}
	
	@GetMapping("/detalle/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			detalleDao.delete(id);
		}
		return "redirect:/detalle/listar";
	}

	@PostMapping("/detalle/form")
	public String guardar(Detalle detalle) {
	    detalleDao.save(detalle);
	    return "redirect:listar";
	}
}
