package com.edgardo.localiza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {
	@RequestMapping("/")
	public String validateLogin(){
		return "/index";
	}
}
