package com.eligasht.reservation.base;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.eligasht.BuildConfig;
import com.eligasht.R;
import com.eligasht.ServiceApplication;
import com.eligasht.reservation.notification.NotificationReceivedHandler;
import com.eligasht.reservation.views.activities.IDM_Activity;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.font.CustomViewWithTypefaceSupport;
import com.eligasht.reservation.views.ui.font.TextField;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.crash.FirebaseCrash;
import com.onesignal.OneSignal;
import com.orhanobut.hawk.Hawk;
import com.eligasht.reservation.tools.Prefs;
import com.orm.SugarContext;
import com.squareup.leakcanary.LeakCanary;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.OkHttpClient;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class GlobalApplication extends ServiceApplication {

    public static Typeface globalTypeFace;
    public static ArrayList<IDM_Activity> activityStack = new ArrayList<IDM_Activity>();
    public static volatile Context applicationContext;
    public static volatile Handler applicationHandler;
    private static IDM_Activity activity;
    private static Context context;
    private static GlobalApplication mInstance;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;


    public static void setGlobalTypeFace(Context context) {
        globalTypeFace = Typeface.createFromAsset(context.getAssets(),
                context.getResources().getString(R.string.mitra_ttf));
    }

    public void setLocale() {

        String languageToLoad = Prefs.getString("lang", "fa");
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        getResources().updateConfiguration(config,
                getResources().getDisplayMetrics());
    }



    public static String getLogsDirectoryAddress() {
        String address =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/JameNegar_Pakhsh_new/Logs/";
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
        if (activity != null) {
            return activity;
        }
        return context;
    }

    public static void setContext(Context context) {
        GlobalApplication.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);

        sAnalytics = GoogleAnalytics.getInstance(this);

        SingletonContext.getInstance().setContext(this);
        SingletonDate.getInstance().initDate();
        ConnectionBuddyConfiguration networkInspectorConfiguration = new ConnectionBuddyConfiguration.Builder(this).build();
        ConnectionBuddy.getInstance().init(networkInspectorConfiguration);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new NotificationReceivedHandler())
                .init();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.NONE, OneSignal.LOG_LEVEL.NONE);
        Hawk.init(this).build();
        mInstance = this;
        applicationContext = getApplicationContext();
        applicationHandler = new Handler(applicationContext.getMainLooper());

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

        setLocale();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.iran_sans_normal_ttf))
                .setFontAttrId(R.attr.fontPath)
                .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
                .addCustomStyle(TextField.class, R.attr.textFieldStyle)
                .build()
        );

        if (Hawk.get("adjust", true)) {
            String realToken = "niedy5vr1xc0";
            String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
            AdjustConfig config = new AdjustConfig(this, realToken, environment);
            config.setLogLevel(LogLevel.VERBOSE);
            config.setSendInBackground(true);
            Adjust.onCreate(config);
            registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
        }

        FirebaseCrash.setCrashCollectionEnabled(!BuildConfig.DEBUG);
    }

    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {

            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
            Log.e("123", sTracker + "");
        }

        return sTracker;
    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();

        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

}
