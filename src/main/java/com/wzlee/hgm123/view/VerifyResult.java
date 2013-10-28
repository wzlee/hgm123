package com.wzlee.hgm123.view;

/**
 * 验证结果视图
 * @author zhiwei
 *
 */
public class VerifyResult {
	
	private boolean success = true;
	private String errorfiled = "";
	private String message = "";
	
	public VerifyResult(boolean success, String errorfiled, String message) {
		super();
		this.success = success;
		this.message = message;
		this.errorfiled = errorfiled;
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
	public String getErrorfiled() {
		return errorfiled;
	}
	public void setErrorfiled(String errorfiled) {
		this.errorfiled = errorfiled;
	}
}
