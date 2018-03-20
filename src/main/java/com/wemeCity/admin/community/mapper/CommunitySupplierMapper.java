
package com.wemeCity.admin.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.admin.community.model.CommunitySupplier;

/**
 * CommunitySupplierMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-22 新建
 */
@Repository
public interface CommunitySupplierMapper {

	/**
	 * 新增communitySupplier
	 *
	 * @param communitySupplier
	 * @return 新增的对象
	 * @author 向小文 2017-10-22 新建
	 */
	void insertCommunitySupplier(CommunitySupplier communitySupplier);

	/**
	 * 删除communitySupplier
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-22 新建
	 */
	int deleteCommunitySupplier(long id);

	/**
	 * 修改communitySupplier
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-22 新建
	 */
	int updateCommunitySupplier(Map<String, Object> condition);

	/**
	 * 查询communitySupplier
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-22 新建
	 */
	CommunitySupplier readCommunitySupplier(long id);

	/**
	 * 查询communitySupplier集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-22 新建
	 */
	List<CommunitySupplier> queryCommunitySupplierList(Map<String, Object> condition);

	/**
	 * 查询communitySupplier集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-22 新建
	 */
	long queryCommunitySupplierCount(Map<String, Object> condition);

}