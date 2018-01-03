package com.reserv.myapplicationeli.base;



import java.io.File;
import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Typeface;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.ui.font.CustomViewWithTypefaceSupport;
import com.reserv.myapplicationeli.views.ui.font.TextField;
import com.reserv.myapplicationeli.views.activities.IDM_Activity;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class GlobalApplication extends Application {
	public static Typeface globalTypeFace;
	private static IDM_Activity activity;
	private static Context context;
	public static ArrayList<IDM_Activity> activityStack = new ArrayList<IDM_Activity>();

	public static void setGlobalTypeFace(Context context) {
		globalTypeFace = Typeface.createFromAsset(context.getAssets(),
				"fonts/mitra.ttf");
	}
	@Override
	public void onCreate() {
		super.onCreate();
		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/irsans.ttf")
				.setFontAttrId(R.attr.fontPath)
				.addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
				.addCustomStyle(TextField.class, R.attr.textFieldStyle)
				.build()
		);

		new Prefs.Builder()
				.setContext(this)
				.setMode(ContextWrapper.MODE_PRIVATE)
				.setPrefsName(getPackageName())
				.setUseDefaultSharedPreference(true)
				.build();

	}
	public String getMyOperator(Context aContext) {
		TelephonyManager mTelephonyMgr;
		mTelephonyMgr = (TelephonyManager) aContext.getSystemService(Context.TELEPHONY_SERVICE);
		String mynumber = mTelephonyMgr.getNetworkOperator();

		String Operator = "";
		if (mynumber != null) {
			if (mynumber.equals("43211")) {
				Operator = "MCI";
			} else if (mynumber.equals("43235")) {
				Operator = "IRANCELL";
			} else if (mynumber.equals("43232")) {
				Operator = "TALIYA";
			} else if (mynumber.equals("43220")) {
				Operator = "RAITEL";
			}
		}
		return Operator;
	}

	public String getSecurityHead() {
		// Service1 nikSharj = new Service1();
		// String securityCode = nikSharj.SC();
		//
		// if (securityCode == null)
		// return null;
		//
		// String hash = Utility.MD5("j$a$" + securityCode + "$m$e");
		//
		// return hash;
		return "";
	}

	public static String getSoftwareDirectoryAddress() {
		String address = Environment.getExternalStorageDirectory()
				+ "/JameNegar_Pakhsh_new/Software/";
		new File(address).mkdirs();
		return address;
	}

	public static String getMapAddress() {
		String address = Environment.getExternalStorageDirectory()
				+ "/JameNegar_Pakhsh_new/Map/iran.Map";
		new File(address).getParentFile().mkdirs();
		return address;
	}

	public static String getPhotosDirectoryAddress() {
		String address = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/JameNegar_Pakhsh_new/Products_Photos/";
		new File(address).mkdirs();
		return address;
	}

	public static String getReportsDirectoryAddress() {
		String address = Environment.getExternalStorageDirectory().getAbsolutePath() + "/JameNegar_Pakhsh_new/Reports/";
		new File(address).mkdirs();
		return address;
	}

	public static String getLogsDirectoryAddress() {
		String address = Environment.getExternalStorageDirectory().getAbsolutePath() + "/JameNegar_Pakhsh_new/Logs/";
		new File(address).mkdirs();
		return address;
	}

	public static IDM_Activity getActivity() {
		return activity;
	}

	public static void setActivity(IDM_Activity activity) {
		GlobalApplication.activity = activity;
	}

	public static Context getContext() {
		if (activity != null)
			return activity;
		return context;
	}

	public static void setContext(Context context) {
		GlobalApplication.context = context;
	}

	public static String getWebserviceURL() {
		return "";//getActivity().getString(R.string.WEBSERVICE_URL);
	}

	public static void clearStack() {
		for (IDM_Activity activity : GlobalApplication.activityStack) {
			try {
				activity.finish();
			} catch (Exception e) {
			}
		}
		GlobalApplication.activityStack.clear();
	}
}
