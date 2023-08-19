package com.evelasco.crud.empresarial.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evelasco.crud.empresarial.models.dao.CrudCategoriaRepositoryImpl;
import com.evelasco.crud.empresarial.models.entity.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CrudCategoriaRepositoryImpl service;
	
	@Override
	public List<Categoria> findAll() {
		return service.findAll();
	}

}
