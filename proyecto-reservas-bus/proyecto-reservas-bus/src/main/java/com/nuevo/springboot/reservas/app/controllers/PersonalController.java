package com.nuevo.springboot.reservas.app.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
import com.nuevo.springboot.reservas.app.models.entity.Boleto;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.IAsientoService;
import com.nuevo.springboot.reservas.app.models.service.IBoletoService;
import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;
import com.nuevo.springboot.reservas.app.models.service.UsuarioService;


@Controller
public class PersonalController {
	@Autowired
	private IAsientoService asientoService;
	
	@Autowired
	private  IUsuarioService usuarioServicio;

	@Autowired
	private IBoletoService boletoService;
	
	@Autowired
	private IUnidadService unidadService;
	
	
	 @GetMapping("/personal/pagos")
	 public String pagar(Model model) {
		 List<Boleto> boletos = boletoService.getBoletosFechaActualMetodo();
		 boletos.forEach(boleto -> {
		        double totalPago = boleto.getTotalPago();
		        String totalPagoFormateado = String.format("%.2f", totalPago);
		        boleto.setTotalPagoFormateado(totalPagoFormateado);
		    });
	     model.addAttribute("boletos", boletos);
	     return "personal/pagos";
	 }
	 
	 @GetMapping("/pagar/{id}")
	 public String pagar(@PathVariable(value="id") Integer id, RedirectAttributes flash,Model model,SessionStatus status) {
		 Boleto boleto1 = boletoService.findById(id);
		 boleto1.setEstado("Pagado");
		 boletoService.save(boleto1);
		 String mensajeFlash = "Boleto cancelado con exito! El pasajero puede abordar ahora";
		 status.setComplete();
		 flash.addFlashAttribute("success", mensajeFlash);
		 List<Boleto> boletos = boletoService.getBoletosFechaActualMetodo();
		 boletos.forEach(boleto -> {
		        double totalPago = boleto.getTotalPago();
		        String totalPagoFormateado = String.format("%.2f", totalPago);
		        boleto.setTotalPagoFormateado(totalPagoFormateado);
		    });
	     model.addAttribute("boletos", boletos);
	     
	     return "redirect:/personal/pagos";
	 }
	 
	 @GetMapping("/personal/descuentos")
	 public String descuentos(Model model) {
		 List<Boleto> boletos = boletoService.getBoletosFechaActualMetodoDescuento();
		 boletos.forEach(boleto -> {
		        double totalPago = boleto.getTotalPago();
		        String totalPagoFormateado = String.format("%.2f", totalPago);
		        boleto.setTotalPagoFormateado(totalPagoFormateado);
		    });
	     model.addAttribute("boletos", boletos);
	     return "personal/descuentos";
	 }
	 
	 @GetMapping("/aplicar/{id}")
	 public String aplidescuentos(@PathVariable(value="id") Integer id, RedirectAttributes flash,Model model,SessionStatus status) {
		 Boleto boleto1 = boletoService.findById(id);
		 Double total = asientoService.obtenerAplicado(boleto1.getTotalPago());
		 boleto1.setDescuento("Aplicado");
		 boleto1.setTotalPago(total);
		 boletoService.save(boleto1);
		 String mensajeFlash = "Boleto aplicado el descuento con exito! Puede cobrar el nuevo valor";
		 status.setComplete();
		 flash.addFlashAttribute("success", mensajeFlash);
		 List<Boleto> boletos = boletoService.getBoletosFechaActualMetodoDescuento();
		 boletos.forEach(boleto -> {
		        double totalPago = boleto.getTotalPago();
		        String totalPagoFormateado = String.format("%.2f", totalPago);
		        boleto.setTotalPagoFormateado(totalPagoFormateado);
		    });
	     model.addAttribute("boletos", boletos);
	     return "redirect:/personal/descuentos";
	 }
	 
	 @GetMapping("/aplicar/no/{id}")
	 public String aplinodescuentos(@PathVariable(value="id") Integer id, RedirectAttributes flash,Model model,SessionStatus status) {
		 Boleto boleto1 = boletoService.findById(id);
		 boleto1.setDescuento("No aplica");
		 boletoService.save(boleto1);
		 String mensajeFlash = "Boleto descartado para aplicar el descuento! ";
		 status.setComplete();
		 flash.addFlashAttribute("error", mensajeFlash);
		 List<Boleto> boletos = boletoService.getBoletosFechaActualMetodoDescuento();
		 boletos.forEach(boleto -> {
		        double totalPago = boleto.getTotalPago();
		        String totalPagoFormateado = String.format("%.2f", totalPago);
		        boleto.setTotalPagoFormateado(totalPagoFormateado);
		    });
	     model.addAttribute("boletos", boletos);
	     return "redirect:/personal/descuentos";
	 }
	 
	 @GetMapping("/personal/boleto")
		public String listarPersonal(Model model,Authentication authentication) {
		 List<Boleto> boletos = boletoService.getBoletosFechaActual();
		 boletos.forEach(boleto -> {
		        double totalPago = boleto.getTotalPago();
		        String totalPagoFormateado = String.format("%.2f", totalPago);
		        boleto.setTotalPagoFormateado(totalPagoFormateado);
		    });
		    model.addAttribute("unidades", unidadService.findAll());
		    
		    model.addAttribute("boletos", boletos);
		    
		    return "personal/boleto";
		}
	
	 @GetMapping("/personal/camera")
	 public String showCameraPage() {
	     return "personal/activarCamaraQR";
	 }
}
