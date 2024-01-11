package com.nuevo.springboot.reservas.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevo.springboot.reservas.app.models.entity.ConfirmationToken;

public interface IConfirmationTokenDao extends JpaRepository<ConfirmationToken,Long> {

	public ConfirmationToken findByConfirmationToken(String confirmationToken);
}
