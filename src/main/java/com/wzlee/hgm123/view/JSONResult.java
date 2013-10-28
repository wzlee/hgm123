package com.wzlee.hgm123.view;

import java.util.List;

public class JSONResult<T> {
	private String result;
    private List<T> records;
    private T record;
    private String Message;
	public JSONResult(String result) {
		super();
		this.result = result;
	}
	public JSONResult(String result, String message) {
		super();
		this.result = result;
		Message = message;
	}
	public JSONResult(String result, List<T> records) {
		super();
		this.result = result;
		this.records = records;
	}
	public JSONResult(String result, T record) {
		super();
		this.result = result;
		this.record = record;
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
