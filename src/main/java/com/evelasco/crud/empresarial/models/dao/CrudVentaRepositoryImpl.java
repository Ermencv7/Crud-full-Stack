package com.evelasco.crud.empresarial.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.evelasco.crud.empresarial.models.entity.Venta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CrudVentaRepositoryImpl implements CrudRepository<Venta> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Venta> findAll() {
		return em.createQuery("select v from Venta v",Venta.class).getResultList();
	}

	@Override
	public Venta findById(Long id) {
		return em.find(Venta.class, id);
	}

	@Override
	public Venta save(Venta venta) {
		if(venta.getId()!=null && venta.getId()>0) {
			em.merge(venta);
		}else {
			em.persist(venta);
		}
		
		return venta;
	}

	@Override
	public void delete(Long id) {
		em.remove(findById(id));
		
	}
	
	public  Venta findByNombre(String nombre){
		try {
			return em.createQuery("select v from Venta v where producto=:nombre",Venta.class)
					.setParameter("nombre", nombre)
					.getSingleResult();
		}catch (Exception e) {
			return new Venta();
		}
		
	}

}
