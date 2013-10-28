package com.wzlee.hgm123.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Passport implements Serializable {

	private static final long serialVersionUID = 1938369434557650634L;
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String upassid;
	private String password;
	private String nickname;
	private String qq;
	@Indexed
	private String email;
	private Date registerdate = new Date();
	private Date activedate;
	private Date lastlogindate;
	private Date thislogindate;
	private String lastloginip;
	private String thisloginip;
	private boolean online = false;
	private String groupname;
	private String status = "未激活";
	private String loginSequence = UUID.randomUUID().toString(); //登陆序列
	private Long loginTimestamp;	//登陆token
	private String qqOpenid; //腾讯QQOPENID
	private String qqNick; //QQ昵称
	private String qqAvatar; //QQ图像
	private String twOpenid; //腾讯微博PENID
	private String twNick; //腾讯微博昵称
	private String twAvatar; //腾讯微博图像
	private String swOpenid; //新浪微博PENID
	private String swNick; //新浪微博昵称
	private String swAvatar; //新浪微博昵称
	
	@PersistenceConstructor
	public Passport() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpassid() {
		return upassid;
	}

	public void setUpassid(String upassid) {
		this.upassid = upassid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public Date getActivedate() {
		return activedate;
	}

	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}

	public Date getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	public Date getThislogindate() {
		return thislogindate;
	}

	public void setThislogindate(Date thislogindate) {
		this.thislogindate = thislogindate;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getThisloginip() {
		return thisloginip;
	}

	public void setThisloginip(String thisloginip) {
		this.thisloginip = thisloginip;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginSequence() {
		return loginSequence;
	}

	public void setLoginSequence(String loginSequence) {
		this.loginSequence = loginSequence;
	}

	public Long getLoginTimestamp() {
		return loginTimestamp;
	}

	public void setLoginTimestamp(Long loginTimestamp) {
		this.loginTimestamp = loginTimestamp;
	}

	public String getQqOpenid() {
		return qqOpenid;
	}

	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid = qqOpenid;
	}

	public String getQqNick() {
		return qqNick;
	}

	public void setQqNick(String qqNick) {
		this.qqNick = qqNick;
	}

	public String getQqAvatar() {
		return qqAvatar;
	}

	public void setQqAvatar(String qqAvatar) {
		this.qqAvatar = qqAvatar;
	}

	public String getTwOpenid() {
		return twOpenid;
	}

	public void setTwOpenid(String twOpenid) {
		this.twOpenid = twOpenid;
	}

	public String getTwNick() {
		return twNick;
	}

	public void setTwNick(String twNick) {
		this.twNick = twNick;
	}

	public String getTwAvatar() {
		return twAvatar;
	}

	public void setTwAvatar(String twAvatar) {
		this.twAvatar = twAvatar;
	}

	public String getSwOpenid() {
		return swOpenid;
	}

	public void setSwOpenid(String swOpenid) {
		this.swOpenid = swOpenid;
	}

	public String getSwNick() {
		return swNick;
	}

	public void setSwNick(String swNick) {
		this.swNick = swNick;
	}

	public String getSwAvatar() {
		return swAvatar;
	}

	public void setSwAvatar(String swAvatar) {
		this.swAvatar = swAvatar;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", upassid=" + upassid + ", password="
				+ password + ", nickname=" + nickname + ", qq=" + qq
				+ ", email=" + email + ", registerdate=" + registerdate
				+ ", activedate=" + activedate + ", lastlogindate="
				+ lastlogindate + ", thislogindate=" + thislogindate
				+ ", lastloginip=" + lastloginip + ", thisloginip="
				+ thisloginip + ", online=" + online + ", groupname="
				+ groupname + ", status=" + status + ", loginSequence="
				+ loginSequence + ", loginTimestamp=" + loginTimestamp
				+ ", qqOpenid=" + qqOpenid + ", qqNick=" + qqNick
				+ ", qqAvatar=" + qqAvatar + ", twOpenid=" + twOpenid
				+ ", twNick=" + twNick + ", twAvatar=" + twAvatar
				+ ", swOpenid=" + swOpenid + ", swNick=" + swNick
				+ ", swAvatar=" + swAvatar + "]";
	}
	
}
