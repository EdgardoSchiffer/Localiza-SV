package com.edgardo.localiza.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="localiza_tipo_cliente")
public class TipoCliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="autoIncrement" , strategy="increment")
	@GeneratedValue(generator="autoIncrement")
	@Column(name="id_tipo_cliente")
	private int id;
	@Column(name="tipo_cliente", length=30)
	private String tipoCliente;
	@Column(length=300)
	private String descripcion;
	public TipoCliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_tipo_cliente() {
		return id;
	}
	public void setId_tipo_cliente(int id_tipo_cliente) {
		this.id = id_tipo_cliente;
	}
	public String getTipo_cliente() {
		return tipoCliente;
	}
	public void setTipo_cliente(String tipo_cliente) {
		this.tipoCliente = tipo_cliente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
