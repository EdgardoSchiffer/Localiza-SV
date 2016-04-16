package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.serviceImpl.MonitoreoServiceImpl;

@Controller
@RequestMapping("/app")
public class MonitoreoController {
	@Autowired
	private MonitoreoServiceImpl monitoreo;
	
	@RequestMapping(value="getMonitoreo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Monitoreo> getMonitoreo(){
		return monitoreo.findAll();
	}
	
	@RequestMapping(value="newMonitoreo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Monitoreo newMonitoreo(@RequestParam(value="nombre")String nombre,
													@RequestParam(value="apellido")String apellido){
		Monitoreo operador = new Monitoreo();
		operador.setNombre(nombre);
		operador.setApellido(apellido);
		return monitoreo.save(operador);
	}
	
	@RequestMapping(value="deleteMonitoreo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteTipoCliente(@RequestParam("id")int id){
		return monitoreo.delete(id);
	}
	
	@RequestMapping(value="updateMonitoreo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Monitoreo updateMonitoreo(
			@RequestParam(value="nombre")String nombre,
			@RequestParam(value="id")int id,
			@RequestParam(value="apelludo")String apellido){
		Monitoreo entity = new Monitoreo();
		entity.setApellido(apellido);;
		entity.setNombre(nombre);;
		return monitoreo.update(entity, id);
	}
}
