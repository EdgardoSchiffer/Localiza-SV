package com.edgardo.localiza.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="localiza_ubicacion_gps")
public class UbicacionGps implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="autoIncrement" , strategy="increment")
	@GeneratedValue(generator="autoIncrement")
	@Column(name="id_ubicacion")
	private int id;
	@Column(length = 100)
	private String ubicacion;
	public UbicacionGps() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	

}
