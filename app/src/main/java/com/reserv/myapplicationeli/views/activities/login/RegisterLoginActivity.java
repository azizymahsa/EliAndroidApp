package com.reserv.myapplicationeli.views.activities.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.login.WebUserLogin;
import com.reserv.myapplicationeli.models.model.login.call.RegisterListReq;
import com.reserv.myapplicationeli.models.model.login.call.RegisterRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.WebUserRegisterRes;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.WebUserTools;
import com.reserv.myapplicationeli.views.ui.InitUi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class RegisterLoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText txtEmail;
    private EditText txtPass;
    private EditText txtConfirmPass;
    private Button btnRegister;
    private ClientService service;


    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "ثبت نام");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();
        service = ServiceGenerator.createService(ClientService.class);


    }

    private void Register(){

        RegisterListReq registerListReq = new RegisterListReq();
        registerListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        registerListReq.setCulture("fa-IR");
        registerListReq.setUsername(txtEmail.getText().toString());
        registerListReq.setPassword(txtPass.getText().toString());

        needShowProgressDialog();
        Log.e(" request " ,new GsonBuilder().create().toJson(new RegisterRequestModel(registerListReq)));
        Call<WebUserRegisterRes> call = service.Register(new RegisterRequestModel(registerListReq));
        call.enqueue(new Callback<WebUserRegisterRes>() {
            @Override
            public void onResponse(Call<WebUserRegisterRes> call, Response<WebUserRegisterRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getWebUserRegisterResult() == null) {
                    Toast.makeText(RegisterLoginActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getWebUserRegisterResult().getError()!=null){
                    Toast.makeText(RegisterLoginActivity.this, response.body().getWebUserRegisterResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                WebUserLogin webUserLogin = response.body().getWebUserRegisterResult().getWebUserLogin();

                if(webUserLogin== null){
                    Toast.makeText(RegisterLoginActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(webUserLogin.getLoginStatus().toUpperCase().equals("DUP")){
                    Toast.makeText(RegisterLoginActivity.this, " ایمیل وارد شده در حال حاضر در سیستم وجود دارد .", Toast.LENGTH_SHORT).show();
                    return;
                }

                WebUserTools.getInstance().setUser(webUserLogin);
                Intent intent = new Intent(RegisterLoginActivity.this,SuccessResetPassActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<WebUserRegisterRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(RegisterLoginActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initViews() {
        txtEmail = findViewById(R.id.edit_email);
        txtPass = findViewById(R.id.edit_pass);
        txtConfirmPass = findViewById(R.id.edit_confirm_pass);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                if(txtEmail.length() == 0){
                    Toast.makeText(this, "لطفا ایمیل خود را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!ValidationTools.isEmailValid(txtEmail.getText().toString())){
                    Toast.makeText(this, "ایمیل وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(txtPass.length() == 0){
                    Toast.makeText(this, "لطفا رمز عبور خود را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(txtConfirmPass.length() == 0){
                    Toast.makeText(this, "لطفا تکرار رمز عبور را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!txtPass.getText().toString().equals(txtConfirmPass.getText().toString())){
                    Toast.makeText(this, "رمز عبور و تکرار آن با هم مطابقت ندارد .", Toast.LENGTH_SHORT).show();
                    return;
                }

                Register();

                break;
        }
    }
}
