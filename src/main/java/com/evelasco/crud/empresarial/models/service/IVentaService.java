package com.evelasco.crud.empresarial.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evelasco.crud.empresarial.models.entity.Venta;

@Service
public interface IVentaService {
	List<Venta> findAll();
	Venta findById(Long id);
	Venta save(Venta venta);
	void delete(Long id);
	Venta findByNombre(String nombre);
	//List<>findByname(String nombre);
	//List<Object[]>findAllJoin();
}
