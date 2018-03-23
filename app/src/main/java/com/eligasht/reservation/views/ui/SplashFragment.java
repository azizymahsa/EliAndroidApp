package com.eligasht.reservation.views.ui;

import android.Manifest;
import android.animation.Animator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.BuildConfig;
import com.eligasht.R;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.api.userEntranceRequest.request.UserEntranceRequest;
import com.eligasht.reservation.models.hotel.api.userEntranceRequest.request.UserRequest;
import com.eligasht.reservation.models.hotel.api.userEntranceRequest.response.SearchNotes;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.dialog.app.InternetAlert;
import com.eligasht.reservation.views.ui.dialog.app.SplashDialog;
import com.eligasht.reservation.views.ui.dialog.app.UpdateAlert;
import com.farsitel.bazaar.IUpdateCheckService;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.orhanobut.hawk.Hawk;
import com.wang.avi.AVLoadingIndicatorView;
import com.zplesac.connectionbuddy.activities.ConnectionBuddyActivity;
import com.zplesac.connectionbuddy.models.ConnectivityEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class SplashFragment extends ConnectionBuddyActivity implements
        SplashDialog.TryDialogListener {

    boolean isShow = true;
    private Runnable runnable, runnable2;
    private Handler handler, handler2;
    private ImageView ivSplash, ivLoading;
    AVLoadingIndicatorView avi;
    LottieAnimationView lottieAnimationView;
    com.eligasht.reservation.api.app.UserEntranceRequest userEntranceRequest;
    String deviceId;
    String deviceSubscriberID;
    String operator;
    String sdkVersion;
    String model;
    String brand;
    String product;
    private BroadcastReceiver sendDetailFinish;
    InternetAlert internetAlert;
    boolean isConnect;

    int req = 0;
    SplashDialog splashDialog;
    private static final String TAG = "UpdateCheck";
    String packageName;
    UpdateAlert updateAlert;
    String DeviceOSType;
    TextView tvVer;

    @Override
    public void onReturnValue() {

        new GetCommentAsync().execute();
    }

    @Override
    public void returnRestartAppValue() {
        Prefs.putString("lang", "en");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mStartActivity = new Intent(SplashFragment.this, SplashFragment.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent
                        .getActivity(SplashFragment.this, mPendingIntentId, mStartActivity,
                                PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) SplashFragment.this
                        .getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        }, 100);

    }

    IUpdateCheckService service;
    //  UpdateServiceConnection connection;


    private enum DOWNLOAD_TYPE {
        NONE, MAP, SOFTWARE
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_splash);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.list_selection));
        }
      /*  GlobalApplication application = (GlobalApplication) getApplication();
        Tracker mTracker = application.getDefaultTracker();
        mTracker.setScreenName("splash");

        mTracker.setTitle("splash");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        mTracker.send(new HitBuilders.AppViewBuilder().build());*/

        splashDialog = new SplashDialog(SplashFragment.this, this);
        final PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            updateAlert = new UpdateAlert(SplashFragment.this, pInfo.packageName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        internetAlert = new InternetAlert(SplashFragment.this);

        ivSplash = findViewById(R.id.ivSplash);
        ivLoading = findViewById(R.id.ivLoading);
        tvVer = findViewById(R.id.tvVer);
        avi = findViewById(R.id.avi);
        lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("lottie/e-splash.json");
        lottieAnimationView.playAnimation();
        tvVer.setText(BuildConfig.VERSION_NAME);
        Log.d(TAG, "onCreate: ");
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                Log.d(TAG, "onAnimationEnd: ");
                try {
                    TedPermission.with(SplashFragment.this)
                            .setPermissionListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted() {

                                    Log.d(TAG, "onPermissionGranted: 1");
                                    req++;

                                    if (isConnect) {
                                        Log.d(TAG, "onPermissionGranted: ");
                                        new GetCommentAsync().execute();

                                    } else {
                                        internetAlert.isShow();
                                    }
                                }


                                @Override
                                public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                                }
                            })
                            .setDeniedMessage(
                                    "If you reject permission,you can not use this application, Please turn on permissions at [Setting] > [Permission]")
                            .setPermissions(Manifest.permission.READ_PHONE_STATE)
                            .check();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }


    // =========================================================================
    // ===================== DOWNLOAD SOFTWARE =================================
    // =========================================================================
    private void downloadSoftware() {
/*		DLType = DOWNLOAD_TYPE.SOFTWARE;
        UpdateFromWebService service = new UpdateFromWebService(this, null);
		service.DownloadNewVersion(this);
		((TextView) findViewById(R.id.progressDownloadText)).setText("در حال دریافت نسخه جدید نرم افزار");
		findViewById(R.id.mainPassLayout).setVisibility(View.GONE);
		progress.setVisibility(View.VISIBLE);
		findViewById(R.id.downloadButtonsPanel).setVisibility(View.GONE);*/
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private class GetCommentAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            internetAlert.isCancel();
            Log.d(TAG, "onPreExecute: ");
            avi.setVisibility(View.VISIBLE);
            if (!Prefs.getString("loginId", "null").equals("null")) {
                deviceId = null;
                deviceSubscriberID = null;
                operator = null;
                sdkVersion = null;
                model = null;
                brand = null;
                product = null;
                DeviceOSType = null;

            } else {
                deviceId = Utility.getDeviceID(SplashFragment.this);
                deviceSubscriberID = Utility.getSubscriberID(SplashFragment.this);
                operator = Utility.getMyOperator(SplashFragment.this);
                sdkVersion = android.os.Build.VERSION.SDK_INT + "";
                model = android.os.Build.MODEL;
                brand = Build.BRAND;
                product = Build.PRODUCT;
                DeviceOSType = "2";
            }


        }


        @Override
        protected String doInBackground(String... params) {
            try {
                userEntranceRequest = new com.eligasht.reservation.api.app.UserEntranceRequest(
                        new UserRequest
                                (new UserEntranceRequest(deviceId, Prefs.getString("loginId", null),
                                        deviceSubscriberID, sdkVersion, model, product, BuildConfig.VERSION_NAME,
                                        DeviceOSType, operator, brand, getString(R.string.culture),
                                        new Identity("EligashtMlb",
                                                "123qwe!@#QWE", "Mobile"))));
                Log.e("ggg", new Gson().toJson(new UserRequest
                        (new UserEntranceRequest(deviceId, Prefs.getString("loginId", null), deviceSubscriberID,
                                sdkVersion, model, product, BuildConfig.VERSION_NAME, DeviceOSType, operator, brand,
                                getString(R.string.culture), new Identity("EligashtMlb",
                                "123qwe!@#QWE", "Mobile")))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);
            avi.setVisibility(View.GONE);

            try {

                if (userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.errors != null) {
                    splashDialog.seeText(
                            userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.errors.get(0)
                                    .getDetailedMessage());
                    splashDialog.showAlert();

                } else {
                    Utility.sendTag("Splash", true, true);
                    Log.e("loginId",
                            userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.ID + "");
                    Prefs.putString("loginId",
                            userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.ID);
                    for (SearchNotes searchNotes : userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.SearchNotes) {
                        if (searchNotes.Section.equals("H")) {
                            ArrayList<String> strings = new ArrayList<>();

                            for (int i = 0; i < 8; i++) {
                                for (String s : searchNotes.Notes) {
                                    strings.add(new String(s));
                                }

                            }

                            Prefs.putString("H", new Gson().toJson(strings));

                        }

                        if (searchNotes.Section.equals("F")) {
                            ArrayList<String> strings = new ArrayList<>();

                            for (int i = 0; i < 8; i++) {
                                for (String s : searchNotes.Notes) {
                                    strings.add(new String(s));
                                }

                            }
                            Prefs.putString("F", new Gson().toJson(strings));

                        }
                        if (searchNotes.Section.equals("FH")) {
                            ArrayList<String> strings = new ArrayList<>();

                            for (int i = 0; i < 8; i++) {
                                for (String s : searchNotes.Notes) {
                                    strings.add(new String(s));
                                }

                            }
                            Prefs.putString("FH", new Gson().toJson(strings));

                        }
                        if (searchNotes.Section.equals("P")) {
                            ArrayList<String> strings = new ArrayList<>();

                            for (int i = 0; i < 8; i++) {
                                for (String s : searchNotes.Notes) {
                                    strings.add(new String(s));
                                }

                            }
                            Prefs.putString("P", new Gson().toJson(strings));

                        }

                    }
                    Log.e("AdjustEnabled",
                            userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.AdjustEnabled
                                    + "");
                    if (userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.AdjustEnabled) {
                        Hawk.put("adjust", true);
                    } else {
                        Hawk.put("adjust", false);
                    }

                    if (userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.CanEnter) {

                        try {
                            String app = BuildConfig.VERSION_NAME;
                            String server = userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.MinAppVersion;
                            if (Double.valueOf(app.replace(".", "")) < Double.valueOf(server.replace(".", ""))) {

                                updateAlert.show();
                                updateAlert.isForce(false);

                            } else {

                                if (Hawk.get("isFirstEntrance", true)) {
                                    if (
//                      userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.CultureDefault
                                            userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.CultureDefault.contains("en")) {

                                        showRestartDialog();

                                    } else {
                                        startActivity(new Intent(SplashFragment.this, MainActivity.class));
                                        finish();
                                    }

                                } else {
                                    startActivity(new Intent(SplashFragment.this, MainActivity.class));
                                    finish();
                                }
                                Hawk.put("isFirstEntrance", false);


                            }


                        } catch (Exception e) {
                            splashDialog.showAlert();
                            e.printStackTrace();
                        }


                    } else {
                        updateAlert.show();
                        updateAlert.isForce(true);


                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
                splashDialog.showAlert();

            }
        }
    }

    private void showRestartDialog() {
        SplashDialog dialog = new SplashDialog(this, this, true);
        dialog.seeText(getString(R.string.the_locale_will_be_changed));
        dialog.showAlert();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Hawk.get("adjust", true)) {
            try {
                AdjustEvent event = new AdjustEvent("yoi24u");
                Adjust.trackEvent(event);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }


    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    public void onConnectionChange(ConnectivityEvent event) {
        // device has active internet connection
        Log.d(TAG, new Gson().toJson(event));

        try {
            JSONObject jsonObj = new JSONObject(new Gson().toJson(event));
            JSONObject getAirportsResult = jsonObj.getJSONObject("state");

            Log.e("test", getAirportsResult.getString("value"));

            if (getAirportsResult.getString("value").equals("1")) {
                isConnect = true;
                internetAlert.isCancel();
                if (req == 1) {
                    new GetCommentAsync().execute();

                }

            } else {

                isConnect = false;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    /**
     * This is our function to un-binds this activity from our service.
     */


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}



