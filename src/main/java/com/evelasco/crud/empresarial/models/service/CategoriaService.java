package com.evelasco.crud.empresarial.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evelasco.crud.empresarial.models.entity.Categoria;

@Service
public interface CategoriaService {
	List<Categoria> findAll();
}
