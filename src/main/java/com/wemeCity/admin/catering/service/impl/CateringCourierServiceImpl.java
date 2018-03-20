package com.wemeCity.admin.catering.service.impl;

import com.wemeCity.admin.catering.exception.CateringCourierException;
import com.wemeCity.admin.catering.mapper.CateringCourierMapper;
import com.wemeCity.admin.catering.model.CateringCourier;
import com.wemeCity.admin.catering.service.CateringCourierService;
import com.wemeCity.admin.catering.service.CateringRestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * CateringCourierServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-25 新建
 */
@Service
public class CateringCourierServiceImpl implements CateringCourierService {

	private Logger logger = LoggerFactory.getLogger(CateringCourierServiceImpl.class);

	@Autowired
	private CateringRestaurantService cateringRestaurantService;


	/** 数据访问接口 */
	@Autowired
	private CateringCourierMapper cateringCourierMapper;

	/**
	 * 新增cateringCourier
	 *
	 * @param cateringCourier
	 * @return 新增的对象
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public void insertCateringCourier(CateringCourier cateringCourier) throws CateringCourierException {
		try {
			cateringCourierMapper.insertCateringCourier(cateringCourier);
		} catch (Exception e) {
			logger.error("新增CateringCourier时报错", e);
			throw new CateringCourierException("新增CateringCourier时报错", e);
		}
	}

	/**
	 * 删除cateringCourier
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public int deleteCateringCourier(long id) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.deleteCateringCourier(id);
		} catch (Exception e) {
			logger.error("删除CateringCourier时报错", e);
			throw new CateringCourierException("删除CateringCourier时报错", e);
		}
	}

	/**
	 * 修改cateringCourier
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public int updateCateringCourier(Map<String, Object> condition) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.updateCateringCourier(condition);
		} catch (Exception e) {
			logger.error("修改CateringCourier时报错", e);
			throw new CateringCourierException("修改CateringCourier时报错", e);
		}
	}

	/**
	 * 查询cateringCourier
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public CateringCourier readCateringCourier(long id) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.readCateringCourier(id);
		} catch (Exception e) {
			logger.error("查询CateringCourier时报错", e);
			throw new CateringCourierException("查询CateringCourier时报错", e);
		}
	}

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public List<CateringCourier> queryCateringCourierList(Map<String, Object> condition) {
		try {
			return this.cateringCourierMapper.queryCateringCourierList(condition);
		} catch (Exception e) {
			logger.error("查询CateringCourier时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public long queryCateringCourierCount(Map<String, Object> condition) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.queryCateringCourierCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringCourier时报错", e);
			throw new CateringCourierException("查询CateringCourier时报错", e);
		}
	}

}