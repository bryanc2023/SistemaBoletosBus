package com.nuevo.springboot.reservas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nuevo.springboot.reservas.app.models.dao.IConfirmationTokenDao;
import com.nuevo.springboot.reservas.app.models.dao.IUsuarioDao;
import com.nuevo.springboot.reservas.app.models.entity.ConfirmationToken;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
@Controller
public class ConfirmationController {

	 @Autowired
	    private IConfirmationTokenDao confirmationTokenRepository;

	    @Autowired
	    private IUsuarioDao usuarioServicio;

	@GetMapping("/confirm-account")
	public String confirmUserAccount(@RequestParam("token") String confirmationToken, Model model) {
	    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

	    if (token != null) {
	        Usuario user = usuarioServicio.findByEmail(token.getUser().getEmail());
	        user.setEnabled(true);
	        usuarioServicio.save(user);
	        confirmationTokenRepository.delete(token); 
	        model.addAttribute("message", "Cuenta verificada correctamente!");
	        return "accountVerified";
	    } else {
	        model.addAttribute("message", "Enlace o link expirado!");
	        return "error";
	    }
	}
}
