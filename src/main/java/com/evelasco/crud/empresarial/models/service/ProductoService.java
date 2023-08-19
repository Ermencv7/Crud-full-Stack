package com.evelasco.crud.empresarial.models.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.evelasco.crud.empresarial.models.entity.Producto;

@Service
public interface ProductoService {
	List<Producto> findAll();
	Producto findById(Long id);
	Producto save(Producto producto);
	void delete(Long id);
	List<Producto>findByname(String nombre);
	List<Object[]>findAllJoin();
}
