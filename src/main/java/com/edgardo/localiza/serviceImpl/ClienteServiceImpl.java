package com.edgardo.localiza.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Cliente;
import com.edgardo.localiza.model.entity.TipoCliente;
import com.edgardo.localiza.model.repository.ClienteRepository;
import com.edgardo.localiza.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public Cliente save(Cliente entity) {
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
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Cliente update(Cliente entity, Integer id) {
		// TODO Auto-generated method stub
		Cliente cliente = repository.findOne(id);
		cliente.setCliente(entity.getCliente());
		TipoCliente tipo = new TipoCliente();
		tipo.setId_tipo_cliente(entity.getTipoCliente().getId_tipo_cliente());
		cliente.setTipoCliente(tipo);
		return repository.save(cliente);
	}
	
}
