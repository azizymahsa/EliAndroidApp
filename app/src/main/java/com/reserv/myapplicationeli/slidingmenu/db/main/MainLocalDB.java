package com.reserv.myapplicationeli.slidingmenu.db.main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.reserv.myapplicationeli.slidingmenu.db.local.Customers_Table.Columns;


public abstract class MainLocalDB extends SQLiteOpenHelper {
	protected SQLiteDatabase db;
	private final static int DATABASE_VERSION = 3;
	protected static String text = "TEXT";
	protected static String integer = "INTEGER";
	protected static String longType = "LONG";

	public MainLocalDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public MainLocalDB(Context context) {
		super(context, "DatabaseName", null, DATABASE_VERSION);
		db = getWritableDatabase();
		create();
	}

	public void openDB() {
		if (db == null || !db.isOpen())
			db = getWritableDatabase();
	}

	public void closeDB() {
		try {
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * INSERT INTO %s (%s) VALUES (%s)
	 * 
	 * @param 1- TABLE_NAME
	 * @param 2- Columns
	 * @param 3- Values
	 * **/
	protected int INSERT_TO_DB(Object... args) {
		try {
			String queryFormat = "INSERT INTO %s (%s) VALUES (%s)";
			String query = String.format(queryFormat, args);
			openDB();
			db.execSQL(query);
			closeDB();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
			return 0;
		}
	}

	/**
	 * IF EXISTS (SELECT * FROM %s WHERE %s) UPDATE %s SET %s WHERE %s
	 * ELSEINSERT INTO %s (%s) VALUES (%s)
	 * 
	 * @param 1- TABLE_NAME
	 * @param 2- Conditions
	 * @param 3- ValueSet for Update
	 * @param 4- Columns for insert
	 * @param 5- Values for insert
	 * **/
	protected int UPDATE_OR_INSERT_TO_DB(String tableName, String conditions,
			String valueSet, String columns, String values) {
		try {
			CursorManager test = SELECT_FROM_DB("*", tableName, conditions, "",
					0, 1);
			if (test != null && test.getCount() > 0) {
				UPDATE_DB(tableName, valueSet, conditions);
			} else {
				INSERT_TO_DB(tableName, columns, values);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * INSERT OR REPLACE INTO %s (%s) VALUES (%s)
	 * 
	 * @note Columns should contains primary key
	 * 
	 * @param 1- TABLE_NAME
	 * @param 2- Conditions
	 * @param 3- ValueSet for Update
	 * @param 4- Columns for insert
	 * @param 5- Values for insert
	 * **/
	protected int UPDATE_OR_INSERT_TO_DB(String tableName, String columns,
			String values) {
		try {
			String queryFormat = "INSERT OR REPLACE INTO %s (%s) VALUES (%s)";
			String query = String.format(queryFormat, tableName, columns,
					values);
			openDB();
			db.execSQL(query);
			closeDB();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
			return 0;
		}
	}

	/**
	 * GET %s FROM %s WHERE %s ORDERED BY %s
	 * 
	 * @param 1- Selected Columns
	 * @param 2- Table Name
	 * @param 3- Conditions
	 * @param 4- Order
	 * **/
	protected CursorManager SELECT_FROM_DB(String selecteds, String tableName,
			String conditions, String order, int startLimit, int numberLimit) {
		CursorManager cursorManager = null;
		try {
			Cursor cursor;
			String queryFormat = "SELECT %s FROM %s";
			if (!conditions.equals("*"))
				queryFormat += " WHERE %s";

			String query = String.format(queryFormat, selecteds, tableName,
					conditions, order);
			if (!order.equals(""))
				query += " ORDER BY " + order;
			if (numberLimit > 0) {
				query += " limit " + startLimit + "," + numberLimit;
			}
			openDB();
			cursor = db.rawQuery(query, null);
			cursor.moveToFirst();
			closeDB();
			cursorManager = new CursorManager(cursor);
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
		}
		return cursorManager;
	}

	/**
	 * GET %s FROM %s WHERE %s ORDERED BY %s
	 * 
	 * @param 1- Selected Columns
	 * @param 2- Table Name
	 * @param 3- Conditions
	 * @param 4- Order
	 * **/
	protected CursorManager SELECT_FROM_DB_WITHOUT_DB_MANAGEMENT(
			String selecteds, String tableName, String conditions,
			String order, int startLimit, int numberLimit) {
		CursorManager cursorManager = null;
		try {
			Cursor cursor;
			String queryFormat = "SELECT %s FROM %s";
			if (!conditions.equals("*"))
				queryFormat += " WHERE %s";

			String query = String.format(queryFormat, selecteds, tableName,
					conditions, order);
			if (!order.equals(""))
				query += " ORDER BY " + order;
			if (numberLimit > 0) {
				query += " limit " + startLimit + "," + numberLimit;
			}
			cursor = db.rawQuery(query, null);
			cursor.moveToFirst();
			cursorManager = new CursorManager(cursor);
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
		}
		return cursorManager;
	}

	/**
	 * UPDATE %s SET %s WHERE %s
	 * 
	 * @param 1- TABLE_NAME
	 * @param 2- Columns='Values',
	 * @param 3- conditions
	 * **/
	protected int UPDATE_DB(String tableName, String valueSets,
			String conditions) {
		try {
			String queryFormat = "UPDATE %s SET %s WHERE %s ";
			String query = String.format(queryFormat, tableName, valueSets,
					conditions);
			openDB();
			db.execSQL(query);
			closeDB();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
			return 0;
		}
	}

	/**
	 * DELETE FROM %s WHERE %s
	 * 
	 * @param 1- TABLE_NAME
	 * @param 2- Conditions
	 * **/
	protected int DELETE_FROM_DB(String tableName, String condition) {
		try {
			String queryFormat = "DELETE FROM %s WHERE %s";
			String query = String.format(queryFormat, tableName, condition);
			openDB();
			db.execSQL(query);
			closeDB();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
			return 0;
		}
	}

	public void CheckAddColumn(String TableName, String columnName,
			String columnType) {
		boolean check = false;
		for (int i = 0; i < Columns.values().length; i++) {
			if (Columns.values()[i].equals(columnName)) {
				check = true;
				break;
			}
		}
		if (!check)
			try {
				db.execSQL("ALTER TABLE " + TableName + " ADD COLUMN "
						+ columnName + " " + columnType);
			} catch (Exception e) {
			}
	}

	public CursorManager QUERY(String query) {
		CursorManager cursorManger = null;
		Cursor cursor = null;
		try {
			openDB();
			cursor = db.rawQuery(query, null);
			cursor.moveToFirst();
			closeDB();
			cursorManger = new CursorManager(cursor);
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
		}
		return cursorManger;
	}

	protected void dropTable(String tableName) {
		openDB();
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		create();
		closeDB();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		upgrade(db, oldVersion, newVersion);
	}

	public abstract void dropTable();

	public abstract void create();

	public abstract void upgrade(SQLiteDatabase arg0, int lastVersion,
			int newVersion);
}
