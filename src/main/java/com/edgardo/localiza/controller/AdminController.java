package com.edgardo.localiza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.serviceImpl.MonitoreoServiceImpl;
import com.edgardo.localiza.serviceImpl.UserInformationService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired 
	MonitoreoServiceImpl service;
	
	@Autowired
	UserInformationService userService;
	
	@RequestMapping("")
	public String defaultPage(){
		return "redirect:admin/admin";
	}
	
	@RequestMapping(value="admin", method=RequestMethod.GET)
	public String appIndex(){
		return "/admin";
	}
	
	@RequestMapping(value="adminDialog", method=RequestMethod.GET)
	public String adminDialog(){
		return "/tmpl.admin";
	}
	
	@RequestMapping(value="newUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int newUser(
			@RequestParam(value="usuario") String user,
			@RequestParam(value="password") String pass,
			@RequestParam(value="nombre") String nombre,
			@RequestParam(value="apellido") String ape,
			@RequestParam(value="rol") String rol
			){
		return userService.newUser(user, pass, nombre, ape, rol);
	}
	
}
