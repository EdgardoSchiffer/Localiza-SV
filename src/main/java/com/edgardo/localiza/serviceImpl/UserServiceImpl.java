package com.edgardo.localiza.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.User;
import com.edgardo.localiza.model.repository.UserRepository;
import com.edgardo.localiza.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private SimpleJdbcCall storedProcedure;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.storedProcedure = new SimpleJdbcCall(dataSource)
		.withProcedureName("loginvalidate");
		
	}	
	
	@Override
	public boolean validateLogin(String user, String pass) {
		// TODO Auto-generated method stub
		SqlParameterSource in = new MapSqlParameterSource().addValue("loginusername",user).addValue("loginpass",pass);
		Map<String, Object> resul = storedProcedure.execute(in);
		Object response = resul.get("#result-set-1");
		String valoresArr = response.toString();
		String values = valoresArr.substring(9, 10);
		if(Integer.valueOf(values) == 1){
			return true;
		}else{
			return false;
		}
	}
	
}
