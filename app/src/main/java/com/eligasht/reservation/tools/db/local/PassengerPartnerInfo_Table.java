package com.eligasht.reservation.tools.db.local;


import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.tools.db.main.MainLocalDB;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PassengerPartnerInfo_Table extends MainLocalDB {

	public static final String TABLE_NAME = "PASSENGER_PARTNER_INFO";

	public static enum Columns {
		/* "RqPartner_Address": "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran",
	      "RqPartner_Email": "mohebbi@eligasht.com",
	      "RqPartner_FirstNameFa": "مریم",
	      "RqPartner_Gender": "Female",
	      "RqPartner_LastNameFa": "محبی",
	      "RqPartner_Mobile": "09366053684",
	      "RqPartner_NationalCode": "0062532148",
	      "RqPartner_Tel": "21587632"*/
		RqPartner_Address(text), RqPartner_Email(text), RqPartner_FirstNameFa(text)
		,RqPartner_Gender(text), RqPartner_LastNameFa(text), RqPartner_Mobile(text)
		,RqPartner_NationalCode(text), RqPartner_Tel(text);
		
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

	public PassengerPartnerInfo_Table(Activity activity) {
		super(activity);
	}
/*
	public CursorManager getDatas() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", Columns.RequestId.value()
				+ "," + Columns.GoodCode.value(), 0, 0);
	}*/
	/*****************************************************************************************/
	public CursorManager getPartner() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", "", 0, 0);
	}

	/*****************************************************************************************/

	/*****************************************************************************************/
	public int insertData(String RqPartner_Address, String RqPartner_Email, String RqPartner_FirstNameFa,
			String RqPartner_Gender, String RqPartner_LastNameFa, String RqPartner_Mobile,
			String RqPartner_NationalCode, String RqPartner_Tel) {
		ContentValues cv = new ContentValues();
		
		/*RqPartner_Address(text), RqPartner_Email(text), RqPartner_FirstNameFa(text)
		,RqPartner_Gender(text), RqPartner_LastNameFa(text), RqPartner_Mobile(text)
		,RqPartner_NationalCode(text), RqPartner_Tel(text);*/
		
		
		cv.put(Columns.RqPartner_Address.value(), RqPartner_Address);
		cv.put(Columns.RqPartner_Email.value(), RqPartner_Email);
		
		cv.put(Columns.RqPartner_FirstNameFa.value(), RqPartner_FirstNameFa);
		cv.put(Columns.RqPartner_Gender.value(), RqPartner_Gender);
		cv.put(Columns.RqPartner_LastNameFa.value(), RqPartner_LastNameFa);
		
		cv.put(Columns.RqPartner_Mobile.value(), RqPartner_Mobile);
		cv.put(Columns.RqPartner_NationalCode.value(), RqPartner_NationalCode);
		cv.put(Columns.RqPartner_Tel.value(), RqPartner_Tel);
		
		
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
