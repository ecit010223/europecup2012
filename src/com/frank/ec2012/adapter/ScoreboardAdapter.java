package com.frank.ec2012.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.frank.ec2012.R;
import com.frank.ec2012.entity.Game;
import com.frank.ec2012.entity.Group;
import com.frank.ec2012.entity.Team;

public class ScoreboardAdapter extends BaseExpandableListAdapter {

	private Group group = null;

	private LayoutInflater inflater = null;

	private Context context = null;

	public ScoreboardAdapter(Group group, Context context) {
		this.group = group;
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
	 * ��д����ķ��� �õ������ĶԻ����й��м�������
	 */
	public int getGroupCount() {
		if (group == null)
			return 0;
		return group.getTeams().size();
	}

	/*
	 * ��д����ķ��� �õ�ÿһ�������б������ж��������� ����ֻ��ʾ��ù��й�ϵ�ı���
	 */
	public int getChildrenCount(int groupPosition) {

		String teamName = group.getTeams().get(groupPosition).getName();
		List<Game> games = this.group.findGamesByAssignedTeam(teamName);

		if (games == null)
			return 0;
		return games.size();
	}

	/*
	 * ��д����ķ��� �õ������Ի����е�ĳһ��
	 */
	public Object getGroup(int groupPosition) {

		if (group == null || group.getTeams().size() <= 0)
			return null;

		return group.getTeams().get(groupPosition);
	}

	/*
	 * ��д����ķ��� �õ������ĶԻ����е�ĳһ��չ�����ĳһ����
	 */
	public Object getChild(int groupPosition, int childPosition) {
		String teamName = group.getTeams().get(groupPosition).getName();
		List<Game> games = this.group.findGamesByAssignedTeam(teamName);
		return games.get(childPosition);
	}

	/*
	 * ��д����ķ���
	 */
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/*
	 * ��д����ķ���
	 */
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/*
	 * ��д����ķ��� �Ƿ�ָ��������ͼ��������ͼ��ID��Ӧ�ĺ�̨���ݸı�Ҳ�ᱣ�ָ�ID
	 */
	public boolean hasStableIds() {
		return true;
	}

	/*
	 * ��д����ķ��� ָ��λ�õ�����ͼ�Ƿ��ѡ��
	 */
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	/**
	 * ��д����ķ��� ��ȡ����ͼ����
	 */
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		if (group == null || group.getTeams().size() <= 0)
			return convertView;

		ScoreAdapterRowManager manager = new ScoreAdapterRowManager(inflater);

		// �õ�����
		LinearLayout row = (LinearLayout) manager.getRow(convertView);

		// �õ������ڵĿؼ�
		TeamHolder holder = (TeamHolder) manager.getHolder(convertView);

		setDataForGroupRow(holder, groupPosition);

		return row;
	}

	/**
	 * ��д����ķ��� ��ȡ����ͼ����
	 */
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		String teamName = group.getTeams().get(groupPosition).getName();
		List<Game> games = this.group.findGamesByAssignedTeam(teamName);
		Game game = games.get(childPosition);

		PlayerEventAdapterRowManager manager = new PlayerEventAdapterRowManager(
				inflater);
		LinearLayout row = (LinearLayout) manager.getRow(convertView);
		PlayerHolder holder = (PlayerHolder) manager.getHolder(convertView);

		setDataForChildRow(holder, game);

		return row;
	}

	/********************************************************************************/
	private void setDataForChildRow(PlayerHolder holder, Game game) {

		List<Map<String, Object>> parameters = new ArrayList<Map<String, Object>>();

		//��ȡ���ӻ�Ͷ�����������ֵ
		int size = game.getHome_event().getPlayers().size() > game
				.getAway_event().getPlayers().size() ? game.getHome_event()
				.getPlayers().size() : game.getAway_event().getPlayers().size();

		for (int i = 0; i < size; i++) {
			Map<String, Object> parameter = new HashMap<String, Object>();

			if (i >= game.getHome_event().getPlayers().size()) {
				parameter.put("icon_home", null);
				parameter.put("name_home", null);
				parameter.put("time_home", null);
			} else {
				parameter.put("icon_home", getImageIdentitifier(game
						.getHome_event().getPlayers().get(i).getEvent()));
				parameter.put("name_home", game.getHome_event().getPlayers()
						.get(i).getName());
				parameter.put("time_home", game.getHome_event().getPlayers()
						.get(i).getTime());
			}
			if (i >= game.getAway_event().getPlayers().size()) {
				parameter.put("icon_away", null);
				parameter.put("name_away", null);
				parameter.put("time_away", null);
			} else {
				parameter.put("icon_away", getImageIdentitifier(game
						.getAway_event().getPlayers().get(i).getEvent()));
				parameter.put("name_away", game.getAway_event().getPlayers()
						.get(i).getName());
				parameter.put("time_away", game.getAway_event().getPlayers()
						.get(i).getTime());
			}

			parameters.add(parameter);
		}

		SimpleAdapter adapter = new SimpleAdapter(context, parameters,
				R.layout.score_players_layout, new String[] { "icon_home",
						"name_home", "time_home", "icon_away", "name_away",
						"time_away" }, new int[] { R.id.playerstatus_home,
						R.id.txtPlayerName_home, R.id.txtTime_home,
						R.id.playerstatus_away, R.id.txtPlayerName_away,
						R.id.txtTime_away });

		holder.players.setAdapter(adapter);
		holder.result.setText(game.getHome_team() + " " + game.getResult()
				+ " " + game.getAway_team());
	}

	private void setDataForGroupRow(TeamHolder holder, int groupPosition) {
		Team team = group.getTeams().get(groupPosition);
		holder.sequence.setText(String.valueOf(groupPosition + 1));
		holder.name.setImageResource(getImageIdentitifier(team.getName()));
		holder.W.setText(team.getW());
		holder.D.setText(team.getD());
		holder.L.setText(team.getL());
		holder.F.setText(team.getF());
		holder.A.setText(team.getA());
		holder.Pts.setText(team.getPts());
	}

	private int getImageIdentitifier(String imageName) {

		String name = imageName.substring(0, 1).toLowerCase()
				+ imageName.substring(1);

		int identitifier = this.context.getResources().getIdentifier(name,
				"drawable", context.getPackageName());

		return identitifier;
	}

}
