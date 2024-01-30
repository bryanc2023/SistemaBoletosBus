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

import com.nuevo.springboot.reservas.app.controlador.dto.UsuarioRegistroDTO;
import com.nuevo.springboot.reservas.app.models.entity.Cooperativa;
import com.nuevo.springboot.reservas.app.models.entity.Cronograma;
import com.nuevo.springboot.reservas.app.models.entity.Ruta;
import com.nuevo.springboot.reservas.app.models.entity.Unidad;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.CooperativaService;
import com.nuevo.springboot.reservas.app.models.service.ICooperativaService;
import com.nuevo.springboot.reservas.app.models.service.ICronogramaService;
import com.nuevo.springboot.reservas.app.models.service.IRutaService;
import com.nuevo.springboot.reservas.app.models.service.IUnidadService;
import com.nuevo.springboot.reservas.app.models.service.IUsuarioService;
import com.nuevo.springboot.reservas.app.models.service.RutaService;
import com.nuevo.springboot.reservas.app.models.service.UnidadService;
import com.nuevo.springboot.reservas.app.models.service.UsuarioService;


@Controller
public class AdministradorController {
	
	@Autowired
	private  IUsuarioService usuarioServicio;

	@Autowired
	private ICronogramaService cronogramaService;

	@Autowired
	private UnidadService unidadService;

	@Autowired
	private RutaService rutaService;
	
	@Autowired
	private CooperativaService cooperativaService;
	
	@GetMapping("/personal/listar")
	public String listar(Model model) {
	    model.addAttribute("titulo", "LISTA DE PERSONAL");
	    model.addAttribute("usuarios", usuarioServicio.findByRole("ROLE_PERSONAL"));
	    return "administrador/listarPersonal";
	}
	
	@GetMapping("/personal/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Model model) {
		return null;
		
	}
	
	@GetMapping("/personal/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if(id > 0) {
			usuarioServicio.delete(id);
			flash.addFlashAttribute("success", "Personal eliminado con exito!");
		}
		return "redirect:/personal/listar";
	}
	

	
	@GetMapping("/cooperativa/listar")
	public String listar2(Model model) {
	    model.addAttribute("titulo", "LISTA DE COOPERATIVAS");
	    model.addAttribute("cooperativas", cooperativaService.findAll());
	    return "listarCooperativa";
	}

	@GetMapping("/cooperativa/form")
	public String crear(Model model) {
	    Cooperativa cooperativa = new Cooperativa();
	    model.addAttribute("cooperativa", cooperativa);
	    model.addAttribute("titulo", "Ingrese la cooperativa");
	    return "formCooperativa";
	}
	
	@GetMapping("/cooperativa/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Cooperativa cooperativa = null;
		if (id > 0) {
			cooperativa = cooperativaService.findOne(id);
			if (cooperativa == null) {
				flash.addFlashAttribute("error", "El id de la cooperativa no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id de la cooperativa no puede ser 0!");
			return "redirect:listar";
		}
		model.addAttribute("cooperativa", cooperativa);
		model.addAttribute("titulo", "Editar cooperativa");
		return "formCooperativa";
	}
	
	@GetMapping("/cooperativa/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			cooperativaService.delete(id);
			flash.addFlashAttribute("success", "Cooperativa eliminada con exito!");
		}
		return "redirect:/cooperativa/listar";
	}

	@PostMapping("/cooperativa/form")
	public String guardar(Cooperativa cooperativa, RedirectAttributes flash, SessionStatus status,Model model) {
		try {
		String mensajeFlash = (cooperativa.getId() != null)? "Cooperativa editada con exito!" : "Cooperativa creada con exito!";
		cooperativaService.save(cooperativa);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
	    return "redirect:/cooperativa/listar";
	  } catch (DataIntegrityViolationException ex) {
          String errorMessage = "Error: Cooperativa duplicada. Esta cooperativa ya existe.";
          model.addAttribute("mensaje", errorMessage);
          return "formCooperativa"; // Redirige de vuelta al formulario con el mensaje de error.
      }
	}

	
	
	@GetMapping("/cronograma/listar")
	public String listar3(Model model) {
		model.addAttribute("titulo", "LISTA DE CRONOGRAMAS");
		model.addAttribute("cronogramas", cronogramaService.findAll());
		model.addAttribute("unidad", unidadService.findAll());
		model.addAttribute("ruta", rutaService.findAll());
		return "listarCro";
	}

	@GetMapping("/cronograma/form")
	public String crear2(Model model) {
		Cronograma cronograma = new Cronograma();
		model.addAttribute("cronograma", cronograma);
		model.addAttribute("titulo", "Ingrese el cronograma");
		model.addAttribute("unidades", unidadService.findAll());
		model.addAttribute("rutas", rutaService.findAll());
		model.addAttribute("cooperativa", cooperativaService.findAll());
		return "formCro";
	}

	@GetMapping("/cronograma/form/{id}")
	public String editar2(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {
		Cronograma cronograma = null;
		if (id > 0) {
			cronograma = cronogramaService.findOne(id);
			if (cronograma == null) {
				flash.addFlashAttribute("error", "El id del cronograma no existe en la base de datos!");
				return "redirect:listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id del cronograma no puede ser 0!");

			return "redirect:/cronograma/listar";
		}
		model.addAttribute("cronograma", cronograma);
		model.addAttribute("unidades", unidadService.findAll());
		model.addAttribute("rutas", rutaService.findAll());
		model.addAttribute("cooperativa", cooperativaService.findAll());
		model.addAttribute("titulo", "Editar cronograma");
		return "formCro";
	}

	@PostMapping("/cronograma/form")
	public String guardar(@ModelAttribute("cronograma") Cronograma cronograma, @ModelAttribute("ruta.id") Integer rutaId,
	        @ModelAttribute("unidad.id") Integer unidadId, RedirectAttributes flash, SessionStatus status, Model model) {

		String mensajeFlash = (cronograma.getId() != null)? "Cronograma editado con exito!" : "Cronograma creado con exito!";
	    // Obtener la instancia de Ruta y Unidad utilizando los IDs
	    Ruta rutaSeleccionada = rutaService.findById(rutaId);
	    Unidad unidadSeleccionada = unidadService.findById(unidadId);

	    // Asignar las instancias de Ruta y Unidad al cronograma
	    cronograma.setRuta(rutaSeleccionada);
	    cronograma.setUnidad(unidadSeleccionada);

	    // Verifica si ya existe un cronograma con la misma fecha, unidad y hora de salida
	   try {
		   // Guardar el cronograma con las relaciones establecidas
		    cronogramaService.save(cronograma,unidadSeleccionada);
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash);
		    return "redirect:listar";
	    	
	    } catch (DataIntegrityViolationException ex) {
	    	String errorMessage = "Error: Ya existe un cronograma para la misma fecha, unidad por politica no puede ser posible asignar la misma unidad para la misma fecha.";
	    	model.addAttribute("mensaje", errorMessage);
	        model.addAttribute("unidades", unidadService.findAll());
			model.addAttribute("rutas", rutaService.findAll());
			model.addAttribute("cooperativa", cooperativaService.findAll());
	        return "redirect:/cronograma/form";
	    }
	}

	@GetMapping("/cronograma/eliminar/{id}")
	public String eliminar2(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			cronogramaService.delete(id);
			flash.addFlashAttribute("success", "Cronograma eliminado con exito!");
		}
		return "redirect:/cronograma/listar";
	}
	
	
	@GetMapping("/ruta/listar")
	public String listarRuta(Model model) {
	    model.addAttribute("titulo", "LISTA DE RUTAS");
	    model.addAttribute("rutas", rutaService.findAll());
	    return "listarRuta";
	}
	
	
	

	

	
	@GetMapping("/ruta/form")
	public String crearRuta(Model model) {
	    Ruta ruta = new Ruta();
	    model.addAttribute("ruta", ruta);
	    model.addAttribute("titulo", "Ingrese la ruta");
	    return "formRuta";
	}
	
	@GetMapping("/ruta/form/{id}")
	public String editarRuta(@PathVariable(value="id") Integer id, Model model) {
		Ruta ruta = null;
		if(id > 0) {
			ruta = rutaService.findOne(id);
		}else {
		return "redirect:/ruta/listar";
		}
		model.addAttribute("ruta", ruta);
	    model.addAttribute("titulo", "Editar ruta");
		return "formRuta";
	}
	
	@GetMapping("/ruta/eliminar/{id}")
	public String eliminarRuta(@PathVariable(value="id") Integer id, RedirectAttributes flash) {
		if(id > 0) {
			rutaService.delete(id);
			flash.addFlashAttribute("success", "Ruta eliminada con exito!");
		}
		return "redirect:/ruta/listar";
	}
	
	@PostMapping("/ruta/form")
	public String guardarRuta(Ruta ruta,RedirectAttributes flash, Model model,  SessionStatus status) {
		
		 try {
			 String mensajeFlash = (ruta.getId() != null)? "Ruta editada con exito!" : "Ruta creada con exito!";
	            rutaService.save(ruta);
	            status.setComplete();
	            flash.addFlashAttribute("success", mensajeFlash);
	            return "redirect:/ruta/listar";
	        } catch (DataIntegrityViolationException ex) {
	            String errorMessage = "Error: Ruta duplicada. Esta ruta ya existe.";
	            model.addAttribute("mensaje", errorMessage);
	            return "formRuta"; // Redirige de vuelta al formulario con el mensaje de error.
	        }
	}
	
	
}
