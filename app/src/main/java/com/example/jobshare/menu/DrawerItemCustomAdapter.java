package com.example.jobshare.menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerItemCustomAdapter extends ArrayAdapter<MenuObjectDrawerItem> {

	Context mContext;
	int layoutResourceId;
	MenuObjectDrawerItem data[] = null;

	public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, MenuObjectDrawerItem[] data) {

		super(mContext, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View listItem = convertView;

		LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
		listItem = inflater.inflate(layoutResourceId, parent, false);

		ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
		TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);
		 
		
		MenuObjectDrawerItem folder = data[position];

		
		imageViewIcon.setImageResource(folder.icon);
		textViewName.setText(folder.name);
		
		return listItem;
	}

}