package com.wzlee.hgm123.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wzlee.hgm123.utils.DateHelper;

@Document
public class Advertise implements Serializable{
	
	private static final long serialVersionUID = 4094034283716415395L;

	@Id
	private String id;

	private String gameName;
	
	private String gameEdition;
	
	private String openTime;
	
	private String lineType;
	
	private String gameDescription;
	
	private String gameUrl;

	private Integer gamePopularity = 0;
	
	private String source;
	
	private String dateTime = DateHelper.date2String("yyyy-MM-dd HH:mm:ss");
	
	public Advertise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Advertise(String id, String gameName, String gameEdition,
			String openTime, String lineType, String gameDescription,
			String gameUrl, Integer gamePopularity, String source
				) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.gameEdition = gameEdition;
		this.openTime = openTime;
		this.lineType = lineType;
		this.gameDescription = gameDescription;
		this.gameUrl = gameUrl;
		this.gamePopularity = gamePopularity;
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getGameEdition() {
		return gameEdition;
	}

	public void setGameEdition(String gameEdition) {
		this.gameEdition = gameEdition;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getGameUrl() {
		return gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public Integer getGamePopularity() {
		return gamePopularity;
	}

	public void setGamePopularity(Integer gamePopularity) {
		this.gamePopularity = gamePopularity;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Advertise [id=" + id + ", gameName=" + gameName + ", openTime="
				+ openTime + ", gameEdition=" + gameEdition + ", gameUrl="
				+ gameUrl + ", gameDescription=" + gameDescription
				+ ", gamePopularity=" + gamePopularity + ", lineType="
				+ lineType + "]";
	}
}
