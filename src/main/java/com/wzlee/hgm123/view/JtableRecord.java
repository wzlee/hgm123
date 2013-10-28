package com.wzlee.hgm123.view;

public class JtableRecord<T> {
	private String result;
    private T record;
    private String Message;
	public JtableRecord(String result, T record, String message) {
		super();
		this.result = result;
		this.record = record;
		Message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public T getRecord() {
		return record;
	}
	public void setRecord(T record) {
		this.record = record;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
}
