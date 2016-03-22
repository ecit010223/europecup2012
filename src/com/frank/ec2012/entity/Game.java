package com.frank.ec2012.entity;

public class Game {
	
	private Home home_event = null;
	
	private Away away_event = null;
	
	private String home_team = null;
	
	private String away_team = null;

	private String result = null;
	
	private String date = null;

	public Home getHome_event() {
		return home_event;
	}

	public void setHome_event(Home home_event) {
		this.home_event = home_event;
	}

	public Away getAway_event() {
		return away_event;
	}

	public void setAway_event(Away away_event) {
		this.away_event = away_event;
	}

	public String getHome_team() {
		return home_team;
	}

	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}

	public String getAway_team() {
		return away_team;
	}

	public void setAway_team(String away_team) {
		this.away_team = away_team;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
