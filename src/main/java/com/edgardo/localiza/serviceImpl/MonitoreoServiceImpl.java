package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.model.repository.MonitoreoRepository;
import com.edgardo.localiza.service.MonitoreoService;

@Service
public class MonitoreoServiceImpl implements MonitoreoService{

	@Autowired
	private MonitoreoRepository repository;
	
	@Override
	public Monitoreo save(Monitoreo entity) {
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
	public List<Monitoreo> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Monitoreo update(Monitoreo entity, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
