package com.wemeCity.admin.community.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.admin.community.exception.FacilitiesException;
import com.wemeCity.admin.community.model.Facilities;

/**
 * FacilitiesService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
public interface FacilitiesService {

	/**
	 * 新增facilities
	 *
	 * @param facilities
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	void insertFacilities(Facilities facilities) throws FacilitiesException;

	/**
	 * 删除facilities
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	int deleteFacilities(long id) throws FacilitiesException;

	/**
	 * 修改facilities
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	int updateFacilities(Map<String, Object> condition) throws FacilitiesException;

	/**
	 * 查询facilities
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	Facilities readFacilities(long id) throws FacilitiesException;

	/**
	 * 查询facilities集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	List<Facilities> queryFacilitiesList(Map<String, Object> condition);

	/**
	 * 查询facilities集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	long queryFacilitiesCount(Map<String, Object> condition) throws FacilitiesException;

	/**
	 * 初始化设施
	 *
	 * @Author Xiang xiaowen 2017年11月13日 新建
	 */
	void initFacilitiesCache();
	
	/**
	 * 获取设施
	 *
	 * @param busiCode
	 * @return
	 * @throws FacilitiesException
	 * @Author Xiang xiaowen 2017年11月13日 新建
	 */
	List<Facilities> getFacilitiesList(String busiCode) throws FacilitiesException;

}