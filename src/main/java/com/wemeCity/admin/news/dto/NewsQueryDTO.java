package com.wemeCity.admin.news.dto;

public class NewsQueryDTO {

	/** 栏目编码 */
	private String navigationCode;

	/** 子栏目编码 */
	private String subNavigationCode;

	/** 标题 */
	private String title;

	/** 关键词 */
	private String keyWords;

	/** 状态 */
	private String status;

	public String getNavigationCode() {
		return navigationCode;
	}

	public void setNavigationCode(String navigationCode) {
		this.navigationCode = navigationCode;
	}

	public String getSubNavigationCode() {
		return subNavigationCode;
	}

	public void setSubNavigationCode(String subNavigationCode) {
		this.subNavigationCode = subNavigationCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
