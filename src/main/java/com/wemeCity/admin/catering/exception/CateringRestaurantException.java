
package com.wemeCity.admin.catering.exception;

/**
 * CateringRestaurantException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2018-2-4 新建
 */
public class CateringRestaurantException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringRestaurantException(String message, Exception e){
		super(message, e);
	}

	public CateringRestaurantException(String message){
		super(message);
	}

}