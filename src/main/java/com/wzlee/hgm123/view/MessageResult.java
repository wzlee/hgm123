package com.wzlee.hgm123.view;

/**
 * 输出结果视图
 * @author zhiwei
 *
 */
public class MessageResult {
	
	private boolean success = true;
	private String message = "";
	
	public MessageResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
