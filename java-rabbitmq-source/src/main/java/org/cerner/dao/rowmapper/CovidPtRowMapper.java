package org.cerner.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.cerner.dto.CovidPt;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CovidPtRowMapper implements RowMapper<CovidPt> {

	@Override
	public CovidPt mapRow(ResultSet rs, int rowNum) throws SQLException {

		return new CovidPt(rs.getInt("Id"), rs.getString("LastName"), rs.getString("FirstName"), rs.getInt("Age"),
				rs.getString("Gender"), rs.getString("CoMorbiditiesFlag"), rs.getString("DeceasedFlag"),
				rs.getString("City"));
	}
}
