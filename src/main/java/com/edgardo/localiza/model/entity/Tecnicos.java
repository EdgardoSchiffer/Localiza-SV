package com.edgardo.localiza.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="localiza_tecnicos")
public class Tecnicos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="autoIncrement" , strategy="increment")
	@GeneratedValue(generator="autoIncrement")
	@Column(name="id_tecnico")
	private int tecnicos;
	@Column(length=70)
	private String nombre;
	@Column(length=70)
	private String apellidos;
	public Tecnicos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTecnicos() {
		return tecnicos;
	}
	public void setTecnicos(int tecnicos) {
		this.tecnicos = tecnicos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
}
