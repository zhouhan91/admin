package com.wemeCity.common.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.utils.Constants;

public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 获取当前用户
	 *
	 * @param session
	 * @return
	 * @Author Xiang xiaowen 2017年8月8日 新建
	 */
	public SysUser getCurUser(HttpSession session) {
		return (SysUser) session.getAttribute(Constants.CURRENT_USER);
	}
}
