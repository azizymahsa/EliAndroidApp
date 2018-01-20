package com.reserv.myapplicationeli.views.activities.login;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.login.call.LoginListReq;
import com.reserv.myapplicationeli.models.model.login.call.LoginRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.TWebUserLogin;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.ui.InitUi;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class LogInActivity extends BaseActivity implements View.OnClickListener {



    private FancyButton btnLogin;
    private FancyButton btnRegister;
    private EditText txtEmail;
    private EditText txtPassword;
    private ImageView eLogo;
    private LinearLayout layoutResetPassword;
    private ClientService service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitUi.Toolbar(this, false, R.color.TRANSPARENT, " ");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();
        service = ServiceGenerator.createService(ClientService.class);


    }

    private void Login(){

        LoginListReq loginListReq = new LoginListReq();
        loginListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        loginListReq.setCulture("fa-IR");
        loginListReq.setUserName(txtEmail.getText().toString());
        loginListReq.setPassword(txtPassword.getText().toString());

        Call<TWebUserLogin> call = service.Login(new LoginRequestModel());
        call.enqueue(new Callback<TWebUserLogin>() {
            @Override
            public void onResponse(Call<TWebUserLogin> call, Response<TWebUserLogin> response) {

                Log.e("loginRes", "res : " + response.body().getWebUserLogin());
                if (response == null
                        || response.body() == null
                        || response.body().getWebUserLogin() == null) {
                    return;
                }


            }

            @Override
            public void onFailure(Call<TWebUserLogin> call, Throwable t) {

            }
        });

    }

    private void initViews() {
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btnLogIn);
        txtEmail = findViewById(R.id.txt_email);
        eLogo = findViewById(R.id.e_logo);
        layoutResetPassword = findViewById(R.id.layout_reset_password);
        txtPassword = findViewById(R.id.txt_password);

        btnRegister.setCustomTextFont("fonts/irsans.ttf");
        btnLogin.setCustomTextFont("fonts/irsans.ttf");
        eLogo.setVisibility(View.INVISIBLE);


        btnLogin.setOnClickListener(this);
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

            case R.id.btnLogIn:
                if(txtEmail.length() == 0){
                    Toast.makeText(this, "لطفا ایمیل خود را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!ValidationTools.isEmailValid(txtEmail.getText().toString())){
                    Toast.makeText(this, "ایمیل وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtPassword.length() == 0){
                    Toast.makeText(this, "لطفا رمز عبور خود را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }
                Login();
                break;
        }

    }
}
