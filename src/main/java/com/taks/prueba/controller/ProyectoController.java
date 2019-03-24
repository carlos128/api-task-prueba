package com.taks.prueba.controller;

import static com.taks.prueba.utilitis.Constants.PROYECTO;
import static com.taks.prueba.utilitis.Constants.PROYECTO_ID;

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


import com.taks.prueba.entity.ProyectoEntity;
import com.taks.prueba.services.ProyectoServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/api/")
@Api(description = "Crea  y Gestiona  los proyectos")
public class ProyectoController {
	
	@Autowired
	public  ProyectoServices proyectoServices;
	
	
	@ApiOperation(value="GET", notes="Muestra todos los Proyectos ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ProyectoEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@GetMapping(PROYECTO)
	public List<ProyectoEntity> getProyectos(){
		
		return proyectoServices.get();
	}
	
	@ApiOperation(value="GET", notes="Muestra un proyecto  con el id")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ProyectoEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@GetMapping(PROYECTO_ID)
	public  ProyectoEntity  getProyecto( @PathVariable  Integer id) {
		
		return proyectoServices.getId(id);
	}
	
	@ApiOperation(value="POST", notes="Agrega  un nuevo  proyecto")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@PostMapping(PROYECTO)
	public  ResponseEntity<Object> addProyecto( @RequestBody ProyectoEntity proyecto){
		
		return  proyectoServices.add(proyecto);	
		
	}
	
	@ApiOperation(value="PUT", notes="Actualiza  un proyecto")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=ProyectoEntity.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@PutMapping(PROYECTO_ID)
	public ResponseEntity<Object> updateProyecto(  @RequestBody ProyectoEntity proyecto, 
													@PathVariable Integer id){
		
		return  proyectoServices.update(proyecto, id);
	}
	
	@ApiOperation(value="DELETE", notes="Elimina  un  proyecto")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@DeleteMapping(PROYECTO_ID)
	public void  deleteProyecto( @PathVariable Integer id) {
		 proyectoServices.delete(id);
	}
	
}
