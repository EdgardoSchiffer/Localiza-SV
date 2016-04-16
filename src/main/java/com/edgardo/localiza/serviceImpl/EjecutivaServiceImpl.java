package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Ejecutivas;
import com.edgardo.localiza.model.repository.EjecutivasRepository;
import com.edgardo.localiza.service.EjecutivasService;

@Service
public class EjecutivaServiceImpl implements EjecutivasService{
	@Autowired
	private EjecutivasRepository repository;

	@Override
	public Ejecutivas save(Ejecutivas entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Ejecutivas> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Ejecutivas update(Ejecutivas entity, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
