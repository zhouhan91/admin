
package com.wemeCity.admin.sysUser.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * SysUser实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-8-8 新建
 */
public class SysUser {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (phone,VARCHAR(20), null)手机号码 */
	private String phone;

	/** (password,VARCHAR(50), null)密码 */
	private String password;

	/** (name,VARCHAR(20), null)姓名 */
	private String name;

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

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
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