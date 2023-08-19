package com.evelasco.crud.empresarial.models.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.evelasco.crud.empresarial.models.entity.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CrudProductoRepositoryImpl implements CrudRepository<Producto>  {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Producto> findAll() {	
		return em.createQuery("select p from Producto as p",Producto.class)
				.getResultList();
	}

	@Override
	public Producto findById(Long id) {
		return em.find(Producto.class, id);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		if(producto.getId()!=null && producto.getId()>0) {
			em.merge(producto);
		}else {
			em.persist(producto);
		}
		
		return producto;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findById(id));
		
	}
	
	public List<Producto> findByname(String nombre){
		return em.createQuery("select p from Producto as p where p.nombre like :nombre",Producto.class)
				.setParameter("nombre", "%"+nombre+"%")
				.getResultList();
	}
	
	public List<Object[]> findAllJoin(){
		return em.createQuery("select p.id,p.nombre,p.precio,p.unidad,p.categoria.categoriaNombre from Producto p ",Object[].class)
				.getResultList();
	}

	@Override
	public Producto findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
