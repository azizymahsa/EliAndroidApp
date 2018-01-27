package com.reserv.myapplicationeli.views.activities.login;


import android.annotation.SuppressLint;
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

import com.google.gson.GsonBuilder;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.login.WebUserLogin;
import com.reserv.myapplicationeli.models.model.login.call.LoginListReq;
import com.reserv.myapplicationeli.models.model.login.call.LoginRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.LoginRes;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.WebUserTools;
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
    private LinearLayout btnRegister;
    private EditText txtEmail;
    private EditText txtPassword;
    private ImageView eLogo;
    private LinearLayout layoutResetPassword;
    private ClientService service;

    @SuppressLint("NewApi")
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

        needShowProgressDialog();
        Log.e(" request " ,new GsonBuilder().create().toJson(new LoginRequestModel(loginListReq)));
        Call<LoginRes> call = service.Login(new LoginRequestModel(loginListReq));
        call.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getLoginResult() == null) {
                    Toast.makeText(LogInActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getLoginResult().getError()!=null){
                    Toast.makeText(LogInActivity.this, response.body().getLoginResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                WebUserLogin webUserLogin = response.body().getLoginResult().getWebUserLogin();
                if(webUserLogin == null){
                    Toast.makeText(LogInActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(webUserLogin.getLoginStatus().equals("NO")){
                    Toast.makeText(LogInActivity.this, "ایمیل و یا رمز عبور شما اشتباه می باشد .", Toast.LENGTH_SHORT).show();
                    return;
                }



                WebUserTools.getInstance().setUser(webUserLogin);
                Log.e("contract" , response.body().getLoginResult().getWebUserLogin().getPreviousContracts().get(0).getCntID() +"");
                Intent intent = new Intent(LogInActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();


            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(LogInActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();

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
