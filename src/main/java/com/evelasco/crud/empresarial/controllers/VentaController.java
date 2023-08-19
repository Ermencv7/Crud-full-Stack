package com.evelasco.crud.empresarial.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evelasco.crud.empresarial.models.entity.Historial;
import com.evelasco.crud.empresarial.models.entity.Venta;
import com.evelasco.crud.empresarial.models.service.IVentaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VentaController {
	
	@Autowired(required =false)
	private IVentaService service;
	
	@GetMapping("/ventas")
	public List<Venta> getVentas(){
		return service.findAll();
	}
	
	@GetMapping("/venta/{id}")
	public ResponseEntity<?> getVenta(@PathVariable Long id){
		Map<String,Object>response=new HashMap<>();
		Venta venta=null;
		try {
			venta=service.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje","No se realizo la accion con exito");
			response.put("error", "Verifique bien los datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","Se realizo la transaccion con exito");
		response.put("venta", venta);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/venta")
	public ResponseEntity<?> saveVentaVerificar(@RequestBody Venta venta){
		
		
		Optional<Venta>v=Optional.ofNullable(service.findByNombre(venta.getProducto()));
		if(v.get().getId()!=null && v.get().getId()>0) {
			Venta ven=venta;
			ven.setId(v.get().getId());
			ven.setFecha(LocalDate.now());
			ven.setHora(LocalTime.now());
			//ven.setCantidad(venta.getCantidad());
			System.out.println("venta "+venta.getCantidad());
			System.out.println("ven "+ven.getCantidad());
			Historial.addVenta(ven);
			return setVenta(venta.getId(),venta);
			
		}else {
			return saveVenta(venta);
		}
	}
	
	
	public ResponseEntity<?> saveVenta(@RequestBody Venta venta){
		System.out.println("/// venta"+venta.getProducto());
		Map<String,Object>response=new HashMap<>();
		Venta newVenta=null;
		try {
			newVenta=service.save(venta);
		}catch(DataAccessException e) {
			response.put("mensaje","No se realizo la accion con exito");
			response.put("error", "Verifique bien los datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Historial.addVenta(venta);
		response.put("mensaje","Se realizo la transaccion con exito");
		response.put("Venta", newVenta);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK) ;
	}
	

	public ResponseEntity<?> setVenta(Long id,Venta venta){
		Map<String,Object>response=new HashMap<>();
		Venta setVenta=service.findById(id);
		
		try {
			setVenta.setId(venta.getId());
			setVenta.setProducto(venta.getProducto());
			setVenta.setFecha(venta.getFecha());
			setVenta.setHora(venta.getHora());
			System.out.println("updateSetVenta "+setVenta.getCantidad());
			System.out.println("updateVenta "+venta.getCantidad());
			setVenta.setCantidad(setVenta.getCantidad()+venta.getCantidad());
			setVenta.setValorTotal(venta.getValorTotal());
			setVenta.setValorUnitario(venta.getValorUnitario());
		}catch(DataAccessException e) {
			response.put("mensaje","No se realizo la accion con exito");
			response.put("error", "Verifique bien los datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		setVenta=service.save(setVenta);
		response.put("mensaje","Se realizo la transaccion con exito");
		response.put("Venta", setVenta);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK) ;
	}
	
	@DeleteMapping("/venta/{id}")
	public ResponseEntity<?> removeVenta(@PathVariable Long id){
		Map<String,Object>response=new HashMap<>();
		try {
			service.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje","No se realizo la accion con exito");
			response.put("error", "Verifique bien los datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","Se realizo la transaccion con exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	public List<Venta> getHistoriaVentas(){
		return Historial.getVentas();
	}
	@GetMapping("/historial/{nombre}")
	public List<Venta> getHistorial(@PathVariable String nombre){
		return Historial.getVentas().stream()
				.filter(v->v.getProducto().equals(nombre))
				.toList();
	}
	

}
