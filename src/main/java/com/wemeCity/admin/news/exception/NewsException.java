
package com.wemeCity.admin.news.exception;

/**
 * NewsException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
public class NewsException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsException(String message, Exception e){
		super(message, e);
	}

	public NewsException(String message){
		super(message);
	}

}