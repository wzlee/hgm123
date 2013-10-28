package com.wzlee.hgm123.view;

public class ThirdBean {
	private String code;
	private String openid;
	private String name; //账号
	private String nick; //昵称
	private String avatar; //图像
	public ThirdBean(String code, String openid, String name, String nick,
			String avatar) {
		super();
		this.code = code;
		this.openid = openid;
		this.name = name;
		this.nick = nick;
		this.avatar = avatar;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
