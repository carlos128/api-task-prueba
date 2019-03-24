package com.taks.prueba.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taks.prueba.entity.ComentarioEntity;
import com.taks.prueba.repository.ComentarioRP;


@Service
public class ComentarioServices {
	
	@Autowired
	private  ComentarioRP comentariosRP;
	
	
	public  List<ComentarioEntity> get() {
		
		return comentariosRP.finByActives();
	}
	
	public  ComentarioEntity  getId( int  id ) {
		
		Optional<ComentarioEntity> optional =  comentariosRP.findById(id);
		if(!optional.isPresent())
			return null;
		
		return  optional.get();
	}
	
	public  ResponseEntity<Object>  add( ComentarioEntity comentarios){
			
		ComentarioEntity  comentariosEntity  = comentariosRP.save(comentarios);
		
		URI  location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
							.buildAndExpand(comentariosEntity.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}
	
	public  ResponseEntity<Object>  update( ComentarioEntity  comentarios,  Integer  id){
		Optional  <ComentarioEntity>  optional  = comentariosRP.findById(id);
		
		if(!optional.isPresent())
			return  ResponseEntity.notFound().build();
		comentarios.setId(id);
		comentariosRP.save(comentarios);
		
		return  ResponseEntity.noContent().build();
	}
	
	public  void delete( Integer  id) {
		
		comentariosRP.findByDelete(id);
	}
}
