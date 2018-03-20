package com.wemeCity.admin.sysUser.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.admin.sysUser.service.SysUserService;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;

@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;

	@ResponseBody
	@PostMapping("/doLogin")
	public BaseDTO doLogin(@RequestBody SysUser sysUser, HttpSession session) throws Exception {
		BaseDTO result = null;
		logger.debug("login params: sysUser={}", GsonUtils.toSimpleJson(sysUser));
		if (sysUser == null || StringUtils.isEmpty(sysUser.getPhone()) || StringUtils.isEmpty(sysUser.getPassword())) {
			result = new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			logger.warn("login fail, not-null params validate fail, [result={}, sysUser={}]", new Object[] { GsonUtils.toSimpleJson(result), GsonUtils.toSimpleJson(sysUser) });
			return result;
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("phone", sysUser.getPhone());
		condition.put("password", StringUtils.sha1(Constants.SIGN + sysUser.getPassword()));
		condition.put("isDeleted", Constants.NO);
		List<SysUser> lstUser = sysUserService.querySysUserList(condition);
		if (CollectionUtils.isEmpty(lstUser)) {
			result = new BaseDTO(RequestResultEnum.WRONG_PHONE_OR_PASSWORD, null);
			logger.warn("login fail, wrong phone or password, [result={}, sysUser={}]", new Object[] { GsonUtils.toSimpleJson(result), GsonUtils.toSimpleJson(sysUser) });
			return result;
		}
		session.setAttribute(Constants.CURRENT_USER, lstUser.get(0));
		result = new BaseDTO(RequestResultEnum.SUCCESS, null);
		logger.debug("login success, sysUser={}", GsonUtils.toSimpleJson(sysUser));
		return result;
	}
	
	
	@ResponseBody
	@GetMapping("/signOut")
	public BaseDTO signOut(HttpSession session) throws Exception{
		session.removeAttribute(Constants.CURRENT_USER);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}
}
