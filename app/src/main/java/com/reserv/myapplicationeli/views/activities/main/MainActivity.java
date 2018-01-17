package com.reserv.myapplicationeli.views.activities.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;

import com.reserv.myapplicationeli.base.BaseActivity;

import com.reserv.myapplicationeli.views.activities.AboutActivity;
import com.reserv.myapplicationeli.views.activities.ConditionActivity;
import com.reserv.myapplicationeli.views.activities.ContactUsActivity;
import com.reserv.myapplicationeli.views.fragments.HotelFlightFragment;
import com.reserv.myapplicationeli.views.fragments.PlanFragment;
import com.reserv.myapplicationeli.views.fragments.hotel.HotelFragment;
import com.reserv.myapplicationeli.views.fragments.insurance.InsuranceFragment;
import com.reserv.myapplicationeli.views.fragments.pack.PackageFragment;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.SearchParvazActivity;


import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FancyButton btnMenu;
    private DrawerLayout drawerLayout;
    private TextView tvTitle;
    private RelativeLayout btnFlight,btnHotel,btnPackage,btnTour,btnInsurance,btnHotelFlight,btnAbout,btnContactUs,btn_condition;
    public static String GET_FRAGMENT = null;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        manager = getSupportFragmentManager();
        Window window = getWindow();

        window.setStatusBarColor(getColor(R.color.flight_status));
        InitUi.Toolbar(this,true, R.color.TRANSPARENT,"صفحه اصلی");
        initViews();

        PlanFragment workerStateFragment = new PlanFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, workerStateFragment)
                .commit();

    }

    public void initViews() {
        //findView==================================================================================
        btnMenu = findViewById(R.id.btnMenu);
        drawerLayout = findViewById(R.id.drawerLayout);
        tvTitle = findViewById(R.id.tvTitle);

        btnFlight = findViewById(R.id.btnFlight);
        btnHotel = findViewById(R.id.btnHotel);
        btnPackage = findViewById(R.id.btnPackage);
        btnTour = findViewById(R.id.btnTour);
        btnInsurance = findViewById(R.id.btnInsurance);
        btnHotelFlight = findViewById(R.id.btnHotelFlight);
        btnAbout = findViewById(R.id.btnAbout);
        btnContactUs= findViewById(R.id.btnContactUs);
        btn_condition= findViewById(R.id.btn_condition);

        tvTitle.setText(getString(R.string.searchFlight));


        //onClick===================================================================================
        btnMenu.setOnClickListener(this);
        btnHotel.setOnClickListener(this);
        btnPackage.setOnClickListener(this);
        btnTour.setOnClickListener(this);
        btnInsurance.setOnClickListener(this);
        btnHotelFlight.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
        btn_condition.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerVisible(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.LEFT);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMenu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.btnFlight:
                addFragment(getString(R.string.searchFlight),new PlanFragment());

                break;
            case R.id.btnHotel:
                addFragment(getString(R.string.search_hotel),new HotelFragment());

                break;
            case R.id.btnPackage:
                addFragment(getString(R.string.search_package),new PackageFragment());
                break;
            case R.id.btnTour:
                break;
                case R.id.btnInsurance:
                    addFragment(getString(R.string.btn_insurance),new InsuranceFragment());

                    break;
                case R.id.btnHotelFlight:
                    addFragment("هتل و پرواز",new HotelFlightFragment());

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

        }

    }
    public void addFragment(String title, Fragment fragment){
        tvTitle.setText(title);
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment )
                .commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               drawerLayout.closeDrawer(Gravity.RIGHT);

            }
        }, 500);

    }
}
