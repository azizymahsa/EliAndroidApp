package com.reserv.myapplicationeli.slidingmenu.db.local;

import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainLocalDB;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Position_Table extends MainLocalDB {

	public static final String TABLE_NAME = "POSITIONS";

	public static enum Columns {
		id(integer), Latitude(text), Longitude(text), Date(text), Sent(text);
		public static boolean firstIsPrimery = true;
		private String parameterValue;

		private Columns(String value) {
			this.parameterValue = value;
		}

		public String getType() {
			return parameterValue;
		}

		public String value() {
			return this.name();
		}
	};

	public Position_Table(Context context) {
		super(context);
	}

	public CursorManager getPositions() {
		return SELECT_FROM_DB("*", TABLE_NAME, Columns.Sent + "='false'",
				Columns.id.value(), 0, 100);
	}

	public void insertPosition(double latitude, double longitude, String date) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.Latitude.value(), latitude);
		cv.put(Columns.Longitude.value(), longitude);
		cv.put(Columns.Date.value(), date);
		cv.put(Columns.Sent.value(), "false");
		openDB();
		db.insert(TABLE_NAME, null, cv);
		closeDB();
	}

	public CursorManager getTopPosition() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", Columns.id.value()
				+ " DESC", 0, 1);
	}

	public void positionSent(int id) {
		UPDATE_DB(TABLE_NAME, Columns.Sent + "='true'", Columns.id + "='" + id
				+ "'");
	}

	public void deleteOlderPositions(int id) {
		DELETE_FROM_DB(TABLE_NAME, String.format("%s < '%s' AND %s='true'",
				Columns.id, id, Columns.Sent));
	}

	@Override
	public void create() {
		String params = "";
		int index = 0;
		for (Columns param : Columns.values()) {
			params += param.value() + " " + param.getType();
			params += (index == 0 && Columns.firstIsPrimery) ? " PRIMARY KEY, "
					: ", ";
			index++;
		}
		params = params.substring(0, params.length() - 2);
		String sqlFormat = "CREATE TABLE IF NOT EXISTS %s (%s)";
		String sql = String.format(sqlFormat, TABLE_NAME, params);
		db.execSQL(sql);
	}

	@Override
	public void upgrade(SQLiteDatabase db, int lastVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		create();
	}

	@Override
	public void dropTable() {
		dropTable(TABLE_NAME);
	}

}
