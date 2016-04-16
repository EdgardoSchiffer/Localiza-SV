package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Ejecutivas;
import com.edgardo.localiza.serviceImpl.EjecutivaServiceImpl;

@Controller
@RequestMapping("/app")
public class EjecutivasController {
	@Autowired
	private EjecutivaServiceImpl Ejecutivas;
	
	@RequestMapping(value="getEjecutivas", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Ejecutivas> getEjecutivas(){
		return Ejecutivas.findAll();
	}
	
	@RequestMapping(value="newEjecutivas", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Ejecutivas newEjecutivas(@RequestParam(value="nombre")String nombre,
													@RequestParam(value="apellido")String apellido){
		Ejecutivas operador = new Ejecutivas();
		operador.setNombre(nombre);
		operador.setApellido(apellido);
		return Ejecutivas.save(operador);
	}
	
	@RequestMapping(value="deleteEjecutivas", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteTipoCliente(@RequestParam("id")int id){
		return Ejecutivas.delete(id);
	}
	
	@RequestMapping(value="updateEjecutivas", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Ejecutivas updateEjecutivas(
			@RequestParam(value="nombre")String nombre,
			@RequestParam(value="id")int id,
			@RequestParam(value="apelludo")String apellido){
		Ejecutivas entity = new Ejecutivas();
		entity.setApellido(apellido);;
		entity.setNombre(nombre);;
		return Ejecutivas.update(entity, id);
	}
}
