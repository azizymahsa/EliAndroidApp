package com.eligasht.reservation.views.activities.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.Contract;
import com.eligasht.reservation.models.model.login.WebUser;
import com.eligasht.reservation.models.model.login.WebUserLogin;
import com.eligasht.reservation.models.model.login.call.RegisterListReq;
import com.eligasht.reservation.models.model.login.call.RegisterRequestModel;
import com.eligasht.reservation.models.model.login.response.WebUserRegisterRes;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.newModel.airport.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.airport.response.ResponseAirport;
import com.eligasht.service.model.newModel.login.reSendActivation.request.RequestReSendActivation;
import com.eligasht.service.model.newModel.login.registerActivation.request.RequestRegisterActivation;
import com.eligasht.service.model.newModel.login.registerUser.request.RequestRegisterUser;
import com.eligasht.service.model.newModel.login.registerUser.response.ResponseWebUserLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elham.bonyani on 1/17/2018.
 */

public class RegisterLoginActivity extends BaseActivity implements View.OnClickListener, OnServiceStatus<ResponseWebUserLogin> {

    private EditText txtEmail;
    private EditText txtPass;
    private EditText edtActCode;
    private CheckBox txtCheck;
    private FancyButton btnRegister,btnReturnAct,btnTakmil;
    private ClientService service;
    private LinearLayout lnrReg,lnractivation;
    private RelativeLayout rlBtnAct,rlTakmil,rlRegister;

    private TextView txtTime,txtEditMb,txtMobileNum;
    int seconds = 60;
    int minutes = 1;
    private long timeWhenStopped = 0;
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


    }//end oncreate
    private void initViews() {

        lnrReg = findViewById(R.id.lnrReg);
        lnractivation = findViewById(R.id.lnractivation);
        txtTime = findViewById(R.id.txtTime);
        txtEditMb = findViewById(R.id.txtEditMb);
        txtMobileNum = findViewById(R.id.txtMobileNum);
        rlBtnAct=findViewById(R.id.rlBtnAct);
        rlBtnAct.setVisibility(View.GONE);

        btnTakmil=findViewById(R.id.btnTakmil);
        rlTakmil=findViewById(R.id.rlTakmil);
        rlTakmil.setVisibility(View.GONE);
        rlRegister=findViewById(R.id.rlRegister);
        rlRegister.setVisibility(View.VISIBLE);

        edtActCode = findViewById(R.id.edtActCode);
        txtEmail = findViewById(R.id.edit_email);
        txtPass = findViewById(R.id.edit_pass);
        txtCheck = findViewById(R.id.txtCheck);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnReturnAct = findViewById(R.id.btnReturnAct);
        btnReturnAct.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));

        btnRegister.setOnClickListener(this);
        btnReturnAct.setOnClickListener(this);
        btnTakmil.setOnClickListener(this);
        txtEditMb.setOnClickListener(this);

    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    //request for register and get result
    private void Register() {


        RequestRegisterUser requestRegisterUser = new RequestRegisterUser();

        requestRegisterUser.setCulture(getString(R.string.culture));
        requestRegisterUser.setUserMobile(txtEmail.getText().toString());
        requestRegisterUser.setUserPassword(txtPass.getText().toString());
        Log.e("RequestRegisterUser:",new Gson().toJson(requestRegisterUser ));
        SingletonService.getInstance().getLoginProfile().GetRegisterUser(this, requestRegisterUser);


        needShowProgressDialog();



    }

    //response
    @Override
    public void onReady(ResponseWebUserLogin response) {
        Log.e("ResponseWebUserLogin:",new Gson().toJson(response ));
        needHideProgressDialog();
        if (response == null
                || response == null
                ) {
            Toast.makeText(RegisterLoginActivity.this, R.string.text20, Toast.LENGTH_SHORT).show();
            return;
        }

        if (response.getErrors().size()>0) {
            Toast.makeText(RegisterLoginActivity.this, response.getErrors().get(0).getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }



        if (response.getLoginCode() > 0|| response.getLoginCode() == -103)
        {
            //کد مثبت به معنی ثبت نام موفق و کد -103 به معنی ثبت تکراری ولی عدم ثبت کد.

          Toast.makeText(RegisterLoginActivity.this, response.getLoginStatus(), Toast.LENGTH_SHORT).show();
          activationUI();


        }else if (response.getLoginCode() ==-102)
        {
        //کاربر قبلا عضو شده
            Toast.makeText(RegisterLoginActivity.this,response.getLoginStatus() , Toast.LENGTH_SHORT).show();
        }
                else
        {
            //خطایی رخ داده
            Toast.makeText(RegisterLoginActivity.this,response.getLoginStatus(), Toast.LENGTH_SHORT).show();
        }

/*

                WebUserTools.getInstance().setUser(webUserLogin);
                MainActivity.setUserName(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
*/

             /*   Intent intent = new Intent(RegisterLoginActivity.this,SuccessResetPassActivity.class);
                startActivity(intent);
                finish();*/

    }//end Response

    private void activationUI() {
        lnrReg.setVisibility(View.GONE);
        rlRegister.setVisibility(View.GONE);
        txtMobileNum.setText(txtEmail.getText().toString());
        lnractivation.setVisibility(View.VISIBLE);
        rlTakmil.setVisibility(View.VISIBLE);

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
                        TextView tv = (TextView) findViewById(R.id.txtTime);
                        tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                        seconds -= 1;

                        if(seconds == 0)
                        {
                            if(seconds==0 & minutes==0){
                                rlBtnAct.setVisibility(View.VISIBLE);
                                txtTime.setVisibility(View.GONE);
                            }else{
                                tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));

                                seconds=60;
                                minutes=minutes-1;
                            }

                        }



                    }

                });
            }

        }, 0, 1000);
    }

    @Override
    public void onError(String message) {
        needHideProgressDialog();
        Toast.makeText(RegisterLoginActivity.this, R.string.text25, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTakmil:
               SendRequestRegisterActivation(edtActCode.getText().toString());
             break;
             case R.id.txtEditMb:
                lnractivation.setVisibility(View.GONE);
                rlTakmil.setVisibility(View.GONE);

                lnrReg.setVisibility(View.VISIBLE);
                 rlRegister.setVisibility(View.VISIBLE);
             break;
             case R.id.btnReturnAct:
                 rlBtnAct.setVisibility(View.GONE);
                 txtTime.setVisibility(View.VISIBLE);
                //bayad dobare ttimer faal va request ghabli run mishe
                 RequestReSendActivation();
             break;
                case R.id.btnRegister:
                if (txtEmail.length() == 0) {
                    Toast.makeText(this, R.string._Please_enter_your_mobile, Toast.LENGTH_SHORT).show();
                    return;
                }

               // if (!ValidationTools.isMobileValid(txtEmail.getText().toString())) {
                if (txtEmail.getText().toString().trim().length() != 11) {
                    Toast.makeText(this, R.string.text27, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (txtPass.length() == 0) {
                    Toast.makeText(this, R.string.text28, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!txtCheck.isChecked()) {
                    Toast.makeText(this, R.string._please_read , Toast.LENGTH_SHORT).show();
                    return;
                }

               /* if (!txtPass.getText().toString().equals(txtConfirmPass.getText().toString())) {
                    Toast.makeText(this, R.string.text30, Toast.LENGTH_SHORT).show();
                    return;
                }*/

                Register();

                break;
        }
    }

    private void RequestReSendActivation() {
        if(edtActCode.length()>0){
            RequestReSendActivation requestRegisterActivation=new RequestReSendActivation();
            requestRegisterActivation.setCulture(getString(R.string.culture));
            requestRegisterActivation.setUserMobile(txtEmail.getText().toString());

            Log.e("RequestReSendActivation:",new Gson().toJson(requestRegisterActivation ));
            needShowProgressDialog();
            SingletonService.getInstance().getLoginProfile().GetReSendActivation(new OnServiceStatus<ResponseWebUserLogin>() {
                @Override
                public void onReady(ResponseWebUserLogin response) {
                    Log.e("ResponseReSendActivation:",new Gson().toJson(response ));

                    needHideProgressDialog();
                    if (response == null
                            || response == null
                            ) {
                        Toast.makeText(RegisterLoginActivity.this, R.string.text20, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (response.getErrors().size()>0) {
                        Toast.makeText(RegisterLoginActivity.this, response.getErrors().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }



                    if (response.getLoginCode() > 0|| response.getLoginCode() == -103)
                    {
                        //کد مثبت به معنی ثبت نام موفق و کد -103 به معنی ثبت تکراری ولی عدم ثبت کد.

                        Toast.makeText(RegisterLoginActivity.this, response.getLoginStatus(), Toast.LENGTH_SHORT).show();
                        activationUI();


                    }else if (response.getLoginCode() ==-102)
                    {
                        //کاربر قبلا عضو شده
                        Toast.makeText(RegisterLoginActivity.this,response.getLoginStatus() , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //خطایی رخ داده
                        Toast.makeText(RegisterLoginActivity.this,response.getLoginStatus(), Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onError(String message) {
                    needHideProgressDialog();
                    Toast.makeText(RegisterLoginActivity.this, R.string.text25, Toast.LENGTH_SHORT).show();
                }
            }, requestRegisterActivation );



        }else{
            Toast.makeText(this, R.string._enter_activation_code , Toast.LENGTH_SHORT).show();
        }

    }

    private void SendRequestRegisterActivation(String edtActCode) {
        if(edtActCode.length()>0){
            RequestRegisterActivation requestRegisterActivation=new RequestRegisterActivation();
            requestRegisterActivation.setCulture(getString(R.string.culture));
            requestRegisterActivation.setUserMobile(txtEmail.getText().toString());
            requestRegisterActivation.setActivationCode(edtActCode.trim());
            Log.e("RequestRegisterActivation:",new Gson().toJson(requestRegisterActivation ));
            needShowProgressDialog();
            SingletonService.getInstance().getLoginProfile().GetRegisterActivation(new OnServiceStatus<ResponseWebUserLogin>() {
                @Override
                public void onReady(ResponseWebUserLogin response) {
                    Log.e("ResponseRegisterActivation:",new Gson().toJson(response ));
                    needHideProgressDialog();

                    if (response == null
                            || response == null
                            ) {
                        Toast.makeText(RegisterLoginActivity.this, R.string.text20, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (response.getErrors().size()>0) {
                        Toast.makeText(RegisterLoginActivity.this, response.getErrors().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }



                    if (response.getLoginCode() > 0)
                    {
                       //کد مثبت به معنی ثبت نام موفق .

                        Toast.makeText(RegisterLoginActivity.this, response.getLoginStatus(), Toast.LENGTH_SHORT).show();

                        //**********************

                        WebUserLogin webUserLogin =new WebUserLogin();// response.body().getWebUserProperties().get);//WebUserLogin();
                        webUserLogin.setLoginStatus(response.getLoginStatus());
                        WebUser webUser=new WebUser();
                        webUser.setActivationCode(response.getWebUserProperties().getActivationCode());
                        // webUser.setidentity(response.getWebUserProperties().getIdentity());// = identity;
                        webUser.setCulture(response.getWebUserProperties().getCulture());
                        webUser.setActivationCode(response.getWebUserProperties().getActivationCode());
                        webUser.setBankCardNo(response.getWebUserProperties().getBankCardNo());
                        webUser.setBull(response.getWebUserProperties().getBull());
                        webUser.setDemosticCityFa(response.getWebUserProperties().getDemosticCityFa());
                        webUser.setDemosticCityID(response.getWebUserProperties().getDemosticCityID());
                        webUser.setDemosticPrivinceFa (response.getWebUserProperties().getDemosticPrivinceFa());
                        webUser.setDemosticPrivinceID(response.getWebUserProperties().getDemosticPrivinceID());
                        webUser.setEncryptWebUserID(response.getWebUserProperties().getEncryptWebUserID());
                        webUser.setImgURL(response.getWebUserProperties().getImgURL());
                        webUser.setMsgCount(response.getWebUserProperties().getMsgCount());
                        webUser.setPassword(response.getWebUserProperties().getPassword());
                        webUser.setUsername(response.getWebUserProperties().getUsername());
                        webUser.setWebUserAddress(response.getWebUserProperties().getWebUserAddress());
                        webUser.setWebUserBirthDayMiladi(response.getWebUserProperties().getWebUserBirthDayMiladi());
                        webUser.setWebUserBirthDayShamsi(response.getWebUserProperties().getWebUserBirthDayShamsi());
                        webUser.setWebUserFatherName(response.getWebUserProperties().getWebUserFatherName());
                        webUser.setWebUserFnameE(response.getWebUserProperties().getWebUserFnameE());
                        webUser.setWebUserFnameF(response.getWebUserProperties().getWebUserFnameF());
                        webUser.setWebUserGender(response.getWebUserProperties().getWebUserGender());
                        webUser.setWebUserID(response.getWebUserProperties().getWebUserID());

                        webUserLogin.setWebUserProperties(webUser);

                        ArrayList<Contract> contracts=new ArrayList<>();
                        //قراردادها
                        for (int i = 0; i < response.getPreviousContracts().size(); i++) {
                            Contract contract=new Contract();
                            contract.setRqBaseID(response.getPreviousContracts().get(i).getRqBaseID());
                            contract.setDateFa(response.getPreviousContracts().get(i).getDateFa());
                            contract.setDate(response.getPreviousContracts().get(i).getDate());
                            contract.setPathNames(response.getPreviousContracts().get(i).getPathNames());
                            contract.setDeparture(response.getPreviousContracts().get(i).getDeparture());
                            contract.setCheckin(response.getPreviousContracts().get(i).getCheckin());
                            contract.setRemained(response.getPreviousContracts().get(i).getRemained());
                            contract.setFollowerName(response.getPreviousContracts().get(i).getFollowerName());
                            contract.setVisaConfirm(response.getPreviousContracts().get(i).getVisaConfirm());
                            contract.setTicketConfirm(response.getPreviousContracts().get(i).getTicketConfirm());
                            contract.setHotelConfirm(response.getPreviousContracts().get(i).getHotelConfirm());
                            contract.setCntDocDeliver(response.getPreviousContracts().get(i).getCntDocDeliver());
                            contract.setFinalPrice(response.getPreviousContracts().get(i).getFinalPrice());
                            contracts.add(contract);
                        }
                        webUserLogin.setPreviousContracts(contracts);
                        WebUserTools.getInstance().setUser(webUserLogin);//movaghatan pak kardam
                        MainActivity.setUserName(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
                        //*****************************
                        Intent intent = new Intent(RegisterLoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();


                    }else if (response.getLoginCode() ==-102)
                    {
                        //کاربر قبلا عضو شده
                        Toast.makeText(RegisterLoginActivity.this,response.getLoginStatus() , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //خطایی رخ داده
                        Toast.makeText(RegisterLoginActivity.this,response.getLoginStatus(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onError(String message) {
                    needHideProgressDialog();
                    Toast.makeText(RegisterLoginActivity.this, R.string.text25, Toast.LENGTH_SHORT).show();
                }
            }, requestRegisterActivation );



        }else{
            Toast.makeText(this, R.string._enter_activation_code , Toast.LENGTH_SHORT).show();
        }

    }


}
