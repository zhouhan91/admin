package com.wemeCity.admin.catering.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.admin.catering.dto.CateringOrderDTO;
import com.wemeCity.admin.catering.exception.CateringOrderException;
import com.wemeCity.admin.catering.model.CateringCourier;
import com.wemeCity.admin.catering.model.CateringOrder;
import com.wemeCity.admin.catering.model.CateringOrderDetail;
import com.wemeCity.admin.catering.service.CateringCourierService;
import com.wemeCity.admin.catering.service.CateringOrderDetailService;
import com.wemeCity.admin.catering.service.CateringOrderService;
import com.wemeCity.admin.catering.utils.CateringConstants;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/catering/order")
public class CateringOrderController extends BaseController{


	@Autowired
	private CateringOrderService cateringOrderService;

	@Autowired
	private CateringOrderDetailService cateringOrderDetailService;

	@Autowired
	private CateringCourierService cateringCourierService;

	@GetMapping("/list/{pageNum}")
	public String list(String dateStart, String dateEnd, String code, String phone, String restaurantName,
					   String status, String payType,@PathVariable int pageNum, ModelMap modelMap) {
		logger.debug("CateringOrder list params: pageNum={}");
		Map<String,Object> condition = new HashMap<>();
		if(!StringUtils.isEmpty(dateStart))
			condition.put("dateStart", dateStart);
		if(!StringUtils.isEmpty(dateEnd))
			condition.put("dateEnd",dateEnd);
		if(!StringUtils.isEmpty(code))
			condition.put("code",code);
		if(!StringUtils.isEmpty(phone))
			condition.put("phone",phone);
		if(!StringUtils.isEmpty(restaurantName))
			condition.put("restaurantName",restaurantName);
		if(!StringUtils.isEmpty(status))
			condition.put("status",status);
		if(!StringUtils.isEmpty(payType))
			condition.put("payType",payType);

		condition.put("isDeleted",Constants.NO);
		condition.put("sortColumn","create_time");
		condition.put("sortType","DESC");
		Page<CateringOrder> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringOrderService.queryCateringOrderDTOList(condition));
		modelMap.put("dateStart",dateStart);
		modelMap.put("dateEnd",dateEnd);
		modelMap.put("code",code);
		modelMap.put("phone",phone);
		modelMap.put("restaurantName",restaurantName);
		modelMap.put("status",status);
		modelMap.put("payType",payType);
		modelMap.put("page",page);
		return "catering/cateringOrderList";
	}

	@GetMapping("/initUpdate")
	public String initUpdate(Long id, ModelMap modelMap) {
		logger.debug("CateringOrder params: id={}");
		try {
			CateringOrder order=cateringOrderService.readCateringOrder(id);
			Map<String,Object> condition1 = new HashMap<>();
			condition1.put("orderId",order.getId());
			List<CateringOrderDetail> lstDetail = cateringOrderDetailService.queryCateringOrderList(condition1);
			order.setLstDetail(lstDetail);

			Map<String,Object> condition2 = new HashMap<>();
			condition2.put("restaurantId",order.getRestaurantId());
			List<CateringCourier> courierList = cateringCourierService.queryCateringCourierList(condition2);
			modelMap.put("courierList",courierList);
			modelMap.put("cateringOrder",order);
		} catch (CateringOrderException e) {
			logger.error("获取订单失败，服务器内部错误！id={}",id);
			e.printStackTrace();
		}
		return "catering/cateringOrderEdit";
	}

	@ResponseBody
	@PostMapping("/updateCourierId")
	public BaseDTO updateCourierId(Long id, Long courierId, HttpSession session) {
		try {
			SysUser user = getCurUser(session);
			if(user == null) {
				logger.warn("更新订单失败：管理员未登录！");
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			Map<String,Object> condition = new HashMap<>();
			CateringCourier courier = cateringCourierService.readCateringCourier(courierId);
			condition.put("id",id);
			condition.put("courierId",courierId);
			condition.put("courierName",courier.getName());
			condition.put("courierPhone",courier.getPhone());
			condition.put("modifyTime",ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			condition.put("modifyBy",user.getId());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		}catch (Exception e) {
			logger.error("更新订单失败：服务器内部错误！orderId={}", id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/cancelOrder")
	public BaseDTO cancelOrder(Long id, String cancelReason ,HttpSession session) {
		SysUser user = getCurUser(session);
		try {
			CateringOrder order = cateringOrderService.readCateringOrder(id);
			if(user ==null) {
				logger.warn("取消订单失败：管理员未登录！id={}", id);
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			if(order ==null) {
				logger.warn("取消订单失败：订单不存在！id={}", id);
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			Map<String, Object> condition = new HashMap<>(8);
			condition.put("id", id);
			condition.put("cancelReason", cancelReason);
			condition.put("status", CateringConstants.ORDER_STATUS_CANCEL);
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("取消我的订单失败：服务器内部错误！orderId={} ", id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

}
