package com.wemeCity.admin.community.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CommunityOrder实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-14 新建
 */
public class CommunityOrder {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (code,VARCHAR(32), null)订单编码 */
	private String code;

	/** (user_id,BIGINT(20), null)用户 */
	private long userId;

	/** (community_id,BIGINT(20), null) */
	private long communityId;

	/** (community_name,VARCHAR(200), null)公寓名称 */
	private String communityName;

	/** (community_type,VARCHAR(100), null)房屋类型(公寓、民宿等) */
	private String communityType;

	/** (community_img,VARCHAR(500), null)封面图片 */
	private String communityImg;

	/** (city_id,BIGINT(20), null)城市id */
	private long cityId;

	/** (city_name,VARCHAR(256), null)城市 */
	private String cityName;

	/** (district_id,BIGINT(20), null)区县id */
	private long districtId;

	/** (district_name,VARCHAR(256), null)区县名称 */
	private String districtName;

	/** (address,VARCHAR(1000), null)地址 */
	private String address;

	/** (room_id,BIGINT(20), null)房型id */
	private long roomId;

	/** (room_name,VARCHAR(200), null)房型名称 */
	private String roomName;

	/** (room_type,VARCHAR(200), null)房型(一房一厅、多人房等) */
	private String roomType;

	/** (in_date,DATE, null)入住日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inDate;

	/** (out_date,DATE, null)退房 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate outDate;

	/** (real_name,VARCHAR(200), null)入住人姓名 */
	private String realName;

	/** (phone,VARCHAR(50), null)联系电话 */
	private String phone;

	/** (email,VARCHAR(200), null)邮件 */
	private String email;

	/** (tip,DECIMAL(15,4), null)服务费 */
	private BigDecimal tip;

	/** (price,DECIMAL(15,4), null)房型价格 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)房型优惠价 */
	private BigDecimal discountPrice;

	/** (amount,DECIMAL(15,4), null)总金额 */
	private BigDecimal amount;

	/** (coupon,DECIMAL(15,4), null)优惠金额 */
	private BigDecimal coupon;

	/** (real_pay,DECIMAL(15,4), null)实际支付金额 */
	private BigDecimal realPay;

	/** (pay_status,VARCHAR(1), null)支付状态(1：新建，2：支付中，3：已支付，4：支付失败) */
	private String payStatus;

	/** (pay_type,VARCHAR(16), null)支付类型(微信：wechat, 支付宝：alipay) */
	private String payType;

	/** (status,VARCHAR(2), null)状态(10:新建，20:支付中，30:支付成功，40：支付失败，200：处理中，300：处理完成，400：取消) */
	private String status;

	/** (order_source,VARCHAR(16), null)订单来源(sdk:移动端，program:微信小程序) */
	private String orderSource;

	/** (confirm_flag,VARCHAR(1), null)是否已确认房(Y/N) */
	private String confirmFlag;

	/** (print_contract_flag,VARCHAR(1), null)是否出合同(Y/N) */
	private String printContractFlag;

	/** (sign_contract_flag,VARCHAR(1), null)是否签合同(Y/N) */
	private String signContractFlag;

	/** (complete_flag,VARCHAR(1), null)是否已完成(Y/N) */
	private String completeFlag;

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

	/** 房源 */
	private Community community;

	/** 房型对象 */
	private Room room;

	/** 房源供应商 */
	private CommunitySupplier supplier;

	/** 入住天数 */
	private long inDays;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public long getCommunityId() {
		return this.communityId;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityType(String communityType) {
		this.communityType = communityType;
	}

	public String getCommunityType() {
		return this.communityType;
	}

	public void setCommunityImg(String communityImg) {
		this.communityImg = communityImg;
	}

	public String getCommunityImg() {
		return this.communityImg;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public long getCityId() {
		return this.cityId;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public long getDistrictId() {
		return this.districtId;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getRoomId() {
		return this.roomId;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	public LocalDate getInDate() {
		return this.inDate;
	}

	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}

	public LocalDate getOutDate() {
		return this.outDate;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setTip(BigDecimal tip) {
		this.tip = tip;
	}

	public BigDecimal getTip() {
		return this.tip;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getDiscountPrice() {
		return this.discountPrice;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getCoupon() {
		return this.coupon;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	public BigDecimal getRealPay() {
		return this.realPay;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayStatus() {
		return this.payStatus;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderSource() {
		return this.orderSource;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getConfirmFlag() {
		return this.confirmFlag;
	}

	public void setPrintContractFlag(String printContractFlag) {
		this.printContractFlag = printContractFlag;
	}

	public String getPrintContractFlag() {
		return this.printContractFlag;
	}

	public void setSignContractFlag(String signContractFlag) {
		this.signContractFlag = signContractFlag;
	}

	public String getSignContractFlag() {
		return this.signContractFlag;
	}

	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}

	public String getCompleteFlag() {
		return this.completeFlag;
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

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public CommunitySupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(CommunitySupplier supplier) {
		this.supplier = supplier;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public long getInDays() {
		long days = this.inDate.until(this.outDate, ChronoUnit.DAYS);
		inDays = days <= 0 ? 1 : days;
		return inDays;
	}

	public void setInDays(long inDays) {
		this.inDays = inDays;
	}

}