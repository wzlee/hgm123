package com.wzlee.hgm123.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Feedback implements Serializable {
	private static final long serialVersionUID = -5605926256980026262L;
	
	@Id
	private String id;
	private String email;
	private String upassid;
	private String category;
	private String content;
	private Date feeddate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUpassid() {
		return upassid;
	}
	public void setUpassid(String upassid) {
		this.upassid = upassid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getFeeddate() {
		return feeddate;
	}
	public void setFeeddate(Date feeddate) {
		this.feeddate = feeddate;
	}
	
}
