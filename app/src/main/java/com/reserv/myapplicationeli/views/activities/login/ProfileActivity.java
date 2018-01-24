package com.reserv.myapplicationeli.views.activities.login;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableWeightLayout;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.fragments.profile.ProfilePagerAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * Created by elham.bonyani on 1/23/2018.
 */

public class ProfileActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView img_profile;
    private TextView txt_name;
    private FancyButton btnBack;

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "پروفایل من");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();
        setupPager();
        initParam();
    }

    private void initParam() {
         /**
           here set param , name ande image for profile ;
         **/
    }

    private void setupPager() {
        ProfilePagerAdapter profilePagerAdapter = new ProfilePagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(profilePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViews() {
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        txt_name = findViewById(R.id.txt_name);
        img_profile = findViewById(R.id.img_profile);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
