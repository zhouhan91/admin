
package com.wemeCity.admin.community.exception;

/**
 * CommunityOrderOfflinePayException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-14 新建
 */
public class CommunityOrderOfflinePayException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommunityOrderOfflinePayException(String message, Exception e){
		super(message, e);
	}

	public CommunityOrderOfflinePayException(String message){
		super(message);
	}

}