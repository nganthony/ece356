package com.ece356.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.Visit;
import com.ece356.domain.VisitAudit;
import com.ece356.jdbc.VisitAuditRowMapper;
import com.ece356.jdbc.VisitRowMapper;

@Repository
public class VisitAuditDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public void insert(VisitAudit visitAudit) {
		String sql = "INSERT INTO visit_audit (id,diagnosis, surgery, treatment,drug_id," +
					"comment, start, end, duration, user_id, "+
					"health_card, modified_on, modified_by_id, modified_type, visit_id)" +
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(
				sql,
				new Object[] {null,visitAudit.getDiagnosis(), visitAudit.getSurgery(), visitAudit.getTreatment(), visitAudit.getDrug_id(),
						visitAudit.getComment(), visitAudit.getStart(), visitAudit.getEnd(), visitAudit.getDuration(),
						visitAudit.getUser_id(), visitAudit.getHealth_card(), visitAudit.getModifiedOn(),
						visitAudit.getModifiedById(), visitAudit.getModifiedType(),visitAudit.getVisitId()});
	}

	public List<VisitAudit> getAllAuditVisits() {
		String sql = "SELECT * FROM visit_audit ORDER BY health_card, visit_id, modified_on";
		List<VisitAudit> VisitAudits = jdbcTemplate.query(sql, new VisitAuditRowMapper());
		return VisitAudits;
	}
	
	
}
