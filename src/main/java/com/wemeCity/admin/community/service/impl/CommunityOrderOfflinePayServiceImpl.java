
package com.wemeCity.admin.community.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.community.mapper.CommunityOrderOfflinePayMapper;
import com.wemeCity.admin.community.model.CommunityOrderOfflinePay;
import com.wemeCity.admin.community.exception.CommunityOrderOfflinePayException;
import com.wemeCity.admin.community.service.CommunityOrderOfflinePayService;
/**
 * CommunityOrderOfflinePayServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-14 新建
 */
@Service
public class CommunityOrderOfflinePayServiceImpl implements CommunityOrderOfflinePayService{

	private Logger logger=LoggerFactory.getLogger(CommunityOrderOfflinePayServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CommunityOrderOfflinePayMapper communityOrderOfflinePayMapper;

	/**
	 * 新增communityOrderOfflinePay
	 *
	 * @param communityOrderOfflinePay
	 * @return 新增的对象
	 * @author 向小文 2017-12-14 新建
	 */
	@Override
	public void insertCommunityOrderOfflinePay(CommunityOrderOfflinePay communityOrderOfflinePay) throws CommunityOrderOfflinePayException{
		try{
			communityOrderOfflinePayMapper.insertCommunityOrderOfflinePay(communityOrderOfflinePay);
		}catch(Exception e){
			logger.error("新增CommunityOrderOfflinePay时报错", e);
			throw new CommunityOrderOfflinePayException("新增CommunityOrderOfflinePay时报错", e);
		}
	}

	/**
	 * 删除communityOrderOfflinePay
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-14 新建
	 */
	@Override
	public int deleteCommunityOrderOfflinePay(long id) throws CommunityOrderOfflinePayException{
		try{
			return this.communityOrderOfflinePayMapper.deleteCommunityOrderOfflinePay(id);
		}catch(Exception e){
			logger.error("删除CommunityOrderOfflinePay时报错", e);
			throw new CommunityOrderOfflinePayException("删除CommunityOrderOfflinePay时报错", e);
		}
	}

	/**
	 * 修改communityOrderOfflinePay
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-14 新建
	 */
	@Override
	public int updateCommunityOrderOfflinePay(Map<String, Object> condition) throws CommunityOrderOfflinePayException{
		try{
			return this.communityOrderOfflinePayMapper.updateCommunityOrderOfflinePay(condition);
		}catch(Exception e){
			logger.error("修改CommunityOrderOfflinePay时报错", e);
			throw new CommunityOrderOfflinePayException("修改CommunityOrderOfflinePay时报错", e);
		}
	}

	/**
	 * 查询communityOrderOfflinePay
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-14 新建
	 */
	@Override
	public CommunityOrderOfflinePay readCommunityOrderOfflinePay(long id) throws CommunityOrderOfflinePayException{
		try{
			return this.communityOrderOfflinePayMapper.readCommunityOrderOfflinePay(id);
		}catch(Exception e){
			logger.error("查询CommunityOrderOfflinePay时报错", e);
			throw new CommunityOrderOfflinePayException("查询CommunityOrderOfflinePay时报错", e);
		}
	}

	/**
	 * 查询communityOrderOfflinePay集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-14 新建
	 */
	@Override
	public List<CommunityOrderOfflinePay> queryCommunityOrderOfflinePayList(Map<String, Object> condition){
		try{
			return this.communityOrderOfflinePayMapper.queryCommunityOrderOfflinePayList(condition);
		}catch(Exception e){
			logger.error("查询CommunityOrderOfflinePay时报错", e);
			return null;
		}
	}

	/**
	 * 查询communityOrderOfflinePay集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-14 新建
	 */
	@Override
	public long queryCommunityOrderOfflinePayCount(Map<String, Object> condition) throws CommunityOrderOfflinePayException{
		try{
			return this.communityOrderOfflinePayMapper.queryCommunityOrderOfflinePayCount(condition);
		}catch(Exception e){
			logger.error("查询CommunityOrderOfflinePay时报错", e);
			throw new CommunityOrderOfflinePayException("查询CommunityOrderOfflinePay时报错", e);
		}
	}

}