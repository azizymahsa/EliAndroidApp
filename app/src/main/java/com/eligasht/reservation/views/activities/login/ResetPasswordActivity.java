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

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.call.RequestChangePass;
import com.google.gson.Gson;
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
 * Created by reza nejati on 1/17/2018.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {


    private ClientService service;
    private EditText email_reset_pass;
    private Button btnResetPassword;

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.reset_pass));
        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(ResetPasswordActivity.this
                    , R.color.colorPrimaryDark));
        }

        initViews();
        service = ServiceGenerator.createService(ClientService.class);


    }

    //request for remember password
    private void RememberPass() {
        ResetPassRequestModel resetPassRequestModel = new ResetPassRequestModel();
        RequestChangePass requestChangePass= new RequestChangePass();




        requestChangePass.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        requestChangePass.setCulture(getString(R.string.culture));
        requestChangePass.setUserName(email_reset_pass.getText().toString());
        requestChangePass.setContractNo("0");
        resetPassRequestModel.setRequest(requestChangePass);

        needShowProgressDialog();
        Log.e(" request ", new GsonBuilder().create().toJson(resetPassRequestModel));
        Call<WebUserRememberPasswordRes> call = service.ResetPassword(resetPassRequestModel);
        call.enqueue(new Callback<WebUserRememberPasswordRes>() {
            @Override
            public void onResponse(Call<WebUserRememberPasswordRes> call, Response<WebUserRememberPasswordRes> response) {
                Log.e("logintest",response.body().getWebUserRememberPasswordResult().getWarningss().get(0).getShortText() );
                Log.e("logintest",response.body().getWebUserRememberPasswordResult().getError().get(0).getMessage() );
                needHideProgressDialog();
              if (response.body().getWebUserRememberPasswordResult().getWebUserLogin().getLoginStatus().toLowerCase().equals("ok")){

                  Intent intent = new Intent(ResetPasswordActivity.this, SuccessResetPassActivity.class);
                  intent.putExtra("value",response.body().getWebUserRememberPasswordResult().getWarningss().get(0).getShortText());
                  startActivity(intent);
                  finish();

              }else {

                  Toast.makeText(ResetPasswordActivity.this, response.body().getWebUserRememberPasswordResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
              }


                //do somethings !!

            }

            @Override
            public void onFailure(Call<WebUserRememberPasswordRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ResetPasswordActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(this, R.string.check_mail, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidationTools.isEmailValid(email_reset_pass.getText().toString())) {
                    Toast.makeText(this, R.string.wrong_mail, Toast.LENGTH_SHORT).show();
                    return;
                }

                RememberPass();
                break;
        }
    }
}
