package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nuevo.springboot.reservas.app.models.service.IAsientoService;

@Controller
public class PaymentViewController {


	
    @GetMapping("/payment")
    public String showPaymentPage() {
    	
        return "payment"; 
    }
}