package com.reserv.myapplicationeli.views.adapters;


import com.reserv.myapplicationeli.R;

import com.reserv.myapplicationeli.tools.db.main.CursorManager;
import com.reserv.myapplicationeli.tools.Utility;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class PathListAdapter extends BaseAdapter {

	private LayoutInflater myInflater;
	public CursorManager cursor;
	public int selectedPosition = 0;

	public PathListAdapter() {
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}

	public void setData() {
		this.cursor = new Customers_Table().getPathList();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return cursor == null ? 1 : cursor.getCount() + 1;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.row_path, null);
			holder = new ViewHolder();
			holder.pathName = (TextView) convertView.findViewById(R.id.pathName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position == 0)
			holder.pathName.setText("همه مسیرها");
		else {
			cursor.moveToPosition(position - 1);
			if (cursor.getString(Customers_Table.Columns.PATH_CODE.value())
					.equals("none"))
				holder.pathName.setText("بدون مسیر");
			else
				holder.pathName.setText(cursor
						.getString(Customers_Table.Columns.PATH_CODE.value()));
		}
		if (position == selectedPosition)
			convertView.findViewById(R.id.row).setBackgroundColor(
					Color.parseColor("#885F9CD2"));
		else
			convertView.findViewById(R.id.row).setBackgroundColor(
					Color.TRANSPARENT);
		Utility.applyFonts(convertView);
		return convertView;
	}

	static class ViewHolder {
		TextView pathName;
	}

	public String getPath(int position) {
		if (position == 0)
			return null;
		cursor.moveToPosition(position - 1);
		return cursor.getString(Customers_Table.Columns.PATH_CODE.value());
	}

}
