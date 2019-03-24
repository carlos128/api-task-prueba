package com.taks.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taks.prueba.entity.ComentarioEntity;


@Repository
public interface ComentarioRP extends JpaRepository< ComentarioEntity, Integer> {
	@Query( value="SELECT*FROM  sys_comentarios  c WHERE c.active=true",
			nativeQuery=true)
	List<ComentarioEntity>  finByActives();
	
	@Query("UPDATE ComentarioEntity SET active=0 WHERE id=?1")
	void findByDelete( Integer id);

}
