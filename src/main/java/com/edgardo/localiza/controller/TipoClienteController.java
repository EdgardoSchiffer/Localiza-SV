package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.TipoCliente;
import com.edgardo.localiza.serviceImpl.TipoClienteImpl;

@Controller
@RequestMapping("app")
public class TipoClienteController {
	@Autowired
	private TipoClienteImpl tipoCliente;
	
	@RequestMapping(value="getTipoCliente", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TipoCliente> getTipoCliente(){
		return tipoCliente.findAll();
	}
	
	@RequestMapping(value="newTipoCliente", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TipoCliente newTipoCliente(@RequestParam(value="tipo_cliente")String tipo,
													@RequestParam(value="descripcion")String desc){
		TipoCliente tipoEntity = new TipoCliente();
		tipoEntity.setTipo_cliente(tipo);
		tipoEntity.setDescripcion(desc);
		return tipoCliente.save(tipoEntity);
	}
	
	@RequestMapping(value="deleteTipoCliente", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteTipoCliente(@RequestParam("id")int id){
		return tipoCliente.delete(id);
	}
	
	@RequestMapping(value="updateTipoCliente", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TipoCliente updateTipoCliente(
			@RequestParam(value="descripcion")String desc,
			@RequestParam(value="id_tipo_cliente")int id,
			@RequestParam(value="tipo_cliente")String tipo){
		TipoCliente entity = new TipoCliente();
		entity.setTipo_cliente(tipo);
		entity.setDescripcion(desc);
		return tipoCliente.update(entity, id);
	}
}
