package com.reserv.myapplicationeli.views.fragments.profile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by elham.bonyani on 1/25/2018.
 */

public class ProfilePagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private EditProfileFragment editProfileFragment;
    private MyContractsFragment myContractsFragment;
    private ChangePasswordFragment changePasswordFragment;


    public ProfilePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        editProfileFragment = EditProfileFragment.instance();
        myContractsFragment = MyContractsFragment.instance();
        changePasswordFragment = ChangePasswordFragment.instance();
    }

    public EditProfileFragment getEditProfileFragment(){
        return editProfileFragment;
    }

    public MyContractsFragment getMyContractsFragment() {
        return myContractsFragment;
    }

    public ChangePasswordFragment getChangePasswordFragment() {
        return changePasswordFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return editProfileFragment;
            case 1:
                return myContractsFragment;
            case 2:
                return changePasswordFragment;
            default:
                return editProfileFragment;
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
