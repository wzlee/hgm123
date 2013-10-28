package com.wzlee.hgm123.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Youkuer implements Serializable{
	
	private static final long serialVersionUID = 4094034283716415395L;

	@Id
	private String id;

	private String name;
	
	private String link;
	
	private String avatar;
	
	private String avatar_large;
	
	private String gender;
	
	private String description;

	private Integer videos_count = 0;
	
	private Integer playlists_count = 0;
	
	private Integer favorites_count = 0;
	
	private Integer followers_count = 0;
	
	private Integer following_count = 0;
	
	private Integer statuses_count = 0;
	
	private Integer subscribe_count = 0;
	
	private Integer vv_count = 0;
	
	private String regist_time;
	
	public Youkuer() {
		super();
	}

	public Youkuer(String name, String link, String avatar,
			String avatar_large, String gender, String description,
			Integer videos_count, Integer playlists_count,
			Integer favorites_count, Integer followers_count,
			Integer following_count, Integer statuses_count,
			Integer subscribe_count, Integer vv_count, String regist_time) {
		super();
		this.name = name;
		this.link = link;
		this.avatar = avatar;
		this.avatar_large = avatar_large;
		this.gender = gender;
		this.description = description;
		this.videos_count = videos_count;
		this.playlists_count = playlists_count;
		this.favorites_count = favorites_count;
		this.followers_count = followers_count;
		this.following_count = following_count;
		this.statuses_count = statuses_count;
		this.subscribe_count = subscribe_count;
		this.vv_count = vv_count;
		this.regist_time = regist_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar_large() {
		return avatar_large;
	}

	public void setAvatar_large(String avatar_large) {
		this.avatar_large = avatar_large;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVideos_count() {
		return videos_count;
	}

	public void setVideos_count(Integer videos_count) {
		this.videos_count = videos_count;
	}

	public Integer getPlaylists_count() {
		return playlists_count;
	}

	public void setPlaylists_count(Integer playlists_count) {
		this.playlists_count = playlists_count;
	}

	public Integer getFavorites_count() {
		return favorites_count;
	}

	public void setFavorites_count(Integer favorites_count) {
		this.favorites_count = favorites_count;
	}

	public Integer getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(Integer followers_count) {
		this.followers_count = followers_count;
	}

	public Integer getFollowing_count() {
		return following_count;
	}

	public void setFollowing_count(Integer following_count) {
		this.following_count = following_count;
	}

	public Integer getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(Integer statuses_count) {
		this.statuses_count = statuses_count;
	}

	public Integer getSubscribe_count() {
		return subscribe_count;
	}

	public void setSubscribe_count(Integer subscribe_count) {
		this.subscribe_count = subscribe_count;
	}

	public Integer getVv_count() {
		return vv_count;
	}

	public void setVv_count(Integer vv_count) {
		this.vv_count = vv_count;
	}

	public String getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(String regist_time) {
		this.regist_time = regist_time;
	}

	@Override
	public String toString() {
		return "Youkuer [id=" + id + ", name=" + name + ", link=" + link
				+ ", avatar=" + avatar + ", avatar_large=" + avatar_large
				+ ", gender=" + gender + ", description=" + description
				+ ", videos_count=" + videos_count + ", playlists_count="
				+ playlists_count + ", favorites_count=" + favorites_count
				+ ", followers_count=" + followers_count + ", following_count="
				+ following_count + ", statuses_count=" + statuses_count
				+ ", subscribe_count=" + subscribe_count + ", vv_count="
				+ vv_count + ", regist_time=" + regist_time + "]";
	}

}
