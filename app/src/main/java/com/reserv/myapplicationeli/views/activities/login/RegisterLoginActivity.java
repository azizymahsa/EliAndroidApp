package com.reserv.myapplicationeli.views.activities.login;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.login.call.RegisterListReq;
import com.reserv.myapplicationeli.models.model.login.call.RegisterRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.TWebUserLogin;
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
    private ClientService service;


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
        Register();

    }

    private void Register(){

        RegisterListReq registerListReq = new RegisterListReq();
        registerListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        registerListReq.setCulture("fa-IR");
        registerListReq.setUsername(txtEmail.getText().toString());
        registerListReq.setPassword(txtPass.getText().toString());

        Call<TWebUserLogin> call = service.Register(new RegisterRequestModel());
        call.enqueue(new Callback<TWebUserLogin>() {
            @Override
            public void onResponse(Call<TWebUserLogin> call, Response<TWebUserLogin> response) {
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
        txtEmail = findViewById(R.id.edit_email);
        txtPass = findViewById(R.id.edit_pass);
        txtConfirmPass = findViewById(R.id.edit_confirm_pass);


    }


    @Override
    public void onClick(View v) {

    }
}
