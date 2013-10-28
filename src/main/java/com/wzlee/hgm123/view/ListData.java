package com.wzlee.hgm123.view;

import java.util.List;

/**
 * 输出JSOn数据视图
 * @author zhiwei
 *
 */
public class ListData<T> {
	
	private boolean success = true;
	private List<T> data;
	public ListData(boolean success, List<T> data) {
		super();
		this.success = success;
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
