package com.reserv.myapplicationeli.views.activities.main;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableWeightLayout;
import com.onesignal.OneSignal;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;

import com.reserv.myapplicationeli.api.hotel.comment.AddComment;
import com.reserv.myapplicationeli.base.BaseActivity;

import com.reserv.myapplicationeli.tools.WebUserTools;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequestAdd;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequsetAddComment;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.ReviewComment;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.views.activities.AboutActivity;
import com.reserv.myapplicationeli.views.activities.ConditionActivity;
import com.reserv.myapplicationeli.views.activities.ContactUsActivity;
import com.reserv.myapplicationeli.views.activities.hotel.activity.DetailHotelActivity;
import com.reserv.myapplicationeli.views.activities.login.LogInActivity;
import com.reserv.myapplicationeli.views.activities.login.ProfileActivity;
import com.reserv.myapplicationeli.views.dialogs.LogOutAlert;
import com.reserv.myapplicationeli.views.fragments.HotelFlightFragment;
import com.reserv.myapplicationeli.views.fragments.PlanFragment;
import com.reserv.myapplicationeli.views.fragments.hotel.HotelFragment;
import com.reserv.myapplicationeli.views.fragments.insurance.InsuranceFragment;
import com.reserv.myapplicationeli.views.fragments.pack.PackageFragment;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.SearchParvazActivity;
import com.reserv.myapplicationeli.views.ui.dialog.app.CountTimeAlert;


import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FancyButton btnMenu;
    private DrawerLayout drawerLayout;
    private TextView tvTitle, tvArrow;
    private FancyButton btnFlight, btnHotel, btnPackage, btnTour, btnInsurance, btnHotelFlight, btnAbout, btnContactUs, btn_condition, btnLastBuy;
    public static String GET_FRAGMENT = null;
    private FragmentManager manager;
    RelativeLayout rlUser;
    private static TextView txt_name;
    ExpandableWeightLayout expandableLayout;
    ImageView ivUser;
    RelativeLayout rlHedaer;
    CountDownTimer countDownTimer;
    private BroadcastReceiver sendFinish;
    private BroadcastReceiver sendStartTimer, sendDetailFinish;
    int TotalTime = 2000000;
    Button btnExit;
    private boolean doubleBackToExitPressedOnce = false;
    private int TIME_INTERVAL = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        manager = getSupportFragmentManager();
        Window window = getWindow();
        sendDetailFinish();
        window.setStatusBarColor(getColor(R.color.flight_status));
        InitUi.Toolbar(this, true, R.color.TRANSPARENT, "صفحه اصلی");

        timer();
        timerRecive();
        initViews();
    }

    public void initViews() {
        //findView==================================================================================
        btnMenu = findViewById(R.id.btnMenu);
        drawerLayout = findViewById(R.id.drawerLayout);
        tvTitle = findViewById(R.id.tvTitle);

        btnFlight = findViewById(R.id.btnFlight);
        btnHotel = findViewById(R.id.btnHotel);
        btnPackage = findViewById(R.id.btnPackage);
        btnInsurance = findViewById(R.id.btnInsurance);
        btnHotelFlight = findViewById(R.id.btnHotelFlight);
        btnAbout = findViewById(R.id.btnAbout);
        btnContactUs = findViewById(R.id.btnContactUs);
        btn_condition = findViewById(R.id.btn_condition);
        rlUser = findViewById(R.id.rlUser);
        txt_name = findViewById(R.id.txt_name);
        tvArrow = findViewById(R.id.tvArrow);
        ivUser = findViewById(R.id.ivUser);
        rlHedaer = findViewById(R.id.rlHedaer);
        btnExit = findViewById(R.id.btnExit);

        btnLastBuy = findViewById(R.id.btnLastBuy);

        tvTitle.setText(getString(R.string.searchFlight));
        try {
            if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() != -1) {
                txt_name.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
                btnExit.setVisibility(View.VISIBLE);
                tvArrow.setVisibility(View.VISIBLE);
                rlUser.setClickable(true);


            } else {
                txt_name.setText("ورود به حساب کاربری");
                btnExit.setVisibility(View.GONE);
                tvArrow.setVisibility(View.INVISIBLE);
                rlUser.setClickable(false);

            }
        } catch (Exception e) {
            txt_name.setText("ورود به حساب کاربری");
            btnExit.setVisibility(View.GONE);


        }


        //onClick===================================================================================
        btnMenu.setOnClickListener(this);
        btnHotel.setOnClickListener(this);
        btnPackage.setOnClickListener(this);
//        btnTour.setOnClickListener(this);
        btnInsurance.setOnClickListener(this);
        btnHotelFlight.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
        btn_condition.setOnClickListener(this);
        rlUser.setOnClickListener(this);
        ivUser.setOnClickListener(this);
        rlHedaer.setOnClickListener(this);
        btnFlight.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnLastBuy.setOnClickListener(this);
        expandableLayout = findViewById(R.id.expandableLayout);

        switch (Prefs.getInt("type", 0)) {
            case 0:
                addFragment(getString(R.string.searchFlight), new PlanFragment());
                break;
            case 1:
                addFragment(getString(R.string.search_hotel), new HotelFragment());
                break;
            case 2:
                addFragment(getString(R.string.search_package), new PackageFragment());
                break;
            case 3:
                addFragment(getString(R.string.btn_insurance), new InsuranceFragment());
                break;
            case 4:
                addFragment("هتل و پرواز", new HotelFlightFragment());
                break;
            default:
                PlanFragment workerStateFragment = new PlanFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, workerStateFragment)
                        .commit();
                break;
        }


    }

    public static void setUserName(String name) {
        if (txt_name != null) {
            txt_name.setText(name);
        }
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        if (drawerLayout.isDrawerVisible(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);

        } else {


            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.back_exit), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }

            }, TIME_INTERVAL);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMenu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.btnFlight:
                addFragment(getString(R.string.searchFlight), new PlanFragment());
                Prefs.putInt("type", 0);

                break;
            case R.id.btnHotel:
                addFragment(getString(R.string.search_hotel), new HotelFragment());
                Prefs.putInt("type", 1);

                break;
            case R.id.btnPackage:
                addFragment(getString(R.string.search_package), new PackageFragment());
                Prefs.putInt("type", 2);

                break;

            case R.id.btnInsurance:
                addFragment(getString(R.string.btn_insurance), new InsuranceFragment());
                Prefs.putInt("type", 3);
                break;
            case R.id.btnHotelFlight:
                addFragment("هتل و پرواز", new HotelFlightFragment());
                Prefs.putInt("type", 4);
                addFragment("بلیط هواپیما + رزرو هتل", new HotelFlightFragment());

                break;
            case R.id.btnAbout:
                //addFragment(" درباره ما ",new HotelFlightFragment());
                // Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
                // myIntent.putExtra("key", value); //Optional parameters
                Intent intent1 = new Intent(this, AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnContactUs:
                Intent intent2 = new Intent(this, ContactUsActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_condition:
                Intent intent3 = new Intent(this, ConditionActivity.class);
                startActivity(intent3);
                break;
            case R.id.ivUser:


                try {
                    if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() == -1) {
                        startActivity(new Intent(this, LogInActivity.class));
                    } else {
                        startActivity(new Intent(this, ProfileActivity.class));

                    }
                } catch (Exception e) {
                    startActivity(new Intent(this, LogInActivity.class));


                }


                break;
            case R.id.rlUser:

                if (expandableLayout.isExpanded()) {

                    expandableLayout.collapse();
                    tvArrow.setText(getString(R.string.icon_arrow_up));

                } else {
                    expandableLayout.expand();
                    tvArrow.setText(getString(R.string.icon_arrow_down));

                }

                break;
            case R.id.btnExit:
                new LogOutAlert(this);
                break;
           /* case R.id.btnLastBuy:
                Intent intent =new Intent(this, ProfileActivity.class);
                intent.putExtra("isLastBuy",true);
                startActivity(intent);

                break;*/

        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        try {
            if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() != -1) {
                txt_name.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
                btnExit.setVisibility(View.VISIBLE);
                tvArrow.setVisibility(View.VISIBLE);
                rlUser.setClickable(true);


            } else {
                txt_name.setText("ورود به حساب کاربری");
                btnExit.setVisibility(View.GONE);
                tvArrow.setVisibility(View.INVISIBLE);
                rlUser.setClickable(false);

            }
        } catch (Exception e) {
            txt_name.setText("ورود به حساب کاربری");
            btnExit.setVisibility(View.GONE);


        }

    }

    public void onDestroy() {

        super.onDestroy();
        // Prefs.putInt("type",0);
    }

    public void addFragment(String title, Fragment fragment) {
        tvTitle.setText(title);
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawerLayout.closeDrawer(Gravity.RIGHT);

            }
        }, 500);

    }

    public void timer() {
        countDownTimer = new CountDownTimer(2000000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval

            public void onTick(long millisUntilFinished) {


                Log.e("test", "seconds remaining: " + millisUntilFinished / 1000);
                Prefs.putLong("time", millisUntilFinished);

            }

            public void onFinish() {
                sendFinish(false, 0);
                Toast.makeText(MainActivity.this, "زمان شما به پایان رسید.", Toast.LENGTH_SHORT).show();
            }
        };


    }


    public void sendFinish(boolean finish, int time) {
        Intent intent = new Intent("sendFinish");
        intent.putExtra("time", time);
        intent.putExtra("finish", finish);
        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
    }


    public void timerRecive() {
        sendStartTimer = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                countDownTimer.cancel();
                countDownTimer.start();


            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(sendStartTimer,
                new IntentFilter("sendStartTimer"));

    }

    public void sendDetailFinish() {
        sendDetailFinish = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                countDownTimer.onFinish();


            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(sendDetailFinish,
                new IntentFilter("sendDetailFinish"));

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

}
