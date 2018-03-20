package com.wemeCity.common.utils;

import java.util.Map;

public final class ConditionUtils {

	/**
	 * 添加字符串
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月11日 新建
	 */
	public static void addStr(Map<String, Object> condition, String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return;
		}
		condition.put(key, value);
	}

	/**
	 * 加integer
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月21日 新建
	 */
	public static void addInteger(Map<String, Object> condition, String key, Integer value) {
		if (StringUtils.isEmpty(key) || value<=0) {
			return ;
		}
		condition.put(key, value);
	}
}
