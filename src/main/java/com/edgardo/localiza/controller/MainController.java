package com.edgardo.localiza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
public class MainController {
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String appIndex(){
		return "/index";
	}
	
	@RequestMapping("menu")
	public String menu(){
		return "/menu";
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
}
