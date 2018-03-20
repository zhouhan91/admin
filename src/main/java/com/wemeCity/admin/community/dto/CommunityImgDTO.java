package com.wemeCity.admin.community.dto;

public class CommunityImgDTO {

	/** 业务id */
	private long busiId;

	/** 业务编码 */
	private String busiCode;

	/** 路径 */
	private String path;
	
	/** 图片id */
	private long id;
	
	/** 排序号 */
	private int sortNum;

	public long getBusiId() {
		return busiId;
	}

	public void setBusiId(long busiId) {
		this.busiId = busiId;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

}
