package com.edgardo.localiza.serviceImpl;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.model.entity.User;

@Service
public class UserInformationService {
private SimpleJdbcCall getUserInfo;
private SimpleJdbcCall updateUser;
private SimpleJdbcCall uniqueUser;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.getUserInfo = new SimpleJdbcCall(dataSource)
		.withFunctionName("getuserinfo")
		.declareParameters(new SqlOutParameter("nombre", Types.VARCHAR))
		.declareParameters(new SqlOutParameter("apellido", Types.VARCHAR))
		.declareParameters(new SqlOutParameter("username", Types.VARCHAR));
		this.updateUser = new SimpleJdbcCall(dataSource)
		.withFunctionName("updateuser");
		this.uniqueUser = new SimpleJdbcCall(dataSource)
		.withFunctionName("uniqueuser");
	}	
	
	public int uniqueUser(String user){
		SqlParameterSource in = new MapSqlParameterSource().addValue("user_name", user);
		Map<String, Object> resul = uniqueUser.execute(in);
		return (Integer) resul.get("returnvalue");
	}
	
	public boolean updateUser(String user, String pass, String nombre, String apellido, String rol, int id, boolean pwd){
		SqlParameterSource in = new MapSqlParameterSource().
				addValue("user_name", user).
				addValue("pass_word", pass).
				addValue("nombre_", nombre).
				addValue("apellido_", apellido).
				addValue("rol", rol).
				addValue("password_flag", pwd).
				addValue("id", id);
		try {
			updateUser.execute(in);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Monitoreo getUserInfo(int id){
		SqlParameterSource in = new MapSqlParameterSource().addValue("user_id",id);
		Map<String, Object> resul = getUserInfo.execute(in);
		Monitoreo monitoreo = new Monitoreo();
		monitoreo.setNombre((String)resul.get("nombre"));
		monitoreo.setApellido((String) resul.get("apellido"));
		User user = new User();
		user.setId((Integer) id);
		user.setUsername((String) resul.get("username"));
		user.setPass("");
		monitoreo.setUser(user);
		return monitoreo;
	}

}
