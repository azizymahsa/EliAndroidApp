package com.eligasht.reservation.views.activities.login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.WebUserLogin;
import com.eligasht.reservation.models.model.login.call.LoginListReq;
import com.eligasht.reservation.models.model.login.call.LoginRequestModel;
import com.eligasht.reservation.models.model.login.response.LoginRes;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class LogInActivity extends BaseActivity implements View.OnClickListener {


    RelativeLayout llHome;
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
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(LogInActivity.this
                    , R.color.colorPrimaryDark));
        }
        initViews();
        service = ServiceGenerator.createService(ClientService.class);


    }

    //request for login
    private void Login() {

        LoginListReq loginListReq = new LoginListReq();
        loginListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        loginListReq.setCulture(getString(R.string.culture));
        loginListReq.setUserName(txtEmail.getText().toString());
        loginListReq.setPassword(txtPassword.getText().toString());

        needShowProgressDialog();
        // Log.e(" request " ,new GsonBuilder().create().toJson(new LoginRequestModel(loginListReq)));
        Call<LoginRes> call = service.Login(new LoginRequestModel(loginListReq));
        call.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getLoginResult() == null) {
                    Toast.makeText(LogInActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getLoginResult().getError() != null) {
                    Toast.makeText(LogInActivity.this, response.body().getLoginResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                WebUserLogin webUserLogin = response.body().getLoginResult().getWebUserLogin();
                if (webUserLogin == null) {
                    Toast.makeText(LogInActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                    return;
                }


                if (webUserLogin.getLoginStatus().equals("NO")) {
                    Toast.makeText(LogInActivity.this, R.string.mail_or_pass_is_wrong, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (webUserLogin.getLoginStatus().equals("ACT")) {
                    Toast.makeText(LogInActivity.this, R.string.activation_link_has_been_sent_to_your_email, Toast.LENGTH_SHORT).show();
                    return;
                }


                WebUserTools.getInstance().setUser(webUserLogin);
                MainActivity.setUserName(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
                //  Log.e("contract" , response.body().getLoginResult().getWebUserLogin().getPreviousContracts().get(0).getCntID() +"");
                Intent intent = new Intent(LogInActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();


            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(LogInActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initViews() {
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btnLogIn);
        txtEmail = findViewById(R.id.txt_email);
        llHome = findViewById(R.id.llHome);
        llHome.setVisibility(View.GONE);
        // eLogo = findViewById(R.id.e_logo);
        layoutResetPassword = findViewById(R.id.layout_reset_password);
        txtPassword = findViewById(R.id.txt_password);

        btnLogin.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        //  eLogo.setVisibility(View.INVISIBLE);


        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        layoutResetPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Intent intent = new Intent(this, RegisterLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.layout_reset_password:
                Intent _intent = new Intent(this, ResetPasswordActivity.class);
                startActivity(_intent);
                break;

            case R.id.btnLogIn:
                if (txtEmail.length() == 0) {
                    txtEmail.setError(getString(R.string.please_enter_your_email_address));
                    return;
                }

                if (!ValidationTools.isEmailValid(txtEmail.getText().toString())) {
                    txtEmail.setError(getString(R.string.email_address_is_not_valid));
                    return;
                }
                if (txtPassword.length() == 0) {
                    txtPassword.setError(getString(R.string.enter_your_password));
                    return;
                }
                Login();

                break;
        }

    }
}
