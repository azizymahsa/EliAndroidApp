package com.reserv.myapplicationeli.slidingmenu.db.local;

import com.reserv.myapplicationeli.slidingmenu.db.local.Products_Table.Columns;
import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainLocalDB;
import com.reserv.myapplicationeli.slidingmenu.system.GlobalApplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PassengerMosaferItems_Table extends MainLocalDB {

	public static final String TABLE_NAME = "PASSENGER_MOSAFER_ITEMS";

	public static enum Columns {

		Gender(text), Nationality(text), Nationality_ID(text)
		,RqPassenger_Address(text), RqPassenger_Birthdate(text), RqPassenger_Email(text)
		,RqPassenger_FirstNameEn(text), RqPassenger_FirstNameFa(text), RqPassenger_LastNameEn(text)
		,RqPassenger_LastNameFa(text), RqPassenger_Mobile(text), RqPassenger_NationalCode(text)
		,RqPassenger_PassExpDate(text), RqPassenger_PassNo(text), RqPassenger_Tel(text);
		
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

	public PassengerMosaferItems_Table() {
		super(GlobalApplication.getActivity());
	}
/*
	public CursorManager getDatas() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", Columns.RequestId.value()
				+ "," + Columns.GoodCode.value(), 0, 0);
	}*/
	/*****************************************************************************************/
	public CursorManager getAllMosafer() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", "", 0, 0);
	}

	/*****************************************************************************************/

	/*****************************************************************************************/
	public int insertData(String Gender, String Nationality, String Nationality_ID,
			String RqPassenger_Address, String RqPassenger_Birthdate, String RqPassenger_Email,
			String RqPassenger_FirstNameEn, String RqPassenger_FirstNameFa, String RqPassenger_LastNameEn,
			String RqPassenger_LastNameFa, String RqPassenger_Mobile, String RqPassenger_NationalCode, String RqPassenger_PassExpDate,
			String RqPassenger_PassNo,String RqPassenger_Tel) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.Gender.value(), Gender);
		cv.put(Columns.Nationality.value(), Nationality);
		cv.put(Columns.Nationality_ID.value(), Nationality_ID);
		
		cv.put(Columns.RqPassenger_Address.value(), RqPassenger_Address);
		cv.put(Columns.RqPassenger_Birthdate.value(), RqPassenger_Birthdate);
		cv.put(Columns.RqPassenger_Email.value(), RqPassenger_Email);
		
		cv.put(Columns.RqPassenger_FirstNameEn.value(), RqPassenger_FirstNameEn);
		cv.put(Columns.RqPassenger_FirstNameFa.value(), RqPassenger_FirstNameFa);
		cv.put(Columns.RqPassenger_LastNameEn.value(), RqPassenger_LastNameEn);
		
		cv.put(Columns.RqPassenger_LastNameFa.value(), RqPassenger_LastNameFa);
		cv.put(Columns.RqPassenger_Mobile.value(), RqPassenger_Mobile);
		cv.put(Columns.RqPassenger_NationalCode.value(), RqPassenger_NationalCode);
		
		cv.put(Columns.RqPassenger_PassExpDate.value(), RqPassenger_PassExpDate);
		cv.put(Columns.RqPassenger_PassNo.value(), RqPassenger_PassNo);
		cv.put(Columns.RqPassenger_Tel.value(), RqPassenger_Tel);
		openDB();
		return (int) db.insert(TABLE_NAME, null, cv);
	}

	/*****************************************************************************************/
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
