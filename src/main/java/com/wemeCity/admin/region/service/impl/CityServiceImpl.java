package com.wemeCity.admin.region.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.admin.region.exception.CityException;
import com.wemeCity.admin.region.mapper.CityMapper;
import com.wemeCity.admin.region.model.City;
import com.wemeCity.admin.region.service.CityService;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;

/**
 * CityServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-21 新建
 */
@Service
public class CityServiceImpl implements CityService {

	private Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CityMapper cityMapper;

	/**
	 * 新增city
	 *
	 * @param city
	 * @return 新增的对象
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public void insertCity(City city) throws CityException {
		try {
			cityMapper.insertCity(city);
		} catch (Exception e) {
			logger.error("新增City时报错", e);
			throw new CityException("新增City时报错", e);
		}
	}

	/**
	 * 删除city
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public int deleteCity(long id) throws CityException {
		try {
			return this.cityMapper.deleteCity(id);
		} catch (Exception e) {
			logger.error("删除City时报错", e);
			throw new CityException("删除City时报错", e);
		}
	}

	/**
	 * 修改city
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public int updateCity(Map<String, Object> condition) throws CityException {
		try {
			return this.cityMapper.updateCity(condition);
		} catch (Exception e) {
			logger.error("修改City时报错", e);
			throw new CityException("修改City时报错", e);
		}
	}

	/**
	 * 查询city
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public City readCity(long id) throws CityException {
		try {
			return this.cityMapper.readCity(id);
		} catch (Exception e) {
			logger.error("查询City时报错", e);
			throw new CityException("查询City时报错", e);
		}
	}

	/**
	 * 查询city集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public List<City> queryCityList(Map<String, Object> condition) {
		try {
			return this.cityMapper.queryCityList(condition);
		} catch (Exception e) {
			logger.error("查询City时报错", e);
			return null;
		}
	}

	/**
	 * 查询city集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public long queryCityCount(Map<String, Object> condition) throws CityException {
		try {
			return this.cityMapper.queryCityCount(condition);
		} catch (Exception e) {
			logger.error("查询City时报错", e);
			throw new CityException("查询City时报错", e);
		}
	}

	public void initCityCache() {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("isDeleted", Constants.NO);
			List<City> allCity = cityMapper.queryCityList(condition);
			if (CollectionUtils.isEmpty(allCity)) {
				logger.debug("无城市数据！");
				return;
			}
			EHCacheUtils.put("regionInfo", "allCity", allCity);
			Map<String, List<City>> mapCity = new HashMap<>();
			for (City city : allCity) {
				List<City> countryCity = mapCity.get(city.getCountryCode());
				if (CollectionUtils.isEmpty(countryCity)) {
					countryCity = new ArrayList<City>();
					mapCity.put(city.getCountryCode(), countryCity);
				}
				countryCity.add(city);
			}
			mapCity.forEach((k, v) -> {
				EHCacheUtils.put("regionInfo", "city-" + k, v);
			});
		} catch (Exception e) {
			logger.error("初始化城市缓存出错！", e);
		}
	}

	@Override
	public List<City> getCityListByCountryCode(String countryCode) throws CityException {
		Map<String, Object> condition=new HashMap<>(5);
		condition.put("countryCode", countryCode);
		condition.put("isDeleted", Constants.NO);
		return cityMapper.queryCityList(condition);
	}
}