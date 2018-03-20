package com.wemeCity.admin.community.dto;

public class CommunityOrderQueryDTO {

	/** 状态 */
	private String status;

	/** 入住人姓名 */
	private String name;

	/** 入住人电话 */
	private String phone;

	/** 页码 */
	private int pageNum;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
