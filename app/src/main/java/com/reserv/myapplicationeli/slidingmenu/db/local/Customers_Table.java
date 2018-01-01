package com.reserv.myapplicationeli.slidingmenu.db.local;

import com.reserv.myapplicationeli.slidingmenu.db.local.Products_Table.Columns;
import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainLocalDB;
import com.reserv.myapplicationeli.slidingmenu.system.GlobalApplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Customers_Table extends MainLocalDB {

	public static final String TABLE_NAME = "CUSTOMERS";

	public static enum Columns {
		CUSTOMER_ID(integer), CUSTOMER_NAME(text), ADDRESS(text), TELEPHONE(
				text), FAX(text), MANAGER_TEL(text), LATITUDE(text), LONGITUDE(
				text), TYPE(text), PATH_CODE(text), MANAGER_NAME(text), OWNERSHIP_TYPE(
				text), CREDIT_VALUE(text), FACTOR_MULTIPLIER(text), RECEIVE_MULTIPLIER(
				text), FOREIGN_STATUS(text), VISITOR_ID(integer), POSTAL_CODE(
				text), MELLI_CODE(text), BLOCK45(text);
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

	public Customers_Table() {
		super(GlobalApplication.getActivity());
	}

	public int insertData(String customerID, String name, String address,
			String telephoe, String fax, String managerTel, String latitude,
			String longitude, String type, String pathCode, String managerName,
			String ownershipType, String CreditValue, String factorMultiplier,
			String receiveMultiplier, String foriegnStatus, String visitorID,
			String postalCode, String melliCode, String block) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.CUSTOMER_ID.value(), customerID);
		cv.put(Columns.CUSTOMER_NAME.value(), name);
		cv.put(Columns.ADDRESS.value(), address);
		cv.put(Columns.TELEPHONE.value(), telephoe);
		cv.put(Columns.FAX.value(), fax);
		cv.put(Columns.MANAGER_TEL.value(), managerTel);
		cv.put(Columns.LATITUDE.value(), latitude);
		cv.put(Columns.LONGITUDE.value(), longitude);
		cv.put(Columns.TYPE.value(), type);
		cv.put(Columns.PATH_CODE.value(), pathCode);
		cv.put(Columns.MANAGER_NAME.value(), managerName);
		cv.put(Columns.OWNERSHIP_TYPE.value(), ownershipType);
		cv.put(Columns.CREDIT_VALUE.value(), CreditValue);
		cv.put(Columns.FACTOR_MULTIPLIER.value(), factorMultiplier);
		cv.put(Columns.RECEIVE_MULTIPLIER.value(), receiveMultiplier);
		cv.put(Columns.FOREIGN_STATUS.value(), foriegnStatus);
		cv.put(Columns.VISITOR_ID.value(), visitorID);
		cv.put(Columns.POSTAL_CODE.value(), postalCode);
		cv.put(Columns.MELLI_CODE.value(), melliCode);
		cv.put(Columns.BLOCK45.value(), block);
		openDB();
		return (int) db.insert(TABLE_NAME, null, cv);
	}

	public void updatePosition(int customerID, double latitude, double longitude) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.LATITUDE.value(), latitude);
		cv.put(Columns.LONGITUDE.value(), longitude);
		openDB();
		db.update(TABLE_NAME, cv,
				String.format("%s = %s", Columns.CUSTOMER_ID, customerID), null);
	}

	public void edit(int customerID, int visitorID) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.VISITOR_ID.value(), visitorID);
		openDB();
		db.update(TABLE_NAME, cv, Columns.CUSTOMER_ID + "=" + customerID, null);
		closeDB();
	}

	public CursorManager getCustomers() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", Columns.PATH_CODE + ","
				+ Columns.CUSTOMER_ID.value(), 0, 0);
	}

	public CursorManager getPaths() {
		String query = String.format(
				"SELECT %s,COUNT(%s) AS COUNT FROM %s GROUP BY %s",
				Columns.PATH_CODE, Columns.PATH_CODE, TABLE_NAME,
				Columns.PATH_CODE);
		return QUERY(query);
	}

	public CursorManager getCustomersByPath(String path) {
		return SELECT_FROM_DB("*", TABLE_NAME,
				String.format("%s='%s'", Columns.PATH_CODE, path),
				Columns.PATH_CODE + "," + Columns.CUSTOMER_ID.value(), 0, 0);
	}

	public CursorManager getCustomers(String text) {
		text = text.replace((char) 0x6cc, (char) 0x64A);
		String condition = "";
		condition += " " + Columns.CUSTOMER_ID.value() + " LIKE '%" + text
				+ "%' OR";
		condition += " " + Columns.CUSTOMER_NAME.value() + " LIKE '%" + text
				+ "%'";
		return SELECT_FROM_DB("*", TABLE_NAME, condition, Columns.PATH_CODE
				+ "," + Columns.CUSTOMER_ID.value(), 0, 0);
	}

	public CursorManager getPathList() {
		return SELECT_FROM_DB("DISTINCT " + Columns.PATH_CODE, TABLE_NAME, "*",
				Columns.PATH_CODE.value(), 0, 0);
	}

	public CursorManager getSearchedCustomers(String idSearch, String nameSearch) {
		idSearch = idSearch.replace((char) 0x6cc, (char) 0x64A);
		nameSearch = nameSearch.replace((char) 0x6cc, (char) 0x64A);
		String filter = "";
		if (idSearch.length() > 0) {
			filter = String.format("%s LIKE '%%%s%%'", Columns.CUSTOMER_ID, idSearch);
		}
		if (nameSearch.length() > 0) {
			if (filter.length() > 0)
				filter += " AND ";
			filter += String.format("%s LIKE '%%%s%%'", Columns.CUSTOMER_NAME, nameSearch);
		}
		return SELECT_FROM_DB("*", TABLE_NAME, filter.length() > 0 ? filter
				: "*", Columns.PATH_CODE + "," + Columns.CUSTOMER_ID.value(),
				0, 0);
	}

	public CursorManager getCustomers(String text, String path) {
		if (path != null && path.equals("")) {
			path = "null";
		}
		text = text.replace((char) 0x6cc, (char) 0x64A);
		String condition = "";

		if (text.length() > 0) {
			condition += Columns.CUSTOMER_ID.value() + " LIKE '%" + text
					+ "%' OR";
			condition += " " + Columns.CUSTOMER_NAME.value() + " LIKE '%"
					+ text + "%'";
		}
		if (path != null && path.length() > 0) {
			if (condition.length() > 0)
				condition = "(" + condition + ") AND";
			condition = String.format("%s %s='%s'", condition,Columns.PATH_CODE, path);
		}
		if (condition.length() == 0)
			condition = "*";
		return SELECT_FROM_DB("*", TABLE_NAME, condition, Columns.PATH_CODE
				+ "," + Columns.CUSTOMER_ID.value(), 0, 0);
	}

	public CursorManager getCustomer(int id) {
		return SELECT_FROM_DB("*", TABLE_NAME, Columns.CUSTOMER_ID + "='" + id
				+ "'", Columns.CUSTOMER_ID.value(), 0, 0);
	}
	/*****************************************************************************************/
	public CursorManager getCustomersFilter(String searchtext) {
		searchtext = searchtext.replace((char) 0x6cc, (char) 0x64A);
		String query = String.format("SELECT * FROM %s WHERE %s LIKE %s OR %s LIKE %s ;",
						 TABLE_NAME,
						Columns.CUSTOMER_ID,"'%"+searchtext+"%'"
						,
						Columns.CUSTOMER_NAME,"'%"+searchtext+"%'");
		CursorManager c = QUERY(query);
		return c;
	}
	/**************************************************************************************************/
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
