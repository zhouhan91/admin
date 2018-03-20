
package com.wemeCity.components.file.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * FileUpload实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-8-8 新建
 */
public class FileUpload {
	/** (id,BIGINT(20),not null)主键 */
	private long id;

	/** (busi_code,VARCHAR(20), null)业务类型 */
	private String busiCode;

	/** (file_name,VARCHAR(200), null)文件名 */
	private String fileName;

	/** (save_name,VARCHAR(200), null)上传后重新命名 */
	private String saveName;

	/** (path,VARCHAR(200), null)上传路径 */
	private String path;

	/** (is_deleted,VARCHAR(1), null)是否已删除(Y/N) */
	private String isDeleted;

	/** (create_by,VARCHAR(32), null)创建人 */
	private String createBy;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setBusiCode(String busiCode){
		this.busiCode=busiCode;
	}

	public String getBusiCode(){
		return this.busiCode;
	}

	public void setFileName(String fileName){
		this.fileName=fileName;
	}

	public String getFileName(){
		return this.fileName;
	}

	public void setSaveName(String saveName){
		this.saveName=saveName;
	}

	public String getSaveName(){
		return this.saveName;
	}

	public void setPath(String path){
		this.path=path;
	}

	public String getPath(){
		return this.path;
	}

	public void setIsDeleted(String isDeleted){
		this.isDeleted=isDeleted;
	}

	public String getIsDeleted(){
		return this.isDeleted;
	}

	public void setCreateBy(String createBy){
		this.createBy=createBy;
	}

	public String getCreateBy(){
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime){
		this.createTime=createTime;
	}

	public LocalDateTime getCreateTime(){
		return this.createTime;
	}

}