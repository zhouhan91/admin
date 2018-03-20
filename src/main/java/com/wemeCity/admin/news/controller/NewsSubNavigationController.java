package com.wemeCity.admin.news.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.admin.news.service.NewsSubNavigationService;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;

@Controller
@RequestMapping("/subNavigation")
public class NewsSubNavigationController extends BaseController {

	@Autowired
	private NewsSubNavigationService newsSubNavigationService;

	@ResponseBody
	@GetMapping("/getSubNavigation")
	public BaseDTO getSubNavigation(String navigationCode) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("parentCode", navigationCode);
		return new BaseDTO(RequestResultEnum.SUCCESS, newsSubNavigationService.queryNewsSubNavigationList(condition));
	}
}
