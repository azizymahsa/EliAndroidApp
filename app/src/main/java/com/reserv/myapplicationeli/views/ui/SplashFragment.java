package com.reserv.myapplicationeli.views.ui;

import android.Manifest;
import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.layer.Layer;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.reserv.myapplicationeli.BuildConfig;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.app.UserEntranceRequest;
import com.reserv.myapplicationeli.api.hotel.comment.GetComment;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.lost.CommentAdapterRecycle;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequestAdd;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequsetAddComment;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.ReviewComment;
import com.reserv.myapplicationeli.models.hotel.api.getComment.call.GetCommentRequest;
import com.reserv.myapplicationeli.models.hotel.api.getComment.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.request.UserRequest;
import com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.response.SearchNotes;
import com.reserv.myapplicationeli.tools.Prefs;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.activities.AboutActivity;
import com.reserv.myapplicationeli.views.activities.hotel.activity.DetailHotelActivity;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.adapters.hotel.comment.CommentModel;
import com.reserv.myapplicationeli.views.ui.dialog.app.InternetAlert;
import com.reserv.myapplicationeli.views.ui.dialog.app.SplashDialog;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class SplashFragment extends BaseActivity implements SplashDialog.TryDialogListener {

    private Runnable runnable, runnable2;
    private Handler handler, handler2;
    private ImageView ivSplash, ivLoading;
    AVLoadingIndicatorView avi;
    LottieAnimationView lottieAnimationView;
    UserEntranceRequest userEntranceRequest;
    String deviceId;
    String deviceSubscriberID;
    String operator;
    String sdkVersion;
    String model;
    String brand;
    String product;

    @Override
    public void onReturnValue() {
        deviceId = Utility.getDeviceID(SplashFragment.this);
        deviceSubscriberID = Utility.getSubscriberID(SplashFragment.this);
        operator =Utility.getMyOperator(SplashFragment.this);
        sdkVersion = android.os.Build.VERSION.SDK_INT + "";
        model = android.os.Build.MODEL;
        brand = Build.BRAND;
        product =Build.PRODUCT;
        new GetCommentAsync().execute();
    }


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


        //
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



                                if (Utility.isNetworkAvailable(SplashFragment.this)) {
                                    deviceId = Utility.getDeviceID(SplashFragment.this);
                                    deviceSubscriberID = Utility.getSubscriberID(SplashFragment.this);
                                    operator =Utility.getMyOperator(SplashFragment.this);
                                    sdkVersion = android.os.Build.VERSION.SDK_INT + "";
                                    model = android.os.Build.MODEL;
                                    brand = Build.BRAND;
                                    product =Build.PRODUCT;
                                    new GetCommentAsync().execute();

                                } else {

                                    new InternetAlert(SplashFragment.this);
                                }
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                            }
                        })
                        .setDeniedMessage("If you reject permission,you can not use this application, Please turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   handler.removeCallbacks(runnable);
//        handler2.removeCallbacks(runnable2);

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
            avi.setVisibility(View.VISIBLE);


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                userEntranceRequest=new UserEntranceRequest(new UserRequest
                        (new com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.request.
                                UserEntranceRequest(deviceId,deviceSubscriberID,sdkVersion,model,product, BuildConfig.VERSION_CODE,2,operator,brand,"fa-IR",new Identity("EligashtMlb",
                                "123qwe!@#QWE", "Mobile"))));
                Log.e("ggg", new Gson().toJson(new UserRequest
                        (new com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.request.
                                UserEntranceRequest(deviceId,deviceSubscriberID,sdkVersion,model,product, BuildConfig.VERSION_CODE,2,operator,brand,"fa-IR",new Identity("EligashtMlb",
                                "123qwe!@#QWE", "Mobile")))) );


            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);
            avi.setVisibility(View.GONE);

            try {



                Log.e("onon", userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.CanEnter+"" );
                Utility.sendTag("Splash",true,true);
                for (SearchNotes searchNotes :userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.SearchNotes){
                    if (searchNotes.Section.equals("H")){
                        Prefs.putString("H",new Gson().toJson(searchNotes.Notes));

                    }

                    if (searchNotes.Section.equals("F")){
                        Prefs.putString("F",new Gson().toJson(searchNotes.Notes));

                    }
                    if (searchNotes.Section.equals("FH")){
                        Prefs.putString("FH",new Gson().toJson(searchNotes.Notes));

                    }
                    if (searchNotes.Section.equals("P")){
                        Prefs.putString("P",new Gson().toJson(searchNotes.Notes));

                    }

                }
                if (userEntranceRequest.entranceResponse.MobileAppStartupServiceResult.UserEntranceResponse.CanEnter){


                    startActivity(new Intent(SplashFragment.this, MainActivity.class));
                    finish();
                }else{

                    alert();
                }



            } catch (Exception e) {
                //   Toast.makeText(SplashFragment.this, "ارتباط با سرور مقدور نمی باشد", Toast.LENGTH_SHORT).show();

                alert();


            }
        }
    }
    public void alert(){
        new SplashDialog(SplashFragment.this,this);
    }
}
