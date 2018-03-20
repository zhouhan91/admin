
package com.wemeCity.admin.community.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CommunityOrderOfflinePay实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-16 新建
 */
public class CommunityOrderOfflinePay {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (order_id,BIGINT(20), null)订单id */
	private long orderId;

	/** (amount,DECIMAL(15,4), null)线下支付金额 */
	private BigDecimal amount;

	/** (purpose,VARCHAR(100), null)用途 */
	private String purpose;

	/** (pay_type,VARCHAR(20), null)支付方式(现金/微信转账/银行卡转账等) */
	private String payType;

	/** (is_deleted,VARCHAR(1), null)是否已删除(Y/N) */
	private String isDeleted;

	/** (create_by,BIGINT(20), null)操作人 */
	private long createBy;

	/** (create_name,VARCHAR(50), null)操作人姓名 */
	private String createName;

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

	public void setOrderId(long orderId){
		this.orderId=orderId;
	}

	public long getOrderId(){
		return this.orderId;
	}

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return this.amount;
	}

	public void setPurpose(String purpose){
		this.purpose=purpose;
	}

	public String getPurpose(){
		return this.purpose;
	}

	public void setPayType(String payType){
		this.payType=payType;
	}

	public String getPayType(){
		return this.payType;
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

	public void setCreateName(String createName){
		this.createName=createName;
	}

	public String getCreateName(){
		return this.createName;
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