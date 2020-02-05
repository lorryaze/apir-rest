package com.usercontrol.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usercontrol.apirest.models.Functionality;
import com.usercontrol.apirest.repository.FunctionalityRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api")
@Api(value = "User Control Api")
@CrossOrigin(origins="*")
public class FunctionalityController {
	@Autowired
	FunctionalityRepo functionality;
	
	@GetMapping("/funcionalidades")
	@ApiOperation("Lista todas as funcionalidades")
	public List<Functionality> listFunc() {
		return functionality.findAll();
	}
	
	//Lista apenas uma funcionaldade
	@GetMapping("/funcionalidade/{id}")
	@ApiOperation("Lista apenas uma funcionalidade")
	public Functionality listOnlyFunc(@PathVariable(value = "id") long id) {
		return functionality.findById(id);
	}
	//salva func
	@PostMapping("/funcionalidade")
	@ApiOperation("Salva funcionalidade")
	public Functionality saveFunc(@RequestBody Functionality func) {
		return functionality.save(func);
	}
	//deleta uma funcionalidade do banco de dados
	@DeleteMapping("/funcionalidade")
	@ApiOperation("Deleta uma funcionalidade")
	public void deleteFunc(@RequestBody Functionality func) {
		functionality.delete(func);
	}
	//atualiza produto
	@PutMapping("/funcionalidade")
	@ApiOperation("Atualiza funcionalidade")
	public Functionality putFunc(@RequestBody Functionality func) {
		return functionality.save(func);
	}
}
