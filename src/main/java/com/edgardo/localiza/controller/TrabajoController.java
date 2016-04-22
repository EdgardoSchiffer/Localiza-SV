package com.edgardo.localiza.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Cliente;
import com.edgardo.localiza.model.entity.Ejecutivas;
import com.edgardo.localiza.model.entity.MarcaDispositivo;
import com.edgardo.localiza.model.entity.ModeloDispositivo;
import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.model.entity.Tecnicos;
import com.edgardo.localiza.model.entity.TipoTrabajo;
import com.edgardo.localiza.model.entity.Trabajo;
import com.edgardo.localiza.model.entity.UbicacionGps;
import com.edgardo.localiza.serviceImpl.TrabajoServiceImpl;

@Controller
@RequestMapping("/app")
public class TrabajoController {
	@Autowired
	TrabajoServiceImpl trabajoService;
	
	@RequestMapping(value="getTrabajos", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Trabajo> getTrabajos(@RequestParam(value="tipo") String tipo){
		List<Trabajo> trabajo = new ArrayList<Trabajo>();
		trabajo = trabajoService.findByType(tipo);
		return trabajo;
	}
	
	@RequestMapping(value="newTrabajo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Trabajo newTrabajo(
				@RequestParam(value="boleta")int boleta,
				@RequestParam(value="cliente")int cliente,
				@RequestParam(value="comentario")String comentario,
				@RequestParam(value="ejecutiva")int ejecutiva,
				@RequestParam(value="estado")boolean estado,
				@RequestParam(value="fecha")String fecha,
				@RequestParam(value="imei")Integer imei,
				@RequestParam(value="marca")int marca,
				@RequestParam(value="modelo")int modelo,
				@RequestParam(value="monitoreo")int monitoreo,
				@RequestParam(value="sim")String sim,
				@RequestParam(value="tecnico")int tecnico,
				@RequestParam(value="tipo")int tipo,
				@RequestParam(value="ubicacion")int ubicacion,
				@RequestParam(value="unidad")String unidad
			){
		Trabajo t = new Trabajo();
		t.setBoleta(boleta);
		t.setComentario(comentario);
		t.setEstado(estado);
		t.setFecha(Timestamp.valueOf(fecha));
		t.setImei(imei);
		t.setSim(sim);
		t.setUnidad(unidad);
		Cliente c = new Cliente();
		c.setId(cliente);
		t.setCliente(c);
		Ejecutivas e = new Ejecutivas();
		e.setId(ejecutiva);
		t.setEjecutiva(e);
		MarcaDispositivo md = new MarcaDispositivo();
		md.setId(marca);
		t.setMarcaDispositivo(md);
		ModeloDispositivo mo = new ModeloDispositivo();
		mo.setId(modelo);
		t.setModeloDispositivo(mo);
		Monitoreo m = new Monitoreo();
		m.setId(monitoreo);
		t.setMonitoreo(m);
		Tecnicos tec = new Tecnicos();
		tec.setId(tecnico);
		t.setTecnico(tec);
		TipoTrabajo tt = new TipoTrabajo();
		tt.setId(tipo);
		t.setTipoTrabajo(tt);
		UbicacionGps u = new UbicacionGps();
		u.setId(ubicacion);
		t.setUbicacionGps(u);
		return trabajoService.save(t);
	}
	
	@RequestMapping(value="updateTrabajo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Trabajo updateTrabajo(
				@RequestParam(value="boleta")int boleta,
				@RequestParam(value="cliente")int cliente,
				@RequestParam(value="comentario")String comentario,
				@RequestParam(value="ejecutiva")int ejecutiva,
				@RequestParam(value="estado")boolean estado,
				@RequestParam(value="fecha")String fecha,
				@RequestParam(value="id")int id,
				@RequestParam(value="imei")Integer imei,
				@RequestParam(value="marca")int marca,
				@RequestParam(value="modelo")int modelo,
				@RequestParam(value="monitoreo")int monitoreo,
				@RequestParam(value="sim")String sim,
				@RequestParam(value="tecnico")int tecnico,
				@RequestParam(value="tipo")int tipo,
				@RequestParam(value="ubicacion")int ubicacion,
				@RequestParam(value="unidad")String unidad
			){
		Trabajo t = new Trabajo();
		t.setBoleta(boleta);
		t.setComentario(comentario);
		t.setEstado(estado);
		t.setFecha(Timestamp.valueOf(fecha));
		t.setImei(imei);
		t.setSim(sim);
		t.setUnidad(unidad);
		Cliente c = new Cliente();
		c.setId(cliente);
		t.setCliente(c);
		Ejecutivas e = new Ejecutivas();
		e.setId(ejecutiva);
		t.setEjecutiva(e);
		MarcaDispositivo md = new MarcaDispositivo();
		md.setId(marca);
		t.setMarcaDispositivo(md);
		ModeloDispositivo mo = new ModeloDispositivo();
		mo.setId(modelo);
		t.setModeloDispositivo(mo);
		Monitoreo m = new Monitoreo();
		m.setId(monitoreo);
		t.setMonitoreo(m);
		Tecnicos tec = new Tecnicos();
		tec.setId(tecnico);
		t.setTecnico(tec);
		TipoTrabajo tt = new TipoTrabajo();
		tt.setId(tipo);
		t.setTipoTrabajo(tt);
		UbicacionGps u = new UbicacionGps();
		u.setId(ubicacion);
		t.setUbicacionGps(u);
		try {
			trabajoService.update(t, id);
			if (id != t.getBoleta()) {
				trabajoService.delete(id);
			}
		} catch (Exception e2) {
		}
		return new Trabajo();
	}
	
	@RequestMapping(value="deleteTrabajo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteTrabajo(
				@RequestParam(value="boleta")int boleta
			){
		return trabajoService.delete(boleta);
	}

}
