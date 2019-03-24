package com.taks.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taks.prueba.entity.TareaEntity;


@Repository
public interface TareaRP  extends JpaRepository<TareaEntity, Integer> {
	
	@Query( value="SELECT*FROM  TareaEntity t  WHERE t.active=1",
			nativeQuery=true)
	List<TareaEntity>  finByActives();
	
	@Query("UPDATE TareaEntity SET active=0 WHERE id=?1")
	void findByDelete( Integer id);

}
