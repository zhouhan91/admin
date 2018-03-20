
package com.wemeCity.admin.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.news.mapper.NewsCommentLikeMapper;
import com.wemeCity.admin.news.model.NewsCommentLike;
import com.wemeCity.admin.news.exception.NewsCommentLikeException;
import com.wemeCity.admin.news.service.NewsCommentLikeService;
/**
 * NewsCommentLikeServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
@Service
public class NewsCommentLikeServiceImpl implements NewsCommentLikeService{

	private Logger logger=LoggerFactory.getLogger(NewsCommentLikeServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsCommentLikeMapper newsCommentLikeMapper;

	/**
	 * 新增newsCommentLike
	 *
	 * @param newsCommentLike
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public void insertNewsCommentLike(NewsCommentLike newsCommentLike) throws NewsCommentLikeException{
		try{
			newsCommentLikeMapper.insertNewsCommentLike(newsCommentLike);
		}catch(Exception e){
			logger.error("新增NewsCommentLike时报错", e);
			throw new NewsCommentLikeException("新增NewsCommentLike时报错", e);
		}
	}

	/**
	 * 删除newsCommentLike
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int deleteNewsCommentLike(long id) throws NewsCommentLikeException{
		try{
			return this.newsCommentLikeMapper.deleteNewsCommentLike(id);
		}catch(Exception e){
			logger.error("删除NewsCommentLike时报错", e);
			throw new NewsCommentLikeException("删除NewsCommentLike时报错", e);
		}
	}

	/**
	 * 修改newsCommentLike
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int updateNewsCommentLike(Map<String, Object> condition) throws NewsCommentLikeException{
		try{
			return this.newsCommentLikeMapper.updateNewsCommentLike(condition);
		}catch(Exception e){
			logger.error("修改NewsCommentLike时报错", e);
			throw new NewsCommentLikeException("修改NewsCommentLike时报错", e);
		}
	}

	/**
	 * 查询newsCommentLike
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public NewsCommentLike readNewsCommentLike(long id) throws NewsCommentLikeException{
		try{
			return this.newsCommentLikeMapper.readNewsCommentLike(id);
		}catch(Exception e){
			logger.error("查询NewsCommentLike时报错", e);
			throw new NewsCommentLikeException("查询NewsCommentLike时报错", e);
		}
	}

	/**
	 * 查询newsCommentLike集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public List<NewsCommentLike> queryNewsCommentLikeList(Map<String, Object> condition){
		try{
			return this.newsCommentLikeMapper.queryNewsCommentLikeList(condition);
		}catch(Exception e){
			logger.error("查询NewsCommentLike时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsCommentLike集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public long queryNewsCommentLikeCount(Map<String, Object> condition) throws NewsCommentLikeException{
		try{
			return this.newsCommentLikeMapper.queryNewsCommentLikeCount(condition);
		}catch(Exception e){
			logger.error("查询NewsCommentLike时报错", e);
			throw new NewsCommentLikeException("查询NewsCommentLike时报错", e);
		}
	}

}