package com.eligasht.reservation.views.adapters.hotel.hotelDetail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.eligasht.R;
import com.eligasht.reservation.views.fragments.hotelDetail.CommentHotelFragment;
import com.eligasht.reservation.views.fragments.hotelDetail.HotelFacilityFragment;
import com.eligasht.reservation.views.fragments.hotelDetail.MapHotelFragment;
import com.eligasht.reservation.views.fragments.hotelDetail.RoomHotelFragment;

/**
 * Created by Reza.nejati on 4/4/2018.
 */

public class HotelDetailViewPager extends FragmentPagerAdapter {
    private Context context;
    private CommentHotelFragment commentHotelFragment;


    public HotelDetailViewPager(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        commentHotelFragment=CommentHotelFragment.instance();

    }

    public CommentHotelFragment getCommentHotelFragment() {
        return commentHotelFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return commentHotelFragment;

            case 1:
                return MapHotelFragment.instance();
            case 2:
                return HotelFacilityFragment.instance();
            case 3:
                return RoomHotelFragment.instance();
            default:
                return RoomHotelFragment.instance();
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
                return context.getString(R.string.Comments);

            case 1:
                return context.getString(R.string.Map);
            case 2:

                return context.getString(R.string.Possibilities);
            case 3:

                return context.getString(R.string.ReservRoom);
            default:
                return context.getString(R.string.edit_profile);
        }
    }
}
