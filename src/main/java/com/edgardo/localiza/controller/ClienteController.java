package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Cliente;
import com.edgardo.localiza.model.entity.TipoCliente;
import com.edgardo.localiza.serviceImpl.ClienteServiceImpl;

@Controller
@RequestMapping("/app")
public class ClienteController {
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	@RequestMapping(value="getClientes", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cliente> getClientes(){
		return clienteServiceImpl.findAll();
	}
	
	@RequestMapping(value="newCliente", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Cliente newCliente(
				@RequestParam(value="cliente") String cliente,
				@RequestParam(value="id_tipo_cliente")int tipo
			){
		Cliente c = new Cliente();
		c.setCliente(cliente);
		TipoCliente t =  new TipoCliente();
		t.setId_tipo_cliente(tipo);
		c.setTipoCliente(t);
		return clienteServiceImpl.save(c);
	}
	
	@RequestMapping(value="deleteCliente", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteCliente(@RequestParam(value="id")int id){
		return clienteServiceImpl.delete(id);
	}
	
	@RequestMapping(value="updateCliente", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Cliente updateCliente(
				@RequestParam(value="id")int id,
				@RequestParam(value="cliente") String cliente,
				@RequestParam(value="id_tipo_cliente")int tipo
			){
		Cliente c = new Cliente();
		c.setCliente(cliente);
		TipoCliente t =  new TipoCliente();
		t.setId_tipo_cliente(tipo);
		c.setTipoCliente(t);
		return clienteServiceImpl.update(c, id);
	}
}
