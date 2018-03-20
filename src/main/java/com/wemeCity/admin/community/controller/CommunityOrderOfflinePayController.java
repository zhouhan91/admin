package com.wemeCity.admin.community.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.admin.community.model.CommunityOrderOfflinePay;
import com.wemeCity.admin.community.service.CommunityOrderOfflinePayService;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;

@Controller
@RequestMapping("/community/offLinePay")
public class CommunityOrderOfflinePayController extends BaseController {

	@Autowired
	private CommunityOrderOfflinePayService communityOrderOfflinePayService;

	@ResponseBody
	@GetMapping("/queryOrderOfflinePayList/{orderId}")
	public BaseDTO queryOrderOfflinePayList(@PathVariable long orderId) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("orderId", orderId);
		Page<CommunityOrderOfflinePay> page = PageHelper.startPage(1, 50).doSelectPage(() -> communityOrderOfflinePayService.queryCommunityOrderOfflinePayList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@PostMapping("/saveOrderOfflinePay")
	public BaseDTO saveOrderOfflinePay(@RequestBody CommunityOrderOfflinePay offlinePay, HttpSession session) {
		try {
			SysUser user = getCurUser(session);
			if (user == null) {
				logger.warn("保存线下付款明细失败：管理员未登录！offlinePay={}", GsonUtils.toSimpleJson(offlinePay));
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			if (offlinePay == null || offlinePay.getOrderId() <= 0 || BigDecimalUtils.compareTo(offlinePay.getAmount(), BigDecimal.ZERO) <= 0 || StringUtils.isEmpty(offlinePay.getPurpose()) || StringUtils.isEmpty(offlinePay.getPayType())) {
				logger.warn("保存线下付款明细失败：非空参数校验失败！offLinePay={}", GsonUtils.toSimpleJson(offlinePay));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			offlinePay.setIsDeleted(Constants.NO);
			offlinePay.setCreateBy(user.getId());
			offlinePay.setCreateName(user.getName());
			offlinePay.setCreateTime(LocalDateTime.now());
			communityOrderOfflinePayService.insertCommunityOrderOfflinePay(offlinePay);
			return new BaseDTO(RequestResultEnum.SUCCESS, offlinePay);
		} catch (Exception e) {
			logger.error("保存线下付款明细失败：服务器内部错误！offlinePay={}", GsonUtils.toSimpleJson(offlinePay));
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/deleteOrderOfflinePay/{id}")
	public BaseDTO deleteOrderOfflinePay(@PathVariable long id, HttpSession session) {
		try {
			SysUser user = getCurUser(session);
			if (user == null) {
				logger.warn("删除线下付款明细失败：管理员未登录！offlinePay={}", GsonUtils.toSimpleJson(id));
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", id);
			condition.put("isDeleted", Constants.YES);
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			communityOrderOfflinePayService.updateCommunityOrderOfflinePay(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("删除线下付款明细失败：服务器内部错误！id={}", id);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
