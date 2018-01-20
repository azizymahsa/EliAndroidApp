package com.reserv.myapplicationeli.views.activities.login;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;

/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "بازیابی رمز عبور");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();

    }

    private void initViews() {
    }

    @Override
    public void onClick(View v) {

    }
}
