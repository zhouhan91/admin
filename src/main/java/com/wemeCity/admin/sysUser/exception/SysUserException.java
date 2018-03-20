
package com.wemeCity.admin.sysUser.exception;

/**
 * SysUserException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-8-8 新建
 */
public class SysUserException extends Exception{

	private static final long serialVersionUID = 1L;

	public SysUserException(String message, Exception e){
		super(message, e);
	}

	public SysUserException(String message){
		super(message);
	}

}