package com.evelasco.crud.empresarial.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.evelasco.crud.empresarial.models.entity.Categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class CrudCategoriaRepositoryImpl implements CrudRepository<Categoria> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select c from Categoria as c",Categoria.class)
				.getResultList();
	}

	@Override
	public Categoria findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria save(Categoria t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
