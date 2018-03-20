package com.wemeCity.admin.region.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.admin.region.model.District;
import com.wemeCity.admin.region.service.DistrictService;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;

@Controller
@RequestMapping("/district")
public class DistrictController extends BaseController {

	@Autowired
	private DistrictService districtService;

	@ResponseBody
	@GetMapping("/getDistrictListByCityCode/{cityCode}")
	public BaseDTO getDistrictListByCityCode(@PathVariable String cityCode) throws Exception {
		List<District> lstDistrict = districtService.getDistrictListByCityCode(cityCode);
		return new BaseDTO(RequestResultEnum.SUCCESS, lstDistrict);
	}
}
