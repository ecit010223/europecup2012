package com.frank.ec2012.adapter;

import android.view.View;

public interface AdapterRowManager {
	
	public Object getRow(View convertView);
	
	public Object getHolder(View convertView);
}
