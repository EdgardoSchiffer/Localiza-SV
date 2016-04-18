package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Trabajo;
import com.edgardo.localiza.model.repository.TrabajoRepository;
import com.edgardo.localiza.service.TrabajoService;

@Service
public class TrabajoServiceImpl implements TrabajoService{
	@Autowired
	private TrabajoRepository repository;
	
	@Override
	public Trabajo save(Trabajo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
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
		return null;
	}

}
