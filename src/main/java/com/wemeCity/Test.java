package com.wemeCity;

import java.util.HashMap;
import java.util.Map;

import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;

public class Test {

	public static void main(String[] args) {
		
		Map<String, Object> a=new HashMap<String, Object>();
		a.put("errno", 0);
		a.put("data", new String[]{"aaaa","bbbbb"});
		System.out.println(GsonUtils.toSimpleJson(a));
	}

}
