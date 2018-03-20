package com.wemeCity.admin.community.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.admin.community.dto.CommunityImgDTO;
import com.wemeCity.admin.community.model.CommunityImg;
import com.wemeCity.admin.community.service.CommunityImgService;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;

@Controller
@RequestMapping("/communityImg")
public class CommunityImgController extends BaseController {

	@Autowired
	private CommunityImgService communityImgService;

	@ResponseBody
	@GetMapping("/queryCommunityImgList")
	public BaseDTO queryCommunityImgList(long busiId, String busiCode) throws Exception {
		return new BaseDTO(RequestResultEnum.SUCCESS, communityImgService.queryCommunityImgList(busiCode, busiId));
	}

	@ResponseBody
	@PostMapping("/addImg")
	public BaseDTO addImg(@RequestBody CommunityImgDTO dto, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		CommunityImg img = new CommunityImg();
		img.setBusiCode(dto.getBusiCode());
		img.setBusiId(dto.getBusiId());
		img.setIsDeleted(Constants.NO);
		img.setPath(dto.getPath());
		img.setSortNum(dto.getSortNum());
		img.setCreateBy(user.getId());
		img.setCreateTime(LocalDateTime.now());
		communityImgService.insertCommunityImg(img);
		return new BaseDTO(RequestResultEnum.SUCCESS, img);
	}

	@ResponseBody
	@PostMapping("/updateImg")
	public BaseDTO updateImg(@RequestBody CommunityImgDTO dto, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>();
		condition.put("id", dto.getId());
		condition.put("path", dto.getPath());
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		communityImgService.updateCommunityImg(condition);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/updateImgSort")
	public BaseDTO updateImgSort(String idStr, String sortNumStr, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		String[] arrId = idStr.split(",");
		String[] arrSortNum = sortNumStr.split(",");
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		for (int i = 0; i < arrId.length; i++) {
			condition.put("id", arrId[i]);
			condition.put("sortNum", arrSortNum[i]);
			communityImgService.updateCommunityImg(condition);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/deleteImg")
	public BaseDTO deleteImg(long id, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("id", id);
		condition.put("isDeleted", Constants.YES);
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		communityImgService.updateCommunityImg(condition);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}
}
