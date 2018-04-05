package com.eligasht.reservation.views.adapters.hotel.hotelDetail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.eligasht.R;
import com.eligasht.reservation.views.fragments.hotelDetail.MapHotelFragment;
import com.eligasht.reservation.views.fragments.hotelDetail.RoomHotelFragment;

/**
 * Created by Reza.nejati on 4/4/2018.
 */

public class HotelDetailViewPager extends FragmentPagerAdapter {
    private Context context;
    private MapHotelFragment mapHotelFragment;

    public HotelDetailViewPager(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;

        mapHotelFragment = MapHotelFragment.instance();
    }

    public MapHotelFragment getMapHotelFragment() {
        return mapHotelFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RoomHotelFragment.instance();

            case 1:
                return MapHotelFragment.instance();
            case 2:
                return MapHotelFragment.instance();
            case 3:
                return MapHotelFragment.instance();
            default:
                return MapHotelFragment.instance();
        }
    }
    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.Map);

            case 1:
                return context.getString(R.string.Possibilities);
            case 2:

                return context.getString(R.string.Comments);
            case 3:

                return context.getString(R.string.ReservRoom);
            default:
                return context.getString(R.string.edit_profile);
        }
    }
}
