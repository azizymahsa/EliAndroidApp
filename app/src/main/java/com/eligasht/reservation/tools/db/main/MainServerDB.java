package com.eligasht.reservation.tools.db.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class MainServerDB {

/*	private String url = "jdbc:jtds:sqlserver://94.182.198.22:1433;DatabaseName=misdb";
	//private String url = "jdbc:jtds:sqlserver://192.168.5.110:1433;DatabaseName=idmrasa";
	private String userName = "gps";
	private String password = "gpsnokhbegan";*/
	/*private String userName = "admin";
	private String password = "123";
	private String url = "jdbc:jtds:sqlserver://192.168.5.110:1433;DatabaseName=idmrasa";
	private String userName = "admin";
	private String password = "123";*/
	/*private String url = "jdbc:jtds:sqlserver://192.168.0.18:1433;DatabaseName=idmrasa";
	private String userName = "sa";
	private String password = "PowerData%2a";*/
	//private String url = "jdbc:jtds:sqlserver://192.168.5.110:1433;DatabaseName=idmrasa";
	private String url = "jdbc:jtds:sqlserver://94.182.198.22:1433;DatabaseName=misdb";
	private String driver = "net.sourceforge.jtds.jdbc.Driver";
	private String userName = "gps";
	private String password = "gpsnokhbegan";

/*	private String url = "jdbc:jtds:sqlserver://94.101.182.249:14033;DatabaseName=idmrasa";
	//private String url = "jdbc:jtds:sqlserver://10.4.4.18:1433;DatabaseName=Kaboli";
	private String driver = "net.sourceforge.jtds.jdbc.Driver";
	private String userName = "tablet";
	private String password = "Idm@tablet";*/

	//private String url = "jdbc:jtds:sqlserver://192.168.5.100:1433;DatabaseName=idmrasa";
	/*private String url = "jdbc:jtds:sqlserver://94.182.198.22:1433;DatabaseName=misdb";
	private String driver = "net.sourceforge.jtds.jdbc.Driver";
	private String userName = "gps";
	private String password = "gpsnokhbegan";*/
	public Connection connection = null;
	private Statement statement = null;

	public MainServerDB() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void openDB() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url, userName,password);
				statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
				System.out.println("dataBASE:"+url);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDB() {
		// try {
		// connection.close();
		// } catch (Exception e) {
		// }
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
			statement.execute(query);
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
	protected int UPDATE_OR_INSERT_TO_DB(String tableName, String conditions,String valueSet, String columns, String values) {
		try {
			CursorManager test = SELECT_FROM_DB("*", tableName, conditions, "");
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
		// try {
		// Cursor test = SELECT_FROM_DB("*", tableName, conditions, "", 0, 1);
		// if (test != null && test.getCount() > 0) {
		// UPDATE_DB(tableName, valueSet, conditions);
		// } else {
		// INSERT_TO_DB(tableName, columns, values);
		// }
		// return 1;
		// } catch (Exception e) {
		// e.printStackTrace();
		// return 0;
		// }
		try {
			String queryFormat = "INSERT OR REPLACE INTO %s (%s) VALUES (%s)";
			String query = String.format(queryFormat, tableName, columns,values);
			openDB();
			statement.executeQuery(query);
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
	protected CursorManager SELECT_FROM_DB(String selecteds, String tableName,String conditions, String order) {
		CursorManager res = null;
		try {
			ResultSet result = null;
			String queryFormat = "SELECT %s FROM %s";
			if (!conditions.equals("*"))
				queryFormat += " WHERE %s";

			String query = String.format(queryFormat, selecteds, tableName,conditions, order);
			if (!order.equals(""))
				query += " ORDER BY " + order;
			openDB();
			result = statement.executeQuery(query);
			res = new CursorManager(result);
			closeDB();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
			return res;
		}
	}

	/**
	 * UPDATE %s SET %s WHERE %s
	 * 
	 * @param 1- TABLE_NAME
	 * @param 2- Columns='Values',
	 * @param 3- conditions
	 * **/
	protected int UPDATE_DB(String tableName, String valueSets,String conditions) {
		try {
			String queryFormat = "UPDATE %s SET %s WHERE %s ";
			String query = String.format(queryFormat, tableName, valueSets,conditions);
			openDB();
			statement.executeQuery(query);
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
			statement.executeQuery(query);
			closeDB();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
			return 0;
		}
	}

	public CursorManager QUERY(String query) {
		CursorManager res = null;
		try {
			ResultSet result = null;
			openDB();
			result = statement.executeQuery(query);
			res = new CursorManager(result);
			closeDB();
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
		}
		return res;
	}

}
