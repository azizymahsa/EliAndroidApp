package com.eligasht.reservation.base;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Typeface;
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
import com.eligasht.reservation.notification.GetNotification;
import com.eligasht.reservation.views.activities.IDM_Activity;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.font.CustomViewWithTypefaceSupport;
import com.eligasht.reservation.views.ui.font.TextField;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.request.Identity;
import com.eligasht.service.model.hotel.hotelAvail.request.Request;
import com.eligasht.service.model.hotel.hotelAvail.request.Room;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.crash.FirebaseCrash;
import com.onesignal.OneSignal;
import com.orhanobut.hawk.Hawk;
import com.pixplicity.easyprefs.library.Prefs;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import retrofit2.Retrofit;
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

    @Inject
    Retrofit serviceGenerator;

    public static void setGlobalTypeFace(Context context) {
        globalTypeFace = Typeface.createFromAsset(context.getAssets(),
                context.getResources().getString(R.string.mitra_ttf));
    }

    public static void setLocale(Context context) {

        String languageToLoad = Prefs.getString("lang", "fa");
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
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
        String address = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/JameNegar_Pakhsh_new/Reports/";
        new File(address).mkdirs();
        return address;
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

        HotelAvailReq hotelAvailReq = new HotelAvailReq();
        Request request = new Request();
        request.setCheckinString("2018/03/28");
        request.setCheckoutString("2018/04/30");
        request.setDepart("c25972");
        request.setEchoToken("H");
        request.setEDepart("DXB");
        request.setRoomsString("2,4,1,0,0,0");
        Identity identity=new Identity();
        request.setIdentity(identity);
        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        room.setAdultCount(2);
        room.setChildCount(2);
        rooms.add(room);
        request.setRooms(rooms);
        request.setSource("");
        hotelAvailReq.setRequest(request);

        SingletonService.getInstance().getHotelService().hotelAvail(new OnServiceStatus<HotelAvailRes>() {
            @Override
            public void onReady(HotelAvailRes hotelAvailRes) {
                hotelAvailRes.getHotelAvailResult().showLog();
            }

            @Override
            public void onError(Throwable error) {
                Log.e("test20", error.getMessage());
            }
        }, hotelAvailReq).start();
//        Log.e("Class", serviceGenerator.toString());
        sAnalytics = GoogleAnalytics.getInstance(this);


        SingletonContext.getInstance().setContext(this);
        SingletonDate.getInstance().initDate();
        ConnectionBuddyConfiguration networkInspectorConfiguration = new ConnectionBuddyConfiguration.Builder(
                this).build();
        ConnectionBuddy.getInstance().init(networkInspectorConfiguration);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new GetNotification())
                .init();
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

        setLocale(this);

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
