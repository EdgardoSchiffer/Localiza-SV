package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Tecnicos;
import com.edgardo.localiza.model.repository.TecnicosRepository;
import com.edgardo.localiza.model.specificartion.TecnicoSpecification;
import com.edgardo.localiza.service.TecnicosService;

@Service
public class TecnicosServiceImpl implements TecnicosService{
	@Autowired
	private TecnicosRepository repository;

	@Override
	public Tecnicos save(Tecnicos entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tecnicos> findAll() {
		// TODO Auto-generated method stub
		//return repository.findAll();
		//Specification<Tecnicos> tec = TecnicoSpecification.findTecnicoBySpecification("Sebastian");
		//return repository.findAll(tec);
		return repository.findAll();
	}

	@Override
	public Tecnicos update(Tecnicos entity, Integer id) {
		// TODO Auto-generated method stub
		Tecnicos t = repository.findOne(id);
		t.setNombre(entity.getNombre());
		t.setApellidos(entity.getApellidos());
		return repository.saveAndFlush(t);
	};
}
