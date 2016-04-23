package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Tecnicos;
import com.edgardo.localiza.serviceImpl.TecnicosServiceImpl;

@Controller
@RequestMapping("/app")
public class TecnicosController {
	@Autowired
	private TecnicosServiceImpl Tecnicos;
	
	@RequestMapping(value="getTecnicos", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Tecnicos> getTecnicos(){
		return Tecnicos.findAll();
	}
	
	@RequestMapping(value="newTecnicos", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Tecnicos newTecnicos(@RequestParam(value="nombre")String nombre,
													@RequestParam(value="apellido")String apellido){
		Tecnicos operador = new Tecnicos();
		operador.setNombre(nombre);
		operador.setApellidos(apellido);
		return Tecnicos.save(operador);
	}
	
	@RequestMapping(value="deleteTecnicos", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteTipoCliente(@RequestParam("id")int id){
		return Tecnicos.delete(id);
	}
	
	@RequestMapping(value="updateTecnicos", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Tecnicos updateTecnicos(
			@RequestParam(value="nombre")String nombre,
			@RequestParam(value="id")int id,
			@RequestParam(value="apelludo")String apellido){
		Tecnicos entity = new Tecnicos();
		entity.setApellidos(apellido);;
		entity.setNombre(nombre);;
		return Tecnicos.update(entity, id);
	}
	
	@RequestMapping(value="getTecnicoSpec", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Tecnicos> getTecnicosSpec(){
		return Tecnicos.findAll();
	}
}
