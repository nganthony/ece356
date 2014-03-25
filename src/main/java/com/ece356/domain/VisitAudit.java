package com.ece356.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class VisitAudit extends BaseEntity {

	int id;
	
	String diagnosis;
	
	String surgery;
	
	String treatment;
	
	String comment;
	
	//@NotNull
	Timestamp start;
	
	//@NotNull
	Timestamp end;
	
	int user_id;
	
	int duration;
	
	@NotNull
	String health_card;
	
	Timestamp modifiedOn;
	
	int modifiedById;
	
	public int getModifiedById() {
		return modifiedById;
	}
	public void setModifiedById(int modifiedById) {
		this.modifiedById = modifiedById;
	}
	String modifyType;
	
	int visitId;
	
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getModifyType() {
		return modifyType;
	}
	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}
	public int getVisitId() {
		return visitId;
	}
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	private transient boolean edit;
	
	public boolean getEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getSurgery() {
		return surgery;
	}
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getHealth_card() {
		return health_card;
	}
	public void setHealth_card(String health_card) {
		this.health_card = health_card;
	}

}

