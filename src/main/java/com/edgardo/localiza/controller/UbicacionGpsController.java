package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.UbicacionGps;
import com.edgardo.localiza.serviceImpl.UbicacionGpsServiceImpl;

@Controller
@RequestMapping("/app")
public class UbicacionGpsController {
	@Autowired
	private UbicacionGpsServiceImpl serviceImpl;
	
	@RequestMapping(value="getUbicacion", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UbicacionGps> getUbicacion(){
		return serviceImpl.findAll();
	}
	
	@RequestMapping(value="newUbicacion", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UbicacionGps newUbicacion(
				@RequestParam(value="ubicacion") String ubicacion
			){
		UbicacionGps gps = new UbicacionGps();
		gps.setUbicacion(ubicacion);
		return serviceImpl.save(gps);
	}
	
	@RequestMapping(value="deleteUbicacion", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteUbicacion(
				@RequestParam(value="id")int id
			){
		return serviceImpl.delete(id);
	}
	
	@RequestMapping(value="updateUbicacion", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UbicacionGps updateUbicacion(
				@RequestParam(value="id") int id,
				@RequestParam(value="ubicacion") String ubicacion
			){
		UbicacionGps gps = new UbicacionGps();
		gps.setUbicacion(ubicacion);
		return serviceImpl.update(gps, id);
	}
}
