package com.edgardo.localiza.serviceImpl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.edgardo.localiza.model.entity.Ejecutivas;
import com.edgardo.localiza.model.entity.Monitoreo;
import com.edgardo.localiza.model.entity.User;

@Service
public class UserInformationService {
private SimpleJdbcCall storedProcedure;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.storedProcedure = new SimpleJdbcCall(dataSource)
		.withFunctionName("getuserinfo")
		.declareParameters(new SqlOutParameter("nombre", Types.VARCHAR))
		.declareParameters(new SqlOutParameter("apellido", Types.VARCHAR))
		.declareParameters(new SqlOutParameter("username", Types.VARCHAR));
		
	}	
	
	
	public Monitoreo getMonitoreoUserInfo(Integer id){
		SqlParameterSource in = new MapSqlParameterSource().addValue("user_id",id);
		Map<String, Object> resul = storedProcedure.execute(in);
		Monitoreo monitoreo = new Monitoreo();
		monitoreo.setNombre((String)resul.get("nombre"));
		monitoreo.setApellido((String) resul.get("apellido"));
		User user = new User();
		user.setId(id);
		user.setUsername((String) resul.get("username"));
		user.setPass("");
		monitoreo.setUser(user);
		return monitoreo;
	}
	
	public List<Ejecutivas> getEjecutivasUserInfo(Integer id){
		return null;
		
	}
}
