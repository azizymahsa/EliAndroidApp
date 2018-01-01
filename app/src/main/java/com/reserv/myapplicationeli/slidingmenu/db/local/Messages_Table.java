package com.reserv.myapplicationeli.slidingmenu.db.local;

import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainLocalDB;
import com.reserv.myapplicationeli.slidingmenu.system.GlobalApplication;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Messages_Table extends MainLocalDB {

	private final String TABLE_NAME = "MESSAGES";

	public enum Columns {
		MESSAGE_ID(integer), SENDER(text), SUBJECT(text), CONTENT(text), DATE(
				text), SEEN(text), FLAG(integer);

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

	public Messages_Table() {
		super(GlobalApplication.getContext());
	}

	public CursorManager getMessage(int messageID) {
		String conditions = Columns.MESSAGE_ID + "='" + messageID + "'";
		return SELECT_FROM_DB("*", TABLE_NAME, conditions,
				Columns.MESSAGE_ID.value(), 0, 0);
	}

	public CursorManager getMessages() {
		return SELECT_FROM_DB("*", TABLE_NAME, "*", Columns.MESSAGE_ID.value(),
				0, 0);
	}

	public int getUnreadMessageCount() {
		try {
			return SELECT_FROM_DB(Columns.MESSAGE_ID.value(), TABLE_NAME,
					String.format("%s='%s'", Columns.SEEN.value(), "false"),
					"", 0, 0).getCount();
		} catch (Exception e) {
			return 0;
		}
	}

	public int getTopMessageID() {
		try {
			return SELECT_FROM_DB(Columns.MESSAGE_ID.value(), TABLE_NAME, "*",
					Columns.MESSAGE_ID.value() + " DESC", 0, 1).getInt(
					Columns.MESSAGE_ID.value());
		} catch (Exception e) {
			return 0;
		}
	}

	public void setMessageAsSeen(int messageID) {
		UPDATE_DB(TABLE_NAME, String.format("%s = '%s'", Columns.SEEN.value(),
				"true"), String.format("%s = '%s'", Columns.MESSAGE_ID.value(),
				messageID));
	}

	public boolean isExists(int messageID) {
		String conditions = Columns.MESSAGE_ID + "='" + messageID + "'";
		return SELECT_FROM_DB_WITHOUT_DB_MANAGEMENT("*", TABLE_NAME,
				conditions, Columns.MESSAGE_ID.value(), 0, 0).getCount() == 1;
	}

	public void removeNotFlagedMessges() {
		db.delete(TABLE_NAME, Columns.FLAG.value() + "=" + 0, null);
	}

	public void resetFlags() {
		ContentValues cv = new ContentValues();
		cv.put(Columns.FLAG.value(), 0);
		db.update(TABLE_NAME, cv, null, null);
	}

	public void setFlag(int messageID) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.FLAG.value(), 1);
		db.update(TABLE_NAME, cv, Columns.MESSAGE_ID.value() + "=" + messageID,
				null);
	}

	public void insertMessage(String messageID, String sender, String subject,
			String content, String date) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.MESSAGE_ID.value(), messageID);
		cv.put(Columns.SENDER.value(), sender);
		cv.put(Columns.SUBJECT.value(), subject);
		cv.put(Columns.CONTENT.value(), content);
		cv.put(Columns.DATE.value(), date);
		cv.put(Columns.SEEN.value(), "false");
		db.insert(TABLE_NAME, null, cv);
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
