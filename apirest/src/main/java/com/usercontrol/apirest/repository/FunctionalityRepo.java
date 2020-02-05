package com.usercontrol.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usercontrol.apirest.models.Functionality;

public interface FunctionalityRepo extends JpaRepository<Functionality, Long> {
	//Busca a funcionalidade no banco de dados pelo id
	Functionality findById(long id);
}
