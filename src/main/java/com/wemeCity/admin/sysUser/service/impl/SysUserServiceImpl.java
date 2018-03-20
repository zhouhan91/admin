
package com.wemeCity.admin.sysUser.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.admin.sysUser.mapper.SysUserMapper;
import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.admin.sysUser.exception.SysUserException;
import com.wemeCity.admin.sysUser.service.SysUserService;
/**
 * SysUserServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-8-8 新建
 */
@Service
public class SysUserServiceImpl implements SysUserService{

	private Logger logger=LoggerFactory.getLogger(SysUserServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 新增sysUser
	 *
	 * @param sysUser
	 * @return 新增的对象
	 * @author 向小文 2017-8-8 新建
	 */
	@Override
	public void insertSysUser(SysUser sysUser) throws SysUserException{
		try{
			sysUserMapper.insertSysUser(sysUser);
		}catch(Exception e){
			logger.error("新增SysUser时报错", e);
			throw new SysUserException("新增SysUser时报错", e);
		}
	}

	/**
	 * 删除sysUser
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-8-8 新建
	 */
	@Override
	public int deleteSysUser(long id) throws SysUserException{
		try{
			return this.sysUserMapper.deleteSysUser(id);
		}catch(Exception e){
			logger.error("删除SysUser时报错", e);
			throw new SysUserException("删除SysUser时报错", e);
		}
	}

	/**
	 * 修改sysUser
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-8-8 新建
	 */
	@Override
	public int updateSysUser(Map<String, Object> condition) throws SysUserException{
		try{
			return this.sysUserMapper.updateSysUser(condition);
		}catch(Exception e){
			logger.error("修改SysUser时报错", e);
			throw new SysUserException("修改SysUser时报错", e);
		}
	}

	/**
	 * 查询sysUser
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-8-8 新建
	 */
	@Override
	public SysUser readSysUser(long id) throws SysUserException{
		try{
			return this.sysUserMapper.readSysUser(id);
		}catch(Exception e){
			logger.error("查询SysUser时报错", e);
			throw new SysUserException("查询SysUser时报错", e);
		}
	}

	/**
	 * 查询sysUser集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-8-8 新建
	 */
	@Override
	public List<SysUser> querySysUserList(Map<String, Object> condition){
		try{
			return this.sysUserMapper.querySysUserList(condition);
		}catch(Exception e){
			logger.error("查询SysUser时报错", e);
			return null;
		}
	}

	/**
	 * 查询sysUser集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-8-8 新建
	 */
	@Override
	public long querySysUserCount(Map<String, Object> condition) throws SysUserException{
		try{
			return this.sysUserMapper.querySysUserCount(condition);
		}catch(Exception e){
			logger.error("查询SysUser时报错", e);
			throw new SysUserException("查询SysUser时报错", e);
		}
	}

}