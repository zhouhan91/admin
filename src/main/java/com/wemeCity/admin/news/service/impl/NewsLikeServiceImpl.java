
package com.wemeCity.admin.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.news.mapper.NewsLikeMapper;
import com.wemeCity.admin.news.model.NewsLike;
import com.wemeCity.admin.news.exception.NewsLikeException;
import com.wemeCity.admin.news.service.NewsLikeService;
/**
 * NewsLikeServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
@Service
public class NewsLikeServiceImpl implements NewsLikeService{

	private Logger logger=LoggerFactory.getLogger(NewsLikeServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsLikeMapper newsLikeMapper;

	/**
	 * 新增newsLike
	 *
	 * @param newsLike
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public void insertNewsLike(NewsLike newsLike) throws NewsLikeException{
		try{
			newsLikeMapper.insertNewsLike(newsLike);
		}catch(Exception e){
			logger.error("新增NewsLike时报错", e);
			throw new NewsLikeException("新增NewsLike时报错", e);
		}
	}

	/**
	 * 删除newsLike
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int deleteNewsLike(long id) throws NewsLikeException{
		try{
			return this.newsLikeMapper.deleteNewsLike(id);
		}catch(Exception e){
			logger.error("删除NewsLike时报错", e);
			throw new NewsLikeException("删除NewsLike时报错", e);
		}
	}

	/**
	 * 修改newsLike
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int updateNewsLike(Map<String, Object> condition) throws NewsLikeException{
		try{
			return this.newsLikeMapper.updateNewsLike(condition);
		}catch(Exception e){
			logger.error("修改NewsLike时报错", e);
			throw new NewsLikeException("修改NewsLike时报错", e);
		}
	}

	/**
	 * 查询newsLike
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public NewsLike readNewsLike(long id) throws NewsLikeException{
		try{
			return this.newsLikeMapper.readNewsLike(id);
		}catch(Exception e){
			logger.error("查询NewsLike时报错", e);
			throw new NewsLikeException("查询NewsLike时报错", e);
		}
	}

	/**
	 * 查询newsLike集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public List<NewsLike> queryNewsLikeList(Map<String, Object> condition){
		try{
			return this.newsLikeMapper.queryNewsLikeList(condition);
		}catch(Exception e){
			logger.error("查询NewsLike时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsLike集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public long queryNewsLikeCount(Map<String, Object> condition) throws NewsLikeException{
		try{
			return this.newsLikeMapper.queryNewsLikeCount(condition);
		}catch(Exception e){
			logger.error("查询NewsLike时报错", e);
			throw new NewsLikeException("查询NewsLike时报错", e);
		}
	}

}