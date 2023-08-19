package com.evelasco.crud.empresarial.models.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.annotation.ApplicationScope;

import jakarta.persistence.PostPersist;

@ApplicationScope
public class Historial {
	private static List<Venta>ventas=new ArrayList<>();
	
	
	
	public static void addVenta(Venta venta) {
		ventas.add(venta);
	}

	public static List<Venta> getVentas() {
		return ventas;
	}
	
}
