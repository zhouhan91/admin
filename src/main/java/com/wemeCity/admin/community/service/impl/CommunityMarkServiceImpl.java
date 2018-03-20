
package com.wemeCity.admin.community.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.community.mapper.CommunityMarkMapper;
import com.wemeCity.admin.community.model.CommunityMark;
import com.wemeCity.admin.community.exception.CommunityMarkException;
import com.wemeCity.admin.community.service.CommunityMarkService;
/**
 * CommunityMarkServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
@Service
public class CommunityMarkServiceImpl implements CommunityMarkService{

	private Logger logger=LoggerFactory.getLogger(CommunityMarkServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CommunityMarkMapper communityMarkMapper;

	/**
	 * 新增communityMark
	 *
	 * @param communityMark
	 * @return 新增的对象
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public void insertCommunityMark(CommunityMark communityMark) throws CommunityMarkException{
		try{
			communityMarkMapper.insertCommunityMark(communityMark);
		}catch(Exception e){
			logger.error("新增CommunityMark时报错", e);
			throw new CommunityMarkException("新增CommunityMark时报错", e);
		}
	}

	/**
	 * 删除communityMark
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int deleteCommunityMark(long id) throws CommunityMarkException{
		try{
			return this.communityMarkMapper.deleteCommunityMark(id);
		}catch(Exception e){
			logger.error("删除CommunityMark时报错", e);
			throw new CommunityMarkException("删除CommunityMark时报错", e);
		}
	}

	/**
	 * 修改communityMark
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public int updateCommunityMark(Map<String, Object> condition) throws CommunityMarkException{
		try{
			return this.communityMarkMapper.updateCommunityMark(condition);
		}catch(Exception e){
			logger.error("修改CommunityMark时报错", e);
			throw new CommunityMarkException("修改CommunityMark时报错", e);
		}
	}

	/**
	 * 查询communityMark
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public CommunityMark readCommunityMark(long id) throws CommunityMarkException{
		try{
			return this.communityMarkMapper.readCommunityMark(id);
		}catch(Exception e){
			logger.error("查询CommunityMark时报错", e);
			throw new CommunityMarkException("查询CommunityMark时报错", e);
		}
	}

	/**
	 * 查询communityMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public List<CommunityMark> queryCommunityMarkList(Map<String, Object> condition){
		try{
			return this.communityMarkMapper.queryCommunityMarkList(condition);
		}catch(Exception e){
			logger.error("查询CommunityMark时报错", e);
			return null;
		}
	}

	/**
	 * 查询communityMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-9 新建
	 */
	@Override
	public long queryCommunityMarkCount(Map<String, Object> condition) throws CommunityMarkException{
		try{
			return this.communityMarkMapper.queryCommunityMarkCount(condition);
		}catch(Exception e){
			logger.error("查询CommunityMark时报错", e);
			throw new CommunityMarkException("查询CommunityMark时报错", e);
		}
	}

}