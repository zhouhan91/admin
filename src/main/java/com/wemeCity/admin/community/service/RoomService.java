package com.wemeCity.admin.community.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.admin.community.exception.RoomException;
import com.wemeCity.admin.community.model.Room;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.dto.BaseDTO;

/**
 * RoomService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
public interface RoomService {

	/**
	 * 新增room
	 *
	 * @param room
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	void insertRoom(Room room) throws RoomException;

	/**
	 * 删除room
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	int deleteRoom(long id) throws RoomException;

	/**
	 * 修改room
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	int updateRoom(Map<String, Object> condition) throws RoomException;

	/**
	 * 查询room
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	Room readRoom(long id) throws RoomException;

	/**
	 * 查询room集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	List<Room> queryRoomList(Map<String, Object> condition);

	/**
	 * 查询room集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	long queryRoomCount(Map<String, Object> condition) throws RoomException;

	/**
	 * 根据房源查询房型
	 *
	 * @param communityId
	 * @return
	 * @throws RoomException
	 * @history 2017年10月21日 新建
	 * @auther xiaowen
	 */
	List<Room> queryRoomListByCommunityId(long communityId) throws RoomException;

	/**
	 * 删除房型
	 *
	 * @param arrId
	 * @param user
	 * @return
	 * @throws RoomException
	 * @Author Xiang xiaowen 2017年11月16日 新建
	 */
	BaseDTO deleteRooms(String[] arrId, SysUser user) throws RoomException;
	
	/**
	 * 操作房型时重新计算房源价格
	 *
	 * @param communityId
	 * @throws RoomException
	 * @history 2018年1月2日 新建
	 * @auther xiaowen
	 */
	void updateCommunityPrice(long communityId) throws RoomException;
}