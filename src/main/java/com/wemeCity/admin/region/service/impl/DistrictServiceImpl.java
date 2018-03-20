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

import com.wemeCity.admin.region.exception.DistrictException;
import com.wemeCity.admin.region.mapper.DistrictMapper;
import com.wemeCity.admin.region.model.District;
import com.wemeCity.admin.region.service.DistrictService;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;

/**
 * DistrictServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-21 新建
 */
@Service
public class DistrictServiceImpl implements DistrictService {

	private Logger logger = LoggerFactory.getLogger(DistrictServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private DistrictMapper districtMapper;

	/**
	 * 新增district
	 *
	 * @param district
	 * @return 新增的对象
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public void insertDistrict(District district) throws DistrictException {
		try {
			districtMapper.insertDistrict(district);
		} catch (Exception e) {
			logger.error("新增District时报错", e);
			throw new DistrictException("新增District时报错", e);
		}
	}

	/**
	 * 删除district
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public int deleteDistrict(long id) throws DistrictException {
		try {
			return this.districtMapper.deleteDistrict(id);
		} catch (Exception e) {
			logger.error("删除District时报错", e);
			throw new DistrictException("删除District时报错", e);
		}
	}

	/**
	 * 修改district
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public int updateDistrict(Map<String, Object> condition) throws DistrictException {
		try {
			return this.districtMapper.updateDistrict(condition);
		} catch (Exception e) {
			logger.error("修改District时报错", e);
			throw new DistrictException("修改District时报错", e);
		}
	}

	/**
	 * 查询district
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public District readDistrict(long id) throws DistrictException {
		try {
			return this.districtMapper.readDistrict(id);
		} catch (Exception e) {
			logger.error("查询District时报错", e);
			throw new DistrictException("查询District时报错", e);
		}
	}

	/**
	 * 查询district集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public List<District> queryDistrictList(Map<String, Object> condition) {
		try {
			return this.districtMapper.queryDistrictList(condition);
		} catch (Exception e) {
			logger.error("查询District时报错", e);
			return null;
		}
	}

	/**
	 * 查询district集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-21 新建
	 */
	@Override
	public long queryDistrictCount(Map<String, Object> condition) throws DistrictException {
		try {
			return this.districtMapper.queryDistrictCount(condition);
		} catch (Exception e) {
			logger.error("查询District时报错", e);
			throw new DistrictException("查询District时报错", e);
		}
	}

	@Override
	public void initDistrictCache() {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("isDeleted", Constants.NO);
			List<District> allDistrict = districtMapper.queryDistrictList(condition);
			if (CollectionUtils.isEmpty(allDistrict)) {
				logger.debug("无城市数据！");
				return;
			}
			EHCacheUtils.put("regionInfo", "allCity", allDistrict);
			Map<String, List<District>> mapDistrict = new HashMap<>();
			for (District district : allDistrict) {
				List<District> cityDistrict = mapDistrict.get(district.getCityCode());
				if (CollectionUtils.isEmpty(cityDistrict)) {
					cityDistrict = new ArrayList<District>();
					mapDistrict.put(district.getCityCode(), cityDistrict);
				}
				cityDistrict.add(district);
			}
			mapDistrict.forEach((k, v) -> {
				EHCacheUtils.put("regionInfo", "district-" + k, v);
			});
		} catch (Exception e) {
			logger.error("初始化区县缓存出错", e);
		}
	}

	@Override
	public List<District> getDistrictListByCityCode(String cityCode) throws DistrictException {
		Map<String, Object> condition=new HashMap<>(5);
		condition.put("cityCode", cityCode);
		condition.put("isDeleted", Constants.NO);
		return districtMapper.queryDistrictList(condition);
	}

}