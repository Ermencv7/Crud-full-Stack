package com.evelasco.crud.empresarial;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import com.evelasco.crud.empresarial.models.entity.Producto;
import com.evelasco.crud.empresarial.models.service.ProductoService;

@SpringBootTest
class CrudEmpresarialApplicationTests {
	
	@Autowired
	ProductoService service;
	
	
	@Test
	void contextLoads() {
		List<Producto>productos=service.findAll();
		assertNotNull(productos);
	}
	
}
