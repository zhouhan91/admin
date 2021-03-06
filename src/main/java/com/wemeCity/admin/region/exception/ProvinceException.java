
package com.wemeCity.admin.region.exception;

/**
 * ProvinceException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-21 新建
 */
public class ProvinceException extends Exception{

	private static final long serialVersionUID = 1L;

	public ProvinceException(String message, Exception e){
		super(message, e);
	}

	public ProvinceException(String message){
		super(message);
	}

}