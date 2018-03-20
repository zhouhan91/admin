
package com.wemeCity.admin.sysUser.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.admin.sysUser.model.SysUser;

/**
 * SysUserMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-8-8 新建
 */
@Repository
public interface SysUserMapper {

	/**
	 * 新增sysUser
	 *
	 * @param sysUser
	 * @return 新增的对象
	 * @author 向小文 2017-8-8 新建
	 */
	void insertSysUser(SysUser sysUser);

	/**
	 * 删除sysUser
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-8-8 新建
	 */
	int deleteSysUser(long id);

	/**
	 * 修改sysUser
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-8-8 新建
	 */
	int updateSysUser(Map<String, Object> condition);

	/**
	 * 查询sysUser
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-8-8 新建
	 */
	SysUser readSysUser(long id);

	/**
	 * 查询sysUser集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-8-8 新建
	 */
	List<SysUser> querySysUserList(Map<String, Object> condition);

	/**
	 * 查询sysUser集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-8-8 新建
	 */
	long querySysUserCount(Map<String, Object> condition);

}