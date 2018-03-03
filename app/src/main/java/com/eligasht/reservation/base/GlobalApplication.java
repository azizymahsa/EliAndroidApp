package com.eligasht.reservation.base;



import java.io.File;
import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Typeface;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.telephony.TelephonyManager;

import com.onesignal.OneSignal;
import com.pixplicity.easyprefs.library.Prefs;

import com.eligasht.R;
import com.eligasht.reservation.notification.GetNotification;
import com.eligasht.reservation.views.ui.font.CustomViewWithTypefaceSupport;
import com.eligasht.reservation.views.ui.font.TextField;
import com.eligasht.reservation.views.activities.IDM_Activity;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class GlobalApplication extends Application {
	public static Typeface globalTypeFace;
	private static IDM_Activity activity;
	private static Context context;
	public static ArrayList<IDM_Activity> activityStack = new ArrayList<IDM_Activity>();
	private static GlobalApplication mInstance;
	public static volatile Context applicationContext;
	public static volatile Handler applicationHandler;

	public static void setGlobalTypeFace(Context context) {
		globalTypeFace = Typeface.createFromAsset(context.getAssets(),
				"fonts/mitra.ttf");
	}
	@Override
	public void onCreate() {
		super.onCreate();
		ConnectionBuddyConfiguration networkInspectorConfiguration = new ConnectionBuddyConfiguration.Builder(this).build();
		ConnectionBuddy.getInstance().init(networkInspectorConfiguration);
		OneSignal.startInit(this)
				.inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
				.unsubscribeWhenNotificationsAreDisabled(true)
				.setNotificationReceivedHandler(new GetNotification())
				.init();

		mInstance = this;
		applicationContext = getApplicationContext();
		applicationHandler = new Handler(applicationContext.getMainLooper());
		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/iran_sans_normal.ttf")
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

		new com.eligasht.reservation.tools.Prefs.Builder()
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



	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

}
