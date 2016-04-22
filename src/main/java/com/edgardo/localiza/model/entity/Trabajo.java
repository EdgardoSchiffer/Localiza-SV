package com.edgardo.localiza.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="localiza_trabajo")
@NamedQueries({
	@NamedQuery(name="Trabajo.findAll", query="SELECT t.boleta, t.comentario, t.estado, t.fecha, t.imei, t.sim, t.unidad, c.cliente, concat(e.nombre, ' ', e.apellido) as ejecutiva, ma.marca, mo.modelo, concat(m.nombre, ' ', m.apellido) as monitoreo, concat(tec.nombre, ' ', tec.apellidos) as tecnico, u.ubicacion "+
											  "FROM Trabajo as t "+
											  "INNER JOIN t.cliente as c "+
											  "INNER JOIN t.ejecutiva as e "+
											  "INNER JOIN t.modeloDispositivo as mo "+
											  "INNER JOIN t.marcaDispositivo as ma "+
											  "INNER JOIN t.monitoreo as m "+
											  "INNER JOIN t.tecnico as tec "+
											  "INNER JOIN t.ubicacionGps as u ")
})
public class Trabajo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	/*@GenericGenerator(name="autoIncrement" , strategy="increment")
	@GeneratedValue(generator="autoIncrement")*/
	@Column(name="boleta")
	private int boleta;
	private Integer imei;
	@Column(length=10)
	private String sim;
	private Timestamp fecha;
	private boolean estado;
	@Column(length=300)
	private String unidad;
	@Column(length=300)
	private String comentario;
	@JoinColumn(name="fk_tipo_trabajo", referencedColumnName="id_tipo_trabajo")
	@ManyToOne
	private TipoTrabajo tipoTrabajo;
	@JoinColumn(name="fk_tecnico", referencedColumnName="id_tecnico")
	@ManyToOne
	private Tecnicos tecnico;
	@JoinColumn(name="fk_ejecutiva", referencedColumnName="id_ejecutiva")
	@ManyToOne
	private Ejecutivas ejecutiva;
	@JoinColumn(name="fk_monitoreo", referencedColumnName="id_monitoreo")
	@ManyToOne
	private Monitoreo monitoreo;
	@JoinColumn(name="fk_dispositivo", referencedColumnName="id_marca")
	@ManyToOne
	private MarcaDispositivo marcaDispositivo;
	@JoinColumn(name="fk_modelo", referencedColumnName="id_modelo")
	@ManyToOne
	private ModeloDispositivo modeloDispositivo;
	@JoinColumn(name="fk_cliente", referencedColumnName="id_cliente")
	@ManyToOne
	private Cliente cliente;
	@JoinColumn(name="fk_ubicacion", referencedColumnName="id_ubicacion")
	@ManyToOne
	private UbicacionGps ubicacionGps;
	public Trabajo() {
	}
	public int getBoleta() {
		return boleta;
	}
	public void setBoleta(int boleta) {
		this.boleta = boleta;
	}
	
	public Integer getImei() {
		return imei;
	}
	public void setImei(Integer imei) {
		this.imei = imei;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public TipoTrabajo getTipoTrabajo() {
		return tipoTrabajo;
	}
	public void setTipoTrabajo(TipoTrabajo tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}
	public Tecnicos getTecnico() {
		return tecnico;
	}
	public void setTecnico(Tecnicos tecnico) {
		this.tecnico = tecnico;
	}
	public Ejecutivas getEjecutiva() {
		return ejecutiva;
	}
	public void setEjecutiva(Ejecutivas ejecutiva) {
		this.ejecutiva = ejecutiva;
	}
	public Monitoreo getMonitoreo() {
		return monitoreo;
	}
	public void setMonitoreo(Monitoreo monitoreo) {
		this.monitoreo = monitoreo;
	}
	public MarcaDispositivo getMarcaDispositivo() {
		return marcaDispositivo;
	}
	public void setMarcaDispositivo(MarcaDispositivo marcaDispositivo) {
		this.marcaDispositivo = marcaDispositivo;
	}
	public ModeloDispositivo getModeloDispositivo() {
		return modeloDispositivo;
	}
	public void setModeloDispositivo(ModeloDispositivo modeloDispositivo) {
		this.modeloDispositivo = modeloDispositivo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public UbicacionGps getUbicacionGps() {
		return ubicacionGps;
	}
	public void setUbicacionGps(UbicacionGps ubicacionGps) {
		this.ubicacionGps = ubicacionGps;
	}
	
	
	
}
