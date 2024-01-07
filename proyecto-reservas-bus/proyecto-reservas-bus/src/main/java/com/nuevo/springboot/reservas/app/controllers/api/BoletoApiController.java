package com.nuevo.springboot.reservas.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;
import com.nuevo.springboot.reservas.app.models.service.IBoletoService;

@RestController
@RequestMapping("/api")
public class BoletoApiController {

	@Autowired
	private IBoletoService boletoService;
	
	@GetMapping("/boleto")
	public List<Boleto> index(){
		return boletoService.findAll();
	}
	
	@GetMapping("/boleto/{id}")
	public Boleto show(@PathVariable Integer id) {
		return boletoService.findById(id);
	}
	
	@PostMapping("/boleto")
	@ResponseStatus(HttpStatus.CREATED)
	public Boleto create(@RequestBody Boleto boleto) {
	   return boletoService.save1(boleto);
	}
	
	@PutMapping("/boleto/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Boleto update(@RequestBody Boleto boleto, @PathVariable Integer id) {
		Boleto boletoActual = boletoService.findById(id);
		
		boletoActual.setDetalle(boleto.getDetalle());
		boletoActual.setDia(boleto.getDia());
		boletoActual.setFechaViaje(boleto.getFechaViaje());
		boletoActual.setHoraSalida(boleto.getHoraSalida());
		boletoActual.setId(boleto.getId());
		boletoActual.setMetodoPago(boleto.getMetodoPago());
		boletoActual.setNumeroAsiento(boleto.getNumeroAsiento());
		boletoActual.setTotalPago(boleto.getTotalPago());
		boletoActual.setUnidad(boleto.getUnidad());
		
		
		return boletoService.save1(boletoActual);
	}
	
	@DeleteMapping("/boleto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		boletoService.delete(id);
	}
}
