package com.edgardo.localiza.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edgardo.localiza.model.entity.Cliente;
import com.edgardo.localiza.model.entity.Trabajo;
/**
 * 
 * 
 * ESTA CLASE NO TIENE FUNCION ACTUALMENTE, SOLO ESTA PARA PROPOSITOS
 * INFORMATIVOS, CONTIENE UNA FORMA ALTERNATIVA PARA EJECUTIVAR
 * STORED PROCEDURES
 * @author ed_ba
 *
 */
@Repository
public class TrabajoDetailService {
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
	}
	
	public List<Trabajo> findAll(Integer id){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_cliente", id);
		return getJdbcTemplate().query("SELECT * FROM getDetalleTrabajo(1)", paramMap, new RowMapper<Trabajo>() {

			@Override
			public Trabajo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Trabajo t = new Trabajo();
				t.setBoleta(rs.getInt(1));
				Cliente c = new Cliente();
				c.setCliente(rs.getString(2));
				t.setCliente(c);
				t.setFecha(rs.getTimestamp(3));
				t.setImei(rs.getInt(4));
				return t;
			}
		});
	}
	
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
