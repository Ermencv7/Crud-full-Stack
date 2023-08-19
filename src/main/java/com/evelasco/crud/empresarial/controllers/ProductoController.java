package com.evelasco.crud.empresarial.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evelasco.crud.empresarial.models.entity.Producto;
import com.evelasco.crud.empresarial.models.service.ProductoService;

import jakarta.validation.Valid;

@CrossOrigin(origins ="http://localhost:4200" )
@RestController
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping("/productos")
	public  List<Producto> listaProductos(){
		return service.findAll();
	}
	
	@GetMapping("/producto/{id}")
	public ResponseEntity<?>encontrarProducto(@PathVariable Long id){
		
		Producto producto=null;
		Map<String,Object> response=new HashMap<>();
		try {
			producto=service.findById(id);
		}catch(DataAccessException e) {
			response.put("error", "Error al intentar consutar por id "+ e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(producto==null) {
			response.put("mensaje","El producto con id".concat(id.toString())+"no exite en la base de Datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
	
	@PostMapping("/producto")
	public ResponseEntity<?> registrarProducto(@Valid @RequestBody Producto producto,BindingResult result){
		Producto newProducto=null;
		Map<String, Object>response=new HashMap<>();
		if(result.hasErrors()) {
			List<String>errors=new ArrayList<>();
			
			for(FieldError err:result.getFieldErrors()) {
				errors.add("El campo '"+err.getField()+"' "+err.getDefaultMessage());
			}
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try{
			newProducto=service.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje","No se pudo realizar el insert en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("producto",newProducto);
		response.put("mensaje", "Se incerto correcta mente en la base de datos");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@CrossOrigin(origins ="http://localhost:4200" )
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> actualizarProducto(@Valid @RequestBody Producto producto,BindingResult result,@PathVariable Long id){
		
		Map<String,Object>response=new HashMap<>();
		Producto productoActualizado=service.findById(id);
		
		if(result.hasErrors()) {
			List<String>errors=result.getFieldErrors().stream()
					.map(e->"El campo '"+e.getField()+"' "+e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			productoActualizado.setNombre(producto.getNombre());
			productoActualizado.setPrecio(producto.getPrecio());
			productoActualizado.setCategoria(producto.getCategoria());
			productoActualizado.setUnidad(producto.getUnidad());
			productoActualizado.setPrecio(producto.getPrecio());
		}catch(DataAccessException e) {
			response.put("mensaje","Errer al tratar de actualizar los datos de la base de datos");
			response.put("error", e.getMessage());
		}
		
		productoActualizado=service.save(productoActualizado);
		response.put("producto", productoActualizado);
		response.put("mensaje", "Se actualizaron correctamente los datos en la base de datos");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	
	}
	
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String,Object>response=new HashMap<>();
		try {
			service.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "no se logro borrar el dato de la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","Se ha borrado exitosa mente");		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/productos/{nombre}")
	public List<Producto> buscarPorNombre(@PathVariable String nombre){
		return service.findByname(nombre);
	}
	
	@GetMapping("/productos/join")
	public List<Object[]> listar(){
		service.findAllJoin().forEach(p->{
			/* Long id=(Long)p[0];
			 String nombre=(String)p[1];
			 int precio=(int)p[2];
			 int unidad=(int)p[3];
			 String nombreCate=(String)p[4];*/
			 
		 });
		return service.findAllJoin();
	}
}
