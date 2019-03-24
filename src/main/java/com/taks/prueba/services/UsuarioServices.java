package com.taks.prueba.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taks.prueba.entity.UsuarioEntity;
import com.taks.prueba.repository.UsuarioRP;

@Service
public class UsuarioServices {
	
	@Autowired
	private   UsuarioRP usuarioRP;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public  List<UsuarioEntity> get() {
		
		return usuarioRP.finByActives();
	}
	
	public  UsuarioEntity  getId( int  id ) {
		
		Optional<UsuarioEntity> optional =  usuarioRP.findById(id);
		if(!optional.isPresent())
			return null;
		
		return  optional.get();
	}
	
	public  ResponseEntity<Object>  add( UsuarioEntity usuario){
		
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		UsuarioEntity  usuarioEntity  = usuarioRP.save(usuario);
		
		URI  location  =  ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
							.buildAndExpand(usuarioEntity.getId()).toUri();
		
		return ResponseEntity.created(location).body("operacion  relaizada con exito");

	}
	
	public  ResponseEntity<Object>  update( UsuarioEntity  usuario,  Integer  id){
		Optional  <UsuarioEntity>  optional  = usuarioRP.findById(id);
		
		if(!optional.isPresent())
			return  ResponseEntity.notFound().build();
		usuario.setId(id);
		usuarioRP.save(usuario);
		
		return  ResponseEntity.noContent().build();
	}
	
	public  void delete( Integer  id) {
		
		usuarioRP.findByDelete(id);
	}
}
