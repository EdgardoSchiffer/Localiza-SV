package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Cliente;
import com.edgardo.localiza.model.entity.Ejecutivas;
import com.edgardo.localiza.model.entity.MarcaDispositivo;
import com.edgardo.localiza.model.entity.ModeloDispositivo;
import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.model.entity.Tecnicos;
import com.edgardo.localiza.model.entity.TipoTrabajo;
import com.edgardo.localiza.model.entity.Trabajo;
import com.edgardo.localiza.model.entity.UbicacionGps;
import com.edgardo.localiza.model.repository.TrabajoRepository;
import com.edgardo.localiza.service.TrabajoService;

@Service
public class TrabajoServiceImpl implements TrabajoService{
	@Autowired
	private TrabajoRepository repository;
	
	@Override
	public Trabajo save(Trabajo entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if (repository.exists(id)) {
			repository.delete(id);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Trabajo> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	public List<Trabajo> findByType(String tipo){
		return repository.findByTipoTrabajoTipoTrabajo(tipo);
		
	}

	@Override
	public Trabajo update(Trabajo entity, Integer id) {
		// TODO Auto-generated method stub
		Trabajo t = new Trabajo();
		t.setBoleta(entity.getBoleta());
		t.setComentario(entity.getComentario());
		t.setEstado(entity.isEstado());
		t.setFecha(entity.getFecha());
		t.setImei(entity.getImei());
		t.setSim(entity.getSim());
		t.setUnidad(entity.getUnidad());
		Cliente c = new Cliente();
		c.setId(entity.getCliente().getId());
		t.setCliente(c);
		Ejecutivas e = new Ejecutivas();
		e.setId(entity.getEjecutiva().getId());
		t.setEjecutiva(e);
		MarcaDispositivo md = new MarcaDispositivo();
		md.setId(entity.getMarcaDispositivo().getId());
		t.setMarcaDispositivo(md);
		ModeloDispositivo mo = new ModeloDispositivo();
		mo.setId(entity.getModeloDispositivo().getId());
		t.setModeloDispositivo(mo);
		Monitoreo m = new Monitoreo();
		m.setId(entity.getMonitoreo().getId());
		t.setMonitoreo(m);
		Tecnicos tec = new Tecnicos();
		tec.setId(entity.getTecnico().getId());
		t.setTecnico(tec);
		TipoTrabajo tt = new TipoTrabajo();
		tt.setId(entity.getTipoTrabajo().getId());
		t.setTipoTrabajo(tt);
		UbicacionGps u = new UbicacionGps();
		u.setId(entity.getUbicacionGps().getId());
		t.setUbicacionGps(u);
		return repository.saveAndFlush(entity);
	}

}
