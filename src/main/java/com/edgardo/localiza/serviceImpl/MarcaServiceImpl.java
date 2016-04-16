package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.MarcaDispositivo;
import com.edgardo.localiza.model.repository.MarcaRepository;
import com.edgardo.localiza.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService{
	
	@Autowired
	private MarcaRepository repository;

	@Override
	public MarcaDispositivo save(MarcaDispositivo entity) {
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
	public List<MarcaDispositivo> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public MarcaDispositivo update(MarcaDispositivo entity, Integer id) {
		// TODO Auto-generated method stub
		MarcaDispositivo marca = repository.findOne(id);
		marca.setMarca(entity.getMarca());
		return repository.saveAndFlush(marca);
	}

}
