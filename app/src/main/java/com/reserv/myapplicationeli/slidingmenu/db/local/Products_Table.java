package com.reserv.myapplicationeli.slidingmenu.db.local;

import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainLocalDB;
import com.reserv.myapplicationeli.slidingmenu.system.GlobalApplication;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Products_Table extends MainLocalDB {

	public static final String TABLE_NAME = "PRODUCTS";

	public enum Columns {
		GoodCode(integer), GoodName(text), LatinName(text), GName(text), CountUnit(
				text), SecondUnit(text), UnitRatio(text), Stock(integer), LogicalAmount(
				integer), PFPrice(integer), PSPrice(integer), PUPrice(integer), LimitCount(text), Grade(text);
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

	public Products_Table() {
		super(GlobalApplication.getActivity());
	}

	/*****************************************************************************************/
	public int insertData(String goodCode, String goodName, String latinName,
			String gName, String countUnit, String secondUnit,
			String unitRatio, String stock, String logicalAmount,
			String pfPrice, String psPrice, String puPrice, String limit,
			String grade) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.GoodCode.value(), goodCode);
		cv.put(Columns.GoodName.value(), goodName);
		cv.put(Columns.LatinName.value(), latinName);
		cv.put(Columns.GName.value(), gName);
		cv.put(Columns.CountUnit.value(), countUnit);
		cv.put(Columns.SecondUnit.value(), secondUnit);
		cv.put(Columns.UnitRatio.value(), unitRatio);
		cv.put(Columns.Stock.value(), stock);
		cv.put(Columns.LogicalAmount.value(), logicalAmount);
		cv.put(Columns.PFPrice.value(), pfPrice);
		cv.put(Columns.PSPrice.value(), psPrice);
		cv.put(Columns.PUPrice.value(), puPrice);
		cv.put(Columns.LimitCount.value(), limit);
		cv.put(Columns.Grade.value(), grade);
		openDB();
		return (int) db.insert(TABLE_NAME, null, cv);
	}

	/*****************************************************************************************/
	public void insertOrUpdateStock(String goodCode, int stock, int lAmount) {
		CursorManager c = QUERY(String.format("SELECT %s FROM %s WHERE %s='%s'",Columns.GoodCode, TABLE_NAME, Columns.GoodCode,Columns.GoodCode));
		ContentValues cv = new ContentValues();
		cv.put(Columns.Stock.value(), stock);
		cv.put(Columns.LogicalAmount.value(), lAmount);
		if (c != null && c.getCount() > 0) {
			db.update(TABLE_NAME, cv,String.format("%s='%s'", Columns.GoodCode, goodCode), null);
		} else {
			cv.put(Columns.GoodCode.value(), goodCode);
			db.insert(TABLE_NAME, null, cv);
		}
	}

	/*****************************************************************************************/
	public CursorManager getProducers() {
		String query = String.format("SELECT * FROM (SELECT %s,COUNT(%s) COUNT FROM %s GROUP BY %s)r ORDER BY COUNT DESC ",
						Columns.GName, Columns.GoodCode, TABLE_NAME,
						Columns.GName);
		CursorManager c = QUERY(query);
		return c;
	}
	
	/*****************************************************************************************/
	public CursorManager getProductsFilter(String searchtext) {
		searchtext = searchtext.replace((char) 0x6cc, (char) 0x64A);
		String query = String.format("SELECT * FROM %s WHERE %s LIKE %s OR %s LIKE %s ;",
						 TABLE_NAME,
						Columns.GoodCode,"'%"+searchtext+"%'"
						,
						Columns.GoodName,"'%"+searchtext+"%'");
		CursorManager c = QUERY(query);
		return c;
	}
	/**************************************************************************************************/
/*	public CursorManager getProducts(ArrayList<String> columns,ArrayList<String> filters, String text) {
		text = text.replace((char) 0x6cc, (char) 0x64A);
		String condition = "";
		if (columns != null && text != null && text.length() > 0) {
			for (int i = 0; i < columns.size(); i++) {
				condition += " " + columns.get(i) + " LIKE '%" + text + "%' OR";
			}
		}
		if (condition.length() > 0) {
			condition = condition.substring(0, condition.length() - 3);
			condition = String.format("(%s)", condition);
		}
		String filter = "";
		if (filters != null) {
			for (int i = 0; i < filters.size(); i++) {
				filter += filters.get(i) + " AND ";
			}
		}
		if (filter.length() > 0) {
			filter = filter.substring(0, filter.length() - 5);
			if (condition.length() > 0) {
				condition = condition + " AND " + filter;
			} else
				condition = filter;
		}
		String selecteds = String.format("P.%s", Products_Table.Columns.GoodCode);
		String query = String
				.format("SELECT DISTINCT %s FROM %s AS P LEFT JOIN %s AS A ON A.%s=P.%s  %s ORDER BY P.%s",
						selecteds, Products_Table.TABLE_NAME,
						GAward_Table.TABLE_NAME, GAward_Table.Columns.GoodCode,
						Products_Table.Columns.GoodCode,
						condition.length() > 0 ? "WHERE " + condition : "",
						Products_Table.Columns.GoodCode);
		openDB();
		CursorManager c = QUERY(query);
		closeDB();
		return c;
	}*/

	/**************************************************************************************************/
	public CursorManager getRequestData(int goodCode) {
		String selecteds = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
				Columns.GoodCode, Columns.GoodName, Columns.CountUnit,
				Columns.SecondUnit, Columns.UnitRatio, Columns.PSPrice,
				Columns.Stock, Columns.LogicalAmount, Columns.LimitCount);
		CursorManager c = SELECT_FROM_DB(selecteds, TABLE_NAME,
				Columns.GoodCode + " = '" + goodCode + "'",
				Columns.GoodCode.value(), 0, 0);
		if (c == null) {
			c = SELECT_FROM_DB(selecteds, TABLE_NAME, Columns.GoodCode + " = '"
					+ goodCode + "'", Columns.GoodCode.value(), 0, 0);
		}
		return c;
	}

	/*****************************************************************************************/
	public CursorManager getFee(int goodCode) {
		return SELECT_FROM_DB(Columns.PSPrice.value(), TABLE_NAME,
				Columns.GoodCode + "=" + goodCode, "", 0, 0);
	}

	/*****************************************************************************************/
	public CursorManager getAllProducts() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", "", 0, 0);
	}

	/*****************************************************************************************/

	public void updatePrices(int goodCode, int psPrice, int pfPrice, int puPrice) {
		if (!db.isOpen())
			openDB();
		ContentValues cv = new ContentValues();
		cv.put(Columns.PSPrice.value(), psPrice);
		cv.put(Columns.PFPrice.value(), pfPrice);
		cv.put(Columns.PUPrice.value(), puPrice);
		db.update(TABLE_NAME, cv,
				String.format("%s='%s'", Columns.GoodCode, goodCode), null);
	}

	/*****************************************************************************************/
	public int getAvailable(int GoodCode) {
		String condition = Columns.GoodCode.value() + "=" + GoodCode;
		try {
			return SELECT_FROM_DB(Columns.Stock.value(), TABLE_NAME, condition,
					Columns.GoodCode.value(), 0, 0).getInt(Columns.Stock.value());
		} catch (Exception e) {
			return 0;
		}
	}

	/*****************************************************************************************/

	public void updateStocks(int goodCode, int newAvailable, int logicalAmount) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.LogicalAmount.value(), logicalAmount);
		cv.put(Columns.Stock.value(), newAvailable);
		db.update(TABLE_NAME, cv,
				String.format("%s='%s'", Columns.GoodCode, goodCode), null);
	}

	/*****************************************************************************************/
	public CursorManager getProductBachDatas(int goodCode) {
		CursorManager c = SELECT_FROM_DB(
				Columns.GoodCode.value() + "," + Columns.PFPrice.value() + ","
						+ Columns.PUPrice.value() + ","
						+ Columns.PSPrice.value() + ","
						+ Columns.SecondUnit.value() + ","
						+ Columns.UnitRatio.value() + ","
						+ Columns.CountUnit.value() + ","
						+ Columns.GName.value(), TABLE_NAME, Columns.GoodCode
						+ "='" + goodCode + "'", Columns.GoodCode.value(), 0, 0);
		return c;
	}

	/*****************************************************************************************/
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

	/*****************************************************************************************/

	@Override
	public void upgrade(SQLiteDatabase db, int lastVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		create();
	}

	/*****************************************************************************************/

	@Override
	public void dropTable() {
		dropTable(TABLE_NAME);
	}

}
