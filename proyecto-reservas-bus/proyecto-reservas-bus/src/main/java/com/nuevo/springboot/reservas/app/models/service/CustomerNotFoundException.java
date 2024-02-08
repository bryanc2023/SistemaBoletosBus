package com.nuevo.springboot.reservas.app.models.service;

public class CustomerNotFoundException extends Exception {

	/* Funciones: Funciones de validacion*/
	/* Descripcion: Funcione para saber si existe o no el cliente , caso contrario envia un mensaje*/
	public CustomerNotFoundException(String message) {
		super(message);
	
	}

}
