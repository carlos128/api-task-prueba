package com.taks.prueba.controller;

import static com.taks.prueba.utilitis.Constants.TAREAS;
import static com.taks.prueba.utilitis.Constants.TAREAS_ID;

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

import com.taks.prueba.entity.TareaEntity;
import com.taks.prueba.services.TareaServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/v1/api/")
@Api(description = "Crea  y Gestion las  teras delos usuarios")
public class TareaController {
	
	@Autowired
	public  TareaServices tareaServices;
	
	@ApiOperation(value="GET", notes="Muestra todas las tareas ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=TareaEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})

	@GetMapping(TAREAS)
	public List<TareaEntity> getTareas(){
		
		return tareaServices.get();
	}
	
	@ApiOperation(value="GET", notes="Muestra  el detalle de una tarea ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=TareaEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@GetMapping(TAREAS_ID)
	public  TareaEntity  getTarea( @PathVariable  Integer id) {
		
		return tareaServices.getId(id);
	}
	
	@ApiOperation(value="POST", notes="Agrega  una tarea ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=TareaEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@PostMapping(TAREAS)
	public  ResponseEntity<Object> addTarea( @RequestBody TareaEntity tarea){
		
		return  tareaServices.add(tarea);	
		
	}
	
	@ApiOperation(value="PUT", notes="Actuliza una  tarea ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=TareaEntity.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@PutMapping(TAREAS_ID)
	public ResponseEntity<Object> updateTarea(  @RequestBody TareaEntity tarea, 
													@PathVariable Integer id){
		
		return  tareaServices.update(tarea, id);
	}
	
	@ApiOperation(value="DELETE", notes="Actuliza una  tarea ")
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	
	@DeleteMapping(TAREAS_ID)
	public void  deleteTarea( @PathVariable Integer id) {
		 tareaServices.delete(id);
	}

}
