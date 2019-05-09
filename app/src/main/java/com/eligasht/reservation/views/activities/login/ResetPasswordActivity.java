package com.eligasht.reservation.views.activities.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.Contract;
import com.eligasht.reservation.models.model.login.WebUser;
import com.eligasht.reservation.models.model.login.WebUserLogin;
import com.eligasht.reservation.models.model.login.call.RequestChangePass;
import com.eligasht.reservation.tools.JustifiedTextView;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.loginMVP.view.LoginActivity;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.newModel.login.forgetPassword.request.RequestForgetPassword;
import com.eligasht.service.model.newModel.login.forgetPasswordActivation.request.RequestForgetPasswordActivation;
import com.eligasht.service.model.newModel.login.forgetPasswordChangePassword.request.RequestForgetPasswordChangePassword;
import com.eligasht.service.model.newModel.login.registerUser.response.ResponseWebUserLogin;
import com.eligasht.service.model.weather.response.Atmosphere;
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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by reza nejati on 1/17/2018.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {


    private ClientService service;
    private EditText email_reset_pass;
    private Button btnResetPassword,btnChangPass;
    CountDownTimer countDownTimer;
    private TextView countDownTextView;
    LinearLayout lnractivation,lnrFrgtPass,lnrchangePass;
    Button btnActivation;
    int seconds = 60;
    int minutes = 1;
    private TextView txtMobileNum;
    private TextView txtTime,txtEditMb;
    private FancyButton btnReturnAct;
    private RelativeLayout rlBtnAct;
    private EditText edtActCode,changePass_username_new_confirm,changePass_username_new;
    private String strloginResult="";

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

    @Override
    public boolean needTerminate() {
        return false;
    }

    //request for remember password
    private void RememberPass() {
        RequestForgetPassword resetPassRequestModel = new RequestForgetPassword();


        resetPassRequestModel.setCulture(getString(R.string.culture));
        resetPassRequestModel.setUserMobile(email_reset_pass.getText().toString());


        needShowProgressDialog();
        Log.e(" RequestForgetPassword ", new GsonBuilder().create().toJson(resetPassRequestModel));
        SingletonService.getInstance().getLoginProfile().GetForgetPassword(new OnServiceStatus<ResponseWebUserLogin>() {
            @Override
            public void onReady(ResponseWebUserLogin response) {
                Log.e("ResponseForgetPassword:",new Gson().toJson(response ));
                needHideProgressDialog();
                if (response.getLoginCode()>0){
                    //کد مثبت به معنی موفق.

                    activationUI();



                }else {

                    Toast.makeText(ResetPasswordActivity.this, response.getErrors().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onError(String message) {
                needHideProgressDialog();
                Toast.makeText(ResetPasswordActivity.this,
                        getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
        }, resetPassRequestModel);

    }

    private void activationUI() {
        //lnractivation,lnrFrgtPass,btnActivation,btnResetPassword
        lnrFrgtPass.setVisibility(View.GONE);
        btnResetPassword.setVisibility(View.GONE);
        lnrchangePass.setVisibility(View.GONE);
        btnActivation.setVisibility(View.GONE);
        txtMobileNum.setText(email_reset_pass.getText().toString());
        lnractivation.setVisibility(View.VISIBLE);
        btnActivation.setVisibility(View.VISIBLE);

        ///////timer
        rlBtnAct.setVisibility(View.GONE);
        txtTime.setVisibility(View.VISIBLE);
        minutes=1;
        seconds=60;
        //Declare the timer
        Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                      //  TextView tv = (TextView) findViewById(R.id.txtTime);
                        txtTime.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                        seconds -= 1;

                        if(seconds == 0)
                        {
                            if(seconds==0 & minutes==0){
                                rlBtnAct.setVisibility(View.VISIBLE);
                                txtTime.setVisibility(View.GONE);
                            }else{
                                txtTime.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));

                                seconds=60;
                                minutes=minutes-1;
                            }

                        }



                    }

                });
            }

        }, 0, 1000);
    }

    private void initViews() {
        email_reset_pass = findViewById(R.id.edit_email_resetPass);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        btnChangPass = findViewById(R.id.btnChangPass);
        btnActivation = findViewById(R.id.btnActivation);
       // countDownTextView = findViewById(R.id.countDownTextView);
        lnrchangePass = findViewById(R.id.lnrchangePass);
        lnractivation = findViewById(R.id.lnractivation);
        lnrFrgtPass = findViewById(R.id.lnrFrgtPass);
        txtMobileNum = findViewById(R.id.txtMobileNum);
        rlBtnAct=findViewById(R.id.rlBtnAct);
        rlBtnAct.setVisibility(View.GONE);
        btnReturnAct = findViewById(R.id.btnReturnAct);
        btnReturnAct.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
         txtTime = findViewById(R.id.txtTime);
        txtEditMb = findViewById(R.id.txtEditMb);
        edtActCode = findViewById(R.id.edtActCode);

        changePass_username_new_confirm = findViewById(R.id.changePass_username_new_confirm);
        changePass_username_new = findViewById(R.id.changePass_username_new);

        btnResetPassword.setOnClickListener(this);
        btnChangPass.setOnClickListener(this);
        btnActivation.setOnClickListener(this);
        txtEditMb.setOnClickListener(this);
        btnReturnAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnResetPassword:
                if (email_reset_pass.length() == 0) {
                    Toast.makeText(this, R.string._Please_enter_your_mobile, Toast.LENGTH_SHORT).show();
                    return;
                }

                //if (!ValidationTools.isEmailValid(email_reset_pass.getText().toString())) {
                if (email_reset_pass.getText().toString().trim().length() != 11) {
                    Toast.makeText(this, R.string.text27, Toast.LENGTH_SHORT).show();
                    return;
                }

                RememberPass();
                break;
                case R.id.btnReturnAct://ارسال مجدد کد فعال سازی
                    rlBtnAct.setVisibility(View.GONE);
                    txtTime.setVisibility(View.VISIBLE);
                    //bayad dobare ttimer faal va request ghabli run mishe
                    RememberPass();
                break;
                case R.id.btnActivation:
                    if(edtActCode.getText().toString().trim().length()>0){
                        sendRequestForgetPasswordActivation();
                    }else{
                        Toast.makeText(this, R.string._enter_activation_code , Toast.LENGTH_SHORT).show();
                    }
                break;
            case R.id.txtEditMb:
                //ویرایش شماره موبایل
                lnrFrgtPass.setVisibility(View.VISIBLE);
                btnResetPassword.setVisibility(View.VISIBLE);

                lnractivation.setVisibility(View.GONE);
                btnActivation.setVisibility(View.GONE);
                break;
                case R.id.btnChangPass:
                    //  changePass_username_new_confirm
                    //changePass_username_new
                    if(changePass_username_new_confirm.getText().toString().trim().equals(changePass_username_new.getText().toString().trim())){
                        sendRequestChangePass(strloginResult);
                    }else{
                        Toast.makeText(this, R.string._password_incorrect , Toast.LENGTH_SHORT).show();
                    }
                break;
        }
    }

    private void sendRequestForgetPasswordActivation() {
        RequestForgetPasswordActivation resetPassRequestModel = new RequestForgetPasswordActivation();


        resetPassRequestModel.setCulture(getString(R.string.culture));
        resetPassRequestModel.setUserMobile(email_reset_pass.getText().toString());
        resetPassRequestModel.setActivationCode(edtActCode.getText().toString().trim());


        needShowProgressDialog();
        Log.e(" RequestForgetPasswordActivation: ", new GsonBuilder().create().toJson(resetPassRequestModel));
        SingletonService.getInstance().getLoginProfile().GetForgetPasswordActivation(new OnServiceStatus<ResponseWebUserLogin>() {
            @Override
            public void onReady(ResponseWebUserLogin response) {
                strloginResult="";
                Log.e("ResponseForgetPasswordActivation:",new Gson().toJson(response ));
                needHideProgressDialog();
                if (response.getLoginCode()>0){
                    //کد مثبت به معنی موفق.
                //ui jadid baraye gereftane pass
                   /* activationUI();
                   */
                  changePassUI(response.getLoginResult());

                }else if (response.getLoginCode() ==-102)
                {
                //کاربر قبلا عضو شده
                   changePassUI(response.getLoginResult());///??????
                }
                else
                {
                    //خطایی رخ داده
                    Toast.makeText(ResetPasswordActivity.this, response.getLoginStatus(), Toast.LENGTH_SHORT).show();
                }







            }

            @Override
            public void onError(String message) {
                needHideProgressDialog();
                Toast.makeText(ResetPasswordActivity.this,
                        getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
        }, resetPassRequestModel);
    }

    private void changePassUI(String loginResult) {
        lnractivation.setVisibility(View.GONE);
        btnActivation.setVisibility(View.GONE);

        lnrchangePass.setVisibility(View.VISIBLE);
        btnChangPass.setVisibility(View.VISIBLE);
        strloginResult = loginResult;

    }

    private void sendRequestChangePass(String loginResult) {
      //  changePass_username_new_confirm
        //changePass_username_new
        RequestForgetPasswordChangePassword requestForgetPasswordChangePassword = new RequestForgetPasswordChangePassword();

//UserMobile, ActivationCode, EUserId , UserPassword,Culture
        requestForgetPasswordChangePassword.setCulture(getString(R.string.culture));
        requestForgetPasswordChangePassword.setUserMobile(email_reset_pass.getText().toString()+"");
        requestForgetPasswordChangePassword.setActivationCode(edtActCode.getText().toString().trim()+"");
        requestForgetPasswordChangePassword.setUserPassword(changePass_username_new.getText().toString().trim()+"");
        requestForgetPasswordChangePassword.setEUserId(loginResult+"");


        needShowProgressDialog();
        Log.e(" RequestForgetPasswordChangePassword: ", new GsonBuilder().create().toJson(requestForgetPasswordChangePassword));
        SingletonService.getInstance().getLoginProfile().GetForgetPasswordChangePassword(new OnServiceStatus<ResponseWebUserLogin>() {
            @Override
            public void onReady(ResponseWebUserLogin response) {
                Log.e("ResponseForgetPasswordChangePassword:",new Gson().toJson(response ));
                needHideProgressDialog();

                if (response.getLoginCode() > 0)
                {
                //کد مثبت به معنی ثبت نام موفق .
                    Toast.makeText(ResetPasswordActivity.this,response.getLoginStatus() , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ResetPasswordActivity.this, LogInActivity.class);
                    //intent.putExtra("value",response.getWarningss().get(0).getShortText());
                    startActivity(intent);
                   // finish();

                }else if (response.getLoginCode() ==-102)
                {
                //کاربر قبلا عضو شده
                    Toast.makeText(ResetPasswordActivity.this,response.getLoginStatus() , Toast.LENGTH_SHORT).show();
                }
                else
                {
                 //خطایی رخ داده
                    Toast.makeText(ResetPasswordActivity.this,response.getLoginStatus() , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String message) {
                needHideProgressDialog();
                Toast.makeText(ResetPasswordActivity.this,
                        getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
        }, requestForgetPasswordChangePassword);
    }


}
