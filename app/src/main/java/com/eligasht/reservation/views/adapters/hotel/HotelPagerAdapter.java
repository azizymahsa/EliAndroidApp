/*
package com.reserv.myapplicationeli.views.adapters.hotel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import MapFragment;
import com.reserv.myapplicationeli.views.activities.hotel.viewPagerFragmnets.SelectRoomsFragment;
import RoomsModel;

import java.util.ArrayList;

*/
/**
 * Created by Reza.nejati on 1/3/2018.
 *//*


public class HotelPagerAdapter extends FragmentPagerAdapter {
    ArrayList<RoomsModel> roomsModels;
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
                return MapFragment.newInstance(0, "نقشه هتل");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return SelectRoomsFragment.newInstance(1 ,"امکانات",roomsModels);
            case 2: // Fragment # 1 - This will show SecondFragment
                return SelectRoomsFragment.newInstance(2, "انتخاب و رزرو اتاق",roomsModels);

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
                title="نقشه هتل";
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

*/
