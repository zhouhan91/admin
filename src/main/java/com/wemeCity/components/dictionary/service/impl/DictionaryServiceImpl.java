package com.wemeCity.components.dictionary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.components.dictionary.exception.DictionaryException;
import com.wemeCity.components.dictionary.mapper.DictionaryMapper;
import com.wemeCity.components.dictionary.model.Dictionary;
import com.wemeCity.components.dictionary.service.DictionaryService;

/**
 * DictionaryServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

	private Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private DictionaryMapper dictionaryMapper;

	/**
	 * 新增dictionary
	 *
	 * @param dictionary
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public void insertDictionary(Dictionary dictionary) throws DictionaryException {
		try {
			dictionaryMapper.insertDictionary(dictionary);
		} catch (Exception e) {
			logger.error("新增Dictionary时报错", e);
			throw new DictionaryException("新增Dictionary时报错", e);
		}
	}

	/**
	 * 删除dictionary
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int deleteDictionary(long id) throws DictionaryException {
		try {
			return this.dictionaryMapper.deleteDictionary(id);
		} catch (Exception e) {
			logger.error("删除Dictionary时报错", e);
			throw new DictionaryException("删除Dictionary时报错", e);
		}
	}

	/**
	 * 修改dictionary
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int updateDictionary(Map<String, Object> condition) throws DictionaryException {
		try {
			return this.dictionaryMapper.updateDictionary(condition);
		} catch (Exception e) {
			logger.error("修改Dictionary时报错", e);
			throw new DictionaryException("修改Dictionary时报错", e);
		}
	}

	/**
	 * 查询dictionary
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public Dictionary readDictionary(long id) throws DictionaryException {
		try {
			return this.dictionaryMapper.readDictionary(id);
		} catch (Exception e) {
			logger.error("查询Dictionary时报错", e);
			throw new DictionaryException("查询Dictionary时报错", e);
		}
	}

	/**
	 * 查询dictionary集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public List<Dictionary> queryDictionaryList(Map<String, Object> condition) {
		try {
			return this.dictionaryMapper.queryDictionaryList(condition);
		} catch (Exception e) {
			logger.error("查询Dictionary时报错", e);
			return null;
		}
	}

	/**
	 * 查询dictionary集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public long queryDictionaryCount(Map<String, Object> condition) throws DictionaryException {
		try {
			return this.dictionaryMapper.queryDictionaryCount(condition);
		} catch (Exception e) {
			logger.error("查询Dictionary时报错", e);
			throw new DictionaryException("查询Dictionary时报错", e);
		}
	}

	@Override
	public void initDictionary() {
		try {
			Map<String, Object> condition = new HashMap<>();
			List<Dictionary> lstDictionaries = dictionaryMapper.queryDictionaryList(condition);
			// 总数据map，按code分组list
			Map<String, List<Dictionary>> listGroup = new HashMap<>();
			// 总数据map，按code分组map
			Map<String, Map<String, Dictionary>> mapGroup = new HashMap<>();
			// 总数据，按code-key
			Map<String, Dictionary> allKey = new HashMap<>();
			for (Dictionary dictionary : lstDictionaries) {
				// 加入map组数据
				Map<String, Dictionary> subMapGroup = mapGroup.get(dictionary.getCode());
				if (subMapGroup == null) {
					subMapGroup = new HashMap<String, Dictionary>();
					mapGroup.put(dictionary.getCode(), subMapGroup);
				}
				subMapGroup.put(dictionary.getKey(), dictionary);
				// 加入list组数据
				List<Dictionary> subListGroup = listGroup.get(dictionary.getCode());
				if (subListGroup == null) {
					subListGroup = new ArrayList<>(10);
					listGroup.put(dictionary.getCode(), subListGroup);
				}
				subListGroup.add(dictionary);
				// 加入key数据
				allKey.put(dictionary.getCode() + "-" + dictionary.getKey(), dictionary);
			}
			EHCacheUtils.put("dictionary", "listGroup", listGroup);
			EHCacheUtils.put("dictionary", "mapGroup", mapGroup);
			EHCacheUtils.put("dictionary", "allKeys", allKey);
		} catch (Exception e) {
			logger.error("初始化字典项缓存失败：服务器内部错误！, e={}", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Dictionary> getDictionaryMapGroup(String code) throws DictionaryException {
		Object groupsInCache = EHCacheUtils.get("dictionary", "mapGroup");
		if (groupsInCache == null) {
			this.initDictionary();
		}
		groupsInCache = EHCacheUtils.get("dictionary", "mapGroup");
		if (groupsInCache == null) {
			return null;
		}
		Map<String, Map<String, Dictionary>> allGroups = (Map<String, Map<String, Dictionary>>) groupsInCache;
		return allGroups.get(code);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Dictionary getDictionary(String code, String key) throws DictionaryException {
		Object keysInCache = EHCacheUtils.get("dictionary", "allKeys");
		if (keysInCache == null) {
			this.initDictionary();
		}
		keysInCache = EHCacheUtils.get("dictionary", "allKeys");
		if (keysInCache == null) {
			return null;
		}
		Map<String, Dictionary> allKeys = (Map<String, Dictionary>) keysInCache;
		return allKeys.get(code + "-" + key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dictionary> getDictionaryListGroup(String code) {
		Object groupsInCache = EHCacheUtils.get("dictionary", "listGroup");
		if (groupsInCache == null) {
			this.initDictionary();
		}
		groupsInCache = EHCacheUtils.get("dictionary", "listGroup");
		if (groupsInCache == null) {
			return null;
		}
		Map<String, List<Dictionary>> listGroup = (Map<String, List<Dictionary>>) groupsInCache;
		return listGroup.get(code);
	}
}