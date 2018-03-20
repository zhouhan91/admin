
package com.wemeCity.components.dictionary.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.components.dictionary.model.Dictionary;

/**
 * DictionaryMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
@Repository
public interface DictionaryMapper {

	/**
	 * 新增dictionary
	 *
	 * @param dictionary
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	void insertDictionary(Dictionary dictionary);

	/**
	 * 删除dictionary
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-9 新建
	 */
	int deleteDictionary(long id);

	/**
	 * 修改dictionary
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-9 新建
	 */
	int updateDictionary(Map<String, Object> condition);

	/**
	 * 查询dictionary
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	Dictionary readDictionary(long id);

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
	long queryDictionaryCount(Map<String, Object> condition);

}