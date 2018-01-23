package com.reserv.myapplicationeli.views.activities.login;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;


/**
 * Created by elham.bonyani on 1/23/2018.
 */

public class ProfileActivity extends BaseActivity{

    private ClientService service;

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        InitUi.Toolbar(this, false, R.color.TRANSPARENT, " ");
//        Window window = getWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
//        }
        initViews();
        service = ServiceGenerator.createService(ClientService.class);


    }

    private void initViews() {
    }
}
