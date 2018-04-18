package com.eligasht.reservation.views.ui;
import android.Manifest;
import android.animation.Animator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.BuildConfig;
import com.eligasht.R;
import com.eligasht.reservation.views.activities.hotel.activity.SelectHotelActivity;
import com.eligasht.service.model.startup.response.SearchNote;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.dialog.app.InternetAlert;
import com.eligasht.reservation.views.ui.dialog.app.SplashDialog;
import com.eligasht.reservation.views.ui.dialog.app.UpdateAlert;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.startup.request.Request;
import com.eligasht.service.model.startup.request.StartupServiceRequest;
import com.eligasht.service.model.startup.response.StartupServiceResponse;
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
import java.util.Locale;
import java.util.UUID;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
public class SplashActivity extends ConnectionBuddyActivity implements
        SplashDialog.TryDialogListener, OnServiceStatus<StartupServiceResponse>, PermissionListener, Animator.AnimatorListener, DialogInterface.OnCancelListener {
    private AVLoadingIndicatorView avi;
    private LottieAnimationView lottieAnimationView;
    private String deviceId;
    private String deviceSubscriberID;
    private String operator;
    private String sdkVersion;
    private String model;
    private String brand;
    private String product;
    private InternetAlert internetAlert;
    private SplashDialog splashDialog;
    private UpdateAlert updateAlert;
    private String DeviceOSType;
    private TextView tvVer;

    @Override
    public void onReturnValue() {
        startUpRequest();
    }

    @Override
    public void returnRestartAppValue() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mStartActivity = new Intent(SplashActivity.this, SplashActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(SplashActivity.this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) SplashActivity.this.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        }, 100);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Prefs.putString("Rooms", "[{\"CountB\":1,\"CountK\":0,\"CountN\":0,\"childModels\":[]}]");
        try {
            String languageToLoad = Prefs.getString("lang", "fa"); // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } catch (Exception e) {
        }
        setContentView(R.layout.fragment_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.list_selection));
        }
        splashDialog = new SplashDialog(SplashActivity.this, this);
        final PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            updateAlert = new UpdateAlert(SplashActivity.this, pInfo.packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        internetAlert = new InternetAlert(SplashActivity.this);
        internetAlert.alertDialog().setOnCancelListener(this);
        tvVer = findViewById(R.id.tvVer);
        avi = findViewById(R.id.avi);
        lottieAnimationView = findViewById(R.id.animation_view);
        tvVer.setText(BuildConfig.VERSION_NAME);
        lottieAnimationView.addAnimatorListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startUpRequest() {
        StartupServiceRequest startupServiceRequest = new StartupServiceRequest();
        Request request = new Request();
        if (!Prefs.getString("loginId", "null").equals("null")) {
            deviceId = null;
            deviceSubscriberID = null;
            operator = null;
            sdkVersion = null;
            model = null;
            brand = null;
            product = null;
            DeviceOSType = null;
            // request.setID("");
            request.setID(Prefs.getString("loginId", "null"));
        } else {
            String uniqueID = UUID.randomUUID().toString();
            deviceId = uniqueID;
            sdkVersion = android.os.Build.VERSION.SDK_INT + "";
            model = android.os.Build.MODEL;
            brand = Build.BRAND;
            product = Build.PRODUCT;
            DeviceOSType = "2";
        }
        request.setAppVersion(BuildConfig.VERSION_NAME);
        request.setBrand(brand);
        request.setCulture(getString(R.string.culture));
        request.setDeviceName(model);
        request.setDeviceOSType(DeviceOSType);
        request.setDeviceProduct(product);
        request.setIMEI(deviceId);
        request.setIMSI(deviceSubscriberID);
        request.setOperatorName(operator);
        request.setSDKVersion(sdkVersion);
        com.eligasht.service.model.startup.request.Identity identity = new com.eligasht.service.model.startup.request.Identity();
        identity.setPassword("123qwe!@#QWE");
        identity.setTermianlId("Mobile");
        identity.setUserName("EligashtMlb");
        request.setIdentity(identity);
        startupServiceRequest.setRequest(request);
        avi.setVisibility(View.VISIBLE);
        SingletonService.getInstance().getAppService().startUp(this, startupServiceRequest);
    }

    @Override
    public void onReady(StartupServiceResponse startupServiceResponse) {
        avi.setVisibility(View.GONE);
        try {
            if (startupServiceResponse.getMobileAppStartupServiceResult().getErrors() != null) {
                splashDialog.seeText(
                        startupServiceResponse.getMobileAppStartupServiceResult().getErrors().get(0)
                                .getDetailedMessage());
                splashDialog.showAlert();
            } else {
                Utility.sendTag("Splash", true, true);
                Prefs.putString("loginId", startupServiceResponse.getMobileAppStartupServiceResult().getID() + "");
                for (SearchNote searchNotes : startupServiceResponse.getMobileAppStartupServiceResult().getUserEntranceResponse().getSearchNotes()) {
                    if (searchNotes.getSection().equals("H")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            for (String s : searchNotes.getNotes()) {
                                strings.add(new String(s));
                            }
                        }
                        Prefs.putString("H", new Gson().toJson(strings));
                    }
                    if (searchNotes.getSection().equals("F")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            for (String s : searchNotes.getNotes()) {
                                strings.add(new String(s));
                            }
                        }
                        Prefs.putString("F", new Gson().toJson(strings));
                    }
                    if (searchNotes.getSection().equals("FH")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            for (String s : searchNotes.getNotes()) {
                                strings.add(new String(s));
                            }
                        }
                        Prefs.putString("FH", new Gson().toJson(strings));
                    }
                    if (searchNotes.getSection().equals("P")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            for (String s : searchNotes.getNotes()) {
                                strings.add(new String(s));
                            }
                        }
                        Prefs.putString("P", new Gson().toJson(strings));
                    }
                }
                if (startupServiceResponse.getMobileAppStartupServiceResult().getAdjustEnabled()) {
                    if (Prefs.getBoolean("isAdjustSend", true)) {
                        Prefs.putBoolean("isAdjustSend", false);
                        Hawk.put("adjust", true);
                        try {
                            AdjustEvent event = new AdjustEvent("yoi24u");
                            Adjust.trackEvent(event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Hawk.put("adjust", false);
                }
                if (startupServiceResponse.getMobileAppStartupServiceResult().getUserEntranceResponse().getCanEnter()) {

                        String app = BuildConfig.VERSION_NAME;
                        String server = startupServiceResponse.getMobileAppStartupServiceResult().getUserEntranceResponse().getMinAppVersion();
                        if (Double.valueOf(app.replace(".", "")) < Double.valueOf(server.replace(".", ""))) {
                            updateAlert.show();
                            updateAlert.isForce(false);
                        } else {
                            if (Prefs.getBoolean("isFirstEntrance", true)) {
                                if (startupServiceResponse.getMobileAppStartupServiceResult().getCultureDefault()
                                        .split("-")[0].equals(Prefs.getString("lang", "fa"))) {
                                    Prefs.putBoolean("isFirstEntrance", false);
                                    Prefs.putString("lang", "fa");
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Prefs.putBoolean("isFirstEntrance", false);
                                    Prefs.putString("lang", startupServiceResponse.getMobileAppStartupServiceResult().getCultureDefault().split("-")[0]);
                                    showRestartDialog();
                                }
                            } else {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }
                            Prefs.putBoolean("isFirstEntrance", false);
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

    @Override
    public void onError(String message) {
        avi.setVisibility(View.GONE);
        if (!Utility.isNetworkAvailable(SplashActivity.this)) {
            internetAlert.isShow();
        } else {
            splashDialog.showAlert();
        }
    }

    @Override
    public void onPermissionGranted() {
        startUpRequest();
    }

    @Override
    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
    }

    private void showRestartDialog() {
        SplashDialog dialog = new SplashDialog(this, this, true);
        dialog.seeText(getString(R.string.the_locale_will_be_changed));
        dialog.showAlert();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            String languageToLoad = Prefs.getString("lang", "fa"); // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } catch (Exception e) {
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionChange(ConnectivityEvent event) {
        try {
            JSONObject jsonObj = new JSONObject(new Gson().toJson(event));
            JSONObject getAirportsResult = jsonObj.getJSONObject("state");
            if (getAirportsResult.getString("value").equals("1")) {
                if (internetAlert.alertDialog().isShowing()) {
                    internetAlert.isCancel();
                }
            } else {
                internetAlert.isShow();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (Utility.isNetworkAvailable(SplashActivity.this)) {
            startUpRequest();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        startUpRequest();
    }
}



