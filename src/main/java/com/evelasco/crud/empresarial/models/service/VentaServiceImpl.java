package com.evelasco.crud.empresarial.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evelasco.crud.empresarial.models.dao.CrudRepository;
import com.evelasco.crud.empresarial.models.entity.Venta;
@Service
public class VentaServiceImpl implements IVentaService {
	
	@Autowired(required = false)
	private CrudRepository<Venta> ventaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Venta> findAll() {
		return ventaDao.findAll();
	}

	@Override
	@Transactional
	public Venta findById(Long id) {
		return ventaDao.findById(id);
	}

	@Override
	@Transactional
	public Venta save(Venta venta) {
		return ventaDao.save(venta);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ventaDao.delete(id);
	}

	@Override
	@Transactional
	public Venta findByNombre(String nombre) {
		return ventaDao.findByNombre(nombre);
	}

}
