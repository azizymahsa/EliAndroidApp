package com.reserv.myapplicationeli.views.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.base.BaseActivity;
import com.reserv.myapplicationeli.views.ui.PlanFragment;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FancyButton btnMenu;
    private DrawerLayout drawerLayout;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

        btnMenu.setCustomTextFont("fonts/icomoon.ttf");
        tvTitle.setText(getString(R.string.searchFlight));
        btnMenu.setText(getString(R.string.icon_menu));

        //onclick===================================================================================
        btnMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnMenu:
                drawerLayout.openDrawer(Gravity.RIGHT);

                break;
        }

    }
}
