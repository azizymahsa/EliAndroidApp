package com.reserv.myapplicationeli.views.activities.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.reserv.myapplicationeli.views.fragments.HotelActivity;
import com.reserv.myapplicationeli.views.fragments.PackageFragment;
import com.reserv.myapplicationeli.views.fragments.PlanFragment;
import com.reserv.myapplicationeli.views.ui.InitUi;


import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FancyButton btnMenu;
    private DrawerLayout drawerLayout;
    private TextView tvTitle;
    private RelativeLayout btnFlight,btnHotel,btnPackage,btnTour,btnInsurance;
    public static String GET_FRAGMENT = null;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        manager = getSupportFragmentManager();
        Window window = getWindow();

        window.setStatusBarColor(getColor(R.color.flight_status));
        InitUi.Toolbar(this,true, R.color.TRANSPARENT);
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


        tvTitle.setText(getString(R.string.searchFlight));


        //onClick===================================================================================
        btnMenu.setOnClickListener(this);
        btnHotel.setOnClickListener(this);
        btnPackage.setOnClickListener(this);
        btnTour.setOnClickListener(this);
        btnInsurance.setOnClickListener(this);

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
                Toast.makeText(this, "btnFlight", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnHotel:
                break;
            case R.id.btnPackage:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                tvTitle.setText(getString(R.string.search_package));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawerLayout.closeDrawer(Gravity.RIGHT);

                    }
                }, 500);

                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                PackageFragment packageFragment = new PackageFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,packageFragment )
                        .commit();
                break;
            case R.id.btnTour:
                Toast.makeText(this, "btnTour", Toast.LENGTH_SHORT).show();
                break;
                case R.id.btnInsurance:
                Toast.makeText(this, "btnInsurance", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
