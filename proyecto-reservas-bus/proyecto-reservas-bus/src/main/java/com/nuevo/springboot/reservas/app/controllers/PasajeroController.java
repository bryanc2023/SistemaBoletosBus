package com.nuevo.springboot.reservas.app.controllers;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.IAsientoService;
import com.nuevo.springboot.reservas.app.models.service.IBoletoService;
import com.nuevo.springboot.reservas.app.models.service.ICooperativaService;
import com.nuevo.springboot.reservas.app.models.service.ICronogramaService;
import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;

@Controller
public class PasajeroController {

	@Autowired
	private IBoletoService boletoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IAsientoService asientoService;
	
	
	
	@Autowired
    private ICronogramaService cronogramaService;
	
	@Autowired
	private IUnidadService unidadService;
	
	@Autowired
	private ICooperativaService cooperativaService;
	
	@Autowired
	private IRutaService rutaService;
	
	
	
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
	 
        List<Boleto> boletos = boletoService.findByIdUsuarioEfectivo(idUsuario);
        boletos.forEach(boleto -> {
	        double totalPago = boleto.getTotalPago();
	        String totalPagoFormateado = String.format("%.2f", totalPago);
	        boleto.setTotalPagoFormateado(totalPagoFormateado);
	    });
	    model.addAttribute("boletos", boletos);
	    List<Boleto> boletos2 = boletoService.findByIdUsuarioTarjeta(idUsuario);
        boletos2.forEach(boleto2 -> {
	        double totalPago2 = boleto2.getTotalPago();
	        String totalPagoFormateado2 = String.format("%.2f", totalPago2);
	        boleto2.setTotalPagoFormateado(totalPagoFormateado2);
	    });
	    model.addAttribute("boletos2", boletos2);
	    return "pasajero/listarreservas";
	}
	
	
	 @GetMapping("/reserva/no/{id}")
	 public String pagar(@PathVariable(value="id") Integer id, RedirectAttributes flash,Model model,SessionStatus status) {
		
		 Boleto boleto1 = boletoService.findById(id);
		 double pago= boleto1.getTotalPago();
		 double costo = boleto1.getCronograma().getRuta().getCostoRuta();
		 double t = pago-costo;
		 String totalPagoFormateado = String.format("%.2f", pago);
		 String totalPagoFormateado2 = String.format("%.0f", t);
	     boleto1.setTotalPagoFormateado(totalPagoFormateado);
		 model.addAttribute("pago1", boleto1.getTotalPagoFormateado());
		 model.addAttribute("pago", totalPagoFormateado2);
		 model.addAttribute("boleto", id);
	     return "/pasajero/cancelar";
	 }
	 
	 
	 @GetMapping("/cancelar/{id}")
	 public String cancelar(@PathVariable(value="id") Integer id, RedirectAttributes flash,Model model,SessionStatus status,Authentication authentication) {
		
		Boleto boleto1 = boletoService.findOne(id);
		boleto1.getAsiento().setEstado("Disponible");
		boletoService.delete(id);
		 String mensajeFlash = "Reserva cancelada! ";
		 status.setComplete();
		 flash.addFlashAttribute("warning", mensajeFlash);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String email = ((UserDetails) principal).getUsername();
        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
	 
        List<Boleto> boletos = boletoService.findByIdUsuarioEfectivo(idUsuario);
        boletos.forEach(boleto -> {
	        double totalPago = boleto.getTotalPago();
	        String totalPagoFormateado = String.format("%.2f", totalPago);
	        boleto.setTotalPagoFormateado(totalPagoFormateado);
	    });
	    model.addAttribute("boletos", boletos);
	    List<Boleto> boletos2 = boletoService.findByIdUsuarioTarjeta(idUsuario);
        boletos2.forEach(boleto2 -> {
	        double totalPago2 = boleto2.getTotalPago();
	        String totalPagoFormateado2 = String.format("%.2f", totalPago2);
	        boleto2.setTotalPagoFormateado(totalPagoFormateado2);
	    });
	    model.addAttribute("boletos2", boletos2);
	     return "redirect:/pasajero/reservas";
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
	
	 @GetMapping("/asientos/{cronogramaId}")
	    public String mostrarAsientosCronograma(@PathVariable("cronogramaId") Integer cronogramaId, Model model) {

	    	model.addAttribute("datos", asientoService.obtenerCronogramaPorId(cronogramaId));
	        model.addAttribute("asientos", asientoService.findByCronogramaId1(cronogramaId));
	        model.addAttribute("asientos2", asientoService.findByCronogramaId2(cronogramaId));
	        model.addAttribute("asientos3", asientoService.findByCronogramaId3(cronogramaId));
	        model.addAttribute("asientos4", asientoService.findByCronogramaId4(cronogramaId));
	        model.addAttribute("cronograma", cronogramaService.findAll());
	       
	  
	        return "pasajero/listar_asientos"; // Nombre de la vista para mostrar los asientos
	    }
	    
	    
	    @PostMapping("/asientos/guardarReserva/{cronogramaId}")
	    public String guardarReserva(@PathVariable("cronogramaId") Integer id, Model model, RedirectAttributes flash) {
	    	   List<Asiento> asientosReservados = asientoService.countByEstado(id);
	           int cantidadDeAsientosReservados = asientosReservados.size();
	           if(cantidadDeAsientosReservados == 0) {
	        	   model.addAttribute("titulo", cantidadDeAsientosReservados);
	               flash.addFlashAttribute("error", "No ha seleccionado asientos");
	               return "redirect:/asientos/"+id;
	           }else {
	        	model.addAttribute("cronograma", cronogramaService.findAll());
				model.addAttribute("unidad", unidadService.findAll());
				model.addAttribute("cooperativa", cooperativaService.findAll());
	       		model.addAttribute("asientos", asientosReservados);
	       		model.addAttribute("datos", asientoService.obtenerCronogramaPorId(id));
	       		model.addAttribute("ruta", rutaService.findAll());
	       		model.addAttribute("cantidad", cantidadDeAsientosReservados);
	       		model.addAttribute("costo", asientoService.obtenerCostoTotal(id));
	       		model.addAttribute("costo2", asientoService.obtenerCostoRutaPorCronogramaId(id));
	       		model.addAttribute("costo3", asientoService.obtenerSubtotal(id));
	       		model.addAttribute("costo4", asientoService.obtenerCostoTotalD(id));
	       		return "pasajero/listarseleccion"; 
	           }
	        
	    }
	  
	    
	    @PostMapping("/asientos/cancelarReserva/{cronogramaId}")
	    public String cancelarReserva(@PathVariable("cronogramaId") Integer id, Model model, RedirectAttributes flash) {
	    	   List<Asiento> asientosReservados = asientoService.countByEstado(id);
	           int cantidadDeAsientosReservados = asientosReservados.size();
	           if(cantidadDeAsientosReservados == 0) {
	        	   List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
			        model.addAttribute("resultados", resultados);
	               return "pasajero/home2";
	           }else {
	        	asientoService.updateEstadoToDisponible(id);
	        	List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
		        model.addAttribute("resultados", resultados);
	       		return "pasajero/home2"; 
	           }
	        
	    }
	    
	    @PostMapping("/asiento/{asientoId}/actualizarEstado")
	    public String actualizarEstadoAsiento(@PathVariable("asientoId") Integer asientoId, RedirectAttributes flash, Model model) {
	        Asiento asiento = asientoService.findOne(asientoId);
	     // Verificar la cantidad de asientos reservados después de actualizar el estado
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	String email = ((UserDetails) principal).getUsername();
	        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
	        List<Boleto> boletosReservados =boletoService.findByIdUsuarioCronograma(idUsuario,asiento.getCronograma().getId());
	        List<Asiento> asientosReservados = asientoService.countByEstado(asiento.getCronograma().getId());
	        int cantidadDeAsientosReservados = asientosReservados.size();
	        int cantidadDeBoletosReservados = boletosReservados.size();
	        if(asiento.getEstado().equals("Reservado")|| cantidadDeAsientosReservados+cantidadDeBoletosReservados< asientoService.obtenerMax()){
	        	 model.addAttribute("titulo", cantidadDeAsientosReservados);
	             String estadoActual = asiento.getEstado();
	             String nuevoEstado = estadoActual.equals("Disponible") ? "Reservado" : "Disponible";
	             asiento.setEstado(nuevoEstado);

	             // Guardar el asiento actualizado en la base de datos
	             asientoService.save(asiento);
	             // Agregar mensaje de éxito para mostrar en la página
	         
	             String mensajeFlash = (asiento.getEstado()=="Disponible")? "Asiento deseleccionado con exito" : "Asiento seleccionado con exito";
	          
	             flash.addFlashAttribute("success", mensajeFlash);
	        }else {
	        	    model.addAttribute("titulo", cantidadDeAsientosReservados);
	                flash.addFlashAttribute("error", "Se alcanzó el límite de asientos por persona (máximo "+asientoService.obtenerMax()+")");
	            }
	        
	        
	         
	        model.addAttribute("datos", asientoService.obtenerCronogramaPorId(asiento.getCronograma().getId()));
	        model.addAttribute("asientos", asientoService.findByCronogramaId(asiento.getCronograma().getId()));
	        model.addAttribute("cronograma", cronogramaService.findAll());

	        
	        return "redirect:/asientos/"+asiento.getCronograma().getId();
	    }


	    
	    @PostMapping("/asientos/guardarReserva/{cronogramaId}/{costoTotal}")
	    public String aceptarReserva(@PathVariable("cronogramaId") Integer id, @PathVariable("costoTotal") Float costoTotal, @RequestParam("idsAsientosSeleccionados") String idsAsientosSeleccionados, Model model,
	    		Authentication authentication,RedirectAttributes flash,
	    		@RequestParam(value = "metodoPagoCheck", required = false, defaultValue = "false") boolean metodoPagoC) {
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	String email = ((UserDetails) principal).getUsername();
	        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
	        Usuario usuario =usuarioService.findById(idUsuario);
	    	String[] idsAsientosArray = idsAsientosSeleccionados.split(",");
	    	DecimalFormat decimalFormat = new DecimalFormat("#.00");
	        String costoTotalFormateado = decimalFormat.format(costoTotal);
	        costoTotal = Float.parseFloat(costoTotalFormateado.replace(",", "."));
	    	
	        if(metodoPagoC == true) {
	    	 for (String idAsiento : idsAsientosArray) {
	    	        // Crear un nuevo Boleto y establecer los valores necesarios
	    	        Boleto boleto = new Boleto();
	    	        Asiento asiento= new Asiento();
	    	        
	    	        asiento = asientoService.findOne(Integer.parseInt(idAsiento));
	    	        asiento.setEstado("Ocupado");
	    	        asientoService.save(asiento);

	    	        Cronograma cronograma = cronogramaService.findOne(id);
	    	        boleto.setAsiento(asiento);
	    	        boleto.setCronograma(cronograma);
	    	        boleto.setMetodoPago("Efectivo");
	    	        boleto.setTotalPago(costoTotal);
	    	        boleto.setUsuario(usuario);
	    	        boleto.setEstado("Pendiente");
	    	        boleto.setDescuento("Solicitado");
	    	        boletoService.save(boleto);
	    	        
	    	       
	    	    }

	    	 List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
	    	 String mensajeFlash =  "Boleto reservado correctamente, acerquese a la ventanilla a cancelar el total." ;
	         
		        model.addAttribute("resultados", resultados);
	    		return "pasajero/confirmar_3"; // Redireccionar a la página deseada después de procesar la reserva
	    
	        }else
	        {
	        	for (String idAsiento : idsAsientosArray) {
	    	        // Crear un nuevo Boleto y establecer los valores necesarios
	    	        Boleto boleto = new Boleto();
	    	        Asiento asiento= new Asiento();
	    	        
	    	        asiento = asientoService.findOne(Integer.parseInt(idAsiento));
	    	        asiento.setEstado("Ocupado");
	    	        asientoService.save(asiento);

	    	        Cronograma cronograma = cronogramaService.findOne(id);
	    	        boleto.setAsiento(asiento);
	    	        boleto.setCronograma(cronograma);
	    	        boleto.setMetodoPago("Efectivo");
	    	        boleto.setTotalPago(costoTotal);
	    	        boleto.setUsuario(usuario);
	    	        boleto.setEstado("Pendiente");
	    	        boleto.setDescuento("No");
	    	        boletoService.save(boleto);
	    	        
	    	       
	    	    }

	    	 List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
	    	 String mensajeFlash =  "Boleto reservado correctamente, acerquese a la ventanilla a cancelar el total." ;
	         
		        model.addAttribute("resultados", resultados);
	    		return "pasajero/confirmar_1";
	        }
	    }
	   
	 
	    @GetMapping("/asientos/guardarReserva2/{cronogramaId}/{costoTotal}")
	    public String aceptarReserva2(@PathVariable("cronogramaId") Integer id, @PathVariable("costoTotal") Float costoTotal, @RequestParam("idsAsientosSeleccionados") String idsAsientosSeleccionados, Model model,
	    		Authentication authentication,RedirectAttributes flash) {
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	String email = ((UserDetails) principal).getUsername();
	        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
	        Usuario usuario =usuarioService.findById(idUsuario);
	    	String[] idsAsientosArray = idsAsientosSeleccionados.split(",");
	    	DecimalFormat decimalFormat = new DecimalFormat("#.00");
	        String costoTotalFormateado = decimalFormat.format(costoTotal);
	        costoTotal = Float.parseFloat(costoTotalFormateado.replace(",", "."));
	    	
	    	 for (String idAsiento : idsAsientosArray) {
	    	        // Crear un nuevo Boleto y establecer los valores necesarios
	    	        Boleto boleto = new Boleto();
	    	        Asiento asiento= new Asiento();
	    	        
	    	        asiento = asientoService.findOne(Integer.parseInt(idAsiento));
	    	        asiento.setEstado("Ocupado");
	    	        asientoService.save(asiento);

	    	        Cronograma cronograma = cronogramaService.findOne(id);
	    	        boleto.setAsiento(asiento);
	    	        boleto.setCronograma(cronograma);
	    	        boleto.setMetodoPago("Tarjeta");
	    	        boleto.setTotalPago(costoTotal);
	    	        boleto.setUsuario(usuario);
	    	        boleto.setEstado("Pagado");
	    	        boleto.setDescuento("Aplicado");
	    	        
	    	        boletoService.save(boleto);
	    	        
	    	       
	    	    }

	    	 List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
	    	 String mensajeFlash =  "Boleto reservado correctamente, acerquese a la ventanilla a cancelar el total." ;
	         
		        model.addAttribute("resultados", resultados);
	    		return "pasajero/confirmar_2"; // Redireccionar a la página deseada después de procesar la reserva
	    }
	
	
	    @GetMapping("pasajero/contacto")
	    public String showContactoPage() {
	        return "pasajero/contacto";
	    }
	    
	    
	    @PostMapping("/search")
		public String searchProduct(@RequestParam("fecha") String fecha, Model model) {
			
	        List<Object[]> unidades = unidadService.findByCronogramaFecha(fecha);
	        model.addAttribute("resultados", unidades);
	        return "pasajero/resultados";
		}
	    
	    @PostMapping("/search2")
		public String searchInforme(@RequestParam("fecha") String fecha, Model model) {

	        List<Object[]> unidades = unidadService.findByCronogramaFecha(fecha);
	        model.addAttribute("resultados", unidades);
	        return "pasajero/resultados2";
		}
		
		@GetMapping("/pasajero/perfil")
		public String mostrarPerfil(Authentication authentication,Model model) {
			
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  String email = ((UserDetails) principal).getUsername();
		        Usuario user = usuarioService.findByEmail(email);
		        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
	          Usuario pasajero= usuarioService.findById(idUsuario);
	          model.addAttribute("nombre", pasajero.getNombre());
	          model.addAttribute("apellido", pasajero.getApellido());
	          model.addAttribute("correo", pasajero.getEmail());
	        return "pasajero/perfil";
		}
	
	 
	 
}
