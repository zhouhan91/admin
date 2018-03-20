package com.wemeCity.admin.community.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.admin.community.enums.CommunityImgBusiCodeEnum;
import com.wemeCity.admin.community.enums.RoomStatusEnum;
import com.wemeCity.admin.community.exception.RoomException;
import com.wemeCity.admin.community.mapper.RoomMapper;
import com.wemeCity.admin.community.model.Room;
import com.wemeCity.admin.community.service.CommunityImgService;
import com.wemeCity.admin.community.service.CommunityService;
import com.wemeCity.admin.community.service.RoomService;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;

/**
 * RoomServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
@Service
public class RoomServiceImpl implements RoomService {

	private Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

	@Autowired
	private CommunityImgService communityImgService;

	@Autowired
	private CommunityService communityService;

	/** 数据访问接口 */
	@Autowired
	private RoomMapper roomMapper;

	/**
	 * 新增room
	 *
	 * @param room
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public void insertRoom(Room room) throws RoomException {
		try {
			roomMapper.insertRoom(room);
		} catch (Exception e) {
			logger.error("新增Room时报错", e);
			throw new RoomException("新增Room时报错", e);
		}
	}

	/**
	 * 删除room
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int deleteRoom(long id) throws RoomException {
		try {
			return this.roomMapper.deleteRoom(id);
		} catch (Exception e) {
			logger.error("删除Room时报错", e);
			throw new RoomException("删除Room时报错", e);
		}
	}

	/**
	 * 修改room
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int updateRoom(Map<String, Object> condition) throws RoomException {
		try {
			return this.roomMapper.updateRoom(condition);
		} catch (Exception e) {
			logger.error("修改Room时报错", e);
			throw new RoomException("修改Room时报错", e);
		}
	}

	/**
	 * 查询room
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public Room readRoom(long id) throws RoomException {
		try {
			return this.roomMapper.readRoom(id);
		} catch (Exception e) {
			logger.error("查询Room时报错", e);
			throw new RoomException("查询Room时报错", e);
		}
	}

	/**
	 * 查询room集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public List<Room> queryRoomList(Map<String, Object> condition) {
		try {
			return this.roomMapper.queryRoomList(condition);
		} catch (Exception e) {
			logger.error("查询Room时报错", e);
			return null;
		}
	}

	/**
	 * 查询room集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public long queryRoomCount(Map<String, Object> condition) throws RoomException {
		try {
			return this.roomMapper.queryRoomCount(condition);
		} catch (Exception e) {
			logger.error("查询Room时报错", e);
			throw new RoomException("查询Room时报错", e);
		}
	}

	@Override
	public List<Room> queryRoomListByCommunityId(long communityId) throws RoomException {
		try {
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("communityId", communityId);
			condition.put("isDeleted", Constants.NO);
			condition.put("status", RoomStatusEnum.ON_LINE.getKey());
			condition.put("sortColumn", "sort_num");
			condition.put("sortType", "asc");
			List<Room> lstRoom = roomMapper.queryRoomList(condition);
			for (Room room : lstRoom) {
				room.setImages(communityImgService.queryCommunityImgList(CommunityImgBusiCodeEnum.ROOM.getKey(), room.getId()));
			}
			return lstRoom;
		} catch (Exception e) {
			logger.error("读取社区房型出错, communityId={}", communityId, e);
			throw new RoomException("读取社区房型出错", e);
		}
	}

	@Override
	public BaseDTO deleteRooms(String[] arrId, SysUser user) throws RoomException {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			for (String id : arrId) {
				condition.put("id", id);
				condition.put("isDeleted", Constants.YES);
				roomMapper.updateRoom(condition);
			}
			Room room = roomMapper.readRoom(Long.valueOf(arrId[0]));
			this.updateCommunityPrice(room.getCommunityId());
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("删除房源出错：服务器内部错误！, arrId={}, user={}", arrId.toString(), GsonUtils.toSimpleJson(user), e);
			throw new RoomException("删除房源出错：服务器内部错误！", e);
		}
	}

	@Override
	public void updateCommunityPrice(long communityId) throws RoomException {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("isDeleted", Constants.NO);
			condition.put("communityId", communityId);
			condition.put("status", RoomStatusEnum.ON_LINE.getKey());
			List<Room> lstRoom = roomMapper.queryRoomList(condition);
			if (CollectionUtils.isEmpty(lstRoom)) {
				return;
			}
			Room minRoom = lstRoom.get(0);
			if (lstRoom.size() > 1) {
				// 比较最小
				for (int i = 1; i < lstRoom.size(); i++) {
					Room temp = lstRoom.get(i);
					if (BigDecimalUtils.compareTo(minRoom.getDiscountPrice(), temp.getDiscountPrice()) > 0) {
						minRoom = temp;
					}
				}
			}
			condition.clear();
			condition.put("id", communityId);
			condition.put("discountPrice", minRoom.getDiscountPrice());
			condition.put("price", minRoom.getPrice());
			communityService.updateCommunity(condition);
		} catch (Exception e) {
			logger.error("修改房源价格失败：服务器内部错误！");
			throw new RoomException("修改房源价格失败：服务器内部错误！", e);
		}
	}

}