package com.wemeCity.admin.community.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wemeCity.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.admin.community.enums.CommunityStatusEnum;
import com.wemeCity.admin.community.model.Community;
import com.wemeCity.admin.community.service.CommunityService;
import com.wemeCity.admin.community.service.CommunitySupplierService;
import com.wemeCity.admin.community.service.FacilitiesService;
import com.wemeCity.admin.region.service.CountryService;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.components.dictionary.service.DictionaryService;

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

	@Autowired
	private CommunityService communityService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CommunitySupplierService communitySupplierService;

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private FacilitiesService facilitiesService;

	@GetMapping("/list/{pageNum}")
	public String list(Community community, @PathVariable int pageNum, ModelMap modelMap) throws Exception {
 		logger.debug("Community list params: community={}, pageNum={}", GsonUtils.toSimpleJson(community), pageNum);
		Map<String, Object> condition = new HashMap<>();
		if(!StringUtils.isEmpty(community.getName())){
			condition.put("name",community.getName());
		}
		condition.put("isDeleted", Constants.NO);
		Page<Community> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> communityService.queryCommunityList(condition));
		modelMap.put("name",community.getName());
		modelMap.put("page", page);
		// 获取国家
		modelMap.put("lstCountry", countryService.queryAllCountryList());
		return "community/communityList";
	}

	@GetMapping("/initUpdate")
	public String initUpdate(int id, ModelMap modelMap) throws Exception {
		Community community = communityService.readCommunity(id);
		modelMap.put("community", community);
		modelMap.put("allSupplier", communitySupplierService.queryAllSupplierList());
		modelMap.put("allCommunitySourceType", dictionaryService.getDictionaryListGroup("communitySourceType"));
		modelMap.put("allCommunityType", dictionaryService.getDictionaryListGroup("communityType"));
		modelMap.put("allCommunityLeaseType", dictionaryService.getDictionaryListGroup("communityLeaseType"));
		modelMap.put("allCommunityDepositType", dictionaryService.getDictionaryListGroup("communityDepositType"));
		modelMap.put("allRoomLeaseModel", dictionaryService.getDictionaryListGroup("roomLeaseModel"));
		modelMap.put("allRoomType", dictionaryService.getDictionaryListGroup("roomType"));
		modelMap.put("allBathroomType", dictionaryService.getDictionaryListGroup("communityBathroomType"));
		modelMap.put("allRoomBedType", dictionaryService.getDictionaryListGroup("roomBedType"));
		// 设施
		modelMap.put("privateFacilities", facilitiesService.getFacilitiesList("private"));
		modelMap.put("commonFacilities", facilitiesService.getFacilitiesList("common"));
		modelMap.put("rentFacilities", facilitiesService.getFacilitiesList("rent"));
		// 国家
		modelMap.put("lstCountry", countryService.queryAllCountryList());
		return "community/communityEdit";
	}

	@GetMapping("/initInsert")
	public String initInsert(ModelMap modelMap) throws Exception {
		Community community = new Community();
		community.setCityCode("");
		community.setDistrictCode("");
		community.setTypeKey("");
		community.setLeaseTypeKey("");
		community.setDepositTypeKey("");
		community.setSourceTypeKey("");
		community.setLeaseModelKey("");
		community.setBathroomTypeKey("");
		community.setRoomTypeKey("");
		community.setPrivateFacilities("");
		community.setCommonFacilities("");
		community.setRentFacilities("");

		modelMap.put("community", community);
		modelMap.put("allSupplier", communitySupplierService.queryAllSupplierList());
		modelMap.put("allCommunitySourceType", dictionaryService.getDictionaryListGroup("communitySourceType"));
		modelMap.put("allCommunityType", dictionaryService.getDictionaryListGroup("communityType"));
		modelMap.put("allCommunityLeaseType", dictionaryService.getDictionaryListGroup("communityLeaseType"));
		modelMap.put("allCommunityDepositType", dictionaryService.getDictionaryListGroup("communityDepositType"));
		modelMap.put("allRoomLeaseModel", dictionaryService.getDictionaryListGroup("roomLeaseModel"));
		modelMap.put("allRoomType", dictionaryService.getDictionaryListGroup("roomType"));
		modelMap.put("allBathroomType", dictionaryService.getDictionaryListGroup("communityBathroomType"));
		// 设施
		modelMap.put("privateFacilities", facilitiesService.getFacilitiesList("private"));
		modelMap.put("commonFacilities", facilitiesService.getFacilitiesList("common"));
		modelMap.put("rentFacilities", facilitiesService.getFacilitiesList("rent"));
		// 国家
		modelMap.put("lstCountry", countryService.queryAllCountryList());
		return "community/communityEdit";
	}

	@ResponseBody
	@PostMapping("/save")
	public BaseDTO save(@RequestBody Community community, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		if (community.getId() <= 0) {
			community.setIsDeleted(Constants.NO);
			community.setCreateBy(user.getId());
			community.setCreateTime(LocalDateTime.now());
			communityService.insertCommunity(community);
		} else {
			Map<String, Object> condition = new HashMap<>(30);
			condition.put("id", community.getId());
			condition.put("coverPicture", community.getCoverPicture());
			condition.put("name", community.getName());
			condition.put("localName", community.getLocalName());
			condition.put("countryCode", community.getCountryCode());
			condition.put("countryName", community.getCountryName());
			condition.put("cityCode", community.getCityCode());
			condition.put("cityName", community.getCityName());
			condition.put("districtCode", community.getDistrictCode());
			condition.put("districtName", community.getDistrictName());
			condition.put("title", community.getTitle());
			condition.put("localTitle", community.getLocalTitle());
			condition.put("description", community.getDescription());
			condition.put("address", community.getAddress());
			condition.put("grade", community.getGrade());
			condition.put("typeKey", community.getTypeKey());
			condition.put("type", community.getType());
			condition.put("leaseModelKey", community.getLeaseModelKey());
			condition.put("leaseModel", community.getLeaseModel());
			condition.put("leaseTypeKey", community.getLeaseTypeKey());
			condition.put("leaseType", community.getLeaseType());
			condition.put("depositTypeKey", community.getDepositTypeKey());
			condition.put("depositType", community.getDepositType());
			condition.put("sourceTypeKey", community.getSourceTypeKey());
			condition.put("sourceType", community.getSourceType());
			condition.put("bathroomTypeKey", community.getBathroomTypeKey());
			condition.put("bathroomType", community.getBathroomType());
			condition.put("privateFacilities", community.getPrivateFacilities());
			condition.put("commonFacilities", community.getCommonFacilities());
			condition.put("rentFacilities", community.getRentFacilities());
			condition.put("owner", community.getOwner());
			condition.put("supplierId", community.getSupplierId());
			condition.put("supplierName", community.getSupplierName());
			condition.put("roomTypeKey", community.getRoomTypeKey());
			condition.put("roomType", community.getRoomType());
			condition.put("routeDescription", community.getRouteDescription());
			condition.put("advantage", community.getAdvantage());
			condition.put("tip", community.getTip());
			condition.put("longitude", community.getLongitude());
			condition.put("latitude", community.getLatitude());
			condition.put("videoUrl", community.getVideoUrl());
			condition.put("sortNum", community.getSortNum());
			condition.put("keyWords", community.getKeyWords());
			condition.put("ownerPhone", community.getOwnerPhone());
			condition.put("ownerWechat", community.getOwnerWechat());
			condition.put("ownerEmail", community.getOwnerEmail());
			communityService.updateCommunity(condition);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, community.getId());
	}

	@ResponseBody
	@PostMapping("/putOnLine")
	public BaseDTO putOnLine(long id, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>();
		condition.put("id", id);
		condition.put("status", CommunityStatusEnum.ON_LINE.getKey());
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		communityService.updateCommunity(condition);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/putOffLine")
	public BaseDTO putOffLine(long id, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>();
		condition.put("id", id);
		condition.put("status", CommunityStatusEnum.OFF_LINE.getKey());
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		communityService.updateCommunity(condition);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/delete")
	public BaseDTO delete(String idStr, HttpSession session) throws Exception {
		String[] arrId = idStr.split(",");
		for (String id : arrId) {
			communityService.deleteCommunity(Long.valueOf(id));
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}
}
