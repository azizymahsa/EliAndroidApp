package com.eligasht.reservation.views.activities.login;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.WebUserLogin;
import com.eligasht.reservation.models.model.login.call.RegisterListReq;
import com.eligasht.reservation.models.model.login.call.RegisterRequestModel;
import com.eligasht.reservation.models.model.login.response.WebUserRegisterRes;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.google.gson.GsonBuilder;

import mehdi.sakout.fancybuttons.FancyButton;
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
    private FancyButton btnRegister;
    private ClientService service;


    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        InitUi.Toolbar(this, false, android.R.color.transparent, "");
        Window window = getWindow();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }


        initViews();
        service = ServiceGenerator.createService(ClientService.class);
        findViewById(R.id.txt_hom).setVisibility(View.INVISIBLE);

    }

    //request for register and get result
    private void Register() {

        RegisterListReq registerListReq = new RegisterListReq();
        registerListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        registerListReq.setCulture(getString(R.string.culture));
        registerListReq.setUsername(txtEmail.getText().toString());
        registerListReq.setPassword(txtPass.getText().toString());

        needShowProgressDialog();
        Log.e(" request ", new GsonBuilder().create().toJson(new RegisterRequestModel(registerListReq)));
        Call<WebUserRegisterRes> call = service.Register(new RegisterRequestModel(registerListReq));
        call.enqueue(new Callback<WebUserRegisterRes>() {
            @Override
            public void onResponse(Call<WebUserRegisterRes> call, Response<WebUserRegisterRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getWebUserRegisterResult() == null) {
                    Toast.makeText(RegisterLoginActivity.this, R.string.text20, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getWebUserRegisterResult().getError() != null) {
                    Toast.makeText(RegisterLoginActivity.this, response.body().getWebUserRegisterResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                WebUserLogin webUserLogin = response.body().getWebUserRegisterResult().getWebUserLogin();

                if (webUserLogin == null) {
                    Toast.makeText(RegisterLoginActivity.this, R.string.text21, Toast.LENGTH_SHORT).show();
                    return;
                }


                if (webUserLogin.getLoginStatus().toUpperCase().equals("DUP")) {
                    Toast.makeText(RegisterLoginActivity.this, R.string.text22, Toast.LENGTH_SHORT).show();
                    return;
                }
/*

                WebUserTools.getInstance().setUser(webUserLogin);
                MainActivity.setUserName(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
*/

             /*   Intent intent = new Intent(RegisterLoginActivity.this,SuccessResetPassActivity.class);
                startActivity(intent);
                finish();*/
                Toast.makeText(RegisterLoginActivity.this, R.string.text24, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<WebUserRegisterRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(RegisterLoginActivity.this, R.string.text25, Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initViews() {
        txtEmail = findViewById(R.id.edit_email);
        txtPass = findViewById(R.id.edit_pass);
        txtConfirmPass = findViewById(R.id.edit_confirm_pass);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));

        btnRegister.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                if (txtEmail.length() == 0) {
                    Toast.makeText(this, R.string.text26, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidationTools.isEmailValid(txtEmail.getText().toString())) {
                    Toast.makeText(this, R.string.text27, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (txtPass.length() == 0) {
                    Toast.makeText(this, R.string.text28, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (txtConfirmPass.length() == 0) {
                    Toast.makeText(this, R.string.text29, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!txtPass.getText().toString().equals(txtConfirmPass.getText().toString())) {
                    Toast.makeText(this, R.string.text30, Toast.LENGTH_SHORT).show();
                    return;
                }

                Register();

                break;
        }
    }
}
