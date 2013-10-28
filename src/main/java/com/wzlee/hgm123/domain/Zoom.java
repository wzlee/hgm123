package com.wzlee.hgm123.domain;

import java.io.Serializable;

public class Zoom implements Serializable {
	
	private static final long serialVersionUID = 3943509282807424752L;

	private String id;
	
	private String zoomName;
	
	private String zoomDate;
	
	private String zoomTime;
	
	private Integer zoomPopularity = 0;
	
	private enum Status {
		PREPARE,OPEN,CLOSE
	} 
	
	private Zoom.Status zoomStatus;

	
	public Zoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Zoom(String zoomName, String zoomDate, String zoomTime,
			Integer zoomPopularity, Status zoomStatus) {
		super();
		this.zoomName = zoomName;
		this.zoomDate = zoomDate;
		this.zoomTime = zoomTime;
		this.zoomPopularity = zoomPopularity;
		this.zoomStatus = zoomStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZoomName() {
		return zoomName;
	}

	public void setZoomName(String zoomName) {
		this.zoomName = zoomName;
	}

	public String getZoomDate() {
		return zoomDate;
	}

	public void setZoomDate(String zoomDate) {
		this.zoomDate = zoomDate;
	}

	public String getZoomTime() {
		return zoomTime;
	}

	public void setZoomTime(String zoomTime) {
		this.zoomTime = zoomTime;
	}

	public Integer getZoomPopularity() {
		return zoomPopularity;
	}

	public void setZoomPopularity(Integer zoomPopularity) {
		this.zoomPopularity = zoomPopularity;
	}

	public Zoom.Status getZoomStatus() {
		return zoomStatus;
	}

	public void setZoomStatus(Zoom.Status zoomStatus) {
		this.zoomStatus = zoomStatus;
	}

	@Override
	public String toString() {
		return "Zoom [id=" + id + ", zoomName=" + zoomName + ", zoomDate="
				+ zoomDate + ", zoomTime=" + zoomTime + ", zoomPopularity="
				+ zoomPopularity + ", zoomStatus=" + zoomStatus + "]";
	}

}
