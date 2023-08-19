package com.evelasco.crud.empresarial.models.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Venta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column()
	private String producto;
	
	private LocalDate fecha;
	private LocalTime hora;
	private int cantidad;
	@Column(name="valor_producto")
	private int valorUnitario;
	@Column(name = "valor_total")
	private int valorTotal;
	
	@PostPersist
	private void registroLocalDateTime() {
		fecha=LocalDate.now();
		hora=LocalTime.now();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(int valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, fecha, hora, id, producto, valorTotal, valorUnitario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return cantidad == other.cantidad && Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora)
				&& Objects.equals(id, other.id) && Objects.equals(producto, other.producto)
				&& valorTotal == other.valorTotal && valorUnitario == other.valorUnitario;
	}
	

	
	
	
}
