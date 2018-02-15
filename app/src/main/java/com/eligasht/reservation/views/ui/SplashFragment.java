package com.eligasht.reservation.views.ui;

import android.Manifest;
import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.reservation.models.hotel.api.userEntranceRequest.request.UserEntranceRequest;
import com.eligasht.reservation.views.ui.dialog.app.UpdateAlert;
import com.farsitel.bazaar.IUpdateCheckService;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.eligasht.reservation.BuildConfig;
import com.eligasht.reservation.R;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.api.userEntranceRequest.request.UserRequest;
import com.eligasht.reservation.models.hotel.api.userEntranceRequest.response.SearchNotes;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.dialog.app.InternetAlert;
import com.eligasht.reservation.views.ui.dialog.app.SplashDialog;
import com.wang.avi.AVLoadingIndicatorView;
import com.zplesac.connectionbuddy.activities.ConnectionBuddyActivity;
import com.zplesac.connectionbuddy.models.ConnectivityEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class SplashFragment extends ConnectionBuddyActivity implements SplashDialog.TryDialogListener {
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
    boolean isStop = false;
    int req = 0;
    SplashDialog splashDialog;
    private static final String TAG = "UpdateCheck";
    String packageName;

    @Override
    public void onReturnValue() {

        new GetCommentAsync().execute();
    }

    IUpdateCheckService service;
    //  UpdateServiceConnection connection;
    UpdateServiceConnection connection;

    private enum DOWNLOAD_TYPE {
        NONE, MAP, SOFTWARE
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_splash);
        super.onCreate(savedInstanceState);
        initService();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.list_selection));
        }

        splashDialog = new SplashDialog(SplashFragment.this, this);


        internetAlert = new InternetAlert(SplashFragment.this);


        ivSplash = findViewById(R.id.ivSplash);
        ivLoading = findViewById(R.id.ivLoading);
        avi = findViewById(R.id.avi);
        lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("e-splash.json");
        lottieAnimationView.playAnimation();

        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                new TedPermission(SplashFragment.this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {

                       /*         ReactiveNetwork.observeNetworkConnectivity(SplashFragment.this)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<Connectivity>() {
                                            @Override
                                            public void accept(final Connectivity connectivity) {
                                                Log.e("testt", connectivity.getState().toString());
                                                if (connectivity.getState().toString().equals("DISCONNECTED")) {
                                                    try{internetAlert.isShow();}
                                                    catch (Exception e){}



                                                } else if (connectivity.getState().toString().equals("CONNECTED")){
                                                    try{    internetAlert.isCancel();
                                                        new GetCommentAsync().execute();}
                                                    catch (Exception e){}


                                                }

                                            }
                                        });*/
                                req++;

                                if (isConnect) {
                                    new GetCommentAsync().execute();


                                } else {

                                    internetAlert.isShow();
                                }
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                            }
                        })
                        .setDeniedMessage("If you reject permission,you can not use this application, Please turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                        .check();


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
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private class GetCommentAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            internetAlert.isCancel();

            avi.setVisibility(View.VISIBLE);

            deviceId = Utility.getDeviceID(SplashFragment.this);
            deviceSubscriberID = Utility.getSubscriberID(SplashFragment.this);
            operator = Utility.getMyOperator(SplashFragment.this);
            sdkVersion = android.os.Build.VERSION.SDK_INT + "";
            model = android.os.Build.MODEL;
            brand = Build.BRAND;
            product = Build.PRODUCT;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                userEntranceRequest = new com.eligasht.reservation.api.app.UserEntranceRequest(new UserRequest
                        (new UserEntranceRequest(deviceId, Prefs.getString("loginId", null), deviceSubscriberID, sdkVersion, model, product, BuildConfig.VERSION_CODE, 2, operator, brand, "fa-IR", new Identity("EligashtMlb",
                                "123qwe!@#QWE", "Mobile"))));
                Log.e("ggg", new Gson().toJson(new UserRequest
                        (new UserEntranceRequest(deviceId, Prefs.getString("loginId", null), deviceSubscriberID, sdkVersion, model, product, BuildConfig.VERSION_CODE, 2, operator, brand, "fa-IR", new Identity("EligashtMlb",
                                "123qwe!@#QWE", "Mobile")))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);
            avi.setVisibility(View.GONE);

            //    try {

            if (userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.errors != null) {
                splashDialog.seeText(userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.errors.get(0).getDetailedMessage());
                splashDialog.showAlert();

            } else {


            /*    if (Float.valueOf(userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.MinAppVersion) > Float.valueOf(BuildConfig.VERSION_NAME)) {
                    Log.e("test", userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.MinAppVersion);
                }else{
                    Log.e("test2", BuildConfig.VERSION_NAME);

                }*/

//                Log.e("onon", userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.CanEnter + "");
                Utility.sendTag("Splash", true, true);
                Prefs.putString("loginId", userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.ID);
                for (SearchNotes searchNotes : userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.SearchNotes) {
                    if (searchNotes.Section.equals("H")) {
                        Prefs.putString("H", new Gson().toJson(searchNotes.Notes));

                    }

                    if (searchNotes.Section.equals("F")) {
                        Prefs.putString("F", new Gson().toJson(searchNotes.Notes));

                    }
                    if (searchNotes.Section.equals("FH")) {
                        Prefs.putString("FH", new Gson().toJson(searchNotes.Notes));

                    }
                    if (searchNotes.Section.equals("P")) {
                        Prefs.putString("P", new Gson().toJson(searchNotes.Notes));

                    }

                }
                if (userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.CanEnter) {
                    if (!isStop) {
                        startActivity(new Intent(SplashFragment.this, MainActivity.class));
                        finish();
                    }else{
                        new UpdateAlert(SplashFragment.this,packageName, false);


                    }


                } else {
                    try {
                        if (Integer.valueOf(userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.MinAppVersion) > Integer.valueOf(BuildConfig.VERSION_CODE)) {
                            new UpdateAlert(SplashFragment.this, packageName, true);

                        }
                    } catch (Exception e) {
                        splashDialog.showAlert();
                    }


                }

            }



/*
            } catch (Exception e) {
                //   Toast.makeText(SplashFragment.this, "ارتباط با سرور مقدور نمی باشد", Toast.LENGTH_SHORT).show();

                splashDialog.showAlert();


            }*/
        }
    }


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    public void onConnectionChange(ConnectivityEvent event) {
        // device has active internet connection


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

    class UpdateServiceConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IUpdateCheckService.Stub
                    .asInterface((IBinder) boundService);
            try {
                final PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                long vCode = service.getVersionCode(pInfo.packageName);
                packageName = pInfo.packageName;
                Log.e(TAG, pInfo.packageName+"12");

                if (vCode != -1) {
                    isStop = true;
                    Log.e(TAG, pInfo.packageName);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            service = null;
        }
    }

    private void initService() {
        Log.i(TAG, "initService()");
        connection = new UpdateServiceConnection();
        try {
            Intent i = new Intent(
                    "com.farsitel.bazaar.service.UpdateCheckService.BIND");
            i.setPackage("com.farsitel.bazaar");
            boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
        }
    }

    /**
     * This is our function to un-binds this activity from our service.
     */
    private void releaseService() {
        unbindService(connection);
        connection = null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }
}



