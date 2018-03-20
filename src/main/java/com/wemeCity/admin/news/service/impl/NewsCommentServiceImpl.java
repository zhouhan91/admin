
package com.wemeCity.admin.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.news.mapper.NewsCommentMapper;
import com.wemeCity.admin.news.model.NewsComment;
import com.wemeCity.admin.news.exception.NewsCommentException;
import com.wemeCity.admin.news.service.NewsCommentService;
/**
 * NewsCommentServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
@Service
public class NewsCommentServiceImpl implements NewsCommentService{

	private Logger logger=LoggerFactory.getLogger(NewsCommentServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsCommentMapper newsCommentMapper;

	/**
	 * 新增newsComment
	 *
	 * @param newsComment
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public void insertNewsComment(NewsComment newsComment) throws NewsCommentException{
		try{
			newsCommentMapper.insertNewsComment(newsComment);
		}catch(Exception e){
			logger.error("新增NewsComment时报错", e);
			throw new NewsCommentException("新增NewsComment时报错", e);
		}
	}

	/**
	 * 删除newsComment
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int deleteNewsComment(long id) throws NewsCommentException{
		try{
			return this.newsCommentMapper.deleteNewsComment(id);
		}catch(Exception e){
			logger.error("删除NewsComment时报错", e);
			throw new NewsCommentException("删除NewsComment时报错", e);
		}
	}

	/**
	 * 修改newsComment
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int updateNewsComment(Map<String, Object> condition) throws NewsCommentException{
		try{
			return this.newsCommentMapper.updateNewsComment(condition);
		}catch(Exception e){
			logger.error("修改NewsComment时报错", e);
			throw new NewsCommentException("修改NewsComment时报错", e);
		}
	}

	/**
	 * 查询newsComment
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public NewsComment readNewsComment(long id) throws NewsCommentException{
		try{
			return this.newsCommentMapper.readNewsComment(id);
		}catch(Exception e){
			logger.error("查询NewsComment时报错", e);
			throw new NewsCommentException("查询NewsComment时报错", e);
		}
	}

	/**
	 * 查询newsComment集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public List<NewsComment> queryNewsCommentList(Map<String, Object> condition){
		try{
			return this.newsCommentMapper.queryNewsCommentList(condition);
		}catch(Exception e){
			logger.error("查询NewsComment时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsComment集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public long queryNewsCommentCount(Map<String, Object> condition) throws NewsCommentException{
		try{
			return this.newsCommentMapper.queryNewsCommentCount(condition);
		}catch(Exception e){
			logger.error("查询NewsComment时报错", e);
			throw new NewsCommentException("查询NewsComment时报错", e);
		}
	}

}