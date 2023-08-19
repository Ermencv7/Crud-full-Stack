package com.evelasco.crud.empresarial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evelasco.crud.empresarial.models.entity.Categoria;
import com.evelasco.crud.empresarial.models.service.CategoriaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoriaController {
	
	@Autowired
	CategoriaService service;
	
	@GetMapping("/categorias")
	public List<Categoria> listarCategoria() {
		return service.findAll();
	}
	
	
}
