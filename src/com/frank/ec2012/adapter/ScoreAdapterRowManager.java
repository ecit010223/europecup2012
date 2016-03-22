package com.frank.ec2012.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frank.ec2012.R;

public class ScoreAdapterRowManager implements AdapterRowManager {

	private LayoutInflater inflater = null;

	private LinearLayout row = null;

	public ScoreAdapterRowManager(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	private LinearLayout getGroupRow(View convertView) {

		if (convertView == null) {
			row = (LinearLayout) inflater.inflate(R.layout.scoreboard_layout,
					null);
		} else {
			row = (LinearLayout) convertView;
		}

		return row;
	}

	private TeamHolder getTeamHolderAndSaveIt(View convertView) {
		
		if(convertView != null) {
			return (TeamHolder)row.getTag();
		}
		
		TeamHolder holder = new TeamHolder();
		row.setTag(holder);
		holder.sequence = (TextView) row.findViewById(R.id.txtScoreSequence);
		holder.name = (ImageView) row.findViewById(R.id.txt_scorebar_name);
		holder.W = (TextView) row.findViewById(R.id.txt_scorebar_w);
		holder.D = (TextView) row.findViewById(R.id.txt_scorebar_d);
		holder.L = (TextView) row.findViewById(R.id.txt_scorebar_l);
		holder.F = (TextView) row.findViewById(R.id.txt_scorebar_goal);
		holder.A = (TextView) row.findViewById(R.id.txt_scorebar_lgoal);
		holder.Pts = (TextView) row.findViewById(R.id.txt_scorebar_score);

		return holder;
	}

	public Object getRow(View convertView) {

		return getGroupRow(convertView);
	}

	public Object getHolder(View convertView) {
		return getTeamHolderAndSaveIt(convertView);
	}

}
