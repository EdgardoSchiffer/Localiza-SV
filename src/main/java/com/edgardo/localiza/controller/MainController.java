package com.edgardo.localiza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
public class MainController {
	@RequestMapping(value="", method=RequestMethod.GET)
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
}
