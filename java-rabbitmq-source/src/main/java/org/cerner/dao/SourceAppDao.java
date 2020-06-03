package org.cerner.dao;

import java.util.List;

import org.cerner.dao.rowmapper.CovidPtRowMapper;
import org.cerner.dto.CovidPt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SourceAppDao {

	private static final String GET_ALL_COVID_PT_SQL = "SELECT * FROM CovidPt";

	private JdbcTemplate jdbcTemplate;

	private CovidPtRowMapper rowMapper;

	@Autowired
	public SourceAppDao(JdbcTemplate jdbcTemplate, CovidPtRowMapper rowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapper = rowMapper;
	}

	public List<CovidPt> getAllCovidPatients() {
		return jdbcTemplate.query(GET_ALL_COVID_PT_SQL, rowMapper);
	}
}
