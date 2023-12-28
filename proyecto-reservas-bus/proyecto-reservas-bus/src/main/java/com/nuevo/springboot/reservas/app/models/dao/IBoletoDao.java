package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.repository.CrudRepository;


import com.nuevo.springboot.reservas.app.models.entity.Boleto;


public interface IBoletoDao extends CrudRepository<Boleto, Integer>{

}
