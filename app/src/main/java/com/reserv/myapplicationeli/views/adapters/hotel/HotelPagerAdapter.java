package com.reserv.myapplicationeli.views.adapters.hotel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.reserv.myapplicationeli.views.activities.hotel.viewPagerFragmnets.MapFragment;

/**
 * Created by Reza.nejati on 1/3/2018.
 */

public class HotelPagerAdapter extends FragmentPagerAdapter {
    public HotelPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return MapFragment.newInstance(0, "خدمات");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return MapFragment.newInstance(1, "امکانات");
            case 2: // Fragment # 1 - This will show SecondFragment
                return MapFragment.newInstance(2, "انتخاب و رزرو اتاق");

            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position)
        {
            case 0:
                title="خدمات";
            break;
            case 1:
                title="امکانات";
                break;
            case 2:
                title="انتخاب و رزرو اتاق";

                break;
        }


        return title;
    }

}

