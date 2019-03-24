package com.taks.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taks.prueba.entity.UsuarioEntity;

public interface UsuarioRP extends JpaRepository<UsuarioEntity, Integer>{
	
	UsuarioEntity  findByEmail(String email);
	@Query( value="SELECT*FROM sys_usuarios u  WHERE u.active=true",
			nativeQuery=true)
	List<UsuarioEntity>  finByActives();
	
	@Query( value="UPDATE sys_usuarios  SET active=false WHERE id = ?1 RETURNING id",
			 nativeQuery = true)
	void findByDelete( Integer id);
	
	
	
} 
