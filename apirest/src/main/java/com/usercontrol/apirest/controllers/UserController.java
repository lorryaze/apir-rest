package com.usercontrol.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usercontrol.apirest.models.User;
import com.usercontrol.apirest.repository.UserRepo;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	UserRepo user;
	
	//retorna a lista de usuários do banco de dados
	@GetMapping("/usuarios")
	public List<User> listUser() {
		return user.findAll();
	}
	
	//Lista apenas um usuário
	@GetMapping("/usuario/{id}")
	public User listOnlyUser(@PathVariable(value = "id") long id) {
		return user.findById(id);
	}
	
	//Salva um produto no banco de dados
	@PostMapping("/usuario")
	public User saveUser(@RequestBody User usuario) {
		return user.save(usuario);
	}
	
}
