package com.frank.ec2012.adapter;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class CountryAdapter extends SimpleAdapter {

	private Context context = null;

	public CountryAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		this.context = context;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void setViewImage(ImageView v, String value) {
		
		String name = value.substring(0,1).toLowerCase() + value.substring(1);

		int iconId = context.getResources().getIdentifier(name, "drawable",
				context.getPackageName());
		
		v.setImageResource(iconId);
	}

}
