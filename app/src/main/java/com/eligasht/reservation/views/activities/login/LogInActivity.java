package com.eligasht.reservation.views.activities.login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
import com.eligasht.reservation.models.model.login.Contract;
import com.eligasht.reservation.models.model.login.WebUser;
import com.eligasht.reservation.models.model.login.WebUserLogin;
import com.eligasht.reservation.models.model.login.call.LoginListReq;
import com.eligasht.reservation.models.model.login.call.LoginRequestModel;
import com.eligasht.reservation.models.model.login.response.LoginRes;
import com.eligasht.reservation.models.model.pack.response.ResponseSearchPackage;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.model.newModel.login.loginUser.request.RequestLoginUser;
import com.eligasht.service.model.newModel.login.loginUser.response.ResponseLoginUser;
import com.eligasht.service.model.newModel.login.loginUser.response.WebUserProperties;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

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
      //  StatusBarUtil.setTranslucent(this, 2);


    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    //request for login
    private void Login() {

        RequestLoginUser loginListReq = new RequestLoginUser();
        //loginListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        loginListReq.setCulture(getString(R.string.culture));
        loginListReq.setUserMobile(txtEmail.getText().toString());
        loginListReq.setUserPassword(txtPassword.getText().toString());

        needShowProgressDialog();
        Log.d("RequestLoginUser: ",new Gson().toJson(loginListReq));
        Call<ResponseLoginUser> call = service.Login(loginListReq);
        call.enqueue(new Callback<ResponseLoginUser>() {
            @Override
            public void onResponse(Call<ResponseLoginUser> call, Response<ResponseLoginUser> response) {
                Log.d("ResponseLoginUser: ",new Gson().toJson(response));
                try {
                    needHideProgressDialog();
                    if (response == null
                            || response.body() == null
                            ) {
                        Toast.makeText(LogInActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (response.body().getErrors().size()>0) {
                        Toast.makeText(LogInActivity.this, response.body().getErrors().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    WebUserLogin webUserLogin =new WebUserLogin();// response.body().getWebUserProperties().get);//WebUserLogin();
                    webUserLogin.setLoginStatus(response.body().getLoginStatus());
                    WebUser webUser=new WebUser();
                    webUser.setActivationCode(response.body().getWebUserProperties().getActivationCode());
                    webUser.setCulture(response.body().getWebUserProperties().getCulture());
                    webUser.setActivationCode(response.body().getWebUserProperties().getActivationCode());
                    webUser.setBankCardNo(response.body().getWebUserProperties().getBankCardNo());
                    webUser.setBull(response.body().getWebUserProperties().getBull());
                    webUser.setDemosticCityFa(response.body().getWebUserProperties().getDemosticCityFa());
                    webUser.setDemosticCityID(response.body().getWebUserProperties().getDemosticCityID());
                    webUser.setDemosticPrivinceFa (response.body().getWebUserProperties().getDemosticPrivinceFa());
                    webUser.setDemosticPrivinceID(response.body().getWebUserProperties().getDemosticPrivinceID());
                    webUser.setEncryptWebUserID(response.body().getWebUserProperties().getEncryptWebUserID());
                    webUser.setImgURL(response.body().getWebUserProperties().getImgURL());
                    webUser.setMsgCount(response.body().getWebUserProperties().getMsgCount());
                    webUser.setPassword(response.body().getWebUserProperties().getPassword());
                    webUser.setUsername(response.body().getWebUserProperties().getUsername());
                    webUser.setWebUserAddress(response.body().getWebUserProperties().getWebUserAddress());
                    webUser.setWebUserBirthDayMiladi(response.body().getWebUserProperties().getWebUserBirthDayMiladi());
                    webUser.setWebUserBirthDayShamsi(response.body().getWebUserProperties().getWebUserBirthDayShamsi());
                    webUser.setWebUserFatherName(response.body().getWebUserProperties().getWebUserFatherName());
                    webUser.setWebUserFnameE(response.body().getWebUserProperties().getWebUserFnameE());
                    webUser.setWebUserFnameF(response.body().getWebUserProperties().getWebUserFnameF());
                    webUser.setWebUserGender(response.body().getWebUserProperties().getWebUserGender());
                    webUser.setWebUserID(response.body().getWebUserProperties().getWebUserID());
                    webUser.setWebUserLnameE(response.body().getWebUserProperties().getWebUserLnameE());
                    webUser.setWebUserLnameF(response.body().getWebUserProperties().getWebUserLnameF());
                    webUser.setWebUserMail(response.body().getWebUserProperties().getWebUserMail());
                    webUser.setWebUserTel(response.body().getWebUserProperties().getWebUserTel());
                    webUser.setWebUserMobile(response.body().getWebUserProperties().getWebUserMobile());
                    webUser.setWebUserNationalCode(response.body().getWebUserProperties().getWebUserNationalCode());


                    webUserLogin.setWebUserProperties(webUser);

                    ArrayList<Contract> contracts=new ArrayList<>();
                    //قراردادها
                    for (int i = 0; i < response.body().getPreviousContracts().size(); i++) {
                        Contract contract=new Contract();
                        contract.setRqBaseID(response.body().getPreviousContracts().get(i).getRqBaseID());
                        contract.setDateFa(response.body().getPreviousContracts().get(i).getDateFa());
                        contract.setDate(response.body().getPreviousContracts().get(i).getDate());
                        contract.setPathNames(response.body().getPreviousContracts().get(i).getPathNames());
                        contract.setDeparture(response.body().getPreviousContracts().get(i).getDeparture());
                        contract.setCheckin(response.body().getPreviousContracts().get(i).getCheckin());
                        contract.setRemained(response.body().getPreviousContracts().get(i).getRemained());
                        contract.setFollowerName(response.body().getPreviousContracts().get(i).getFollowerName());
                        contract.setVisaConfirm(response.body().getPreviousContracts().get(i).getVisaConfirm());
                        contract.setTicketConfirm(response.body().getPreviousContracts().get(i).getTicketConfirm());
                        contract.setHotelConfirm(response.body().getPreviousContracts().get(i).getHotelConfirm());
                        contract.setCntDocDeliver(response.body().getPreviousContracts().get(i).getCntDocDeliver());
                        contract.setFinalPrice(response.body().getPreviousContracts().get(i).getFinalPrice());
                        contracts.add(contract);
                    }
                    webUserLogin.setPreviousContracts(contracts);
                   //////////////////////
                    if (webUserLogin == null) {
                        Toast.makeText(LogInActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                        return;
                    }

/*
                    if (response.body().getLoginStatus().equals("NotRegistered")) {//ino khodam ezafe kardam
                        Toast.makeText(LogInActivity.this,R.string.not_register_user, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.body().getLoginStatus().equals("NO")) {
                        Toast.makeText(LogInActivity.this, R.string.mail_or_pass_is_wrong, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (response.body().getLoginStatus().equals("ACT")) {
                        Toast.makeText(LogInActivity.this, R.string.activation_link_has_been_sent_to_your_email, Toast.LENGTH_SHORT).show();
                        return;
                    }*/

                    if (response.body().getLoginCode()> 0)
                    {
                    //کد مثبت به معنی موفق.
                        Toast.makeText(LogInActivity.this, response.body().getLoginStatus(), Toast.LENGTH_SHORT).show();
                        WebUserTools.getInstance().setUser(webUserLogin);//movaghatan pak kardam
                        String username=WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF();
                        String lasrName=WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF();

                        MainActivity.setUserName(username + " " + lasrName);

                        Intent intent = new Intent(LogInActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        //خطایی رخ داده
                        Toast.makeText(LogInActivity.this, response.body().getLoginStatus(), Toast.LENGTH_SHORT).show();
                    }


                   }catch (Exception e){
                    e.getMessage();
                }



            }

            @Override
            public void onFailure(Call<ResponseLoginUser> call, Throwable t) {
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

               // if (!ValidationTools.isEmailValid(txtEmail.getText().toString())) {
                if (txtEmail.getText().toString().trim().length() != 11) {
                  //  Toast.makeText(this, R.string.text27, Toast.LENGTH_SHORT).show();
                    txtEmail.setError(getString(R.string.text27));
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
