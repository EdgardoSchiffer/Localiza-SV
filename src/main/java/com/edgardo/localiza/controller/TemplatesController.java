package com.edgardo.localiza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class TemplatesController {
	@RequestMapping("menu")
	public String menu(){
		return "/tmpl.menu";
	}
	
	@RequestMapping("trabajoDialog")
	public String tdialog(){
		return "/tmpl.trabajoDialog";
	}
	
	@RequestMapping("indexDialog")
	public String idialog(){
		return "/tmpl.index";
	}
	
	@RequestMapping("categoriaClienteDialog")
	public String ccDialog(){
		return "/tmpl.categoriaCliente";
	}
	
	@RequestMapping("clientesDialog")
	public String cDialog(){
		return "/tmpl.clientes";
	}
	
	@RequestMapping("marcaDialog")
	public String mDialog(){
		return "/tmpl.marca";
	}
	
	@RequestMapping("modeloDialog")
	public String moDialog(){
		return "/tmpl.modelo";
	}
}
