package com.reserv.myapplicationeli.views.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.base.BaseActivity;
import com.reserv.myapplicationeli.views.ui.PlanFragment;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FancyButton btnMenu;
    private DrawerLayout drawerLayout;
    private TextView tvTitle;
    private RelativeLayout btnFlight,btnHotel,btnPackage,btnTour,btnInsurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
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

        btnMenu.setCustomTextFont("fonts/icomoon.ttf");
        tvTitle.setText(getString(R.string.searchFlight));
        btnMenu.setText(getString(R.string.icon_menu));

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
                Toast.makeText(this, "btnHotel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnPackage:
                Toast.makeText(this, "btnPackage", Toast.LENGTH_SHORT).show();
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
