package com.wzlee.hgm123.view;

import java.util.List;

public class JtableRecords<T> {
	private String result;
    private List<T> records;
    private String Message;
	public JtableRecords(String result, List<T> records, String message) {
		super();
		this.result = result;
		this.records = records;
		Message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
    
}
