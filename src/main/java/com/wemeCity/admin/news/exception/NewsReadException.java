
package com.wemeCity.admin.news.exception;

/**
 * NewsReadException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
public class NewsReadException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsReadException(String message, Exception e){
		super(message, e);
	}

	public NewsReadException(String message){
		super(message);
	}

}