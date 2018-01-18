package com.reserv.myapplicationeli.views.activities.login;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class LogInActivity extends BaseActivity implements View.OnClickListener {



    private FancyButton btnLogin;
    private FancyButton btnRegister;
    private EditText txtEmail;
    private ImageView eLogo;
    private LinearLayout layoutResetPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitUi.Toolbar(this, false, R.color.TRANSPARENT, " ");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();

    }

    private void initViews() {
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btnLogIn);
        txtEmail = findViewById(R.id.txt_email);
        eLogo = findViewById(R.id.e_logo);
        layoutResetPassword = findViewById(R.id.layout_reset_password);

        btnRegister.setCustomTextFont("fonts/irsans.ttf");
        btnLogin.setCustomTextFont("fonts/irsans.ttf");
        eLogo.setVisibility(View.INVISIBLE);


        btnRegister.setOnClickListener(this);
        layoutResetPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register :
                Intent intent = new Intent(this,RegisterLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_reset_password :
                Intent _intent = new Intent(this,ResetPasswordActivity.class);
                startActivity(_intent);
                break;
        }

    }
}
