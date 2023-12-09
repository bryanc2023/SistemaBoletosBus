package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nuevo.springboot.reservas.app.models.dao.IConsolidatedDao;

import com.nuevo.springboot.reservas.app.models.entity.Cronograma;





@Controller
public class CronogramaController {
	@Autowired
	private IConsolidatedDao<Cronograma> croDao;
	
	@GetMapping("/cronograma/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE CRONOGRAMAS");
	    model.addAttribute("cronogramas", croDao.findAll());
	    return "listarCro";
	}
	
	@GetMapping("/cronograma/form")
	public String crear(Model model) {
	    Cronograma cronograma = new Cronograma();
	    model.addAttribute("cronograma", cronograma);
	    model.addAttribute("titulo", "Ingrese el cronograma");
	    return "formCro";
	}
	
	@GetMapping("/cronograma/form/{id}")
	public String editar(@PathVariable(value="id") Integer id, Model model) {
		Cronograma cronograma = null;
		if(id > 0) {
			cronograma = croDao.findOne(id);
		}else {
		return "redirect:/cronograma/listar";
		}
		model.addAttribute("cronograma", cronograma);
	    model.addAttribute("titulo", "Editar cronograma");
		return "formCro";
	}
	
	@GetMapping("/cronograma/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id) {
		if(id > 0) {
			croDao.delete(id);
		}
		return "redirect:/cronograma/listar";
	}
	
	@PostMapping("/cronograma/form")
	public String guardar(Cronograma cronograma) {
	    croDao.save(cronograma);
	    return "redirect:listar";
	}
	
	
}
