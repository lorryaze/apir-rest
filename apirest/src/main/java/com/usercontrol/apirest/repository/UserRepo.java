package com.usercontrol.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usercontrol.apirest.models.User;

//Jpa repository já tem métodos consistentes para alterações no banco de dados, vamos utilizar os métodos do repository 
//no model produto
public interface UserRepo extends JpaRepository<User, Long>{
	//Buscar um usuario pelo id no banco de dados
	User findById(long id);

}
