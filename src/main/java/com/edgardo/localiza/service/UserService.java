package com.edgardo.localiza.service;

import com.edgardo.localiza.framework.GenericService;
import com.edgardo.localiza.model.entity.User;

public interface UserService extends GenericService<User>{
	public Integer validateLogin (String user, String pass);
	public String getRole(int id);
}
