package com.wemeCity.admin.catering.service;

import com.wemeCity.admin.catering.dto.CateringAllData;
import com.wemeCity.admin.catering.dto.CateringMonthData;
import com.wemeCity.admin.catering.dto.CateringOrderDTO;
import com.wemeCity.admin.catering.dto.TodayStatisticsInfo;
import com.wemeCity.admin.catering.exception.CateringOrderException;
import com.wemeCity.admin.catering.model.CateringOrder;

import java.util.List;
import java.util.Map;

/**
 * CateringOrderService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringOrderService {

	/**
	 * 新增cateringOrder
	 *
	 * @param cateringOrder
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringOrder(CateringOrder cateringOrder) throws CateringOrderException;

	/**
	 * 删除cateringOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringOrder(long id) throws CateringOrderException;

	/**
	 * 修改cateringOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringOrder(Map<String, Object> condition) throws CateringOrderException;

	/**
	 * 查询cateringOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringOrder readCateringOrder(long id) throws CateringOrderException;

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringOrder> queryCateringOrderList(Map<String, Object> condition);

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringOrderCount(Map<String, Object> condition) throws CateringOrderException;


	/**
	 * 统计今天的数据
	 *
	 * @param condition
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	TodayStatisticsInfo queryTodayStatisticsInfo(Map<String, Object> condition) throws CateringOrderException;

	
	/**
	 * 统计所有数据
	 *
	 * @param condition
	 * @return
	 * @history 2018年2月12日 新建
	 * @auther xiaowen
	 */
	CateringAllData queryAllData(Map<String, Object> condition);
	
	/**
	 * 统计本月数据
	 *
	 * @param condition
	 * @return
	 * @history 2018年2月12日 新建
	 * @auther xiaowen
	 */
	CateringMonthData queryMonthData(Map<String, Object> condition);

	/**
	 * 查询 CateringOrderDTO
	 *
	 * @param condition
	 * @return
	 */
	List<CateringOrderDTO> queryCateringOrderDTOList(Map<String, Object> condition);
}