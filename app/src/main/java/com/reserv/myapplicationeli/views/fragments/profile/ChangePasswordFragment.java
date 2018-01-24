package com.reserv.myapplicationeli.views.fragments.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.reserv.myapplicationeli.R;


public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    public ViewGroup view;

    public static ChangePasswordFragment instance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_change_password, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initViews();
        return view;
    }


    private void initViews() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
