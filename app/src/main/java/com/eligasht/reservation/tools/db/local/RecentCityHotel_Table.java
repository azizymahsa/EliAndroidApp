package com.eligasht.reservation.tools.db.local;


import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.tools.db.main.MainLocalDB;

public class RecentCityHotel_Table extends MainLocalDB {

	public static final String TABLE_NAME = "RECENTCITYHOTEL";

	public enum Columns {

		Id(integer), CityNameFa(text), CityNameEn(text)
		,CityCode(text)
 ,Tesxt(text)
		,  TextFa(text)
		 , ShortDes(text)
		 , LongDes(text)
		 , Tag(text)
		 , Icon(text)
		 , IconDown(text)
		 , IsSelectable(integer);
		public static boolean firstIsPrimery = true;
		private String parameterValue;

		Columns(String value) {
			this.parameterValue = value;
		}

		public String getType() {
			return parameterValue;
		}

		public String value() {
			return this.name();
		}
	}

    public RecentCityHotel_Table(Activity activity) {
		super(activity);
	}

	/*****************************************************************************************/
	public CursorManager getRecentAll() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", "", 0, 0);
	}

	/*****************************************************************************************/
	public CursorManager getRecentMabda() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", "", 0, 0);
	}
	/*****************************************************************************************/
	/*public int insertData(String CityNameFa, String CityNameEn, String CityCode) {
		ContentValues cv = new ContentValues();

		cv.put(Columns.CityNameFa.value(), CityNameFa);
		
		cv.put(Columns.CityNameEn.value(), CityNameEn);
		cv.put(Columns.CityCode.value(), CityCode);


		
		
		openDB();
		return (int) db.insert(TABLE_NAME, null, cv);
	}*/
	public int insertData(String Tesxt, String TextFa, String ShortDes,String LongDes, String Tag, String Icon, String IconDown,int IsSelectable,String CityCode) {
		ContentValues cv = new ContentValues();

		cv.put(Columns.Tesxt.value(), Tesxt);
		cv.put(Columns.TextFa.value(), TextFa);
		cv.put(Columns.ShortDes.value(), ShortDes);

		cv.put(Columns.LongDes.value(), LongDes);
		cv.put(Columns.Tag.value(), Tag);
		cv.put(Columns.Icon.value(), Icon);
		cv.put(Columns.IconDown.value(), IconDown);
		cv.put(Columns.IsSelectable.value(), IsSelectable);

		cv.put(Columns.CityCode.value(), CityCode);

		openDB();
		return (int) db.insert(TABLE_NAME, null, cv);
	}
	/*****************************************************************************************/
	/*public CursorManager getAll() {
		//return SELECT_FROM_DB("*", TABLE_NAME,Columns.flag + "=" + typeFlag, Columns.Id+"  desc", 0, 0);
		String query = String.format("SELECT  DISTINCT  "+ Columns.CityCode+","+ Columns.CityNameFa+","+ Columns.CityNameEn +" FROM %s   order by %s desc  LIMIT 5",TABLE_NAME, Columns.Id);
		//String query = String.format("SELECT  TOP 3 * FROM %s WHERE %s=%s  order by %s desc ",TABLE_NAME, Columns.flag,typeFlag,Columns.Id);

		System.out.println("guery:"+query);
		return QUERY(query);
	}*/
	public CursorManager getAll() {
		//return SELECT_FROM_DB("*", TABLE_NAME,Columns.flag + "=" + typeFlag, Columns.Id+"  desc", 0, 0);
		String query = String.format("SELECT  DISTINCT  "+Columns.ShortDes+","+Columns.Tesxt+","+Columns.LongDes+","+Columns.IsSelectable+","+Columns.Icon+","+Columns.Tag +","+Columns.CityCode +" FROM %s   order by %s desc  LIMIT 5",TABLE_NAME, Columns.Id);
		//String query = String.format("SELECT  TOP 3 * FROM %s WHERE %s=%s  order by %s desc ",TABLE_NAME, Columns.flag,typeFlag,Columns.Id);

		System.out.println("guery:"+query);
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
		if (!db.isOpen())
			openDB();
		db.execSQL(sql);
		try {

		} catch (Exception e) {
			closeDB();
		}
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
