package com.reserv.myapplicationeli.slidingmenu.db.local;

import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainLocalDB;
import com.reserv.myapplicationeli.slidingmenu.system.GlobalApplication;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * 
 * @author Mahsa Azizi
 */
public class PromotionDesc_Table extends MainLocalDB {

	public final static String TABLE_NAME = "PROMOTIONDESC";
///CONDITIONID("ConditionID"),PRIZEDETAILID("PrizeDetailID"),PROMOTIONDESC("PromotionDesc"),PROMOTIONTYPEDESC("PromotionTypeDesc"),GOODCODE("GoodCode");
	public enum Columns {
		ConditionID(text), PrizeDetailID(text), PromotionDesc(text), PromotionTypeDesc(text), GoodCode(text),PrizeType(text),PrizeId(text),CreditDay(text);
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

	public PromotionDesc_Table() {
		super(GlobalApplication.getActivity());
	}
	public CursorManager getCreaditday(int prizeDetailId) {
		String query = String.format("SELECT * FROM %s WHERE %s=%s",TABLE_NAME, Columns.PrizeDetailID,prizeDetailId);
		return QUERY(query);
	}

	public CursorManager getConditionId(int goodCode) {
		String query = String.format("SELECT * FROM %s WHERE %s=%s",TABLE_NAME, Columns.GoodCode,goodCode);
		return QUERY(query);
	}
	
	public CursorManager getAwardsNew(int goodCode) {
		String query = String.format("SELECT * FROM %s WHERE %s=%s",TABLE_NAME, Columns.GoodCode,goodCode);
		return QUERY(query);
	}//SELECT CustomerName,City FROM Customers; 
	/*public int getPromotionTypeDesc(int goodCode) {
		String query = String.format("SELECT TOP 1  %s FROM %s WHERE %s=%s",Columns.PromotionTypeDesc.value(), TABLE_NAME, Columns.GoodCode,goodCode);
		return QUERY(query).getInt(Columns.PromotionTypeDesc.value());
	}*/
	/*****************************************************************************************/
	/*public int getPromotionTypeDesc(int GoodCode) {
		String condition = Columns.GoodCode.value() + "=" + GoodCode;
		try {
			return SELECT_FROM_DB(Columns.PromotionTypeDesc.value(), TABLE_NAME, condition,Columns.GoodCode.value(), 0, 0).getInt(Columns.PromotionTypeDesc.value());
		} catch (Exception e) {
			return 0;
		}
	}
*/
	/*****************************************************************************************/
	/**
	 * 
	 * @param goodCode
	 * @return {@link CursorManager}
	 */
	/*public CursorManager getBatchs(int goodCode) {
		String conditions = Columns.GoodCode + "='" + goodCode + "'";
		String columns = String.format("%s,%s,%s", Columns.BachNo,
				Columns.BachYear, Columns.StrEpireDate);
		return SELECT_FROM_DB(columns, TABLE_NAME, conditions,
				Columns.BachNo.value(), 0, 0);
	}*/

	/**
	 * 
	 * @param goodCode
	 * @param bachNo
	 * @param StrExpireDate
	 * @param bachYear
	 */
	public void insertPromotions(String conditionId, String prizeDetailId, String PromotionDesc, String PromotionTypeDesc,String GoodCode,String PrizeType,String PrizeID,String CreditDay) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.ConditionID.value(), conditionId);
		cv.put(Columns.PrizeDetailID.value(), prizeDetailId);
		cv.put(Columns.PromotionDesc.value(), PromotionDesc);
		cv.put(Columns.PromotionTypeDesc.value(), PromotionTypeDesc);
		cv.put(Columns.GoodCode.value(), GoodCode);
		cv.put(Columns.PrizeType.value(), PrizeType);
		cv.put(Columns.PrizeId.value(), PrizeID);
		cv.put(Columns.CreditDay.value(), CreditDay);
		openDB();
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
