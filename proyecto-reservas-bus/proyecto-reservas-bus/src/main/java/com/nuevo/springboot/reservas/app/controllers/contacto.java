package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;


@Controller
public class contacto {

    @GetMapping("pasajero/contacto")
    public String showContactoPage() {
        return "pasajero/contacto";
    }
}