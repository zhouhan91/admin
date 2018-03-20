
package com.wemeCity.admin.community.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.admin.community.exception.CommunityOrderOfflinePayException;
import com.wemeCity.admin.community.model.CommunityOrderOfflinePay;

/**
 * CommunityOrderOfflinePayService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-14 新建
 */
public interface CommunityOrderOfflinePayService {

	/**
	 * 新增communityOrderOfflinePay
	 *
	 * @param communityOrderOfflinePay
	 * @return 新增的对象
	 * @author 向小文 2017-12-14 新建
	 */
	void insertCommunityOrderOfflinePay(CommunityOrderOfflinePay communityOrderOfflinePay) throws CommunityOrderOfflinePayException;

	/**
	 * 删除communityOrderOfflinePay
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-14 新建
	 */
	int deleteCommunityOrderOfflinePay(long id) throws CommunityOrderOfflinePayException;

	/**
	 * 修改communityOrderOfflinePay
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-14 新建
	 */
	int updateCommunityOrderOfflinePay(Map<String, Object> condition) throws CommunityOrderOfflinePayException;

	/**
	 * 查询communityOrderOfflinePay
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-14 新建
	 */
	CommunityOrderOfflinePay readCommunityOrderOfflinePay(long id) throws CommunityOrderOfflinePayException;

	/**
	 * 查询communityOrderOfflinePay集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-14 新建
	 */
	List<CommunityOrderOfflinePay> queryCommunityOrderOfflinePayList(Map<String, Object> condition) ;

	/**
	 * 查询communityOrderOfflinePay集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-14 新建
	 */
	long queryCommunityOrderOfflinePayCount(Map<String, Object> condition) throws CommunityOrderOfflinePayException;

}