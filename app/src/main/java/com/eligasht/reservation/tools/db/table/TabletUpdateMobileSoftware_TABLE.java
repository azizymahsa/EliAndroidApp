package com.eligasht.reservation.tools.db.table;

public class TabletUpdateMobileSoftware_TABLE {
	public static final String TABLE_NAME = "TabletUpdateMobileSoftware";
	public enum Columns {
		OS("os"),VER("ver"),URLANDSQL("urlAndSql"),SOFTWARENAME("SoftwareName"),ISVALID("isValid");

		private String parameterValue;

		Columns(String value) {
			this.parameterValue = value;
		}

		public String value() {
			return this.parameterValue;
		}
	}
}