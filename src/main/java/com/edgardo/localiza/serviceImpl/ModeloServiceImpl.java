package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.MarcaDispositivo;
import com.edgardo.localiza.model.entity.ModeloDispositivo;
import com.edgardo.localiza.model.repository.ModeloRepository;
import com.edgardo.localiza.service.ModeloService;

@Service
public class ModeloServiceImpl implements ModeloService{
	@Autowired
	private ModeloRepository repository;
	
	@Override
	public ModeloDispositivo save(ModeloDispositivo entity) {
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
	public List<ModeloDispositivo> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ModeloDispositivo update(ModeloDispositivo entity, Integer id) {
		// TODO Auto-generated method stub
		ModeloDispositivo modelo = repository.findOne(id);
		modelo.setModelo(entity.getModelo());
		MarcaDispositivo marca = new MarcaDispositivo();
		marca.setId(entity.getMarca().getId());
		modelo.setMarca(marca);
		return repository.saveAndFlush(modelo);
	}

}
