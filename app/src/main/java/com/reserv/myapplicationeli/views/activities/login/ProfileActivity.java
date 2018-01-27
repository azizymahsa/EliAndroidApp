package com.reserv.myapplicationeli.views.activities.login;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.model.login.WebUserLogin;
import com.reserv.myapplicationeli.models.model.login.call.ChangePasswordReq;
import com.reserv.myapplicationeli.models.model.login.call.ChangePasswordRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.EmailContractRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.LoginRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.RegisterRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.EmailContractRes;
import com.reserv.myapplicationeli.models.model.login.response.EmailContractResult;
import com.reserv.myapplicationeli.models.model.login.response.WebUserChangePasswordRes;
import com.reserv.myapplicationeli.models.model.login.response.WebUserUpdateProfileRes;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.WebUserTools;
import com.reserv.myapplicationeli.views.fragments.profile.ProfilePagerAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by elham.bonyani on 1/23/2018.
 */

public class ProfileActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView img_profile;
    private TextView txt_name;
    private Button btnSaveInfo;
    private ProfilePagerAdapter profilePagerAdapter;
    private ClientService service;

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "پروفایل من");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();
        setupPager();
        initParam();
        service = ServiceGenerator.createService(ClientService.class);

    }

    private void initParam() {
        txt_name.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF()+ " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
    }

    private void setupPager() {
        profilePagerAdapter = new ProfilePagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(profilePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(onTabSelectedListener);

    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()){
                case 0:
                    btnSaveInfo.setText("ثبت و ذخیره اطلاعات");
                    break;
                case 1:
                    btnSaveInfo.setText("ارسال مدارک");
                    break;
                case 2:
                    btnSaveInfo.setText("تغییر کلمه عبور");
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


    private void initViews() {
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        txt_name = findViewById(R.id.txt_name);
        img_profile = findViewById(R.id.img_profile);
        btnSaveInfo = findViewById(R.id.btnSaveInfo);

        btnSaveInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveInfo:
                switch (tabLayout.getSelectedTabPosition()){
                    case 0:
                        updateProfile();
                        break;
                    case 1:
                        emailContractProfile();
                        break;
                    case 2:
                        changePasswordProfile();
                        break;
                }
                break;
        }
    }

    private void changePasswordProfile() {
        if(!profilePagerAdapter.getChangePasswordFragment().isValidForm()){
            return;
        }

        needShowProgressDialog();

        Call<WebUserChangePasswordRes> call = service.changePasswordProfile(new ChangePasswordRequestModel(profilePagerAdapter.getChangePasswordFragment().getChangePasswordReq()));
        call.enqueue(new Callback<WebUserChangePasswordRes>() {
            @Override
            public void onResponse(Call<WebUserChangePasswordRes> call, Response<WebUserChangePasswordRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getWebUserChangePasswordResult() == null) {

                    Toast.makeText(ProfileActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(!ValidationTools.isEmptyOrNull(response.body().getWebUserChangePasswordResult().getError())){
                    Toast.makeText(ProfileActivity.this, response.body().getWebUserChangePasswordResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                EmailContractResult webUserChangePasswordResult = response.body().getWebUserChangePasswordResult();
                if(webUserChangePasswordResult.getSuccessResult() == 1){
                    Toast.makeText(ProfileActivity.this, "درخواست شما با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WebUserChangePasswordRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ProfileActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateProfile() {
        if(!profilePagerAdapter.getEditProfileFragment().isValidForm()){
            return;
        }
        needShowProgressDialog();

        Call<WebUserUpdateProfileRes> call = service.updateProfile(new RegisterRequestModel(profilePagerAdapter.getEditProfileFragment().getRegisterListReq()));
        call.enqueue(new Callback<WebUserUpdateProfileRes>() {
            @Override
            public void onResponse(Call<WebUserUpdateProfileRes> call, Response<WebUserUpdateProfileRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getWebUserUpdateProfilerResult() == null) {

                    Toast.makeText(ProfileActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!ValidationTools.isEmptyOrNull(response.body().getWebUserUpdateProfilerResult().getError())){
                    Toast.makeText(ProfileActivity.this, response.body().getWebUserUpdateProfilerResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                WebUserLogin webUserLogin = response.body().getWebUserUpdateProfilerResult().getWebUserLogin();

                if(webUserLogin == null){
                    Toast.makeText(ProfileActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }

                WebUserTools.getInstance().setUser(webUserLogin);
                Toast.makeText(ProfileActivity.this, "درخواست شما با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<WebUserUpdateProfileRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ProfileActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void emailContractProfile(){
        if(!profilePagerAdapter.getMyContractsFragment().isValidForm()){
            return;
        }
        needShowProgressDialog();
        Log.e(" requestContract " ,new GsonBuilder().create().toJson(new EmailContractRequestModel(profilePagerAdapter.getMyContractsFragment().getEmailContractReq())));
        Call<EmailContractRes> call = service.emailContractProfile(new EmailContractRequestModel(profilePagerAdapter.getMyContractsFragment().getEmailContractReq()));
        call.enqueue(new Callback<EmailContractRes>() {
            @Override
            public void onResponse(Call<EmailContractRes> call, Response<EmailContractRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getEmailContractResult() == null) {

                    Toast.makeText(ProfileActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(!ValidationTools.isEmptyOrNull(response.body().getEmailContractResult().getError())){
                    Toast.makeText(ProfileActivity.this, response.body().getEmailContractResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                EmailContractResult emailContractResult = response.body().getEmailContractResult();
                if(emailContractResult.getSuccessResult() == 1){
                    Toast.makeText(ProfileActivity.this, "درخواست شما با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmailContractRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ProfileActivity.this, "در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمیباشد", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
