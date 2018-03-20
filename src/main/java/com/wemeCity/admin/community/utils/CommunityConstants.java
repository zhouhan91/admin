package com.wemeCity.admin.community.utils;

public interface CommunityConstants {

	/** 订单状态-新建 */
	public static final String ORDER_STATUS_NEW = "10";
	/** 订单状态-支付中 */
	public static final String ORDER_STATUS_PAYING = "20";
	/** 订单状态-支付成功 */
	public static final String ORDER_STATUS_PAID = "30";
	/** 订单状态-支付失败 */
	public static final String ORDER_STATUS_PAY_FAIL = "40";
	/** 订单状态-处理中 */
	public static final String ORDER_STATUS_WORKING = "200";
	/** 订单状态-处理完成 */
	public static final String ORDER_STATUS_COMPLETE = "300";
	/** 订单状态-取消 */
	public static final String ORDER_STATUS_CANCEL = "400";

	/** 租期-月租 */
	public static final String ROOM_LEASE_MODEL_YUEZU = "yuezu";
	/** 租期-短租 */
	public static final String ROOM_LEASE_MODEL_DUANZU = "duanzu";
}
