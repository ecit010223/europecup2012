package com.frank.ec2012.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.frank.ec2012.R;

public class PlayerEventAdapterRowManager implements AdapterRowManager {

	private LayoutInflater inflater = null;

	private LinearLayout row = null;

	public PlayerEventAdapterRowManager(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	private LinearLayout getChildRow(View convertView) {
		if (convertView == null) {
			row = (LinearLayout) inflater.inflate(R.layout.score_result_layout,
					null);
		} else {
			row = (LinearLayout) convertView;
		}

		return row;
	}

	private PlayerHolder getPlayerHolderAndSaveIt(View convertView) {
		if (convertView != null)
			return (PlayerHolder) row.getTag();
		
		PlayerHolder holder = new PlayerHolder();
		row.setTag(holder);
		
		holder.result = (TextView) row.findViewById(R.id.txtScoreResult);
		holder.players = (ListView) row.findViewById(R.id.gvPlayers);
		return holder;
	}

	public Object getRow(View convertView) {
		return getChildRow(convertView);
	}

	public Object getHolder(View convertView) {
		return getPlayerHolderAndSaveIt(convertView);
	}

}
