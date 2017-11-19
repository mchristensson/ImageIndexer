package org.mac.nasbackup.db.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ImageDao {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<ImageEntry> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM imageindex";
		List<ImageEntry> result = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper(ImageEntry.class));
		return result;
	}

	private final KeyHolder keyHolder = new GeneratedKeyHolder();
	public int insert(ImageEntry entry) {
		
		String sql = "insert into imageindex(filename,filepath,filesize,make,model,software)" +
	       		" values(:fileName,:filePath,:size,:make,:model,:software)";

		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entry);
		return namedParameterJdbcTemplate.update(sql, paramSource , keyHolder);

	}

	private static final class UserMapper implements RowMapper {

		public ImageEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
			ImageEntry entry = new ImageEntry();
			entry.setId(rs.getInt("id"));
			entry.setSize(rs.getInt("filesize"));
			entry.setFilePath(rs.getString("filepath"));
			entry.setFileName(rs.getString("filename"));
			return entry;
		}
	}

}
