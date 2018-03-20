package com.wemeCity.admin.community.controller;

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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.admin.community.dto.CommunityOrderQueryDTO;
import com.wemeCity.admin.community.dto.CommunityOrderSaveDTO;
import com.wemeCity.admin.community.model.Community;
import com.wemeCity.admin.community.model.CommunityOrder;
import com.wemeCity.admin.community.model.CommunitySupplier;
import com.wemeCity.admin.community.service.CommunityOrderService;
import com.wemeCity.admin.community.service.CommunityService;
import com.wemeCity.admin.community.service.CommunitySupplierService;
import com.wemeCity.admin.community.service.RoomService;
import com.wemeCity.admin.community.utils.CommunityConstants;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;

@Controller
@RequestMapping("/community/order")
public class CommunityOrderController extends BaseController {

	@Autowired
	private CommunityOrderService communityOrderService;

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private RoomService roomService;

	@Autowired
	private CommunitySupplierService communitySupplierService;

	/** 查询状态-最新 */
	static final String QUERY_STATUS_NEW = "1";
	/** 查询状态-处理中 */
	static final String QUERY_STATUS_WORKING = "2";
	/** 查询状态-已完成 */
	static final String QUERY_STATUS_COMPLETE = "3";

	@ResponseBody
	@PostMapping("/queryCommunityOrderList")
	public BaseDTO queryCommunityOrderList(@RequestBody CommunityOrderQueryDTO queryDTO) {
		try {
			if (queryDTO == null || queryDTO.getPageNum() <= 0 || StringUtils.isEmpty(queryDTO.getStatus())) {
				logger.warn("查询租房订单失败：非空参数校验失败！queryDTO={}", GsonUtils.toSimpleJson(queryDTO));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			Map<String, Object> condition = new HashMap<>(10);
			ConditionUtils.addStr(condition, "realNameLike", queryDTO.getName());
			ConditionUtils.addStr(condition, "phoneLike", queryDTO.getPhone());
			condition.put("isDeleted", Constants.NO);
			if (QUERY_STATUS_NEW.equals(queryDTO.getStatus())) {
				condition.put("status", CommunityConstants.ORDER_STATUS_PAID);
				condition.put("sortColumn", "id");
				condition.put("sortType", "desc");
			} else if (QUERY_STATUS_WORKING.equals(queryDTO.getStatus())) {
				condition.put("arrStatus", new String[] { CommunityConstants.ORDER_STATUS_WORKING });
				condition.put("sortColumn", "modify_time");
				condition.put("sortType", "desc");
			} else if (QUERY_STATUS_COMPLETE.equals(queryDTO.getStatus())) {
				condition.put("status", CommunityConstants.ORDER_STATUS_COMPLETE);
				condition.put("sortColumn", "modify_time");
				condition.put("sortType", "desc");
			}
			Page<CommunityOrder> page = PageHelper.startPage(queryDTO.getPageNum(), Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> communityOrderService.queryCommunityOrderList(condition));
			return new PageDTO(RequestResultEnum.SUCCESS, page);
		} catch (Exception e) {
			logger.error("查询租房订单失败：服务器内部错误！queryDTO={}", GsonUtils.toSimpleJson(queryDTO), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/readCommunityOrderInfo/{id}")
	public BaseDTO readCommunityOrderInfo(@PathVariable long id, HttpSession session) {
		try {
			SysUser user = getCurUser(session);
			if (user == null) {
				logger.warn("读取租房订单详情失败：管理员未登录！id={}", id);
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			CommunityOrder order = communityOrderService.readCommunityOrder(id);
			if (order == null) {
				logger.warn("读取租房订单详情失败：未找到订单！id={}", id);
				return new BaseDTO(RequestResultEnum.COMMUNITY_ORDER_NOT_FOUND, null);
			}
			Community community = communityService.readCommunity(order.getCommunityId());
			if (community == null) {
				logger.warn("读取租房订单详情失败：未找到房源信息！id={}, order={}", id, GsonUtils.toSimpleJson(order));
				return new BaseDTO(RequestResultEnum.COMMUNITY_NOT_FOUND, null);
			}
			CommunitySupplier supplier = communitySupplierService.readCommunitySupplier(community.getSupplierId());
			order.setCommunity(community);
			order.setRoom(roomService.readRoom(order.getRoomId()));
			order.setSupplier(supplier);
			// 修改状态为待处理
			if (CommunityConstants.ORDER_STATUS_PAID.equals(order.getStatus())) {
				Map<String, Object> condition = new HashMap<>();
				condition.put("id", order.getId());
				condition.put("status", CommunityConstants.ORDER_STATUS_WORKING);
				condition.put("modifyBy", user.getId());
				condition.put("modifyTime", LocalDateTime.now());
				communityOrderService.updateCommunityOrder(condition);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, order);
		} catch (Exception e) {
			logger.error("读取租房订单详情失败：服务器内部错误！id={}", id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/saveCommunityOrder")
	public BaseDTO saveCommunityOrder(@RequestBody CommunityOrderSaveDTO saveDTO, HttpSession session) {
		try {
			SysUser user = getCurUser(session);
			if (user == null) {
				logger.warn("保存订单失败：管理员未登录！saveDTO={}", GsonUtils.toSimpleJson(saveDTO));
				return new BaseDTO(RequestResultEnum.SUCCESS, null);
			}
			if (saveDTO == null || saveDTO.getId() <= 0) {
				logger.warn("保存订单失败：非空参数校验失败！saveDTO={}, user={}", GsonUtils.toSimpleJson(saveDTO), GsonUtils.toSimpleJson(user));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			return communityOrderService.saveCommunityOrder(saveDTO, user);
		} catch (Exception e) {
			logger.error("保存订单失败：服务器内部错误！saveDTO={}", GsonUtils.toSimpleJson(saveDTO));
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}

	}

	@ResponseBody
	@PostMapping("/cancelOrder/{id}")
	public BaseDTO cancelOrder(@PathVariable long id, HttpSession session) {
		try {
			SysUser user = getCurUser(session);
			if (user == null) {
				logger.warn("取消订单失败：管理员未登录！id={}", id);
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", id);
			condition.put("status", CommunityConstants.ORDER_STATUS_CANCEL);
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			communityOrderService.updateCommunityOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("取消订单失败：服务器内部错误！id={}", id);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
