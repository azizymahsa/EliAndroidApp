package com.eligasht.reservation.views.activities.login;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.login.WebUserLogin;
import com.eligasht.reservation.models.model.login.call.ChangePasswordRequestModel;
import com.eligasht.reservation.models.model.login.call.EmailContractReq;
import com.eligasht.reservation.models.model.login.call.EmailContractRequestModel;
import com.eligasht.reservation.models.model.login.call.RegisterRequestModel;
import com.eligasht.reservation.models.model.login.response.EmailContractRes;
import com.eligasht.reservation.models.model.login.response.EmailContractResult;
import com.eligasht.reservation.models.model.login.response.WebUserChangePasswordRes;
import com.eligasht.reservation.models.model.login.response.WebUserUpdateProfileRes;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.fragments.profile.ProfilePagerAdapter;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.google.gson.GsonBuilder;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by elham.bonyani on 1/23/2018.
 */

public class ProfileActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView txt_name;
    private TextView title;
    private Button btnSaveInfo;
    private FancyButton btnBack;
    private FancyButton btnHome;
    private ProfilePagerAdapter profilePagerAdapter;
    private ClientService service;
    private RelativeLayout llHome;
    private CoordinatorLayout coordinatorLayout;
    private ImageView imageView;
    private Toolbar toolbar;
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    btnSaveInfo.setText(R.string.text52);
                    btnSaveInfo.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    btnSaveInfo.setVisibility(View.GONE);
                    //   btnSaveInfo.setText("ارسال مدارک");
                    break;
                case 2:

                    btnSaveInfo.setText(R.string.text51);
                    btnSaveInfo.setVisibility(View.VISIBLE);
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

    //
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        initViews();

        if (!ValidationTools.isEmptyOrNull(WebUserTools.getInstance().getUser().getWebUserProperties().getImgURL())){
            GlideApp
                    .with(this)
                    .load(WebUserTools.getInstance().getUser().getWebUserProperties().getImgURL())
                    .centerCrop()
                    .error(R.drawable.not_found)
                    .into(imageView);
        }
        else{

        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (!ValidationTools.isEmptyOrNull(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF())||
                !ValidationTools.isEmptyOrNull(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF())){
            title.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF()+" "+WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());

        }else{
            title.setText(getString(R.string.my_profile));

        }
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        setupPager();
        initParam();
        service = ServiceGenerator.createService(ClientService.class);
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(), SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf)));
                }
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initParam() {
        try {


            try {
              //  img_profile.setText(String.valueOf(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameE().charAt(0) + "" + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameE().charAt(0)).toUpperCase());
            } catch (Exception e) {
               // img_profile.setText("");
            }

            try {
                txt_name.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
            } catch (Exception e) {
                txt_name.setText("");
            }
        } catch (Exception e) {

        }

    }

    private void setupPager() {
        profilePagerAdapter = new ProfilePagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(profilePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2);
        tabLayout.setOnTabSelectedListener(onTabSelectedListener);
        try {
            if (getIntent().getExtras().getBoolean("isLastBuy")) {
                viewPager.setCurrentItem(1);
            }

        } catch (Exception e) {
        }


    }

    private void initViews() {
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        txt_name = findViewById(R.id.txt_name);
        imageView = findViewById(R.id.imageView);
      //  img_profile = findViewById(R.id.img_profile);
        btnSaveInfo = findViewById(R.id.btnSaveInfo);
        title = findViewById(R.id.title);
        btnBack = findViewById(R.id.btnBack);
        btnHome = findViewById(R.id.btnHome);
        coordinatorLayout = findViewById(R.id.coordinator);
        toolbar = findViewById(R.id.toolbar);
        btnSaveInfo.setOnClickListener(this);
        initToolbar(toolbar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveInfo:
                switch (tabLayout.getSelectedTabPosition()) {
                    case 0:
                        changePasswordProfile();

                        break;
                    case 1:
                        //   emailContractProfile();
                        break;
                    case 2:
                        updateProfile();
                        break;
                }
                break;
        }
    }

    //request for changePassword to server and get results
    private void changePasswordProfile() {
        if (!profilePagerAdapter.getChangePasswordFragment().isValidForm()) {
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

                    Toast.makeText(ProfileActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!ValidationTools.isEmptyOrNull(response.body().getWebUserChangePasswordResult().getError())) {
                    Toast.makeText(ProfileActivity.this, response.body().getWebUserChangePasswordResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                EmailContractResult webUserChangePasswordResult = response.body().getWebUserChangePasswordResult();
                if (webUserChangePasswordResult.getSuccessResult() == 1) {
                    Toast.makeText(ProfileActivity.this, getString(R.string.success), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WebUserChangePasswordRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ProfileActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //request for updateProfile and get results
    public void updateProfile() {
        if (!profilePagerAdapter.getEditProfileFragment().isValidForm()) {
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

                    Toast.makeText(ProfileActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!ValidationTools.isEmptyOrNull(response.body().getWebUserUpdateProfilerResult().getError())) {
                    Toast.makeText(ProfileActivity.this, response.body().getWebUserUpdateProfilerResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                WebUserLogin webUserLogin = response.body().getWebUserUpdateProfilerResult().getWebUserLogin();

                if (webUserLogin == null) {
                    Toast.makeText(ProfileActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                    return;
                }

                WebUserTools.getInstance().setUser(webUserLogin);
                Toast.makeText(ProfileActivity.this, getString(R.string.success), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<WebUserUpdateProfileRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ProfileActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //request for send contracts and get result
    public void emailContractProfile(EmailContractReq getEmailContractReq) {
        if (!profilePagerAdapter.getMyContractsFragment().isValidForm()) {
            return;
        }
        needShowProgressDialog();
        Log.e(" requestContract ", new GsonBuilder().create().toJson(new EmailContractRequestModel(getEmailContractReq)));
        Call<EmailContractRes> call = service.emailContractProfile(new EmailContractRequestModel(getEmailContractReq));
        call.enqueue(new Callback<EmailContractRes>() {
            @Override
            public void onResponse(Call<EmailContractRes> call, Response<EmailContractRes> response) {
                needHideProgressDialog();
                if (response == null
                        || response.body() == null
                        || response.body().getEmailContractResult() == null) {

                    Toast.makeText(ProfileActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!ValidationTools.isEmptyOrNull(response.body().getEmailContractResult().getError())) {
                    Toast.makeText(ProfileActivity.this, response.body().getEmailContractResult().getError().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                EmailContractResult emailContractResult = response.body().getEmailContractResult();
                if (emailContractResult.getSuccessResult() == 0) {
                    Toast.makeText(ProfileActivity.this, getString(R.string.success), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmailContractRes> call, Throwable t) {
                needHideProgressDialog();
                Toast.makeText(ProfileActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void initToolbar(Toolbar toolbar) {
        if (toolbar == null)
            return;
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        View customView = getLayoutInflater().inflate(R.layout.toolbar, null);
        customView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        actionBar.setCustomView(customView);
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }

}
