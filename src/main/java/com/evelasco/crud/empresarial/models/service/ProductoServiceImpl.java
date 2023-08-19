package com.evelasco.crud.empresarial.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evelasco.crud.empresarial.models.dao.CrudProductoRepositoryImpl;
//import com.evelasco.crud.empresarial.models.dao.CrudRepository;
import com.evelasco.crud.empresarial.models.entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private CrudProductoRepositoryImpl repository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return repository.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		 repository.delete(id);
		
	}

	@Override
	public List<Producto> findByname(String nombre) {
		
		return repository.findByname(nombre);
	}

	@Override
	public List<Object[]> findAllJoin() {
	
		// TODO Auto-generated method stub
		return repository.findAllJoin();
	}

}
