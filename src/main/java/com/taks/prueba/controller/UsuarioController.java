package com.taks.prueba.controller;

import static com.taks.prueba.utilitis.Constants.USUARIO;
import static com.taks.prueba.utilitis.Constants.USUARIO_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taks.prueba.entity.UsuarioEntity;
import com.taks.prueba.services.UsuarioServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/v1/api/")
@Api(description = "Crea  y  Administra  los usuarios del sistema")
public class UsuarioController {

	@Autowired
	public  UsuarioServices usuarioServices;
	
	@ApiOperation(value="GET", notes="Muestra los todos los usuarios del sistema")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=UsuarioEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@GetMapping(USUARIO)
	public List<UsuarioEntity> getUsuarios(){
		
		return usuarioServices.get();
	}
	
	@GetMapping(USUARIO_ID)
	public  UsuarioEntity  getUsuario( @PathVariable  Integer id) {
		
		return usuarioServices.getId(id);
	}
	
	@PostMapping(USUARIO)
	public  ResponseEntity<Object> addUsuario( @RequestBody UsuarioEntity usuario){
		return  usuarioServices.add(usuario);	
		
	}
	
	@PostMapping("signUp")
	public  ResponseEntity<Object> addUsuarios( @RequestBody UsuarioEntity usuario){
		return  usuarioServices.add(usuario);	
		
	}
	
	@PutMapping(USUARIO_ID)
	public ResponseEntity<Object> updateUsuario(  @RequestBody UsuarioEntity usuario, 
													@PathVariable Integer id){
		
		return  usuarioServices.update(usuario, id);
	}
	
	@DeleteMapping(USUARIO_ID)
	public void  deleteUsuario( @PathVariable Integer id) {
		 usuarioServices.delete(id);
	}

}
