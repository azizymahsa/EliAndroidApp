package com.eligasht.reservation.views.activities.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.login.call.ResetPassRequestModel;
import com.eligasht.reservation.models.model.login.response.WebUserRememberPasswordRes;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.ui.InitUi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {


    private ClientService service;
    private EditText email_reset_pass;
    private Button btnResetPassword;

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "بازیابی رمز عبور");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(ResetPasswordActivity.this
                    , R.color.colorPrimaryDark));
        }

        initViews();
        service = ServiceGenerator.createService(ClientService.class);

        findViewById(R.id.txt_hom).setVisibility(View.INVISIBLE);

    }

    //request for remember password
    private void RememberPass() {
        ResetPassRequestModel resetPassRequestModel = new ResetPassRequestModel();
        resetPassRequestModel.setRequest(email_reset_pass.getText().toString());

        needShowProgressDialog();
        Log.e(" request ", new GsonBuilder().create().toJson(resetPassRequestModel));
        Call<WebUserRememberPasswordRes> call = service.ResetPassword(resetPassRequestModel);
        call.enqueue(new Callback<WebUserRememberPasswordRes>() {
            @Override
            public void onResponse(Call<WebUserRememberPasswordRes> call, Response<WebUserRememberPasswordRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getWebUserRememberPasswordResult() == null) {
                    Toast.makeText(ResetPasswordActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getWebUserRememberPasswordResult().getWebUserLogin() == null && response.body().getWebUserRememberPasswordResult().getError() != null) {
                    Toast.makeText(ResetPasswordActivity.this, response.body().getWebUserRememberPasswordResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(ResetPasswordActivity.this, SuccessResetPassActivity.class);
                startActivity(intent);

                //do somethings !!

            }

            @Override
            public void onFailure(Call<WebUserRememberPasswordRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ResetPasswordActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initViews() {
        email_reset_pass = findViewById(R.id.edit_email_resetPass);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnResetPassword:
                if (email_reset_pass.length() == 0) {
                    Toast.makeText(this, "لطفا ایمیل خود را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidationTools.isEmailValid(email_reset_pass.getText().toString())) {
                    Toast.makeText(this, "ایمیل وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show();
                    return;
                }

                RememberPass();
                break;
        }
    }
}
