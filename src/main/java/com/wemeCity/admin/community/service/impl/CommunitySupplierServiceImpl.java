package com.wemeCity.admin.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.admin.community.exception.CommunitySupplierException;
import com.wemeCity.admin.community.mapper.CommunitySupplierMapper;
import com.wemeCity.admin.community.model.CommunitySupplier;
import com.wemeCity.admin.community.service.CommunitySupplierService;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;

/**
 * CommunitySupplierServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-22 新建
 */
@Service
public class CommunitySupplierServiceImpl implements CommunitySupplierService {

	private Logger logger = LoggerFactory.getLogger(CommunitySupplierServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CommunitySupplierMapper communitySupplierMapper;

	/**
	 * 新增communitySupplier
	 *
	 * @param communitySupplier
	 * @return 新增的对象
	 * @author 向小文 2017-10-22 新建
	 */
	@Override
	public void insertCommunitySupplier(CommunitySupplier communitySupplier) throws CommunitySupplierException {
		try {
			communitySupplierMapper.insertCommunitySupplier(communitySupplier);
		} catch (Exception e) {
			logger.error("新增CommunitySupplier时报错", e);
			throw new CommunitySupplierException("新增CommunitySupplier时报错", e);
		}
	}

	/**
	 * 删除communitySupplier
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-22 新建
	 */
	@Override
	public int deleteCommunitySupplier(long id) throws CommunitySupplierException {
		try {
			return this.communitySupplierMapper.deleteCommunitySupplier(id);
		} catch (Exception e) {
			logger.error("删除CommunitySupplier时报错", e);
			throw new CommunitySupplierException("删除CommunitySupplier时报错", e);
		}
	}

	/**
	 * 修改communitySupplier
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-22 新建
	 */
	@Override
	public int updateCommunitySupplier(Map<String, Object> condition) throws CommunitySupplierException {
		try {
			return this.communitySupplierMapper.updateCommunitySupplier(condition);
		} catch (Exception e) {
			logger.error("修改CommunitySupplier时报错", e);
			throw new CommunitySupplierException("修改CommunitySupplier时报错", e);
		}
	}

	/**
	 * 查询communitySupplier
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-22 新建
	 */
	@Override
	public CommunitySupplier readCommunitySupplier(long id) throws CommunitySupplierException {
		try {
			return this.communitySupplierMapper.readCommunitySupplier(id);
		} catch (Exception e) {
			logger.error("查询CommunitySupplier时报错", e);
			throw new CommunitySupplierException("查询CommunitySupplier时报错", e);
		}
	}

	/**
	 * 查询communitySupplier集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-22 新建
	 */
	@Override
	public List<CommunitySupplier> queryCommunitySupplierList(Map<String, Object> condition) {
		try {
			return this.communitySupplierMapper.queryCommunitySupplierList(condition);
		} catch (Exception e) {
			logger.error("查询CommunitySupplier时报错", e);
			return null;
		}
	}

	/**
	 * 查询communitySupplier集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-22 新建
	 */
	@Override
	public long queryCommunitySupplierCount(Map<String, Object> condition) throws CommunitySupplierException {
		try {
			return this.communitySupplierMapper.queryCommunitySupplierCount(condition);
		} catch (Exception e) {
			logger.error("查询CommunitySupplier时报错", e);
			throw new CommunitySupplierException("查询CommunitySupplier时报错", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CommunitySupplier> queryAllSupplierList() {
		Object allSupplierListInCache = EHCacheUtils.get("dictionary", "allSupplierList");
		if (allSupplierListInCache != null) {
			return (List<CommunitySupplier>) allSupplierListInCache;
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		List<CommunitySupplier> allSupplierList = communitySupplierMapper.queryCommunitySupplierList(condition);
		if (!CollectionUtils.isEmpty(allSupplierList)) {
			EHCacheUtils.put("dictionary", "allSupplierList", allSupplierList);
		}
		return allSupplierList;
	}

}