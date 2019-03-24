package com.taks.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taks.prueba.entity.ProyectoEntity;


@Repository
public interface ProyectoRP extends JpaRepository<ProyectoEntity, Integer> {
	
	@Query( value="SELECT*FROM  sys_proyectos p WHERE p.active=1",
			nativeQuery=true)
	List<ProyectoEntity>  finByActives();
	
	@Query( value="UPDATE sys_proyectos  SET active=false WHERE id = ?1",
			 nativeQuery = true)
	void findByDelete( Integer id);
	

}
