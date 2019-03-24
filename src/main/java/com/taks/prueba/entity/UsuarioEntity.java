package com.taks.prueba.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "Esta clase  genera  los usuarios  del sistema")
@Entity
@Table(name="sys_usuarios")
public class UsuarioEntity {
	  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  int id;
	
	@Column
	private  String  nombre;
	
	@Column
	private String apellido;
	
	@Column(unique=true)
	private String  email;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@Column
	private String  password;
	
	@Column
	private  boolean active;
	
	@Column
	private  String  createBy;
	
	@JsonFormat( pattern="yyyy-mm-dd",shape=Shape.STRING)
	@Column
	private  String  createDt;
	
	@Column
	private  String updateBy;
	
	@JsonFormat( pattern="yyyy-mm-dd",shape=Shape.STRING)
	@Column
	private  String  upadateDt;
	
	@ManyToMany(mappedBy="usuarioEntities")
	private List<RolEntity> rolEntities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpadateDt() {
		return upadateDt;
	}

	public void setUpadateDt(String upadateDt) {
		this.upadateDt = upadateDt;
	}

	public List<RolEntity> getRolEntities() {
		return rolEntities;
	}

	public void setRolEntities(List<RolEntity> rolEntities) {
		this.rolEntities = rolEntities;
	}
	
	
}
