package com.reserv.myapplicationeli.tools;

import com.reserv.myapplicationeli.tools.db.local.Config_Table;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Calendar;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build;




public class ExceptionHandler implements UncaughtExceptionHandler {

	private UncaughtExceptionHandler defaultUEH;

	public ExceptionHandler() {
		this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
	}

	public void uncaughtException(Thread t, Throwable e) {
		log(e, "error", "");
		defaultUEH.uncaughtException(t, e);
	}

	public void log(Throwable e, String type, String logExtraInfos) {
		StackTraceElement[] arr = e.getStackTrace();

		String report = "";
		report += "USER_ID: "
				+ new Config_Table().getValue(Config_Table.USER_ID) + "\n";
		report += "Version: " + Utility.getVersionInfos() + "\n";
		report += "Version Code: " + Utility.getVersionCode() + "\n";
		report += "Device Version: " + Build.VERSION.RELEASE + "\n";
		//int state = GooglePlayServicesUtil.isGooglePlayServicesAvailable(GlobalApplication.getContext());
		//report += "GooglePlayService State: " + state + "\n";
		/*report += "GooglePlayService Version: "
				+ GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE
				+ "\n";*/
		report += "Model: " + Build.MODEL + "\n";
		ActivityManager activityManager = (ActivityManager) GlobalApplication
				.getContext().getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo memoryInfo = new MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		report += "Memory: " + memoryInfo.availMem + "_" + memoryInfo.threshold
				+ " => low? = " + memoryInfo.lowMemory + "\n\n";

		report += e.toString() + "\n\n";
		report += "--------- Stack trace ---------\n\n";
		for (int i = 0; i < arr.length; i++) {
			report += "    " + arr[i].toString() + "\n";
		}
		report += "-------------------------------\n\n";
		report += "--------- Extra Infos --------\n\n";
		report += logExtraInfos + "\n";
		report += "-------------------------------\n\n";
		report += "--------- Cause ---------\n\n";
		Throwable cause = e.getCause();
		if (cause != null) {
			report += cause.toString() + "\n\n";
			arr = cause.getStackTrace();
			for (int i = 0; i < arr.length; i++) {
				report += "    " + arr[i].toString() + "\n";
			}
		}
		report += "-------------------------------\n\n";

		try {
			CalendarTool c = new CalendarTool();
			Calendar c1 = Calendar.getInstance();
			FileOutputStream trace = new FileOutputStream(
					GlobalApplication.getLogsDirectoryAddress()
							+ String.format("%s_%s_%s-%s-%s_%s-%s-%s-%s.txt",
									type, new Config_Table()
											.getValue(Config_Table.USER_ID), c
											.getIranianYear(), c
											.getIranianMonth(), c
											.getIranianDay(), c1
											.get(Calendar.HOUR_OF_DAY), c1
											.get(Calendar.MINUTE), c1
											.get(Calendar.SECOND), c1
											.get(Calendar.MILLISECOND)));
			trace.write(report.getBytes());
			trace.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
