package com.reserv.myapplicationeli.views.activities.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;

/**
 * Created by elham.bonyani on 1/23/2018.
 */

public class SuccessResetPassActivity extends BaseActivity implements View.OnClickListener{

    private Button btnBackHomeLog;


    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_reset_pass);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "فعالسازی حساب کاربری");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();


    }

    private void initViews() {
        btnBackHomeLog = findViewById(R.id.btnBackHomePage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackHomePage :
                Intent intent = new Intent(this , LogInActivity.class);
                startActivity(intent);
                break;
        }
    }
}
