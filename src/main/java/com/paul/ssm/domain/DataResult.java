package com.paul.ssm.domain;

import java.io.Serializable;

/**
 * 
 * @author huangyun
 *
 */
public class DataResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8685896287761999370L;

	private int code;
	
	private String msg;
	
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
