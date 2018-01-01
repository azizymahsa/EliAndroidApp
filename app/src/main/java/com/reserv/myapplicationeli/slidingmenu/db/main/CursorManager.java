package com.reserv.myapplicationeli.slidingmenu.db.main;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;
import android.util.Log;

/**
 * 
 * Interface baraye estefade az database local va database server
 * 
 * @author Morteza Rezayi
 *
 */
public class CursorManager {
	/**
	 * zakhire kardan [ColumnName] va [ColumnIndex] dar HashMap
	 */
	private HashMap<String, Integer> map;
	/**
	 * zakhire kardan data haye {@link Cursor} va ya {@link ResultSet}
	 */
	private ArrayList<String[]> data;
	private int position = 0;

	/**
	 * Constructor baraye local database
	 * 
	 * @param cursor
	 */
	public CursorManager(Cursor cursor) {
		map = new HashMap<String, Integer>();
		for (int i = 0; i < cursor.getColumnNames().length; i++) {
			map.put(cursor.getColumnNames()[i], i);
		}
		data = new ArrayList<String[]>();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			String[] d = new String[cursor.getColumnCount()];
			for (int j = 0; j < cursor.getColumnCount(); j++) {
				d[j] = cursor.getString(j);
			}
			data.add(d);
		}
		cursor.close();
	}

	/**
	 * Constructor baray server database
	 * 
	 * @param resultSet
	 */
	public CursorManager(ResultSet resultSet) {
		try {
			map = new HashMap<String, Integer>();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				map.put(rsmd.getColumnName(i + 1), i);
			}
			data = new ArrayList<String[]>();
			while (resultSet.next()) {
				String[] d = new String[rsmd.getColumnCount()];
				for (int j = 0; j < d.length; j++) {
					d[j] = resultSet.getString(j + 1);
				}
				data.add(d);
			}
			Log.e("", getCount() + "");
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getInt(String columnName) {
		try {
			return Integer.parseInt(getString(columnName));
		} catch (Exception e) {
			return 0;
		}
	}

	public long getLong(String columnName) {
		try {
			return Long.parseLong(getString(columnName));
		} catch (Exception e) {
			return 0;
		}
	}

	public double getDouble(String columnName) {
		try {
			return Double.parseDouble(getString(columnName));
		} catch (Exception e) {
			return 0;
		}
	}

	public float getFloat(String columnName) {
		try {
			return Float.parseFloat(getString(columnName));
		} catch (Exception e) {
			return 0;
		}
	}

	public String getString(String columnName) {
		try {
			return data.get(position)[map.get(columnName)];
		} catch (Exception e) {
			return "";
		}
	}

	public boolean getBoolean(String columnName) {
		try {
			return getString(columnName).equals("true")
					|| getString(columnName).equals("1");
		} catch (Exception e) {
			return false;
		}
	}

	public void moveToPosition(int i) {
		position = i;
	}

	public int getCount() {
		return data.size();
	}

	public int getPosition() {
		return position;
	}

}
