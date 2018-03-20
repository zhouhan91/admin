package com.wemeCity.admin.news.dto;

import java.util.List;

import com.wemeCity.admin.news.model.NewsBabieta;

public class BabietaSaveDTO {

	private long id;

	private String coverPicture;

	private String title;

	private List<NewsBabieta> lstBabieta;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<NewsBabieta> getLstBabieta() {
		return lstBabieta;
	}

	public void setLstBabieta(List<NewsBabieta> lstBabieta) {
		this.lstBabieta = lstBabieta;
	}

}
