package com.taks.prueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.Api;



@Entity
@Table(name="sys_comentarios")
@Api(description = "crealos los mensajes y los comentarios de las tareas")
public class ComentarioEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  int id;
	
	@Column
	private  String  comentario;
	
	@Column
	private  String prioridad;
	
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
	
	@JoinColumn(name="fk_id_tareas_comentarios")
	@ManyToOne
	private  TareaEntity tareasEntity;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
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

	public TareaEntity getTareasEntity() {
		return tareasEntity;
	}

	public void setTareasEntity(TareaEntity tareasEntity) {
		this.tareasEntity = tareasEntity;
	}
	
	
	
	
	
	
}
