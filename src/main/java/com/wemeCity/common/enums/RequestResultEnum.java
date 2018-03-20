package com.wemeCity.common.enums;

public enum RequestResultEnum {

	/** 处理成功 */
	SUCCESS("999999", "成功！"),
	/** 处理失败 */
	FAILURE("000000", "失败！"),
	/** 非空参数验证失败 */
	NOT_NULL_PARAM_ERROR("000001", "请填写完整资料！"),
	
	/**
	 * 管理用户端
	 */
	/** 管理员未找到！ */
	SYS_USER_NOT_FOUND("001001", "管理员未找到！"),
	/** 管理员未找到！ */
	WRONG_PHONE_OR_PASSWORD("001002", "用户名或密码错误！"),
	
	/**
	 * 短信端1001
	 */
	/** 短信业务编码错误 */
	WRONG_SMS_BUSI_CODE("100100", "短信业务编码错误！"),
	/** 短信图片验证码错误 */
	WRONG_SMS_IMAGE_CODE("101001", "图片验证码错误！"),
	
	/***文件上传段**/
	/** 文件写入失败 */
	FILE_WRITE_FAIL("100200", "文件上传失败！"),
	
	
	/**
	 * 租房
	 */
	/** 订单不存在！ */
	COMMUNITY_ORDER_NOT_FOUND("002001","订单不存在！"),
	/** 房源不存在！ */
	COMMUNITY_NOT_FOUND("002002","房源不存在！"),

	/**
	 * 美食板块
	 */
	/** 店铺不存在！*/
	RESTAURANT_NOT_FOUND("000301", "店铺不存在！"),
	/** 非法的支付方式 */
	INVALID_PAY_TYPE("000302", "非法的支付方式！"),
	/** 参数错误：商品与数量不匹配！ */
	INVALID_GOODS_COUNT("000303", "参数错误：商品与数量不匹配！"),
	/** 商品不存在！ */
	GOODS_NOT_FOUND("000304", "商品不存在！"),
	/** 配送点不存在！ */
	LOCATION_NOT_FOUND("000305", "配送点不存在！"),
	/** 联系人不存在！ */
	CONTACTS_NOT_FOUND("000306", "联系人不存在！"),
	/** 用户名已存在！ */
	USER_NAME_REPEAT("000307", "用户名已存在！"),
	/** 用户不存在！ */
	CATERING_MANAGER_NOT_FOUND("000309", "用户不存在！"),
	/** 商品分类不存在！ */
	GOODS_CATEGORY_NOT_FOUND("000312", "商品分类不存在！"),
	/** 商品分类不属于该店铺！ */
	GOODS_CATEGORY_NOT_MATCH("000312", "商品分类不属于该店铺！"),
	/** 订单不存在！ */
	CATERING_ORDER_NOT_FOUND("000313", "订单不存在！"),
	/** 订单状态异常！ */
	WRONG_CATERING_ORDER_STATUS("000314", "订单状态异常！"),
	/** 配送员不存在！ */
	CATERING_COURIER_NOT_FOUND("000315", "配送员不存在！"),
	/** 只允许评论一次！ */
	CATERING_ORDER_COMMENT_ONLY_ONCE("000316", "只允许评论一次！"),
	
	
	;
	
	private String key;

	private String description;

	private RequestResultEnum(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}
}
