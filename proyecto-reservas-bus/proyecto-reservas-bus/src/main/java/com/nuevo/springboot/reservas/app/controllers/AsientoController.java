package com.nuevo.springboot.reservas.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class AsientoController {
	
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
	
	@Autowired
	private IBoletoService boletoService;

	@Autowired
	private IUsuarioService usuarioService;
    // Controlador para mostrar los asientos de un cronograma específico
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
       		model.addAttribute("costo", asientoService.obtenerCostoTotal(id));
       		model.addAttribute("costo2", asientoService.obtenerCostoRutaPorCronogramaId(id));
       		model.addAttribute("costo3", asientoService.obtenerSubtotal(id));
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
               return "pasajero/home";
           }else {
        	asientoService.updateEstadoToDisponible(id);
        	List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
	        model.addAttribute("resultados", resultados);
       		return "pasajero/home"; 
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
        if(asiento.getEstado().equals("Reservado")|| cantidadDeAsientosReservados+cantidadDeBoletosReservados< 4){
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
                flash.addFlashAttribute("error", "Se alcanzó el límite de asientos por persona (máximo 4)");
            }
        
        
         
        model.addAttribute("datos", asientoService.obtenerCronogramaPorId(asiento.getCronograma().getId()));
        model.addAttribute("asientos", asientoService.findByCronogramaId(asiento.getCronograma().getId()));
        model.addAttribute("cronograma", cronogramaService.findAll());

        
        return "redirect:/asientos/"+asiento.getCronograma().getId();
    }


    
    @PostMapping("/asientos/guardarReserva/{cronogramaId}/{costoTotal}")
    public String aceptarReserva(@PathVariable("cronogramaId") Integer id, @PathVariable("costoTotal") Float costoTotal, @RequestParam("idsAsientosSeleccionados") String idsAsientosSeleccionados, Model model,
    		Authentication authentication,RedirectAttributes flash) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String email = ((UserDetails) principal).getUsername();
        Long idUsuario = usuarioService.obtenerIdUsuarioPorEmail(email);
        Usuario usuario =usuarioService.findById(idUsuario);
    	String[] idsAsientosArray = idsAsientosSeleccionados.split(",");
    	
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
    	        
    	        boletoService.save(boleto);
    	        
    	       
    	    }

    	 List<Object[]> resultados = unidadService.obtenerUnidadesConCronogramaYRuta();
    	 String mensajeFlash =  "Boleto reservado correctamente, acerquese a la ventanilla a cancelar el total." ;
         
	        model.addAttribute("resultados", resultados);
    		return "pasajero/confirmar_1"; // Redireccionar a la página deseada después de procesar la reserva
    }
   
 
   
}
