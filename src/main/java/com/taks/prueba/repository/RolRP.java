package com.taks.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taks.prueba.entity.RolEntity;

@Repository
public interface RolRP extends   JpaRepository<RolEntity, Integer> {

}
