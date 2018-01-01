package com.reserv.myapplicationeli.slidingmenu.db.server.Interfaces;

import com.reserv.myapplicationeli.slidingmenu.db.Tables.TabletUpdateMobileSoftware_TABLE;
import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.db.main.MainServerDB;
import com.reserv.myapplicationeli.slidingmenu.tools.Utility;

public class CheckBasicInfos extends MainServerDB {
	public String GetLogsAddress() {
		String url = "";
		String condition = String.format("%s='Logs_Pakhsh'",TabletUpdateMobileSoftware_TABLE.Columns.SOFTWARENAME.value());
		CursorManager c = SELECT_FROM_DB("*",TabletUpdateMobileSoftware_TABLE.TABLE_NAME, condition, "");
		if (c != null && c.getCount() > 0) {
			url = c.getString(TabletUpdateMobileSoftware_TABLE.Columns.URLANDSQL.value());
		}
		return url;
	}

	public String GetPhotosAddress() {
		String url = "";
		String condition = String.format("%s='ProductsPhoto'",
				TabletUpdateMobileSoftware_TABLE.Columns.SOFTWARENAME.value());
		CursorManager c = SELECT_FROM_DB("*",TabletUpdateMobileSoftware_TABLE.TABLE_NAME, condition, "");
		if (c != null && c.getCount() > 0) {
			url = c.getString(TabletUpdateMobileSoftware_TABLE.Columns.URLANDSQL.value());
		}
		return url;
	}

	public String GetSoftwareAddress() {
		String url = "";
		String condition = String.format(
				"cast(%s AS FLOAT) > cast ('%s' AS FLOAT) AND %s='Pakhsh_New'",
				TabletUpdateMobileSoftware_TABLE.Columns.VER.value(),
				Utility.getVersionCode(),
				TabletUpdateMobileSoftware_TABLE.Columns.SOFTWARENAME.value());
		CursorManager c = SELECT_FROM_DB("*",
				TabletUpdateMobileSoftware_TABLE.TABLE_NAME, condition,
				String.format("cast(%s AS FLOAT) DESC",
						TabletUpdateMobileSoftware_TABLE.Columns.VER.value()));
		if (c != null && c.getCount() > 0) {
			url = c.getString(TabletUpdateMobileSoftware_TABLE.Columns.URLANDSQL
					.value());
		}
		return url;
	}
}
