package com.nuevo.springboot.reservas.app.models.dao;


import org.springframework.data.repository.CrudRepository;


import com.nuevo.springboot.reservas.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {


}
