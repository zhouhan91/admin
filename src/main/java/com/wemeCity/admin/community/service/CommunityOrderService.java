
package com.wemeCity.admin.community.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.admin.community.dto.CommunityOrderSaveDTO;
import com.wemeCity.admin.community.exception.CommunityOrderException;
import com.wemeCity.admin.community.model.CommunityOrder;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.dto.BaseDTO;

/**
 * CommunityOrderService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
public interface CommunityOrderService {

	/**
	 * 新增communityOrder
	 *
	 * @param communityOrder
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	void insertCommunityOrder(CommunityOrder communityOrder) throws CommunityOrderException;

	/**
	 * 删除communityOrder
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-9 新建
	 */
	int deleteCommunityOrder(long id) throws CommunityOrderException;

	/**
	 * 修改communityOrder
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-9 新建
	 */
	int updateCommunityOrder(Map<String, Object> condition) throws CommunityOrderException;

	/**
	 * 查询communityOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	CommunityOrder readCommunityOrder(long id) throws CommunityOrderException;

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	List<CommunityOrder> queryCommunityOrderList(Map<String, Object> condition) ;

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	long queryCommunityOrderCount(Map<String, Object> condition) throws CommunityOrderException;
	
	/**
	 * 保存订单
	 *
	 * @param saveDTO
	 * @param user
	 * @return
	 * @history 2017年12月16日 新建
	 * @auther xiaowen
	 */
	BaseDTO saveCommunityOrder(CommunityOrderSaveDTO saveDTO, SysUser user);

}