package com.eligasht.reservation.views.activities.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableWeightLayout;
import com.pixplicity.easyprefs.library.Prefs;
import com.eligasht.R;

import com.eligasht.reservation.base.Base;

import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.AboutActivity;
import com.eligasht.reservation.views.activities.ConditionActivity;
import com.eligasht.reservation.views.activities.ContactUsActivity;
import com.eligasht.reservation.views.activities.login.LogInActivity;
import com.eligasht.reservation.views.activities.login.ProfileActivity;
import com.eligasht.reservation.views.dialogs.LogOutAlert;
import com.eligasht.reservation.views.fragments.HotelFlightFragment;
import com.eligasht.reservation.views.fragments.PlanFragment;
import com.eligasht.reservation.views.fragments.hotel.HotelFragment;
import com.eligasht.reservation.views.fragments.insurance.InsuranceFragment;
import com.eligasht.reservation.views.fragments.pack.PackageFragment;
import com.eligasht.reservation.views.ui.InitUi;


import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Base implements View.OnClickListener {
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
    CountDownTimer countDownTimer;
    private BroadcastReceiver sendFinish;
    private BroadcastReceiver sendStartTimer, sendDetailFinish;
    int TotalTime = 2000000;
    Button btnExit;
    private boolean doubleBackToExitPressedOnce = false;
    private int TIME_INTERVAL = 2000;
    LinearLayout rlHedaer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        Prefs.putString("raft", "null");
        Prefs.putString("raftfa", "null");
        Prefs.putString("bargasht", "null");
        Prefs.putString("bargashtfa", "null");
        manager = getSupportFragmentManager();
        sendDetailFinish();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.flight_status));
        }

        InitUi.Toolbar(this, true, R.color.TRANSPARENT, "صفحه اصلی");

        //  timer();
        // timerRecive();
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

        //tvTitle.setText(getString(R.string.searchFlight));



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

        addFragment(getString(R.string.searchFlight), new PlanFragment());

      /*  switch (Prefs.getInt("type", 0)) {
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
                addFragment("بلیط هواپیما + رزرو هتل", new HotelFlightFragment());
                break;
            default:
                PlanFragment workerStateFragment = new PlanFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, workerStateFragment)
                        .commit();
                break;
        }
*/

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
                        Prefs.putString("userId","-1");
                    } else {
                        startActivity(new Intent(this, ProfileActivity.class));
                        Prefs.putString("userId",WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID()+"");

                    }
                } catch (Exception e) {
                    startActivity(new Intent(this, LogInActivity.class));
                    Prefs.putString("userId","-1");

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
            case R.id.btnLastBuy:
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("isLastBuy", true);
                startActivity(intent);

                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
       
            initUser();
    }

    public void onDestroy() {

        super.onDestroy();
        Prefs.putString("raft", "null");
        Prefs.putString("raftfa", "null");
        Prefs.putString("bargasht", "null");
        Prefs.putString("bargashtfa", "null");
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
      /*  sendDetailFinish = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                countDownTimer.onFinish();


            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(sendDetailFinish,
                new IntentFilter("sendDetailFinish"));
*/
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    public void initUser() {


        try {
            if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() != -1) {
                txt_name.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
                btnExit.setVisibility(View.VISIBLE);
                tvArrow.setVisibility(View.VISIBLE);
                rlUser.setClickable(true);
              //  expandableLayout.setVisibility(View.VISIBLE);
                Log.e("initUser", "3");

                Prefs.putString("userId",WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID()+"");
                Log.e("initUser11",WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID()+"");

            } else {
                txt_name.setText("ورود به حساب کاربری");
                btnExit.setVisibility(View.GONE);
                tvArrow.setVisibility(View.INVISIBLE);
                rlUser.setClickable(false);
                Prefs.putString("userId","-1");

                if (expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                }
                Log.e("initUser", "1");

            }
        } catch (Exception e) {
            Log.e("initUser", "2");

            txt_name.setText("ورود به حساب کاربری");
            btnExit.setVisibility(View.GONE);
            Prefs.putString("userId","-1");
            if (expandableLayout.isExpanded()) {
                expandableLayout.collapse();

            }
        }


    }

}
