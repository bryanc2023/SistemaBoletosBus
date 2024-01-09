package com.nuevo.springboot.reservas.app.models.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nuevo.springboot.reservas.app.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);

	public List<Usuario> findByRolesNombre(String rolNombre);

	
	

}
