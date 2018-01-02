package com.reserv.myapplicationeli.views.adapters;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.reserv.myapplicationeli.tools.db.main.CursorManager;

public abstract class IDMAdapter extends BaseAdapter {
	protected CursorManager cursor;

	public abstract int getID(int position);

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	public abstract void setData(CursorManager cursor);

}
