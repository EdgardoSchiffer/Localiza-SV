package com.edgardo.localiza.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="localiza_modelo_dispositivo")
@NamedQueries({
	@NamedQuery(name="ModeloDispositivo.findAll", query="SELECT m.id, m.modelo, md.marca FROM ModeloDispositivo m JOIN m.marca md")
})
public class ModeloDispositivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="autoIncrement" , strategy="increment")
	@GeneratedValue(generator="autoIncrement")
	@Column(name="id_modelo")
	private int id;
	@Column(length=100)
	private String modelo;
	@ManyToOne
	@JoinColumn(name="fk_id_marca", referencedColumnName="id_marca")
	private MarcaDispositivo marca;
	public ModeloDispositivo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public MarcaDispositivo getMarca() {
		return marca;
	}
	public void setMarca(MarcaDispositivo marca) {
		this.marca = marca;
	}
	
	
	
	
}
