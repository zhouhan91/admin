
package com.wemeCity.admin.catering.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.admin.catering.exception.CateringRestaurantException;
import com.wemeCity.admin.catering.model.CateringRestaurant;

/**
 * CateringRestaurantService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2018-2-4 新建
 */
public interface CateringRestaurantService {

	/**
	 * 新增cateringRestaurant
	 *
	 * @param cateringRestaurant
	 * @return 新增的对象
	 * @author 向小文 2018-2-4 新建
	 */
	void insertCateringRestaurant(CateringRestaurant cateringRestaurant) throws CateringRestaurantException;

	/**
	 * 删除cateringRestaurant
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2018-2-4 新建
	 */
	int deleteCateringRestaurant(long id) throws CateringRestaurantException;

	/**
	 * 修改cateringRestaurant
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2018-2-4 新建
	 */
	int updateCateringRestaurant(Map<String, Object> condition) throws CateringRestaurantException;

	/**
	 * 查询cateringRestaurant
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2018-2-4 新建
	 */
	CateringRestaurant readCateringRestaurant(long id) throws CateringRestaurantException;

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2018-2-4 新建
	 */
	List<CateringRestaurant> queryCateringRestaurantList(Map<String, Object> condition) ;

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2018-2-4 新建
	 */
	long queryCateringRestaurantCount(Map<String, Object> condition) throws CateringRestaurantException;

}