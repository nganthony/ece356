package com.ece356.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.VisitAudit;

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
		String sql = "INSERT INTO visit_audit (id,diagnosis, surgery, treatment," +
					"comment, start, end, duration, user_id, "+
					"health_card, modified_on, modified_by_id, modified_type, visit_id)" +
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(
				sql,
				new Object[] {null,visitAudit.getDiagnosis(), visitAudit.getSurgery(), visitAudit.getTreatment(),
						visitAudit.getComment(), visitAudit.getStart(), visitAudit.getEnd(), visitAudit.getDuration(),
						visitAudit.getUser_id(), visitAudit.getHealth_card(), visitAudit.getModifiedOn(),
						visitAudit.getModifiedById(), visitAudit.getModifyType(),visitAudit.getVisitId()});
	}
}
