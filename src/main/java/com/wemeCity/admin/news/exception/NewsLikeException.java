
package com.wemeCity.admin.news.exception;

/**
 * NewsLikeException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-11-18 新建
 */
public class NewsLikeException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsLikeException(String message, Exception e){
		super(message, e);
	}

	public NewsLikeException(String message){
		super(message);
	}

}