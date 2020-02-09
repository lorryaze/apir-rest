package com.usercontrol.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usercontrol.apirest.models.User;
import com.usercontrol.apirest.repository.UserRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@Api(value = "User Control Api")
@CrossOrigin(origins="*")
public class UserController {
	@Autowired
	UserRepo user;
	
	//retorna a lista de usuários do banco de dados
	@GetMapping("/usuarios")
	@ApiOperation("Retorna uma lista de usuários")
	public List<User> listUser() {
		return user.findAll();
	}
	
	//Lista apenas um usuário
	@GetMapping("/usuario/{id}")
	@ApiOperation("Retorna apenas um usuário")
	public User listOnlyUser(@PathVariable(value = "id") long id,
							@AuthenticationPrincipal UserDetails userDetails) {
		System.out.println(userDetails);
		return user.findById(id);
	}
	
	//Salva um produto no banco de dados
	@PostMapping("/usuario")
	@ApiOperation("Salva um usuário")
	public User saveUser(@RequestBody User usuario) {
		return user.save(usuario);
	}
	//deleta um usuário do banco de dados
	@DeleteMapping("/usuario")
	@ApiOperation("Deleta um usuário")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUser(@RequestBody User usuario) {
		user.delete(usuario);
	}
	//atualiza os dados de um um usuário
	@PutMapping("/usuario")
	@ApiOperation("Atualiza os dados de um usuário")
	@PreAuthorize("hasRole('ADMIN')")
	public User putUser(@RequestBody User usuario) {
		return user.save(usuario);
	}
}
