package org.mac.nasbackup.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface GenericDao<T> {

	int insert(T entry);

	List<T> findAll();

	void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate);

}
