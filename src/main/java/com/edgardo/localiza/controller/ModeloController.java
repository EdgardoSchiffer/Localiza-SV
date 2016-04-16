package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.MarcaDispositivo;
import com.edgardo.localiza.model.entity.ModeloDispositivo;
import com.edgardo.localiza.serviceImpl.ModeloServiceImpl;

@Controller
@RequestMapping("/app")
public class ModeloController {
	@Autowired
	private ModeloServiceImpl modeloServiceImpl;
	
	@RequestMapping(value = "getModelo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ModeloDispositivo> getModelo(){
		return modeloServiceImpl.findAll();
	}
	
	@RequestMapping(value="newModelo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModeloDispositivo newModelo(
				@RequestParam(value="modelo") String modelo,
				@RequestParam(value="id_marca") int marca
			){
		ModeloDispositivo m = new ModeloDispositivo();
		MarcaDispositivo md = new MarcaDispositivo();
		md.setId(marca);
		m.setMarca(md);
		m.setModelo(modelo);
		return modeloServiceImpl.save(m);
	}
	
	@RequestMapping(value="deleteModelo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteModelo(
				@RequestParam(value="id")int id
			){
		return modeloServiceImpl.delete(id);
	}
	
	@RequestMapping(value="updateModelo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModeloDispositivo updateModelo(
				@RequestParam(value="id")int id,
				@RequestParam(value="modelo")String modelo,
				@RequestParam(value="id_marca")int idModelo
			){
		ModeloDispositivo md = new ModeloDispositivo();
		MarcaDispositivo m = new MarcaDispositivo();
		md.setModelo(modelo);
		m.setId(idModelo);
		md.setMarca(m);
		return modeloServiceImpl.update(md, id);
	}
	
}
