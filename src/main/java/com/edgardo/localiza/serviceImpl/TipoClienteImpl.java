package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.TipoCliente;
import com.edgardo.localiza.model.repository.TipoClienteRepository;
import com.edgardo.localiza.service.TipoClienteService;

@Service
public class TipoClienteImpl implements TipoClienteService{
	
	@Autowired
	private TipoClienteRepository repository;
	
	@Override
	public TipoCliente save(TipoCliente entity) {
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
	public List<TipoCliente> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public TipoCliente update(TipoCliente entity, Integer id) {
		// TODO Auto-generated method stub
		TipoCliente tipoCliente = repository.findOne(id);
		tipoCliente.setTipo_cliente(entity.getTipo_cliente());
		tipoCliente.setDescripcion(entity.getDescripcion());
		return repository.saveAndFlush(tipoCliente);
	}
	
}
