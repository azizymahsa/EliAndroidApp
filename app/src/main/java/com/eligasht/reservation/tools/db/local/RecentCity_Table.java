package com.eligasht.reservation.tools.db.local;


import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.tools.db.main.MainLocalDB;

public class RecentCity_Table extends MainLocalDB {

	public static final String TABLE_NAME = "RECENTCITY";


	public enum Columns {

		Id(integer), AirPortName(text), CityName(text)
		,AirPortCode(text),flag(integer);

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

    public RecentCity_Table(Activity activity) {
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
	public int insertData(String AirPortName, String CityName, String AirPortCode,int flag) {
		ContentValues cv = new ContentValues();

		cv.put(Columns.AirPortName.value(), AirPortName);
		
		cv.put(Columns.CityName.value(), CityName);
		cv.put(Columns.AirPortCode.value(), AirPortCode);
		cv.put(Columns.flag.value(), flag);

		
		
		openDB();
		return (int) db.insert(TABLE_NAME, null, cv);
	}
	/*****************************************************************************************/

	public CursorManager getCountRow() {
		String query = String.format("SELECT COUNT(%s) FROM %s",Columns.Id,TABLE_NAME);
		return QUERY(query);
	}
	/*****************************************************************************************/
	public void deletById(int uuID) {
		System.out.println("remov:"+uuID);
		DELETE_FROM_DB(TABLE_NAME,
				String.format("%s='%s'", Columns.Id, uuID+""));
	}
	/*****************************************************************************************/
	public CursorManager getAll(int typeFlag) {
		//return SELECT_FROM_DB("*", TABLE_NAME,Columns.flag + "=" + typeFlag, Columns.Id+"  desc", 0, 0);
		String query = String.format("SELECT  DISTINCT  "+Columns.AirPortCode+","+Columns.AirPortName+","+Columns.CityName +" FROM %s WHERE %s=%s  order by %s desc  LIMIT 5",TABLE_NAME, Columns.flag,typeFlag,Columns.Id);
		//String query = String.format("SELECT  TOP 3 * FROM %s WHERE %s=%s  order by %s desc ",TABLE_NAME, Columns.flag,typeFlag,Columns.Id);

		System.out.println("guery:"+query);
		return QUERY(query);
	}
	/*****************************************************************************************/
	public CursorManager getAllAsc(int typeFlag) {
		//return SELECT_FROM_DB("*", TABLE_NAME,Columns.flag + "=" + typeFlag, Columns.Id+"  desc", 0, 0);
		String query = String.format("SELECT  DISTINCT  "+Columns.AirPortCode+","+Columns.AirPortName+","+Columns.CityName +" FROM %s WHERE %s=%s    LIMIT 5",TABLE_NAME, Columns.flag,typeFlag);
		//String query = String.format("SELECT  TOP 3 * FROM %s WHERE %s=%s  order by %s desc ",TABLE_NAME, Columns.flag,typeFlag,Columns.Id);

		System.out.println("guery:"+query);
		return QUERY(query);
	}
	/*****************************************************************************************/
	/*public CursorManager getConditionId(int goodCode) {//SELECT top 1 * FROM GAWARD WHERE GoodCode=100011
		String query = String.format("SELECT top 1 * FROM %s WHERE %s=%s",TABLE_NAME, Columns.GoodCode,goodCode);
		return QUERY(query);
	}*/
	//select column_name from table_name order by column_name desc limit size.
/*	public CursorManager getDatas(int requestId) {
		String conditions = Columns.RequestId + "='" + requestId + "'";
		String columns = String.format("%s,%s,%s", Columns.GoodCode,
				Columns.Amount, Columns.Award);
		return SELECT_FROM_DB(columns, TABLE_NAME, conditions,
				Columns.RequestId.value() + "," + Columns.GoodCode.value(), 0,
				0);
	}
*/
	/*public void insertRequest(int requestId, int goodCode, int amount,String award) {
		String columns = String.format("%s,%s,%s,%s", Columns.RequestId,Columns.GoodCode, Columns.Amount, Columns.Award);
		String values = String.format("'%s','%s','%s','%s'", requestId,goodCode, amount, award);
		INSERT_TO_DB(TABLE_NAME, columns, values);
	}*/

/*	public void updateAmount(int requestId, int goodCode, int amount) {
		UPDATE_DB(TABLE_NAME,
				String.format("%s = '%s'", Columns.Amount, amount),
				String.format("%s = '%s' AND %s = '%s'", Columns.RequestId,
						requestId, Columns.GoodCode, goodCode));
	}

	public void removeRequest(int requestID) {
		DELETE_FROM_DB(TABLE_NAME,
				String.format("%s='%s'", Columns.RequestId, requestID));
	}

	public void removeRequestItem(int reqNo, int goodCode) {
		DELETE_FROM_DB(TABLE_NAME, String.format("%s='%s' AND %s='%s'",
				Columns.RequestId, reqNo, Columns.GoodCode, goodCode));
	}
*/
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
