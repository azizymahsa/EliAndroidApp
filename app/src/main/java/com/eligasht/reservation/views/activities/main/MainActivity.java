package com.eligasht.reservation.views.activities.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.base.Base;
import com.eligasht.reservation.base.SingletonTimer;
import com.eligasht.reservation.map.OverlayRouteActivity;
import com.eligasht.reservation.models.db.NotificationModel;
import com.eligasht.reservation.models.eventbus.TerminateBus;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.AboutActivity;
import com.eligasht.reservation.views.activities.ConditionActivity;
import com.eligasht.reservation.views.activities.ContactUsActivity;
import com.eligasht.reservation.views.activities.NotificationActivity;
import com.eligasht.reservation.views.activities.SettingsActivity;
import com.eligasht.reservation.views.activities.ShakeActivity;
import com.eligasht.reservation.views.activities.login.LogInActivity;
import com.eligasht.reservation.views.activities.login.ProfileActivity;
import com.eligasht.reservation.views.activities.new_survey.MainSurveyActivity;
import com.eligasht.reservation.views.activities.survey.SurveyActivity;
import com.eligasht.reservation.views.dialogs.LogOutAlert;
import com.eligasht.reservation.views.fragments.HotelFlightFragment;
import com.eligasht.reservation.views.fragments.PlanFragment;
import com.eligasht.reservation.views.fragments.hotel.HotelFragment;
import com.eligasht.reservation.views.fragments.insurance.InsuranceFragment;
import com.eligasht.reservation.views.fragments.pack.PackageFragment;
import com.eligasht.reservation.views.fragments.train.TrainFragment;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.dialog.GiftDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPolicy;
import com.github.aakira.expandablelayout.ExpandableWeightLayout;
import com.onesignal.shortcutbadger.ShortcutBadger;
import com.sdsmdg.tastytoast.TastyToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Base implements View.OnClickListener {
    public static String GET_FRAGMENT = null;
    private static TextView txt_name;
    RelativeLayout rlUser;
    ExpandableWeightLayout expandableLayout;
    LottieAnimationView lottieUserMenu;
    CountDownTimer countDownTimer;
    private AlertDialogPolicy dialog;

    int TotalTime = 2000000;
    Button btnExit;
    LinearLayout rlHedaer;
    private FancyButton btnMenu;
    private DrawerLayout drawerLayout;
    private TextView tvTitle, tvArrow, tvBadge;
    private FancyButton btnGhatar,btnFlight, btnHotel, btnPackage, btnTour, btnInsurance, btnHotelFlight, btnAbout, btnContactUs, btn_condition, btnLastBuy, btnSetting, gift, map, btn_message,survey;
    private FragmentManager manager;
    private BroadcastReceiver sendFinish;
    private BroadcastReceiver sendStartTimer, sendDetailFinish;
    private boolean doubleBackToExitPressedOnce = false;
    private int TIME_INTERVAL = 2000;
    boolean isAnimated = true;
    GiftDialog giftDialog;


    public static void setUserName(String name) {
        if (txt_name != null) {
            txt_name.setText(name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        EventBus.getDefault().register(this);
        Prefs.putString("raft", "null");
        Prefs.putString("raftfa", "null");
        Prefs.putString("bargasht", "null");
        Prefs.putString("bargashtfa", "null");
        Prefs.putString("Flag_First_Computing", "F");
        manager = getSupportFragmentManager();
        sendDetailFinish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.flight_status));
        }

        InitUi.Toolbar(this, true, R.color.TRANSPARENT, "صفحه اصلی");

        initViews();
    }


    public void initViews() {
        //findView==================================================================================
        btnMenu = findViewById(R.id.btnMenu);
        drawerLayout = findViewById(R.id.drawerLayout);
        tvTitle = findViewById(R.id.tvTitle);
        tvBadge = findViewById(R.id.tvBadge);

        btnFlight = findViewById(R.id.btnFlight);
        btnGhatar = findViewById(R.id.btnGhatar);
        survey = findViewById(R.id.survey);
        gift = findViewById(R.id.gift);
        btnHotel = findViewById(R.id.btnHotel);
        btnPackage = findViewById(R.id.btnPackage);
        btnInsurance = findViewById(R.id.btnInsurance);
        btnHotelFlight = findViewById(R.id.btnHotelFlight);
        btnAbout = findViewById(R.id.btnAbout);
        btnContactUs = findViewById(R.id.btnContactUs);
        btn_message = findViewById(R.id.btn_message);
        btn_condition = findViewById(R.id.btn_condition);
        rlUser = findViewById(R.id.rlUser);
        txt_name = findViewById(R.id.txt_name);
        tvArrow = findViewById(R.id.tvArrow);
        rlHedaer = findViewById(R.id.rlHedaer);
        btnExit = findViewById(R.id.btnExit);
        btn_message = findViewById(R.id.btn_message);
        btnSetting = findViewById(R.id.btn_setting);
        map = findViewById(R.id.map);
        btnLastBuy = findViewById(R.id.btnLastBuy);
        lottieUserMenu = findViewById(R.id.lottieUserMenu);
        lottieUserMenu.setAnimation("lottie/user.json");
        lottieUserMenu.setSpeed(1.5f);


        //onClick===================================================================================
        btnMenu.setOnClickListener(this);
        gift.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnHotel.setOnClickListener(this);
        btnPackage.setOnClickListener(this);
        map.setOnClickListener(this);
//        btnTour.setOnClickListener(this);
        btnInsurance.setOnClickListener(this);
        btnHotelFlight.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
        btn_condition.setOnClickListener(this);
        rlUser.setOnClickListener(this);
        lottieUserMenu.setOnClickListener(this);
        rlHedaer.setOnClickListener(this);
        btnFlight.setOnClickListener(this);
        btnGhatar.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnLastBuy.setOnClickListener(this);
        btn_message.setOnClickListener(this);
        survey.setOnClickListener(this);
        expandableLayout = findViewById(R.id.expandableLayout);

        addFragment(getString(R.string.searchFlight), new PlanFragment());


        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (!lottieUserMenu.isAnimating() && isAnimated) {
                    isAnimated = false;
                    lottieUserMenu.playAnimation();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(Gravity.RIGHT)) {
            closeDrawer();
        } else if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
            closeDrawer();
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
                openDrawer();
                break;
            case R.id.btnFlight:
                addFragment(getString(R.string.searchFlight), new PlanFragment());
                Prefs.putInt("type", 0);

                break;
             case R.id.btnGhatar:
                addFragment(getString(R.string.trains), new TrainFragment());
                Prefs.putInt("type", 5);

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
                addFragment(getString(R.string.hotel_reservation_and_plane_ticket), new HotelFlightFragment());

                break;
                case R.id.survey:

                    new Handler().postDelayed(this::closeDrawer, 200);
                    Intent intent4 = new Intent(this, SurveyActivity.class);
                    startActivity(intent4);
                   /* new Handler().postDelayed(this::closeDrawer, 200);
                    Intent intent4 = new Intent(this, MainSurveyActivity.class);
                    startActivity(intent4);
*/
                break;

            case R.id.btnAbout:
                //addFragment(" درباره ما ",new HotelFlightFragment());
                // Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
                // myIntent.putExtra("key", value); //Optional parameters
                new Handler().postDelayed(this::closeDrawer, 200);
                Intent intent1 = new Intent(this, AboutActivity.class);
                startActivity(intent1);

                break;

            case R.id.btnContactUs:
                new Handler().postDelayed(this::closeDrawer, 200);
                Intent intent2 = new Intent(this, ContactUsActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_condition:
                new Handler().postDelayed(this::closeDrawer, 200);
                Intent intent3 = new Intent(this, ConditionActivity.class);
                startActivity(intent3);
                break;

            case R.id.lottieUserMenu:
                new Handler().postDelayed(this::closeDrawer, 200);


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
            case R.id.rlHedaer:
                new Handler().postDelayed(this::closeDrawer, 200);

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
                new Handler().postDelayed(this::closeDrawer, 200);

                new LogOutAlert(this);
                break;
            case R.id.btnLastBuy:
                new Handler().postDelayed(() -> closeDrawer(), 200);

                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("isLastBuy", true);
                startActivity(intent);

                break;

            case R.id.btn_setting:

                new Handler().postDelayed(() -> closeDrawer(), 200);
                Intent intent5 = new Intent(this, SettingsActivity.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent5);
                break;
            case R.id.gift:
                //    startActivity(new Intent(this, ShakeActivity.class));
                if (Prefs.getString("userId", "-1").equals("-1")) {
                    giftDialog = new GiftDialog(this);
                    giftDialog.alertDialog().show();
                } else
                    startActivity(new Intent(this, ShakeActivity.class));

                break;
            case R.id.map:
                startActivity(new Intent(this, OverlayRouteActivity.class));

                break;
            case R.id.btn_message:
                new Handler().postDelayed(this::closeDrawer, 200);

                startActivity(new Intent(this, NotificationActivity.class));

                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        initUser();
        onUpdateBadge();
        SingletonTimer.getInstance().stop();


    }

    public void onDestroy() {

        super.onDestroy();
        Prefs.putString("raft", "null");
        Prefs.putString("raftfa", "null");
        Prefs.putString("bargasht", "null");
        Prefs.putString("bargashtfa", "null");
        // Prefs.putInt("type",0);
        EventBus.getDefault().unregister(this);
    }

    public void addFragment(String title, Fragment fragment) {

        tvTitle.setText(title);
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        new Handler().postDelayed(() -> closeDrawer(), 200);

    }

    void closeDrawer() {
        try {
            final Configuration config = getResources().getConfiguration();
            if (Prefs.getString("lang", "fa").equals("fa") || Prefs.getString("lang", "fa").equals("ar")) {

                if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    drawerLayout.closeDrawer(Gravity.END);

                } else {
                    drawerLayout.closeDrawer(Gravity.START);
                }
            } else {
                if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    drawerLayout.closeDrawer(Gravity.END);

                } else {
                    drawerLayout.closeDrawer(Gravity.START);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void openDrawer() {
        try {
            final Configuration config = getResources().getConfiguration();
            if (Prefs.getString("lang", "fa").equals("fa") || Prefs.getString("lang", "fa").equals("ar")) {
                if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    drawerLayout.openDrawer(Gravity.END);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            } else {
                if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    drawerLayout.openDrawer(Gravity.END);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void timer() {
        countDownTimer = new CountDownTimer(2000000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval

            public void onTick(long millisUntilFinished) {


                Log.e("test", "seconds remaining: " + millisUntilFinished / 1000);
                Prefs.putLong("time", millisUntilFinished);

            }

            public void onFinish() {
                sendFinish(false, 0);
                Toast.makeText(MainActivity.this, R.string.your_time_has_expired, Toast.LENGTH_SHORT).show();
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
                if (giftDialog != null && giftDialog.alertDialog().isShowing()) {
                    giftDialog.alertDialog().dismiss();
                }
                //  expandableLayout.setVisibility(View.VISIBLE);

                Prefs.putString("userId", WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() + "");
                //   Log.e("testtest2222", WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() + "");
                rlHedaer.setClickable(false);
                rlHedaer.setEnabled(false);

            } else {
                txt_name.setText(getString(R.string.login));
                btnExit.setVisibility(View.GONE);
                tvArrow.setVisibility(View.INVISIBLE);
                rlUser.setClickable(false);
                Prefs.putString("userId", "-1");

                if (expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                }
                rlHedaer.setClickable(true);
                rlHedaer.setEnabled(true);
                //    Log.e("testtest22", "2222");

            }
        } catch (Exception e) {

            rlHedaer.setClickable(true);
            rlHedaer.setEnabled(true);
            txt_name.setText(getString(R.string.login));
            btnExit.setVisibility(View.GONE);
            Prefs.putString("userId", "1");
            //  Log.e("testtest22", "3333");

            if (expandableLayout.isExpanded()) {
                expandableLayout.collapse();

            }
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(NotificationModel notificationModel) {
        onUpdateBadge();


    }

    public void onUpdateBadge() {
        if (Prefs.getInt("notifiCounter", 0) != 0) {
            tvBadge.setText(Prefs.getInt("notifiCounter", 0) + "");
            tvBadge.setVisibility(View.VISIBLE);
        } else {
            tvBadge.setVisibility(View.GONE);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void terminate(TerminateBus terminateBus) {
        try {
            if (SingletonTimer.NEED_SHOW_TOAST) {
                SingletonTimer.NEED_SHOW_TOAST = false;
           //     TastyToast.makeText(SingletonContext.getInstance().getContext(), "زمان نشست پایان یافته است", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    dialog = new AlertDialogPolicy(MainActivity.this,false);
                    dialog.setTitle("پیغام");
                    dialog.setText("زمان جستجو شما به پایان رسیده است، لطفا مجددا جستجو نمایید.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
