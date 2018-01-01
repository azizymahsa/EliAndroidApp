package com.reserv.myapplicationeli.slidingmenu.db.local;

import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainLocalDB;
import com.reserv.myapplicationeli.slidingmenu.system.GlobalApplication;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Visitors_Table extends MainLocalDB {

	public static final String TABLE_NAME = "VISITORS";

	public static enum Columns {
		VISITOR_ID(integer), VISITOR_NAME(text), PHONE_NUMBER(text);
		public static boolean firstIsPrimery = false;
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

	public Visitors_Table() {
		super(GlobalApplication.getActivity());
	}

	public int insertData(String visitorID, String name, String phoneNumber) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.VISITOR_ID.value(), visitorID);
		cv.put(Columns.VISITOR_NAME.value(), name);
		cv.put(Columns.PHONE_NUMBER.value(), phoneNumber);
		return (int) db.insert(TABLE_NAME, null, cv);
	}

	public CursorManager getDatas() {
		String query = String.format("SELECT * FROM %s", TABLE_NAME);
		return QUERY(query);
	}

	public CursorManager getData(int visitorID) {
		String query = String.format("SELECT * FROM %s WHERE %s='%s'",
				TABLE_NAME, Columns.VISITOR_ID, visitorID);
		return QUERY(query);
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
