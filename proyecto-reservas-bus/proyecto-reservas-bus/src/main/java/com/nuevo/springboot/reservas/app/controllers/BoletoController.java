package com.nuevo.springboot.reservas.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.models.entity.Asiento;
import com.nuevo.springboot.reservas.app.models.entity.Boleto;
import com.nuevo.springboot.reservas.app.models.service.IAsientoService;
import com.nuevo.springboot.reservas.app.models.service.IBoletoService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;



@Controller
public class BoletoController {

	@Autowired
	private IBoletoService boletoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IAsientoService asientoService;
	
	@Autowired
	private IUnidadService unidadService;
	
	@GetMapping("/pasajero/boleto")
	public String listar(Model model,Authentication authentication) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String email = ((UserDetails) principal).getUsername();
        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
	 
	    model.addAttribute("boletos", boletoService.findByIdUsuario(idUsuario));
	    return "pasajero/listarBoleto";
	}
	


	@GetMapping("/cancelar/boleto/{idBoleto}/{idAsiento}")
	public String eliminar(@PathVariable(value="idBoleto") Integer idBoleto, @PathVariable(value="idAsiento") Integer idAsiento,RedirectAttributes flash
			,Model model) {
	      Asiento asiento = asientoService.findOne(idAsiento);
	      asiento.setEstado("Disponible");
	      asientoService.save(asiento);
			flash.addFlashAttribute("success", "Boleto cancelado! ");
			boletoService.delete(idBoleto);
		
		return "redirect:/pasajero/boleto";
	}


	
	 @GetMapping("/ver/boleto")
	    public String verBoletoQR(@RequestParam("id") Integer idBoleto) {
	        
	        return "pasajero/vista"; 
	    }
	 
	 @GetMapping("/personal/boleto")
		public String listarPersonal(Model model,Authentication authentication) {
			
		    model.addAttribute("unidades", unidadService.findAll());
		    model.addAttribute("boletos", boletoService.findAll());
		    return "personal/boleto";
		}
	
	 
	 @PostMapping("/buscarBoleto/{unidadId}")
		public String searchBoleto(@PathVariable("unidadId") Integer id , Model model) {
		   
	        List<Boleto> boletos = boletoService.findByUnidadId(id);
	        model.addAttribute("unidades", unidadService.findAll());
	        model.addAttribute("boletos", boletos);
	        return "pasajero/home";
		}
	 
	 @GetMapping("/personal/camera")
	 public String showCameraPage() {
	     return "personal/activarCamaraQR";
	 }
	 
	 @GetMapping("/personal/pagos")
	    public String mostrarPagos() {
	        return "personal/pagos"; 
	    }

}
