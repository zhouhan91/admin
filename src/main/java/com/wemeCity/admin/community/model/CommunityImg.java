package com.wemeCity.admin.community.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CommunityImg实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-16 新建
 */
public class CommunityImg implements Serializable {

	private static final long serialVersionUID = 7370372430070699697L;

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (busi_id,BIGINT(20), null)关联业务id */
	private long busiId;

	/** (busi_code,VARCHAR(255), null)关联的业务(建筑/房型) */
	private String busiCode;

	/** (file_id,BIGINT(20), null)关联的文件id */
	private long fileId;

	/** (path,VARCHAR(500), null)文件地址 */
	private String path;

	/** (sort_num,INT(11), null)排序字段(升序) */
	private int sortNum;

	/** (is_deleted,VARCHAR(1), null)是否已删除(Y/N) */
	private String isDeleted;

	/** (create_by,BIGINT(20), null)创建人 */
	private long createBy;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** (modify_by,BIGINT(20), null)最后修改人 */
	private long modifyBy;

	/** (modify_time,DATETIME, null)最后修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setBusiId(long busiId) {
		this.busiId = busiId;
	}

	public long getBusiId() {
		return this.busiId;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getBusiCode() {
		return this.busiCode;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getFileId() {
		return this.fileId;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	public long getCreateBy() {
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setModifyBy(long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public LocalDateTime getModifyTime() {
		return this.modifyTime;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

}