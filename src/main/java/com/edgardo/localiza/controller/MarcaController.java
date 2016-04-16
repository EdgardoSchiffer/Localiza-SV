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
import com.edgardo.localiza.serviceImpl.MarcaServiceImpl;

@Controller
@RequestMapping("/app")
public class MarcaController {
	@Autowired
	private MarcaServiceImpl marcaServiceImpl;
	
	@RequestMapping(value="getMarca", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MarcaDispositivo> getList(){
		return marcaServiceImpl.findAll();
	}
	
	@RequestMapping(value="newMarca", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MarcaDispositivo newMarca(
				@RequestParam(value="marca")String marca
			){
		MarcaDispositivo m = new MarcaDispositivo();
		m.setMarca(marca);
		return marcaServiceImpl.save(m);
	}
	
	@RequestMapping(value="deleteMarca", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteMarca(
				@RequestParam(value="id")int id
			){
		return marcaServiceImpl.delete(id);
	}
	
	@RequestMapping(value="updateMarca", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MarcaDispositivo updateMarca(
				@RequestParam(value="id")int id,
				@RequestParam(value="marca")String marca
			){
		MarcaDispositivo m = new MarcaDispositivo();
		m.setMarca(marca);
		return marcaServiceImpl.update(m, id);
	}
}
