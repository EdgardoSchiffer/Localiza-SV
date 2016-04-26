package com.edgardo.localiza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.serviceImpl.MonitoreoServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired 
	MonitoreoServiceImpl service;
	
	@RequestMapping("")
	public String defaultPage(){
		return "redirect:admin/admin";
	}
	
	@RequestMapping(value="admin", method=RequestMethod.GET)
	public String appIndex(){
		return "/admin";
	}
}
