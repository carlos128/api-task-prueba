package com.taks.prueba.controller;

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

import com.taks.prueba.entity.ComentarioEntity;
import com.taks.prueba.services.ComentarioServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.taks.prueba.utilitis.Constants.COMENTARIO;
import static com.taks.prueba.utilitis.Constants.COMENTARIO_ID;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
@Api(description = "Crea  y Gestiona  los comentarios asociados a tareas")
public class ComentarioController {
	
	@Autowired
	public  ComentarioServices comentarioServices;
	
	
	@ApiOperation(value="GET", notes="Muestra todos los comentarios ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ComentarioEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@GetMapping(COMENTARIO)
	public List<ComentarioEntity> getComentarios(){
		
		return comentarioServices.get();
	}
	
	@ApiOperation(value="GET", notes="Muestra  muestra el detalle de un  comentario ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ComentarioEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@GetMapping(COMENTARIO_ID)
	public  ComentarioEntity  getComentario( @PathVariable  Integer id) {
		
		return comentarioServices.getId(id);
	}
	
	@ApiOperation(value="POST", notes="Crea un comentario  ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@PostMapping(COMENTARIO)
	public  ResponseEntity<Object> addComentario( @RequestBody ComentarioEntity comentario){
		
		return  comentarioServices.add(comentario);	
		
	}
	
	@ApiOperation(value="PUT", notes="Actuliza un comentario ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@PutMapping(COMENTARIO_ID)
	public ResponseEntity<Object> updateComentario(  @RequestBody ComentarioEntity comentario, 
													@PathVariable Integer id){
		
		return  comentarioServices.update(comentario, id);
	}
	 
	@ApiOperation(value="DELETE", notes="Elimina  un comentario")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@DeleteMapping(COMENTARIO_ID)
	public void  deleteComentario( @PathVariable Integer id) {
		 comentarioServices.delete(id);
	}
	
	

}
