
package com.wemeCity.admin.community.exception;

/**
 * RoomException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
public class RoomException extends Exception{

	private static final long serialVersionUID = 1L;

	public RoomException(String message, Exception e){
		super(message, e);
	}

	public RoomException(String message){
		super(message);
	}

}