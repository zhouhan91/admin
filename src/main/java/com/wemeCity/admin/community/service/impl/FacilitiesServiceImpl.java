package com.wemeCity.admin.community.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.admin.community.exception.FacilitiesException;
import com.wemeCity.admin.community.mapper.FacilitiesMapper;
import com.wemeCity.admin.community.model.Facilities;
import com.wemeCity.admin.community.service.FacilitiesService;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;

/**
 * FacilitiesServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
@Service
public class FacilitiesServiceImpl implements FacilitiesService {

	private Logger logger = LoggerFactory.getLogger(FacilitiesServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private FacilitiesMapper facilitiesMapper;

	/**
	 * 新增facilities
	 *
	 * @param facilities
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public void insertFacilities(Facilities facilities) throws FacilitiesException {
		try {
			facilitiesMapper.insertFacilities(facilities);
		} catch (Exception e) {
			logger.error("新增Facilities时报错", e);
			throw new FacilitiesException("新增Facilities时报错", e);
		}
	}

	/**
	 * 删除facilities
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int deleteFacilities(long id) throws FacilitiesException {
		try {
			return this.facilitiesMapper.deleteFacilities(id);
		} catch (Exception e) {
			logger.error("删除Facilities时报错", e);
			throw new FacilitiesException("删除Facilities时报错", e);
		}
	}

	/**
	 * 修改facilities
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int updateFacilities(Map<String, Object> condition) throws FacilitiesException {
		try {
			return this.facilitiesMapper.updateFacilities(condition);
		} catch (Exception e) {
			logger.error("修改Facilities时报错", e);
			throw new FacilitiesException("修改Facilities时报错", e);
		}
	}

	/**
	 * 查询facilities
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public Facilities readFacilities(long id) throws FacilitiesException {
		try {
			return this.facilitiesMapper.readFacilities(id);
		} catch (Exception e) {
			logger.error("查询Facilities时报错", e);
			throw new FacilitiesException("查询Facilities时报错", e);
		}
	}

	/**
	 * 查询facilities集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public List<Facilities> queryFacilitiesList(Map<String, Object> condition) {
		try {
			return this.facilitiesMapper.queryFacilitiesList(condition);
		} catch (Exception e) {
			logger.error("查询Facilities时报错", e);
			return null;
		}
	}

	/**
	 * 查询facilities集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public long queryFacilitiesCount(Map<String, Object> condition) throws FacilitiesException {
		try {
			return this.facilitiesMapper.queryFacilitiesCount(condition);
		} catch (Exception e) {
			logger.error("查询Facilities时报错", e);
			throw new FacilitiesException("查询Facilities时报错", e);
		}
	}

	@Override
	public void initFacilitiesCache() {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("isDeleted", Constants.NO);
			List<Facilities> lstFacilities = facilitiesMapper.queryFacilitiesList(condition);
			if (CollectionUtils.isEmpty(lstFacilities)) {
				logger.debug("无任何设施需要初始化！");
				return;
			}
			Map<String, List<Facilities>> mapFacilities = new HashMap<>();
			for (Facilities facilities : lstFacilities) {
				List<Facilities> busiList = mapFacilities.get(facilities.getBusiCode());
				if (CollectionUtils.isEmpty(busiList)) {
					busiList = new ArrayList<>();
					mapFacilities.put(facilities.getBusiCode(), busiList);
				}
				busiList.add(facilities);
			}
			mapFacilities.forEach((k, v) -> {
				EHCacheUtils.put("facilities", k, v);
			});
			EHCacheUtils.put("facilities", "allFacilities", mapFacilities);
		} catch (Exception e) {
			logger.error("设施初始化入缓存失败！", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Facilities> getFacilitiesList(String busiCode) throws FacilitiesException {
		try {
			Object allFacilitiesInCache = EHCacheUtils.get("facilities", "allFacilities");
			if (allFacilitiesInCache == null) {
				initFacilitiesCache();
			}
			return (List<Facilities>) EHCacheUtils.get("facilities", busiCode);
		} catch (Exception e) {
			logger.error("获取设施时报错，busiCode={}", busiCode, e);
			throw new FacilitiesException("查询Facilities时报错", e);
		}
	}

}