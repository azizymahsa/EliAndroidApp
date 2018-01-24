package com.reserv.myapplicationeli.views.fragments.profile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ProfilePagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public ProfilePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EditProfileFragment.instance();
            case 1:
                return MyContractsFragment.instance();
            case 2:
                return ChangePasswordFragment.instance();
            default:
                return EditProfileFragment.instance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ویرایش پروفایل";
            case 1:
                return " قراردادهای من";
            case 2:
                return " تغییر کلمه عبور";
            default:
                return "ویرایش پروفایل";
        }
    }
}
