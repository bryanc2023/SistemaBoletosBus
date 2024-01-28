package com.nuevo.springboot.reservas.app.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
import com.nuevo.springboot.reservas.app.models.entity.Boleto;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.IBoletoService;
import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;
import com.nuevo.springboot.reservas.app.models.service.UsuarioService;


@Controller
public class PersonalController {
	
	@Autowired
	private  IUsuarioService usuarioServicio;

	@Autowired
	private IBoletoService boletoService;
	
	
	
	
	 @GetMapping("/personal/pagos")
	 public String pagar(Model model) {
		 List<Boleto> boletos = boletoService.getBoletosFechaActualMetodo();
	     model.addAttribute("boletos", boletos);
	     return "personal/pagos";
	 }
	
}
