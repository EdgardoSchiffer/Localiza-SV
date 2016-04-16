package com.edgardo.localiza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edgardo.localiza.serviceImpl.UserServiceImpl;

@Controller
public class LoginController {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="loginRequestValidate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer loginRequestValidate(Model model,
													@RequestParam(value="user") String user,
													@RequestParam(value="password") String pass
			){
		return userService.validateLogin(user, pass);
	}
	
	@RequestMapping(value="getRole", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getRole(@RequestParam(value="id")int id){
		String role = userService.getRole(id);
		return "{\"rol\": \""+role+"\"}";
	}
}
