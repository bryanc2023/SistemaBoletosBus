package com.nuevo.springboot.reservas.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import com.nuevo.springboot.reservas.app.models.service.IAsientoService;




@Controller
public class AsientoController {

	@Autowired
    private IAsientoService asientoService;

    // Controlador para mostrar los asientos de un cronograma específico
    @GetMapping("/asientos/{cronogramaId}")
    public String mostrarAsientosCronograma(@PathVariable("cronogramaId") Integer cronogramaId, Model model) {

    	
        model.addAttribute("asientos", asientoService.findByCronogramaId1(cronogramaId));
        model.addAttribute("asientos2", asientoService.findByCronogramaId2(cronogramaId));
        model.addAttribute("asientos3", asientoService.findByCronogramaId3(cronogramaId));
        model.addAttribute("asientos4", asientoService.findByCronogramaId4(cronogramaId));
        return "pasajero/listar_asientos"; // Nombre de la vista para mostrar los asientos
    }
	

}
