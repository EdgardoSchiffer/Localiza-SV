package com.edgardo.localiza.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.model.entity.TipoTrabajo;
import com.edgardo.localiza.model.entity.Trabajo;
import com.edgardo.localiza.model.repository.TrabajoDetailService;
import com.edgardo.localiza.serviceImpl.TipoTrabajoServiceImpl;
import com.edgardo.localiza.serviceImpl.TrabajoServiceImpl;
import com.edgardo.localiza.serviceImpl.UserInformationService;

@Controller
@RequestMapping("/app")
public class MainController {
	@Autowired
	private TipoTrabajoServiceImpl tipoTrabajo;
	@Autowired
	private TrabajoDetailService trabajoService;
	@Autowired
	private TrabajoServiceImpl trabajoServiceImpl;
	
	@Autowired
	private UserInformationService userInformationService;
	
	@RequestMapping("")
	public String defaultPage(){
		return "redirect:app/index";
	}
	
	@RequestMapping(value="getTrabajo", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Trabajo> getTrabajo(@RequestParam(value="id")int id){
		return trabajoService.findAll(id);
	}
	
	@RequestMapping(value="getUserInfo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Monitoreo getMonitoreoUserInfo(@RequestParam(value="id")int id){
		Monitoreo result = userInformationService.getUserInfo(id);
		return result;
	}
	
	@RequestMapping(value="updateUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean updateUser(
			@RequestParam(value="id")int id,
			@RequestParam(value="nombre")String nombre,
			@RequestParam(value="apellido")String apellido,
			@RequestParam(value="rol")String rol,
			@RequestParam(value="user")String user,
			@RequestParam(value="password")String pass,
			@RequestParam(value="pwd")boolean pwd
			){
		return userInformationService.updateUser(user, pass, nombre, apellido, rol, id, pwd);
	}
	
	@RequestMapping(value="uniqueUser", method=RequestMethod.POST)
	public @ResponseBody int uniqueUser(@RequestParam(value="user")String user){
		return userInformationService.uniqueUser(user);
	}
	
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String appIndex(){
		return "/index";
	}
	
	@RequestMapping(value="getTipoTrabajo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TipoTrabajo> getTipoTrabajo(){
		return tipoTrabajo.findAll();
	}
	
	@RequestMapping(value="newTipoTrabajo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TipoTrabajo newTipoTrabajo(@RequestParam(value="tipo_trabajo")String tipo,
													@RequestParam(value="descripcion")String desc){
		TipoTrabajo tipoEntity = new TipoTrabajo();
		tipoEntity.setTipoTrabajo(tipo);
		tipoEntity.setDescripcion(desc);
		return tipoTrabajo.save(tipoEntity);
	}
	
	@RequestMapping(value="deleteTipoTrabajo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean deleteTipoTrabajo(@RequestParam("id")int id){
		return tipoTrabajo.delete(id);
	}
	
	@RequestMapping(value="updateTipoTrabajo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TipoTrabajo updateTipoTrabajo(
			@RequestParam(value="tipo_trabajo")String tipo,
			@RequestParam(value="descripcion")String desc,
			@RequestParam(value="id")int id){
		TipoTrabajo entity = new TipoTrabajo();
		entity.setTipoTrabajo(tipo);
		entity.setDescripcion(desc);
		return tipoTrabajo.update(entity, id);
	}
	
	@RequestMapping("trabajoAjustes")
	public String trabajoAjustes(){
		return "/trabajoAjustes";
	}
	
	@RequestMapping("instalaciones")
	public String instalaciones(){
		return "/instalaciones";
	}

	@RequestMapping("revisiones")
	public String revisiones(){
		return "/revisiones";
	}
	
	@RequestMapping("desmontajes")
	public String desmontajes(){
		return "/desmontajes";
	}
	
	@RequestMapping("categoriaClientes")
	public String categoriaClientes(){
		return "/categoriaClientes";
	}
	
	@RequestMapping("clientes")
	public String clientes(){
		return "/clientes";
	}
	
	@RequestMapping("marcaDispositivo")
	public String marcaDispositivo(){
		return "/marcaDispositivo";
	}
	
	@RequestMapping("modeloDispositivo")
	public String modeloDispositivo(){
		return "/modeloDispositivo";
	}
	
	@RequestMapping("monitoreo")
	public String monitoreo(){
		return "/monitoreo";
	}
	
	@RequestMapping("ejecutivas")
	public String ejecutivas(){
		return "/ejecutivas";
	}
	
	@RequestMapping("tecnicos")
	public String tecnicos(){
		return "/tecnicos";
	}
	
	@RequestMapping(value="ubicacionesGps")
	public String ubicacionesGps(){
		return "/ubicacionesGps";
	}
}
