package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.UbicacionGps;
import com.edgardo.localiza.model.repository.UbicacionGpsRepository;
import com.edgardo.localiza.service.UbicacionGpsService;

@Service
public class UbicacionGpsServiceImpl implements UbicacionGpsService{
	
	@Autowired
	UbicacionGpsRepository repository;
	
	@Override
	public UbicacionGps save(UbicacionGps entity) {
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
	public List<UbicacionGps> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public UbicacionGps update(UbicacionGps entity, Integer id) {
		// TODO Auto-generated method stub
		UbicacionGps gps = repository.findOne(id);
		gps.setUbicacion(entity.getUbicacion());
		return repository.saveAndFlush(gps);
	}

}
