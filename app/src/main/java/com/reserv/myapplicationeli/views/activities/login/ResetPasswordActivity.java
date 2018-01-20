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
import com.reserv.myapplicationeli.models.model.login.call.ResetPassRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.TWebUserLogin;
import com.reserv.myapplicationeli.views.ui.InitUi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {



    private ClientService service;
    private EditText email_reset_pass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "بازیابی رمز عبور");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();
        service = ServiceGenerator.createService(ClientService.class);

    }

    private void RememberPass(){
        ResetPassRequestModel resetPassRequestModel = new ResetPassRequestModel();
        resetPassRequestModel.setRequest(email_reset_pass.getText().toString());

        Call<TWebUserLogin> call = service.ResetPassword(new ResetPassRequestModel());
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
        email_reset_pass = findViewById(R.id.edit_email_resetPass);
    }

    @Override
    public void onClick(View v) {

    }
}
