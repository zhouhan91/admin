
package com.wemeCity.components.dictionary.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Dictionary实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-22 新建
 */
public class Dictionary implements Serializable{
	
    private static final long serialVersionUID = 3363894400666273003L;

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (code,VARCHAR(50), null)编码 */
	private String code;

	/** (key,VARCHAR(50), null)key值 */
	private String key;

	/** (name,VARCHAR(50), null)中文名 */
	private String name;

	/** (description,VARCHAR(100), null)描述 */
	private String description;

	/** (remark,VARCHAR(100), null)备注 */
	private String remark;

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

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return this.code;
	}

	public void setKey(String key){
		this.key=key;
	}

	public String getKey(){
		return this.key;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setIsDeleted(String isDeleted){
		this.isDeleted=isDeleted;
	}

	public String getIsDeleted(){
		return this.isDeleted;
	}

	public void setCreateBy(long createBy){
		this.createBy=createBy;
	}

	public long getCreateBy(){
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime){
		this.createTime=createTime;
	}

	public LocalDateTime getCreateTime(){
		return this.createTime;
	}

	public void setModifyBy(long modifyBy){
		this.modifyBy=modifyBy;
	}

	public long getModifyBy(){
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime){
		this.modifyTime=modifyTime;
	}

	public LocalDateTime getModifyTime(){
		return this.modifyTime;
	}

}