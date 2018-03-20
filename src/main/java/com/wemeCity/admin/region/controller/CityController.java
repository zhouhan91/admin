package com.wemeCity.admin.region.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.admin.region.model.City;
import com.wemeCity.admin.region.service.CityService;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;

@Controller
@RequestMapping("/city")
public class CityController extends BaseController {

	@Autowired
	private CityService cityService;

	@ResponseBody
	@GetMapping("/getCityListByCountryCode/{code}")
	public BaseDTO getCityListByCountryCode(@PathVariable String code) throws Exception {
		List<City> lstCity = cityService.getCityListByCountryCode(code);
		return new BaseDTO(RequestResultEnum.SUCCESS, lstCity);
	}
}
