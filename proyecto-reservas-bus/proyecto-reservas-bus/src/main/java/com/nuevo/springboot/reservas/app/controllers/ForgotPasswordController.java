package com.nuevo.springboot.reservas.app.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nuevo.springboot.reservas.app.Utility;
import com.nuevo.springboot.reservas.app.models.entity.Usuario;
import com.nuevo.springboot.reservas.app.models.service.CustomerNotFoundException;
import com.nuevo.springboot.reservas.app.models.service.UsuarioService;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private JavaMailSender mailSender;
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		model.addAttribute("pageTitle", "Recupera tu contraseña");
		return "forgot_password_form";
	}
	
	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
	    String email = request.getParameter("email");
	    String token = RandomString.make(30);
	     
	    try {
	        usuarioService.updateResetPasswordToken(token, email);
	        String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	        usuarioService.sendEmail2(email, resetPasswordLink);
	        model.addAttribute("message", "Te hemos enviado un mensaje a tu correo por favor verificalo.");
	         
	    } catch (CustomerNotFoundException ex) {
	        model.addAttribute("error", ex.getMessage());
	    } catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Error al enviar el email");
	    }
		model.addAttribute("pageTitle", "Recupera tu contraseña");
	    return "forgot_password_form";
	}

	
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    Usuario usuario = usuarioService.get(token);
	    model.addAttribute("token", token);
	     
	    if (usuario == null) {
	        model.addAttribute("message", "Error al cambiar la contraseña");
	        return "message";
	    }
	     
	    return "reset_password_form";
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
	    String token = request.getParameter("token");
	    String password = request.getParameter("password");
	     
	    Usuario usuario = usuarioService.get(token);
	    model.addAttribute("title", "Cambia tu contraseña");
	     
	    if (usuario == null) {
	        model.addAttribute("message", "Error al cambiar la contraseña");
	        return "message";
	    } else {           
	        usuarioService.updatePassword(usuario, password);
	         
	        model.addAttribute("message", "Has tenido exito al cambiar tu contraseña.");
	    }
	     
	    return "message";
	}
}
