package com.ece356.domain;

public class ReportsTo extends BaseEntity {

	int mangagedId;
	String managesId;
	
	public int getMangagedId() {
		return mangagedId;
	}
	public void setMangagedId(int mangagedId) {
		this.mangagedId = mangagedId;
	}
	public String getManagesId() {
		return managesId;
	}
	public void setManagesId(String managesId) {
		this.managesId = managesId;
	}
	

}

