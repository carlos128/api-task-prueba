package com.taks.prueba.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taks.prueba.entity.ProyectoEntity;
import com.taks.prueba.repository.ProyectoRP;


@Service
public class ProyectoServices {
	
	@Autowired
	private  ProyectoRP proyectoRP;
	
	
	public  List<ProyectoEntity> get() {
		
		return proyectoRP.finByActives();
	}
	
	public  ProyectoEntity  getId( int  id ) {
		
		Optional<ProyectoEntity> optional =  proyectoRP.findById(id);
		if(!optional.isPresent())
			return null;
		
		return  optional.get();
	}
	
	public  ResponseEntity<Object>  add( ProyectoEntity proyecto){
			
		ProyectoEntity  proyectoEntity  = proyectoRP.save(proyecto);
		
		URI  location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
							.buildAndExpand(proyectoEntity.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}
	
	public  ResponseEntity<Object>  update( ProyectoEntity  proyecto,  Integer  id){
		Optional  <ProyectoEntity>  optional  = proyectoRP.findById(id);
		
		if(!optional.isPresent())
			return  ResponseEntity.notFound().build();
		proyecto.setId(id);
		proyectoRP.save(proyecto);
		
		return  ResponseEntity.noContent().build();
	}
	
	public  void delete( Integer  id) {
		
		proyectoRP.findByDelete(id);
	}
}
