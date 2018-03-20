
package com.wemeCity.admin.news.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.admin.news.exception.NewsCommentLikeException;
import com.wemeCity.admin.news.model.NewsCommentLike;

/**
 * NewsCommentLikeService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
public interface NewsCommentLikeService {

	/**
	 * 新增newsCommentLike
	 *
	 * @param newsCommentLike
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	void insertNewsCommentLike(NewsCommentLike newsCommentLike) throws NewsCommentLikeException;

	/**
	 * 删除newsCommentLike
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	int deleteNewsCommentLike(long id) throws NewsCommentLikeException;

	/**
	 * 修改newsCommentLike
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	int updateNewsCommentLike(Map<String, Object> condition) throws NewsCommentLikeException;

	/**
	 * 查询newsCommentLike
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	NewsCommentLike readNewsCommentLike(long id) throws NewsCommentLikeException;

	/**
	 * 查询newsCommentLike集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	List<NewsCommentLike> queryNewsCommentLikeList(Map<String, Object> condition) ;

	/**
	 * 查询newsCommentLike集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	long queryNewsCommentLikeCount(Map<String, Object> condition) throws NewsCommentLikeException;

}