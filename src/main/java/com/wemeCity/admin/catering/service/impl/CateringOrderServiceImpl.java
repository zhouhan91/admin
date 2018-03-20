package com.wemeCity.admin.catering.service.impl;

import com.wemeCity.admin.catering.dto.CateringAllData;
import com.wemeCity.admin.catering.dto.CateringMonthData;
import com.wemeCity.admin.catering.dto.CateringOrderDTO;
import com.wemeCity.admin.catering.dto.TodayStatisticsInfo;
import com.wemeCity.admin.catering.exception.CateringOrderException;
import com.wemeCity.admin.catering.mapper.CateringOrderMapper;
import com.wemeCity.admin.catering.model.CateringOrder;
import com.wemeCity.admin.catering.service.CateringOrderService;
import com.wemeCity.common.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * CateringOrderServiceImpl AdminService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringOrderServiceImpl implements CateringOrderService {

	private Logger logger = LoggerFactory.getLogger(CateringOrderServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringOrderMapper cateringOrderMapper;

	/**
	 * 新增cateringOrder
	 *
	 * @param cateringOrder
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringOrder(CateringOrder cateringOrder) throws CateringOrderException {
		try {
			cateringOrderMapper.insertCateringOrder(cateringOrder);
		} catch (Exception e) {
			logger.error("新增CateringOrder时报错", e);
			throw new CateringOrderException("新增CateringOrder时报错", e);
		}
	}

	/**
	 * 删除cateringOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringOrder(long id) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.deleteCateringOrder(id);
		} catch (Exception e) {
			logger.error("删除CateringOrder时报错", e);
			throw new CateringOrderException("删除CateringOrder时报错", e);
		}
	}

	/**
	 * 修改cateringOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringOrder(Map<String, Object> condition) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.updateCateringOrder(condition);
		} catch (Exception e) {
			logger.error("修改CateringOrder时报错", e);
			throw new CateringOrderException("修改CateringOrder时报错", e);
		}
	}

	/**
	 * 查询cateringOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringOrder readCateringOrder(long id) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.readCateringOrder(id);
		} catch (Exception e) {
			logger.error("查询CateringOrder时报错", e);
			throw new CateringOrderException("查询CateringOrder时报错", e);
		}
	}

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringOrder> queryCateringOrderList(Map<String, Object> condition) {
		try {

			return this.cateringOrderMapper.queryCateringOrderList(condition);
		} catch (Exception e) {
			logger.error("查询CateringOrder时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringOrderCount(Map<String, Object> condition) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.queryCateringOrderCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringOrder时报错", e);
			throw new CateringOrderException("查询CateringOrder时报错", e);
		}
	}

	@Override
	public TodayStatisticsInfo queryTodayStatisticsInfo(Map<String, Object> condition) throws CateringOrderException {
		try {
			return cateringOrderMapper.queryTodayStatisticsInfo(condition);
		} catch (Exception e) {
			logger.error("统计当天订单数据失败：服务器内部错误！condition={}", GsonUtils.toSimpleJson(condition), e);
			throw new CateringOrderException("统计当天订单数据失败：服务器内部错误！", e);
		}
	}

	@Override
    public CateringAllData queryAllData(Map<String, Object> condition) {
	    return cateringOrderMapper.queryAllData(condition);
    }

	@Override
    public CateringMonthData queryMonthData(Map<String, Object> condition) {
	    return cateringOrderMapper.queryMonthData(condition);
    }

	@Override
	public List<CateringOrderDTO> queryCateringOrderDTOList(Map<String, Object> condition)  {
		return cateringOrderMapper.queryCateringOrderDTOList(condition);
	}
}