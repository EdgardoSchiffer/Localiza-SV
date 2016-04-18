package com.edgardo.localiza.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="localiza_tipo_trabajo")
public class TipoTrabajo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="autoIncrement" , strategy="increment")
	@GeneratedValue(generator="autoIncrement")
	@Column(name="id_tipo_trabajo")
	private int id;
	@Column(length=40)
	private String tipo_trabajo;
	@Column(length=300)
	private String descripcion;
	public TipoTrabajo() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo_trabajo() {
		return tipo_trabajo;
	}
	public void setTipo_trabajo(String tipo_trabajo) {
		this.tipo_trabajo = tipo_trabajo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
