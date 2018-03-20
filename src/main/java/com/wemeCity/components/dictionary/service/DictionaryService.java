package com.wemeCity.components.dictionary.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.components.dictionary.exception.DictionaryException;
import com.wemeCity.components.dictionary.model.Dictionary;

/**
 * DictionaryService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
public interface DictionaryService {

	/**
	 * 新增dictionary
	 *
	 * @param dictionary
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	void insertDictionary(Dictionary dictionary) throws DictionaryException;

	/**
	 * 删除dictionary
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	int deleteDictionary(long id) throws DictionaryException;

	/**
	 * 修改dictionary
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-9 新建
	 */
	int updateDictionary(Map<String, Object> condition) throws DictionaryException;

	/**
	 * 查询dictionary
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	Dictionary readDictionary(long id) throws DictionaryException;

	/**
	 * 查询dictionary集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	List<Dictionary> queryDictionaryList(Map<String, Object> condition);

	/**
	 * 查询dictionary集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	long queryDictionaryCount(Map<String, Object> condition) throws DictionaryException;

	/**
	 * 初始化所有数据到缓存中
	 *
	 * @history 2017年10月22日 新建
	 * @auther xiaowen
	 */
	void initDictionary();

	/**
	 * 根据字典项编码获取字典项组
	 *
	 * @param code
	 * @return
	 * @throws DictionaryException
	 * @history 2017年10月22日 新建
	 * @auther xiaowen
	 */
	Map<String, Dictionary> getDictionaryMapGroup(String code) throws DictionaryException;

	/**
	 * 根据字典项编码和key获取字典项对象
	 *
	 * @param code
	 * @param key
	 * @return
	 * @throws DictionaryException
	 * @history 2017年10月22日 新建
	 * @auther xiaowen
	 */
	Dictionary getDictionary(String code, String key) throws DictionaryException;

	/**
	 * 获取list字典项组
	 *
	 * @param code
	 * @return
	 * @Author Xiang xiaowen 2017年10月31日 新建
	 */
	List<Dictionary> getDictionaryListGroup(String code);
}