package com.wemeCity.admin.catering.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.wemeCity.admin.catering.utils.CateringConstants;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.admin.catering.model.CateringRestaurant;
import com.wemeCity.admin.catering.service.CateringRestaurantService;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/catering/restaurant")
public class CateringRestaurantController extends BaseController{

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@GetMapping("/list/{pageNum}")
	public String list(@PathVariable int pageNum, String name, String status, ModelMap modelMap) {
		Map<String, Object> condition = new HashMap<>();
		ConditionUtils.addStr(condition, "nameLike", name);
		ConditionUtils.addStr(condition, "status", status);
		Page<CateringRestaurant> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringRestaurantService.queryCateringRestaurantList(condition));
		modelMap.put("page", page);
		return "catering/cateringRestaurantList";
	}

	@GetMapping("/reviewList/{pageNum}")
	public String reviewList(@PathVariable int pageNum, ModelMap modelMap) {
		Map<String, Object> condition = new HashMap<>();
		ConditionUtils.addStr(condition, "status", CateringConstants.RESTAURANT_STATUS_NEW);
		ConditionUtils.addStr(condition, "isDeleted", Constants.NO);
		ConditionUtils.addStr(condition, "sortColumn", "create_time");
		Page<CateringRestaurant> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringRestaurantService.queryCateringRestaurantList(condition));
		modelMap.put("page", page);
		return "catering/cateringRestaurantReviewList";
	}

	@ResponseBody
	@GetMapping("/auditPass/{id}")
	public BaseDTO auditPass(@PathVariable long id, HttpSession session){
		try{
			SysUser user = getCurUser(session);
			if(user==null){
				logger.warn("更改状态失败，管理员账户未登录！id={}",id);
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(id);
			if(restaurant!=null){
				Map<String,Object> condition = new HashMap<>();
				condition.put("id",restaurant.getId());
				condition.put("status",CateringConstants.RESTAURANT_STATUS_AUDITED);
				condition.put("modifyTime",ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime().toString());
				condition.put("modifyBy",user.getId());
				cateringRestaurantService.updateCateringRestaurant(condition);
			}else{
				logger.warn("店铺不存在！id={}",id);
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND,null);
			}
		}catch (Exception e){
			logger.error("服务器内部错误，id={}",id,e);
			return new BaseDTO(RequestResultEnum.FAILURE,null);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS,null);
	}

	@ResponseBody
	@GetMapping("/auditDeny/{id}")
	public BaseDTO auditDeny(@PathVariable long id, HttpSession session){
		try{
			SysUser user = getCurUser(session);
			if(user==null){
				logger.warn("更改状态失败，管理员账户未登录！id={}",id);
				return new BaseDTO(RequestResultEnum.SYS_USER_NOT_FOUND, null);
			}
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(id);
			if(restaurant!=null){
				Map<String,Object> condition = new HashMap<>();
				condition.put("id",restaurant.getId());
				condition.put("status",CateringConstants.RESTAURANT_STATUS_CANCEL);
				condition.put("modifyTime",ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime().toString());
				condition.put("modifyBy",user.getId());
				cateringRestaurantService.updateCateringRestaurant(condition);
			}else{
				logger.warn("店铺不存在！id={}",id);
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND,null);
			}
		}catch (Exception e){
			logger.error("服务器内部错误，id={}",id,e);
			return new BaseDTO(RequestResultEnum.FAILURE,null);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS,null);
	}
}
