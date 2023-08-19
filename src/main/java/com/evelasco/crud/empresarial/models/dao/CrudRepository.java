package com.evelasco.crud.empresarial.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.evelasco.crud.empresarial.models.entity.Venta;
@Repository
public interface CrudRepository<T> {
	List<T>findAll();
	T findById(Long id);
	T save(T t);
	void delete(Long id);
	T findByNombre(String nombre);
}
