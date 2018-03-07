package com.eligasht.reservation.views.activities;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


import com.eligasht.reservation.base.GlobalApplication;
import com.eligasht.R;
import com.eligasht.reservation.tools.ExceptionHandler;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.db.local.Config_Table;


public class IDM_Activity extends FragmentActivity {
	public static boolean isDBInitiated = false;
	public boolean isBase;
	public Location baseLocation = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
		GlobalApplication.activityStack.add(this);
		GlobalApplication.setActivity(this);
		if (!isDBInitiated) {


			isDBInitiated = true;
		}
		Config_Table config = new Config_Table();
		if (!config.getValue(Config_Table.VERSION).equals(Utility.getVersionCode())) {
			dropAllTables();
			config.updateData(Config_Table.VERSION, Utility.getVersionCode());
			File f = new File(GlobalApplication.getLogsDirectoryAddress());
			if (f.exists() && f.isDirectory()) {
				File[] childs = f.listFiles();
				for (File file : childs) {
					file.delete();
				}
			}
		}
		View v = findViewById(R.id.page);
		if (v != null)
			Utility.applyFonts(v);
	}

	public void dropAllTables() {


		new Config_Table().updateData(Config_Table.LAST_UPDATE, "");
	}

	@Override
	protected void onStart() {
		super.onStart();
		View v = findViewById(R.id.page);
		if (v != null)
			Utility.applyFonts(v);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			GlobalApplication.setActivity(this);
			final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

//			} else {
//				buildAlertMessageNoGps();
//			}
			}
		}
	}
}
	
	
//	protected void buildAlertMessageNoGps() {
//		IDM_Dialog dialog = new IDM_Dialog(this);
//		dialog.setMessage(getString(R.string.your_gps_is_off));
//		dialog.setCancelable(false);
//		dialog.setAcceptButton(getString(R.string.activation), new IDM_Dialog.OnAcceptInterface() {
//
//			@Override
//			public void accept() {
//				startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//			}
//		});
//		dialog.show();
//	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
//		super.onActivityResult(requestCode, resultCode, intent);
//	}

//}
