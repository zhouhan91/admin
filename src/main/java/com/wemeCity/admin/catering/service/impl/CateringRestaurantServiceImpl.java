
package com.wemeCity.admin.catering.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.catering.mapper.CateringRestaurantMapper;
import com.wemeCity.admin.catering.model.CateringRestaurant;
import com.wemeCity.admin.catering.exception.CateringRestaurantException;
import com.wemeCity.admin.catering.service.CateringRestaurantService;
/**
 * CateringRestaurantServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2018-2-4 新建
 */
@Service
public class CateringRestaurantServiceImpl implements CateringRestaurantService{

	private Logger logger=LoggerFactory.getLogger(CateringRestaurantServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringRestaurantMapper cateringRestaurantMapper;

	/**
	 * 新增cateringRestaurant
	 *
	 * @param cateringRestaurant
	 * @return 新增的对象
	 * @author 向小文 2018-2-4 新建
	 */
	@Override
	public void insertCateringRestaurant(CateringRestaurant cateringRestaurant) throws CateringRestaurantException{
		try{
			cateringRestaurantMapper.insertCateringRestaurant(cateringRestaurant);
		}catch(Exception e){
			logger.error("新增CateringRestaurant时报错", e);
			throw new CateringRestaurantException("新增CateringRestaurant时报错", e);
		}
	}

	/**
	 * 删除cateringRestaurant
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2018-2-4 新建
	 */
	@Override
	public int deleteCateringRestaurant(long id) throws CateringRestaurantException{
		try{
			return this.cateringRestaurantMapper.deleteCateringRestaurant(id);
		}catch(Exception e){
			logger.error("删除CateringRestaurant时报错", e);
			throw new CateringRestaurantException("删除CateringRestaurant时报错", e);
		}
	}

	/**
	 * 修改cateringRestaurant
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2018-2-4 新建
	 */
	@Override
	public int updateCateringRestaurant(Map<String, Object> condition) throws CateringRestaurantException{
		try{
			return this.cateringRestaurantMapper.updateCateringRestaurant(condition);
		}catch(Exception e){
			logger.error("修改CateringRestaurant时报错", e);
			throw new CateringRestaurantException("修改CateringRestaurant时报错", e);
		}
	}

	/**
	 * 查询cateringRestaurant
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2018-2-4 新建
	 */
	@Override
	public CateringRestaurant readCateringRestaurant(long id) throws CateringRestaurantException{
		try{
			return this.cateringRestaurantMapper.readCateringRestaurant(id);
		}catch(Exception e){
			logger.error("查询CateringRestaurant时报错", e);
			throw new CateringRestaurantException("查询CateringRestaurant时报错", e);
		}
	}

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2018-2-4 新建
	 */
	@Override
	public List<CateringRestaurant> queryCateringRestaurantList(Map<String, Object> condition){
		try{
			return this.cateringRestaurantMapper.queryCateringRestaurantList(condition);
		}catch(Exception e){
			logger.error("查询CateringRestaurant时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2018-2-4 新建
	 */
	@Override
	public long queryCateringRestaurantCount(Map<String, Object> condition) throws CateringRestaurantException{
		try{
			return this.cateringRestaurantMapper.queryCateringRestaurantCount(condition);
		}catch(Exception e){
			logger.error("查询CateringRestaurant时报错", e);
			throw new CateringRestaurantException("查询CateringRestaurant时报错", e);
		}
	}

}