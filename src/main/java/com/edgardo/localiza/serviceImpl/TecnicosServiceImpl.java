package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Tecnicos;
import com.edgardo.localiza.model.repository.TecnicosRepository;
import com.edgardo.localiza.service.TecnicosService;

@Service
public class TecnicosServiceImpl implements TecnicosService{
	@Autowired
	private TecnicosRepository repository;

	@Override
	public Tecnicos save(Tecnicos entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tecnicos> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Tecnicos update(Tecnicos entity, Integer id) {
		// TODO Auto-generated method stub
		return null;
	};
}
