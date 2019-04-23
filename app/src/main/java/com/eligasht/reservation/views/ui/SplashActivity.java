package com.eligasht.reservation.views.ui;

import android.animation.Animator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.BuildConfig;
import com.eligasht.R;
import com.eligasht.ServiceApplication;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.conf.APIConf;
import com.eligasht.reservation.tools.db.RsaFunction;
import com.eligasht.reservation.tools.db.StringCryptor;
import com.eligasht.service.helper.Const;
import com.eligasht.service.model.newModel.auth.response.ResponseAuth;
import com.eligasht.service.model.newModel.startup.request.RequestStartup;
import com.eligasht.service.model.newModel.startup.response.Branch;
import com.eligasht.service.model.newModel.startup.response.ResponseStartup;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.dialog.app.InternetAlert;
import com.eligasht.reservation.views.ui.dialog.app.SplashDialog;
import com.eligasht.reservation.views.ui.dialog.app.UpdateAlert;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.hawk.Hawk;
import com.wang.avi.AVLoadingIndicatorView;
import com.zplesac.connectionbuddy.activities.ConnectionBuddyActivity;
import com.zplesac.connectionbuddy.models.ConnectivityEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends ConnectionBuddyActivity implements
        SplashDialog.TryDialogListener, OnServiceStatus<ResponseStartup>, PermissionListener, Animator.AnimatorListener, DialogInterface.OnCancelListener {
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
    private ClientService service;

    public static List<Branch> branches;
    public static List<Branch> branchesDef ;

    public static List<Branch> branchesBk;
    public static List<Branch> branchesDefBk ;

    public static List<String> UpdateUrl=new ArrayList<>() ;

    @Override
    public void onReturnValue() {
        Auth_request(false);
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
        service = ServiceGenerator.createService(ClientService.class);
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
        Log.d( "RUN_ACTIVITY: ","9999999999999999"+"SPLASH");
        StatusBarUtil.setTranslucent(this, 2);

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
    private void Auth_request(boolean flagUpdate) {
        String Password = "Eli",UserName="123456";
        String encryptedPassword="";
        try {
            Const.TOKEN= encryptedPassword = StringCryptor.Encrypt("", "");//MD5
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        if(!flagUpdate)
            startUpRequest();
          /*     service = ServiceGenerator.createService(ClientService.class);
            JSONObject paramObject = new JSONObject();
            paramObject.put("grant_type", "password");
            paramObject.put("username", "eli_gasht_1397");
            paramObject.put("password", "Eli@accesstoken");

            Call<ResponseAuth> call = service.getAuthResult("password","eli_gasht_1397","Eli@accesstoken");
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    Log.d("ResponseToken: ","res:"+response.body().getTokenType()+" "+response.body().getAccessToken());
                    Const.TOKEN=response.body().getTokenType()+" "+response.body().getAccessToken();
                   if(!flagUpdate)
                     startUpRequest();
                }
                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t)  {
                    Log.d("requestSearchPackage: ","error");

                }
            });*/

    }
    public void startUpRequest() {

        RequestStartup startupServiceRequest = new RequestStartup();
        //Request request = new Request();
      /*  if (!Prefs.getString("loginId", "null").equals("null")) {
            deviceId = null;
            deviceSubscriberID = null;
            operator = null;
            sdkVersion = null;
            model = null;
            brand = null;
            product = null;
            DeviceOSType = null;
            // request.setID("");
            startupServiceRequest.setID(Prefs.getString("loginId", "null"));
        } else {*/
            String uniqueID = UUID.randomUUID().toString();
            deviceId = uniqueID;
            sdkVersion = android.os.Build.VERSION.SDK_INT + "";
            model = android.os.Build.MODEL;
            brand = Build.BRAND;
            product = Build.PRODUCT;
            DeviceOSType = "2";
        //}
        startupServiceRequest.setAppVersion(BuildConfig.VERSION_NAME);
        startupServiceRequest.setBrand("2");//android
        startupServiceRequest.setCulture(getString(R.string.culture));
        startupServiceRequest.setDeviceModelName(model);
       /* startupServiceRequest.setDeviceOSType(DeviceOSType);
        startupServiceRequest.setDeviceProduct(product);*/
        startupServiceRequest.setDeviceGuid(deviceId);//setIMEI(deviceId);
       /* startupServiceRequest.setIMSI(deviceSubscriberID);
        startupServiceRequest.setOperatorName(operator);*/
        startupServiceRequest.setSdkVersion(sdkVersion);


        avi.setVisibility(View.VISIBLE);

        Log.e("RequestStartup: ",new Gson().toJson(startupServiceRequest).toString() );

        SingletonService.getInstance().getAppService().startUp(this, startupServiceRequest);
        Bundle bundle = new Bundle();
        bundle.putString("TestParam", "123");
        FirebaseAnalytics firebaseAnalytics=FirebaseAnalytics.getInstance(this);
        firebaseAnalytics.logEvent("Test", bundle);

    }

    @Override
    public void onReady(ResponseStartup startupServiceResponse) {
        Log.e("ResponseStartup: ",new Gson().toJson(startupServiceResponse).toString() );
        avi.setVisibility(View.GONE);
        try {
            if (startupServiceResponse.getErrors() != null){
                if (startupServiceResponse.getErrors().size() > 0) {
                    splashDialog.seeText(startupServiceResponse.getErrors().get(0).getDetailedMessage());
                    splashDialog.showAlert();
                }
             }else{
                Utility.sendTag("Splash", true, true);
                branches = new ArrayList<Branch>();
                branchesDef = new ArrayList<Branch>();
               // UpdateUrl.clear();
                //branchesDef.clear();
              //  Log.d( "ActiveOperationSplash: ",branchesDef.size()+"");
                for (int i = 0; i < startupServiceResponse.getUpdateUrl().size(); i++) {

                   UpdateUrl.add(startupServiceResponse.getUpdateUrl().get(i));

                }

                for(Branch branch : startupServiceResponse.getBranches()){
                    branches.add(branch);

                    if (branch.getIsDefault())
                        branchesDef.add(branch);

                }

                //baraye avalin bar check shavaf
                if (branchesDef.get(0).getAdjustEnabled()) {
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


                if(startupServiceResponse.getIsUpdataMandatory()) {
                    updateAlert.show();
                    updateAlert.isForce(true);
                }else  if (startupServiceResponse.getHasUpdate()) {//getUserEntranceResponse().getCanEnter()) {

                    String app = BuildConfig.VERSION_NAME;

                    updateAlert.show();
                    updateAlert.isForce(false);
                }else if (branchesDef.get(0).getIsDefault()){
                        if(!Prefs.getBoolean("isChangeCulture", false)) {
                            Prefs.putString("CultureDef", branchesDef.get(0).getDefaultCulture());

                            if (branchesDef.get(0).getDefaultCulture().contains("fa"))
                                Prefs.putString("lang", "fa");
                            else {
                                Prefs.putString("lang", branchesDef.get(0).getDefaultCulture().split("-")[0]);
                                showRestartDialog();
                            }
                        }
                         if(!Prefs.getBoolean("isChangeBranch", false))
                                Prefs.putString("BranchDef", branchesDef.get(0).getName());

                        if(!Prefs.getBoolean("isChangeUrl", false)){
                                Prefs.putString("BASEURL",branchesDef.get(0).getUrl());
                                Const.BASEURL=branchesDef.get(0).getUrl();
                                  /* ServiceApplication serviceApplication=new ServiceApplication() {
                                        @Override
                                        public void onCreate() {
                                            super.onCreate();

                                        }
                                    };
                            serviceApplication.onCreate();*/
                        }else{
                            APIConf apiConf=new APIConf();
                            ServiceGenerator.changeApiBaseUrl( Prefs.getString("BASEURL",""));
                           // Prefs.putString("BASEURL", Prefs.getString("BASEURL","");
                            Const.BASEURL=Prefs.getString("BASEURL","");
                            Log.d( "ActiveOperationSPASH_RESPONSE: ","100");
                            ServiceApplication serviceApplication=new ServiceApplication() {
                                        @Override
                                        public void onCreate() {
                                            super.onCreate();

                                        }
                                    };
                            serviceApplication.onCreate();
                            Auth_request(true);
                        }

                         if(!Prefs.getBoolean("isChangeCurrency", false))
                             Prefs.putString("CurrencyDef", branchesDef.get(0).getCurrency());

                    Log.d( "ActiveOperationSPASH_RESPONSE: ","200");


                         // Prefs.putString("BASEURL", Prefs.getString("UrlDef",""));




                            Prefs.putBoolean("isFirstEntrance", false);
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }




            }

            /*else {
                Utility.sendTag("Splash", true, true);
                Prefs.putString("loginId", startupServiceResponse.getID() + "");
                for (SearchNote searchNotes : startupServiceResponse.getMobileAppStartupServiceResult().getUserEntranceResponse().getSearchNotes()) {
                    if (searchNotes.getSection().equals("H")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            strings.addAll(searchNotes.getNotes());
                        }
                        Prefs.putString("H", new Gson().toJson(strings));
                    }
                    if (searchNotes.getSection().equals("F")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            strings.addAll(searchNotes.getNotes());
                        }
                        Prefs.putString("F", new Gson().toJson(strings));
                    }
                    if (searchNotes.getSection().equals("FH")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            strings.addAll(searchNotes.getNotes());
                        }
                        Prefs.putString("FH", new Gson().toJson(strings));
                    }
                    if (searchNotes.getSection().equals("P")) {
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            strings.addAll(searchNotes.getNotes());
                        }
                        Prefs.putString("P", new Gson().toJson(strings));
                    }
                }
                //baraye avalin bar check shavaf
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
                               // startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Prefs.putBoolean("isFirstEntrance", false);
                                Prefs.putString("lang", startupServiceResponse.getMobileAppStartupServiceResult().getCultureDefault().split("-")[0]);
                                showRestartDialog();
                            }
                        } else {
                            //startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                        Prefs.putBoolean("isFirstEntrance", false);
                    }

                } else {
                    updateAlert.show();
                    updateAlert.isForce(true);
                }
            }*/
            Log.d( "ActiveOperationSplash: ",branches.size()+"");
            branchesBk = new ArrayList<Branch>();
            branchesDefBk = new ArrayList<Branch>();
            branchesBk=branches;
            branchesDefBk=branchesDef;
            Log.d( "ActiveOperationSPASH_RESPONSE: ","300");
            Log.d( "ActiveOperationSPASH_RESPONSE: ",branchesBk.size()+"");
            Log.d( "ActiveOperationSPASH_RESPONSE: ",branchesDefBk.size()+"");
            Log.d( "ActiveOperationSplash: ",branchesBk.size()+"");
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
        Auth_request(false);
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
            Auth_request(false);
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
        Auth_request(false);
    }
}



