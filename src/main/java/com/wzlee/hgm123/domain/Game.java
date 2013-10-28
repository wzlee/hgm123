package com.wzlee.hgm123.domain;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable{
	
	private static final long serialVersionUID = 7778614269613095470L;

	private String id;
	
	private String gameName;
	
	private String gameEdition;
	
	private String gameUrl;
	
	private String gameDescription;
	
	private List<Zoom> zooms;

	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Game(String gameName, String gameEdition, String gameUrl,
			String gameDescription, List<Zoom> zooms) {
		super();
		this.gameName = gameName;
		this.gameEdition = gameEdition;
		this.gameUrl = gameUrl;
		this.gameDescription = gameDescription;
		this.zooms = zooms;
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

	public String getGameEdition() {
		return gameEdition;
	}

	public void setGameEdition(String gameEdition) {
		this.gameEdition = gameEdition;
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

	public List<Zoom> getZooms() {
		return zooms;
	}

	public void setZooms(List<Zoom> zooms) {
		this.zooms = zooms;
	}
}
