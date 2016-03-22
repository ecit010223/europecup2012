package com.frank.ec2012;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.frank.ec2012.adapter.CountryAdapter;
import com.frank.ec2012.adapter.ScoreboardAdapter;
import com.frank.ec2012.entity.Group;
import com.frank.ec2012.manager.EuropeCup2012Manager;

public class EuropeCup2012Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		super.setContentView(R.layout.ec2012_layout);

		initComponent();
	}

	private void initComponent() {

		//初始化首页面的list
		initTeamsSequence();
		
		//初始化弹出的对话框中的数据
		initDialog();

	}

	private void initDialog() {

		ListView view = (ListView) super.findViewById(R.id.lstCountry);

		view.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AlertDialog dialog = createDialog(position);
				dialog.show();
			}

		});
	}

	private AlertDialog createDialog(int position) {
		Builder dialogContentBuilder = new AlertDialog.Builder(this);

		/******************** Dialog Content *********************/
		
		LinearLayout dialogContentLayout = new LinearLayout(this);
		dialogContentLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dialogContentLayout.setOrientation(LinearLayout.VERTICAL);
		
		LayoutInflater inflater = 
				(LayoutInflater)getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		//设置了弹出框中部内容的最上一排标题，即：Seq T W D L F A Pts
		dialogContentLayout.addView(inflater.inflate(R.layout.scoreboard_title_layout, null));
		
		//本控件可实现弹出框中部内部的其它部分
		ExpandableListView elv = new ExpandableListView(this);
		Group group = EuropeCup2012Manager.getInstance(this).getScore()
				.getGroup(position);
		ScoreboardAdapter adapter = new ScoreboardAdapter(group, this);
		elv.setAdapter(adapter);
		elv.setScrollbarFadingEnabled(false);
		dialogContentLayout.addView(elv);
		
		dialogContentBuilder.setView(dialogContentLayout);

		/******************** Dialog Title *********************/
		AlertDialog dialog = dialogContentBuilder.create();
		dialog.setTitle("Welcome to Euroup Cup 2012");
		dialog.setIcon(R.drawable.ic_menu_largetiles);

		/******************** Dialog Bottom *********************/
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				new android.content.DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		return dialog;
	}

	private void initTeamsSequence() {

		ListView view = (ListView) super.findViewById(R.id.lstCountry);
		String[] counties = super.getResources()
				.getStringArray(R.array.country);

		List<Map<String, Object>> parameters = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < counties.length; i += 4) {

			Map<String, Object> param = new HashMap<String, Object>();

			for (int j = 0; j < 4; j++) {
				param.put("icon" + (j + 1), counties[j + i]);
				param.put("name" + (j + 1), counties[j + i]);
			}

			parameters.add(param);
		}

		CountryAdapter adapter = new CountryAdapter(this, parameters,
				R.layout.europe_layout, new String[] { "icon1", "name1",
						"icon2", "name2", "icon3", "name3", "icon4", "name4" },
				new int[] { R.id.euroup_country_icon1,
						R.id.euroup_country_name1, R.id.euroup_country_icon2,
						R.id.euroup_country_name2, R.id.euroup_country_icon3,
						R.id.euroup_country_name3, R.id.euroup_country_icon4,
						R.id.euroup_country_name4 });

		view.setAdapter(adapter);
	}
}
