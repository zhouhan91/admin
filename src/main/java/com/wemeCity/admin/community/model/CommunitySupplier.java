
package com.wemeCity.admin.community.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CommunitySupplier实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-14 新建
 */
public class CommunitySupplier {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (name,VARCHAR(50), null)姓名 */
	private String name;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (contacts,VARCHAR(200), null)联系人 */
	private String contacts;

	/** (phone,VARCHAR(50), null)联系电话 */
	private String phone;

	/** (wechat,VARCHAR(50), null)微信号 */
	private String wechat;

	/** (email,VARCHAR(200), null)邮箱 */
	private String email;

	/** (is_deleted,VARCHAR(1), null)是否已删除 */
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

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public void setUserId(long userId){
		this.userId=userId;
	}

	public long getUserId(){
		return this.userId;
	}

	public void setContacts(String contacts){
		this.contacts=contacts;
	}

	public String getContacts(){
		return this.contacts;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setWechat(String wechat){
		this.wechat=wechat;
	}

	public String getWechat(){
		return this.wechat;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmail(){
		return this.email;
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