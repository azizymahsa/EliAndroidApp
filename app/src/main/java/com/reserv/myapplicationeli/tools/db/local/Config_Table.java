package com.reserv.myapplicationeli.tools.db.local;

import com.reserv.myapplicationeli.tools.db.main.CursorManager;
import com.reserv.myapplicationeli.tools.db.main.MainLocalDB;

import android.database.sqlite.SQLiteDatabase;


public class Config_Table extends MainLocalDB {

	private final String TABLE_NAME = "CONFIG";
	public static final String VISITOR_MODE = "visitorMode";
	public static final String INV_CODE = "invCode";
	public static final String USER_ID = "UserId";
	public static final String VISITOR_ID = "visitorId";
	public static final String IS_ACTIVE = "IsActive";
	public static final String USER_MOBILE = "VisitorMobile";
	public static final String USER_JOB = "UserJob";
	public static final String USER_NAME = "UserName";
	public static final String VISITOR_NAME = "VisitorName";
	public static final String SIM_SERIAL = "SimSerial";
	//
	public static final String LAST_UPDATE_AWARD = "LastUpdateAward";
	public static final String LAST_UPDATE_DASHBORD = "LastUpdateDashbord";
	public static final String LAST_UPDATE_STOCK = "LastUpdateStock";
	public static final String LAST_UPDATE_KARTABL = "LastUpdateKartabl";
	//
	public static final String LAST_UPDATE = "LastUpdate";
	public static final String IS_PASSWORD_SET = "isPasswordSet";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_TYPE = "passwordType";
	public static final String LAST_CUSTOMER_VISITED = "lastCustomerVisited";
	public static final String VERSION = "version";
	public static final String LAST_UPDATE_TIME_REPORTS = "lastUpdateTimeReports";
	public static final String COMPANY_NAME = "companyName";
	public static final String INVENTORY_NAME = "inventoryName";
	public static final String IS_ADMIN = "isAdmin";
	public static final String GPS_TIME = "GPS_TIME";
	public static final String GPS_DISTANCE = "GPS_DISTANCE";
	public static final String GPS_TIME_AUTOMATE = "GPS_TIME_AUTOMATE";
	public static final String GPS_DISTANCE_AUTOMATE = "GPS_DISTANCE_AUTOMATE";
	public static final String LAST_POSITION_TIME = "LAST_POSITION_TIME";
	public static final String USER_LAT = "VisitorLat";
	public static final String USER_LNG = "VisitorLng";
	public static final String CHECK_PREVIOUS_VERSION = "CheckPreviousVersion";
	public static final String IS_PASSWORD_SUBMITTED = "IsPasswordSubmitted";

	private enum Columns {
		PARAM, VALUE;
		public String value() {
			return this.name();
		}
	};

	public Config_Table() {
		super(GlobalApplication.getContext());
	}

	public int updateData(String param, String value) {
		try {
			String conditions = String.format("%s='%s'", Columns.PARAM, param);
			String valueSet = String.format("%s='%s'", Columns.VALUE, value);
			String columns = String.format("%s,%s", Columns.PARAM,Columns.VALUE);
			String values = String.format("'%s','%s'", param, value);
			return UPDATE_OR_INSERT_TO_DB(TABLE_NAME, conditions, valueSet,columns, values);
		} catch (Exception e) {
			return 0;
		}
	}

	public String getValue(String param) {
		CursorManager c = SELECT_FROM_DB(Columns.VALUE.value(), TABLE_NAME,Columns.PARAM + "='" + param + "'", "", 0, 0);
		String value = c.getCount() > 0 ? c.getString(Columns.VALUE.value())
				: "";
		return value;
	}

	public long getLongValue(String param) {
		CursorManager c = SELECT_FROM_DB(Columns.VALUE.value(), TABLE_NAME,
				Columns.PARAM + "='" + param + "'", "", 0, 0);
		long value = c.getCount() > 0 ? c.getLong(Columns.VALUE.value()) : 0;
		return value;
	}

	public int getIntValue(String param) {
		CursorManager c = SELECT_FROM_DB(Columns.VALUE.value(), TABLE_NAME,
				Columns.PARAM + "='" + param + "'", "", 0, 0);
		int value = c.getCount() > 0 ? c.getInt(Columns.VALUE.value()) : 0;
		return value;
	}

	@Override
	public void create() {
		String sqlFormat = "CREATE TABLE IF NOT EXISTS %s (%s TEXT,%s TEXT)";
		String sql = String.format(sqlFormat, TABLE_NAME, Columns.PARAM,
				Columns.VALUE);
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
