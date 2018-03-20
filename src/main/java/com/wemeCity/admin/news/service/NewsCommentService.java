
package com.wemeCity.admin.news.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.admin.news.exception.NewsCommentException;
import com.wemeCity.admin.news.model.NewsComment;

/**
 * NewsCommentService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
public interface NewsCommentService {

	/**
	 * 新增newsComment
	 *
	 * @param newsComment
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	void insertNewsComment(NewsComment newsComment) throws NewsCommentException;

	/**
	 * 删除newsComment
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	int deleteNewsComment(long id) throws NewsCommentException;

	/**
	 * 修改newsComment
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	int updateNewsComment(Map<String, Object> condition) throws NewsCommentException;

	/**
	 * 查询newsComment
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	NewsComment readNewsComment(long id) throws NewsCommentException;

	/**
	 * 查询newsComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	List<NewsComment> queryNewsCommentList(Map<String, Object> condition) ;

	/**
	 * 查询newsComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	long queryNewsCommentCount(Map<String, Object> condition) throws NewsCommentException;

}