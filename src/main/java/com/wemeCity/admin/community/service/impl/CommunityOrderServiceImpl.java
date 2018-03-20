package com.wemeCity.admin.community.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.admin.community.dto.CommunityOrderSaveDTO;
import com.wemeCity.admin.community.enums.RoomStatusEnum;
import com.wemeCity.admin.community.exception.CommunityOrderException;
import com.wemeCity.admin.community.mapper.CommunityOrderMapper;
import com.wemeCity.admin.community.model.CommunityOrder;
import com.wemeCity.admin.community.model.Room;
import com.wemeCity.admin.community.service.CommunityOrderService;
import com.wemeCity.admin.community.service.RoomService;
import com.wemeCity.admin.community.utils.CommunityConstants;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;

/**
 * CommunityOrderServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
@Service
public class CommunityOrderServiceImpl implements CommunityOrderService {

	private Logger logger = LoggerFactory.getLogger(CommunityOrderServiceImpl.class);

	@Autowired
	private RoomService roomService;

	/** 数据访问接口 */
	@Autowired
	private CommunityOrderMapper communityOrderMapper;

	/**
	 * 新增communityOrder
	 *
	 * @param communityOrder
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public void insertCommunityOrder(CommunityOrder communityOrder) throws CommunityOrderException {
		try {
			communityOrderMapper.insertCommunityOrder(communityOrder);
		} catch (Exception e) {
			logger.error("新增CommunityOrder时报错", e);
			throw new CommunityOrderException("新增CommunityOrder时报错", e);
		}
	}

	/**
	 * 删除communityOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int deleteCommunityOrder(long id) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.deleteCommunityOrder(id);
		} catch (Exception e) {
			logger.error("删除CommunityOrder时报错", e);
			throw new CommunityOrderException("删除CommunityOrder时报错", e);
		}
	}

	/**
	 * 修改communityOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int updateCommunityOrder(Map<String, Object> condition) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.updateCommunityOrder(condition);
		} catch (Exception e) {
			logger.error("修改CommunityOrder时报错", e);
			throw new CommunityOrderException("修改CommunityOrder时报错", e);
		}
	}

	/**
	 * 查询communityOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public CommunityOrder readCommunityOrder(long id) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.readCommunityOrder(id);
		} catch (Exception e) {
			logger.error("查询CommunityOrder时报错", e);
			throw new CommunityOrderException("查询CommunityOrder时报错", e);
		}
	}

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public List<CommunityOrder> queryCommunityOrderList(Map<String, Object> condition) {
		try {
			return this.communityOrderMapper.queryCommunityOrderList(condition);
		} catch (Exception e) {
			logger.error("查询CommunityOrder时报错", e);
			return null;
		}
	}

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public long queryCommunityOrderCount(Map<String, Object> condition) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.queryCommunityOrderCount(condition);
		} catch (Exception e) {
			logger.error("查询CommunityOrder时报错", e);
			throw new CommunityOrderException("查询CommunityOrder时报错", e);
		}
	}

	@Override
	public BaseDTO saveCommunityOrder(CommunityOrderSaveDTO saveDTO, SysUser user) {
		try {
			// 修改订单信息
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", saveDTO.getId());
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			ConditionUtils.addStr(condition, "confirmFlag", saveDTO.getConfirmFlag());
			ConditionUtils.addStr(condition, "printContractFlag", saveDTO.getPrintContractFlag());
			ConditionUtils.addStr(condition, "signContractFlag", saveDTO.getSignContractFlag());
			ConditionUtils.addStr(condition, "completeFlag", saveDTO.getCompleteFlag());
			if (Constants.YES.equals(saveDTO.getCompleteFlag())) {
				condition.put("status", CommunityConstants.ORDER_STATUS_COMPLETE);
			}
			communityOrderMapper.updateCommunityOrder(condition);
			// 关房子
			if (Constants.YES.equals(saveDTO.getCloseFlag())) {
				condition.clear();
				CommunityOrder order = communityOrderMapper.readCommunityOrder(saveDTO.getId());
				Room room = roomService.readRoom(order.getRoomId());
				condition.clear();
				condition.put("id", room.getId());
				condition.put("status", RoomStatusEnum.OFF_LINE.getKey());
				condition.put("modifyBy", user.getId());
				condition.put("modifyTime", LocalDateTime.now());
				roomService.updateRoom(condition);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("保存订单失败：服务器内部错误！saveDTO={}, user={}", GsonUtils.toSimpleJson(saveDTO), GsonUtils.toSimpleJson(user));
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

}