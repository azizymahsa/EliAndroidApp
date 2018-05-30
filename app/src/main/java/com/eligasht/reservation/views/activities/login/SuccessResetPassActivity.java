package com.eligasht.reservation.views.activities.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eligasht.R;

import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.views.ui.InitUi;

/**
 * Created by elham.bonyani on 1/23/2018.
 * this class for return massage that go to email
 */

public class SuccessResetPassActivity extends BaseActivity implements View.OnClickListener{

    private Button btnBackHomeLog;
    TextView tvInfo;


    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_reset_pass);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.activate_account));
        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(SuccessResetPassActivity.this
                    ,R.color.colorPrimaryDark));
        }
        initViews();


    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    private void initViews() {
        btnBackHomeLog = findViewById(R.id.btnBackHomePage);
        tvInfo = findViewById(R.id.tvInfo);
        btnBackHomeLog.setOnClickListener(this);
        tvInfo.setText(getIntent().getExtras().getString("value"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackHomePage :
               /* Intent intent = new Intent(this , LogInActivity.class);
                startActivity(intent);*/
               finish();
                break;
        }
    }
}
