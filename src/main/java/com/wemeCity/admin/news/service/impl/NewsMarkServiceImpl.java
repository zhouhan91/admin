
package com.wemeCity.admin.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.news.mapper.NewsMarkMapper;
import com.wemeCity.admin.news.model.NewsMark;
import com.wemeCity.admin.news.exception.NewsMarkException;
import com.wemeCity.admin.news.service.NewsMarkService;
/**
 * NewsMarkServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
@Service
public class NewsMarkServiceImpl implements NewsMarkService{

	private Logger logger=LoggerFactory.getLogger(NewsMarkServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsMarkMapper newsMarkMapper;

	/**
	 * 新增newsMark
	 *
	 * @param newsMark
	 * @return 新增的对象
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public void insertNewsMark(NewsMark newsMark) throws NewsMarkException{
		try{
			newsMarkMapper.insertNewsMark(newsMark);
		}catch(Exception e){
			logger.error("新增NewsMark时报错", e);
			throw new NewsMarkException("新增NewsMark时报错", e);
		}
	}

	/**
	 * 删除newsMark
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int deleteNewsMark(long id) throws NewsMarkException{
		try{
			return this.newsMarkMapper.deleteNewsMark(id);
		}catch(Exception e){
			logger.error("删除NewsMark时报错", e);
			throw new NewsMarkException("删除NewsMark时报错", e);
		}
	}

	/**
	 * 修改newsMark
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public int updateNewsMark(Map<String, Object> condition) throws NewsMarkException{
		try{
			return this.newsMarkMapper.updateNewsMark(condition);
		}catch(Exception e){
			logger.error("修改NewsMark时报错", e);
			throw new NewsMarkException("修改NewsMark时报错", e);
		}
	}

	/**
	 * 查询newsMark
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public NewsMark readNewsMark(long id) throws NewsMarkException{
		try{
			return this.newsMarkMapper.readNewsMark(id);
		}catch(Exception e){
			logger.error("查询NewsMark时报错", e);
			throw new NewsMarkException("查询NewsMark时报错", e);
		}
	}

	/**
	 * 查询newsMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public List<NewsMark> queryNewsMarkList(Map<String, Object> condition){
		try{
			return this.newsMarkMapper.queryNewsMarkList(condition);
		}catch(Exception e){
			logger.error("查询NewsMark时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-11-18 新建
	 */
	@Override
	public long queryNewsMarkCount(Map<String, Object> condition) throws NewsMarkException{
		try{
			return this.newsMarkMapper.queryNewsMarkCount(condition);
		}catch(Exception e){
			logger.error("查询NewsMark时报错", e);
			throw new NewsMarkException("查询NewsMark时报错", e);
		}
	}

}