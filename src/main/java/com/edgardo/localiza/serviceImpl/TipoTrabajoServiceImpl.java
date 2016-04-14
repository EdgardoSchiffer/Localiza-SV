package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.TipoTrabajo;
import com.edgardo.localiza.model.repository.TipoTrabajoRepository;
import com.edgardo.localiza.service.TipoTrabajoService;

@Service
public class TipoTrabajoServiceImpl implements TipoTrabajoService{
	@Autowired
	private TipoTrabajoRepository repository;
	@Override
	public TipoTrabajo save(TipoTrabajo entity) {
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
	public List<TipoTrabajo> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public TipoTrabajo update(TipoTrabajo entity, Integer id) {
		// TODO Auto-generated method stub
		TipoTrabajo tipoTrabajo = repository.findOne(id);
		tipoTrabajo.setTipo_trabajo(entity.getTipo_trabajo());
		tipoTrabajo.setDescripcion(entity.getDescripcion());
		return repository.saveAndFlush(tipoTrabajo);
	}

}
