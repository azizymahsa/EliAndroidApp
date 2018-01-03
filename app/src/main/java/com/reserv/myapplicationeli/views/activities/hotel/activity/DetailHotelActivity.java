package com.reserv.myapplicationeli.views.activities.hotel.activity;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.adapters.hotel.HotelPagerAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;

public class DetailHotelActivity extends BaseActivity {
    private TextView tvTitle;
    private HotelPagerAdapter hotelPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        InitUi.Toolbar(this, false, R.color.color_hotel);
        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.color_hotel_dark));


        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("چهارشنبه، 28 اسفند-جمعه 30 اسفند");


        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        hotelPagerAdapter = new HotelPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(hotelPagerAdapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(vpPager);
    }
}
