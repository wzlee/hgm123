package com.wzlee.hgm123.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Website implements Serializable {
	private static final long serialVersionUID = 1808891211501568601L;
	
	@Id
	private String id;
	private String type; //eg:template/upload
	@Indexed(unique = true)
	private String sld; //second-level domain,eg:wer.hgm123.com
	private String uploadPath;
	private String templateCode;
	private String title;
	private String serverList;
	private String creator;
	private String createdate;
	private Integer visitors = 0;
	public Website(String id, String type, String sld, String uploadPath,
			String templateCode, String title, String serverList,
			String creator, String createdate) {
		super();
		this.id = id;
		this.type = type;
		this.sld = sld;
		this.uploadPath = uploadPath;
		this.templateCode = templateCode;
		this.title = title;
		this.serverList = serverList;
		this.creator = creator;
		this.createdate = createdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSld() {
		return sld;
	}
	public void setSld(String sld) {
		this.sld = sld;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getServerList() {
		return serverList;
	}
	public void setServerList(String serverList) {
		this.serverList = serverList;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public Integer getVisitors() {
		return visitors;
	}
	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
	
}
