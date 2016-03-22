package com.frank.ec2012.entity;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private String name = null;

	private List<Team> teams = null;

	private List<Game> games = null;

	public List<Game> findGamesByAssignedTeam(String teamName) {
		
		List<Game> games = new ArrayList<Game>();
		games = findByHomeTeam(games,teamName);
		games = findByAwayTeam(games,teamName);
		
		return games;
	}

	private List<Game> findByHomeTeam(List<Game> returnValue, String teamName) {

		if (games == null || games.size() <= 0)
			return returnValue;

		for (Game game : games) {

			if (game.getHome_team().equals(teamName)) {
				returnValue.add(game);
			}
		}

		return returnValue;
	}

	private List<Game> findByAwayTeam(List<Game> returnValue, String teamName) {
		if (games == null || games.size() <= 0)
			return returnValue;

		for (Game game : games) {

			if (game.getAway_team().equals(teamName)) {
				returnValue.add(game);
			}
		}

		return returnValue;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public Game getLastGame() {
		if(games.size() ==0)
			return games.get(0);
		
		return games.get(games.size() - 1);
	}
	
	public Team getLastTeam() {
		if(teams.size() ==0)
			return teams.get(0);
		
		return teams.get(teams.size() - 1);
	}
}
