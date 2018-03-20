
package com.wemeCity.admin.community.exception;

/**
 * CommunitySupplierException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-22 新建
 */
public class CommunitySupplierException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommunitySupplierException(String message, Exception e){
		super(message, e);
	}

	public CommunitySupplierException(String message){
		super(message);
	}

}