package com.nuevo.springboot.reservas.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;



import com.nuevo.springboot.reservas.app.models.entity.Boleto;


public interface IBoletoDao extends JpaRepository<Boleto, Integer>{

}
