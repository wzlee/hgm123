package com.wzlee.hgm123.domain;

import java.io.Serializable;

public class Gamedata implements Serializable{
	
	private static final long serialVersionUID = -4801311647519062741L;

	private String id;
	
	private String pid;
	
	private String aid;
	
	private String type; //eg:publish,share,collect...
	
	private String topic;
	
	private String date;

	public Gamedata(String id, String pid, String aid, String type,String topic, String date) {
		super();
		this.id = id;
		this.pid = pid;
		this.aid = aid;
		this.type = type;
		this.topic = topic;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
