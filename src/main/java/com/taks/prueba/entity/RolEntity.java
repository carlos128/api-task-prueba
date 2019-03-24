package com.taks.prueba.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



import io.swagger.annotations.Api;

@Entity
@Table(name="sys_rol")
@Api(description = "Gestiona  todos  los  roles del sistema ")

public class RolEntity {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO)
	private  int  id;
	
	@Column
	private  String rol;
	
	@Column
	private String  descripcion;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="sys_usuario_rol", 
			joinColumns=@JoinColumn(name="fk_id_rol",nullable=false),
			inverseJoinColumns = @JoinColumn(name="fk_id_usuario", nullable = false)
	)
	private List<UsuarioEntity> usuarioEntities;
	
	public void addUsuario(UsuarioEntity  usEntity){
        if(this.usuarioEntities == null){
            this.usuarioEntities = new ArrayList<>();
        }
        
        this.usuarioEntities.add(usEntity);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<UsuarioEntity> getUsuarioEntities() {
		return usuarioEntities;
	}

	public void setUsuarioEntities(List<UsuarioEntity> usuarioEntities) {
		this.usuarioEntities = usuarioEntities;
	}
	
	 
	
	
}
