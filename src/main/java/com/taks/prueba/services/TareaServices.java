package com.taks.prueba.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taks.prueba.entity.TareaEntity;
import com.taks.prueba.repository.TareaRP;

@Service
public class TareaServices {
	
	@Autowired
	private  TareaRP tareaRP;
	
	
	public  List<TareaEntity> get() {
		
		return tareaRP.finByActives();
	}
	
	public  TareaEntity  getId( int  id ) {
		
		Optional<TareaEntity> optional =  tareaRP.findById(id);
		if(!optional.isPresent())
			return null;
		
		return  optional.get();
	}
	
	public  ResponseEntity<Object>  add( TareaEntity tarea){
			
		TareaEntity  tareaEntity  = tareaRP.save(tarea);
		
		URI  location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
							.buildAndExpand(tareaEntity.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}
	
	public  ResponseEntity<Object>  update( TareaEntity  tarea,  Integer  id){
		Optional  <TareaEntity>  optional  = tareaRP.findById(id);
		
		if(!optional.isPresent())
			return  ResponseEntity.notFound().build();
		tarea.setId(id);
		tareaRP.save(tarea);
		
		return  ResponseEntity.noContent().build();
	}
	
	public  void delete( Integer  id) {
		
		tareaRP.findByDelete(id);
	}
}
