package com.wemeCity.common.utils;

public interface Constants {

	/** session中放置当前用户的session key */
	public static final String CURRENT_USER = "currentUser";

	public static final String YES = "Y";

	public static final String NO = "N";

	public static final String SIGN = "weme5201314";

	/** 默认每页显示行数 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/** 支付状态 - 未支付 */
	public static final String PAY_STATUS_NEW = "1";

	/** 支付状态 - 支付中 */
	public static final String PAY_STATUS_PAYING = "2";

	/** 支付状态 - 已支付 */
	public static final String PAY_STATUS_PAID = "3";

	/** 支付状态 - 支付失败 */
	public static final String PAY_STATUS_PAY_FAIL = "4";
}
