package com.wemeCity.admin.community.controller;

import java.math.BigDecimal;
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

import com.wemeCity.admin.community.enums.CommunityImgBusiCodeEnum;
import com.wemeCity.admin.community.enums.RoomStatusEnum;
import com.wemeCity.admin.community.model.Room;
import com.wemeCity.admin.community.service.CommunityImgService;
import com.wemeCity.admin.community.service.RoomService;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.Constants;

@Controller
@RequestMapping("/room")
public class RoomController extends BaseController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private CommunityImgService communityImgService;

	@ResponseBody
	@GetMapping("/queryRoomList")
	public BaseDTO queryRoomList(long communityId) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("communityId", communityId);
		condition.put("sortColumn", "sort_num");
		condition.put("sortType", "asc");
		return new BaseDTO(RequestResultEnum.SUCCESS, roomService.queryRoomList(condition));
	}

	@ResponseBody
	@GetMapping("/readRoomInfo")
	public BaseDTO readRoomInfo(long id) throws Exception {
		Room room = roomService.readRoom(id);
		room.setImages(communityImgService.queryCommunityImgList(CommunityImgBusiCodeEnum.ROOM.getKey(), id));
		return new BaseDTO(RequestResultEnum.SUCCESS, room);
	}

	@ResponseBody
	@PostMapping("/deleteRoom")
	public BaseDTO deleteRoom(String idStr, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		return roomService.deleteRooms(idStr.split(","), user);
	}

	@ResponseBody
	@PostMapping("/saveUpdate")
	public BaseDTO saveUpdate(@RequestBody Room room, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>(20);
		condition.put("id", room.getId());
		condition.put("name", room.getName());
		condition.put("type", room.getType());
		condition.put("typeCode", room.getTypeCode());
		condition.put("price", room.getPrice());
		condition.put("discountPrice", room.getDiscountPrice());
		condition.put("area", room.getArea());
		condition.put("leaseModelKey", room.getLeaseModelKey());
		condition.put("leaseModel", room.getLeaseModel());
		condition.put("leaseLimit", room.getLeaseLimit());
		condition.put("leaseUnit", room.getLeaseUnit());
		condition.put("bedType", room.getBedType());
		condition.put("bedTypeCode", room.getBedTypeCode());
		condition.put("sortNum", room.getSortNum());
		condition.put("modifyBy", user.getId());
		condition.put("modifyTime", LocalDateTime.now());
		condition.put("payFlag", room.getPayFlag());
		condition.put("tipFlag", room.getTipFlag());
		condition.put("tipPrice", room.getTipPrice());
		condition.put("firstRentMonth", room.getFirstRentMonth());
		condition.put("depositPrice", room.getDepositPrice());
		condition.put("firstDepositMonth", room.getFirstDepositMonth());
		if (Constants.YES.equals(room.getPayFlag()) && Constants.NO.equals(room.getTipFlag())) {
			BigDecimal deposit = BigDecimalUtils.multiply(room.getDepositPrice(), new BigDecimal(room.getFirstDepositMonth()));
			BigDecimal rent = BigDecimalUtils.multiply(room.getDiscountPrice(), new BigDecimal(room.getFirstRentMonth()));
			condition.put("firstAmount", BigDecimalUtils.add(deposit, rent));
		}
		roomService.updateRoom(condition);
		roomService.updateCommunityPrice(room.getCommunityId());
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/saveInsert")
	public BaseDTO saveInsert(@RequestBody Room room, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		room.setIsDeleted(Constants.NO);
		room.setCreateBy(user.getId());
		room.setCreateTime(LocalDateTime.now());
		room.setStatus(RoomStatusEnum.NEW.getKey());
		if (Constants.YES.equals(room.getPayFlag()) && Constants.NO.equals(room.getTipFlag())) {
			BigDecimal deposit = BigDecimalUtils.multiply(room.getDepositPrice(), new BigDecimal(room.getFirstDepositMonth()));
			BigDecimal rent = BigDecimalUtils.multiply(room.getDiscountPrice(), new BigDecimal(room.getFirstRentMonth()));
			room.setFirstAmount(BigDecimalUtils.add(deposit, rent));
		}
		roomService.insertRoom(room);
		roomService.updateCommunityPrice(room.getCommunityId());
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/putOnLine")
	public BaseDTO putOnLine(String idStr, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>();
		String[] arrId = idStr.split(",");
		if(arrId.length<1){
			logger.warn("下架房型失败：非空参数校验失败！");
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		for (String id : arrId) {
			condition.put("id", Long.valueOf(id));
			condition.put("status", RoomStatusEnum.ON_LINE.getKey());
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			roomService.updateRoom(condition);
		}
		Room room = roomService.readRoom(Long.valueOf(arrId[0]));
		roomService.updateCommunityPrice(room.getCommunityId());
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@PostMapping("/putOffLine")
	public BaseDTO putOffLine(String idStr, HttpSession session) throws Exception {
		SysUser user = getCurUser(session);
		Map<String, Object> condition = new HashMap<>();
		String[] arrId = idStr.split(",");
		if(arrId.length<1){
			logger.warn("下架房型失败：非空参数校验失败！");
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		for (String id : arrId) {
			condition.put("id", Long.valueOf(id));
			condition.put("status", RoomStatusEnum.OFF_LINE.getKey());
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			roomService.updateRoom(condition);
		}
		Room room = roomService.readRoom(Long.valueOf(arrId[0]));
		roomService.updateCommunityPrice(room.getCommunityId());
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}
}
