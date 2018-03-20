
package com.wemeCity.components.dictionary.exception;

/**
 * DictionaryException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-9 新建
 */
public class DictionaryException extends Exception{

	private static final long serialVersionUID = 1L;

	public DictionaryException(String message, Exception e){
		super(message, e);
	}

	public DictionaryException(String message){
		super(message);
	}

}