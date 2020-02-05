package com.usercontrol.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usercontrol.apirest.models.Functionality;
import com.usercontrol.apirest.models.User;
import com.usercontrol.apirest.repository.FunctionalityRepo;


@RestController
@RequestMapping(value = "/api")
public class FunctionalityController {
	@Autowired
	FunctionalityRepo functionality;
	
	@GetMapping("/funcionalidades")
	public List<Functionality> listFunc() {
		return functionality.findAll();
	}
	
	//Lista apenas uma funcionaldade
	@GetMapping("/funcionalidade/{id}")
	public Functionality listOnlyFunc(@PathVariable(value = "id") long id) {
		return functionality.findById(id);
	}
	
	@PostMapping("/funcionalidade")
	public Functionality saveFunc(@RequestBody Functionality func) {
		return functionality.save(func);
	}
}