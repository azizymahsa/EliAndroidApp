package com.reserv.myapplicationeli.views.activities;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


import com.reserv.myapplicationeli.GlobalApplication;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.tools.ExceptionHandler;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.db.local.Config_Table;
import com.reserv.myapplicationeli.views.dialogs.IDM_Dialog;


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

			} else {
				buildAlertMessageNoGps();
			}
		}
	}
	
	
	protected void buildAlertMessageNoGps() {
		IDM_Dialog dialog = new IDM_Dialog(this);
		dialog.setMessage("موقعیت یاب دستگاه شما غیر فعال است.");
		dialog.setCancelable(false);
		dialog.setAcceptButton("فعال سازی", new IDM_Dialog.OnAcceptInterface() {

			@Override
			public void accept() {
				startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			}
		});
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
	}


	public void updateBasicInfosFinished() {

	}

	public boolean checkPlayServiceVersion() {
		/*final IDM_Dialog dialog = new IDM_Dialog(this);
		dialog.setAcceptButton(new OnAcceptInterface() {

			@Override
			public void accept() {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("market://details?id="+ GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE)));
				dialog.dismiss();
				IDM_Activity.this.finish();
			}
		});
		dialog.setCancelButton("بستن", new OnCancelInterface() {

			@Override
			public void cancel() {
				dialog.dismiss();
				IDM_Activity.this.finish();
			}
		});
		int state = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());
		switch (state) {
		case ConnectionResult.SUCCESS:
			return true;
		case ConnectionResult.SERVICE_MISSING:
			dialog.setMessage("سرویس google play service یافت نشد. لطفا نصب نمایید");
			dialog.show();
			return false;
		case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
			dialog.setMessage("سرویس google play service بروز نمی باشد. لطفا بروزرسانی نمایید.");
			dialog.show();
			return false;
		case ConnectionResult.SERVICE_DISABLED:
			dialog.setMessage("سرویس google play service غیر فعال است.");
			dialog.show();
			return false;
		case ConnectionResult.SERVICE_INVALID:
			dialog.setMessage("سرویس google play service یافت نشد. لطفا نصب نمایید");
			dialog.show();
			return false;
		default:
			dialog.dismiss();
			IDM_Activity.this.finish();*/
			return false;
		//}
	}
}
