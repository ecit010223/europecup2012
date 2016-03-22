package com.frank.ec2012.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.frank.ec2012.R;
import com.frank.ec2012.entity.Away;
import com.frank.ec2012.entity.EuropeCup2012Score;
import com.frank.ec2012.entity.Game;
import com.frank.ec2012.entity.Group;
import com.frank.ec2012.entity.Home;
import com.frank.ec2012.entity.Player;
import com.frank.ec2012.entity.Team;

public class EuropeCup2012Manager {

	private static EuropeCup2012Manager instance = null;
	private EuropeCup2012Score score = null;

	public static String ROOT_NODE_NAME = "europe_cup";
	public static String GROUP_NODE_NAME = "group";
	public static String TEAM_NODE_NAME = "team";
	public static String GAME_NODE_NAME = "game";
	public static String HOME_NODE_NAME = "home";
	public static String AWAY_NODE_NAME = "away";
	public static String PLAYER_NODE_NAME = "player";

	private XmlResourceParser parser = null;

	private EuropeCup2012Manager(Context context) {
		this.parser = context.getResources().getXml(R.xml.europe_cup);
	}

	public EuropeCup2012Score getScore() {

		if(score != null)
			return score;

		boolean isHome_Event = true;

		try {

			while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {

				if (parser.getEventType() != XmlResourceParser.START_TAG) {
					parser.next();
					continue;
				}

				String tagName = parser.getName();

				if (ROOT_NODE_NAME.equals(tagName)) {
					score = new EuropeCup2012Score();
					List<Group> groups = new ArrayList<Group>();
					score.setGroups(groups);
				}

				if (GROUP_NODE_NAME.equals(tagName)) {
					Group group = createGroup();
					score.getGroups().add(group);
				}

				if (TEAM_NODE_NAME.equals(tagName)) {
					Team team = createTeam();
					score.getLastGroup().getTeams().add(team);
				}

				if (GAME_NODE_NAME.equals(tagName)) {
					Game game = createGame();
					score.getLastGroup().getGames().add(game);
				}

				if (HOME_NODE_NAME.equals(tagName)) {
					Home home = createHome();
					score.getLastGroup().getLastGame().setHome_event(home);
					isHome_Event = true;
				}

				if (AWAY_NODE_NAME.equals(tagName)) {
					Away away = createAway();
					score.getLastGroup().getLastGame().setAway_event(away);
					isHome_Event = false;
				}

				if (PLAYER_NODE_NAME.equals(tagName)) {
					Player player = createPlayer();
					if (isHome_Event)
						score.getLastGroup().getLastGame().getHome_event()
								.getPlayers().add(player);
					else
						score.getLastGroup().getLastGame().getAway_event()
								.getPlayers().add(player);

				}
				parser.next();
			}

		} catch (XmlPullParserException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

		return score;
	}

	private Player createPlayer() {
		Player player = new Player();

		player.setName(this.parser.getAttributeValue(null, "name"));
		player.setEvent(this.parser.getAttributeValue(null, "event"));
		player.setTime(this.parser.getAttributeValue(null, "time"));

		return player;
	}

	private Home createHome() {
		Home home = new Home();
		home.setPlayers(new ArrayList<Player>());
		return home;
	}

	private Away createAway() {
		Away away = new Away();
		away.setPlayers(new ArrayList<Player>());
		return away;
	}

	private Game createGame() {
		Game game = new Game();
		game.setHome_team(this.parser.getAttributeValue(null, "home"));
		game.setAway_team(this.parser.getAttributeValue(null, "away"));
		game.setResult(this.parser.getAttributeValue(null, "result"));
		game.setDate(this.parser.getAttributeValue(null, "date"));
		return game;
	}

	private Team createTeam() {
		Team team = new Team();
		team.setA(this.parser.getAttributeValue(null, "A"));
		team.setD(this.parser.getAttributeValue(null, "D"));
		team.setL(this.parser.getAttributeValue(null, "L"));
		team.setW(this.parser.getAttributeValue(null, "W"));
		team.setName(this.parser.getAttributeValue(null, "name"));
		team.setF(this.parser.getAttributeValue(null, "F"));
		team.setPts(this.parser.getAttributeValue(null, "Pts"));
		return team;
	}

	private Group createGroup() {
		Group group = new Group();
		group.setName(this.parser.getAttributeValue(null, "name"));
		group.setGames(new ArrayList<Game>());
		group.setTeams(new ArrayList<Team>());
		return group;
	}

	public static EuropeCup2012Manager getInstance(Context context) {
		if (instance == null)
			instance = new EuropeCup2012Manager(context);
		return instance;
	}
}
