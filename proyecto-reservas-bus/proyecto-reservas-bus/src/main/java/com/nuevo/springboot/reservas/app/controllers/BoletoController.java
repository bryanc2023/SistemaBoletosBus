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
	

	@GetMapping("/pasajero/reservas")
	public String listarReservas(Model model,Authentication authentication) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String email = ((UserDetails) principal).getUsername();
        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
	 
	    model.addAttribute("boletos", boletoService.findByIdUsuario(idUsuario));
	    return "pasajero/listarreservas";
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

	@GetMapping("/generarQR/{boletoId}")
	public String generarQR(@PathVariable Integer boletoId, Model model) {
	    Boleto boleto = boletoService.findById(boletoId);

	    if (boleto != null) {
	        String asientoInfo = "Asiento no especificado";
	        if (boleto.getAsiento() != null) {
	            // Obtener información específica de Asiento
	            String fila = boleto.getAsiento().getFila();
	            String columna = boleto.getAsiento().getColumna();
	            asientoInfo = String.format(" %s%s", fila, columna);
	        }

	        String usuarioNombre = (boleto.getUsuario() != null) ? boleto.getUsuario().getNombre() : "Usuario no especificado";

	        // Obtener información de unidad y fecha
	        String unidadInfo = (boleto.getCronograma() != null && boleto.getCronograma().getUnidad() != null)
	                ? boleto.getCronograma().getUnidad().toString()
	                : "Unidad no especificada";
	        String fechaInfo = (boleto.getCronograma() != null && boleto.getCronograma().getFecha() != null)
	                ? boleto.getCronograma().getFecha().toString()
	                : "Fecha no especificada";

	        // Usar \n para representar un salto de línea en la cadena QR
	        String qrCodeData = String.format("Boleto ID: %d\nAsiento: %s\nUsuario: %s\nUnidad: %s\nFecha: %s\nTotal Pago: %.2f",
	                boleto.getId(), asientoInfo, usuarioNombre, boleto.getCronograma().getUnidad().getNumero(), boleto.getCronograma().getFecha(), boleto.getTotalPago());

	        model.addAttribute("qrCodeData", qrCodeData);
	        return "pasajero/vista";
	    } else {
	        return "Boleto no encontrado";
	    }
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
	 public String pagar() {
	     return "personal/pagos";
	 }
}
