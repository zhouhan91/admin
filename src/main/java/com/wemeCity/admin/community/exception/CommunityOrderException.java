
package com.wemeCity.admin.community.exception;

/**
 * CommunityOrderException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
public class CommunityOrderException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommunityOrderException(String message, Exception e){
		super(message, e);
	}

	public CommunityOrderException(String message){
		super(message);
	}

}