
package com.wemeCity.admin.news.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.admin.news.exception.NewsContentException;
import com.wemeCity.admin.news.model.NewsContent;

/**
 * NewsContentService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
public interface NewsContentService {

	/**
	 * 新增newsContent
	 *
	 * @param newsContent
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	void insertNewsContent(NewsContent newsContent) throws NewsContentException;

	/**
	 * 删除newsContent
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	int deleteNewsContent(long id) throws NewsContentException;

	/**
	 * 修改newsContent
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	int updateNewsContent(Map<String, Object> condition) throws NewsContentException;

	/**
	 * 查询newsContent
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	NewsContent readNewsContent(long id) throws NewsContentException;

	/**
	 * 查询newsContent集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	List<NewsContent> queryNewsContentList(Map<String, Object> condition) ;

	/**
	 * 查询newsContent集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	long queryNewsContentCount(Map<String, Object> condition) throws NewsContentException;
	
	/**
	 * 根据新闻id读取新闻图文信息
	 *
	 * @param newsId
	 * @return
	 * @history 2017年11月19日 新建
	 * @auther xiaowen
	 */
	NewsContent readNewsContentByNewsId(long newsId);

}