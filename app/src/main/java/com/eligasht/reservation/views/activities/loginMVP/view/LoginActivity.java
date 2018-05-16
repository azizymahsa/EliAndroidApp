/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.eligasht.reservation.views.activities.loginMVP.view;


import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.eligasht.reservation.views.activities.loginMVP.interactor.LoginInteractorImpl;
import com.eligasht.reservation.views.activities.loginMVP.presenters.LoginPresenter;
import com.eligasht.reservation.views.activities.loginMVP.presenters.LoginPresenterImpl;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.adapters.ViewPagerLogin;
import com.eligasht.reservation.views.ui.ViewPagerAttention;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {


    private LottieAnimationView progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;
    private LinearLayout llBasic;
    private LinearLayout llsignIn;
    private LinearLayout lllogon;
    private Button btn_signIn;
    private Button btn_login;
    private LinearLayout llBackLI;
    private LinearLayout llBackSI;
    private LinearLayout log_in_app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvp);
        ArrayList<Integer> resource=new ArrayList<>();
        resource.add(R.drawable.screen_hotel);
        resource.add(R.drawable.screen_menu);
        resource.add(R.drawable.screen_search_hotel);



        new ViewPagerLogin(LoginActivity.this, resource, R.id.intro_view_pager);
        progressBar = (LottieAnimationView) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        llBasic=(LinearLayout)findViewById(R.id.linear_basic);
        llsignIn=(LinearLayout)findViewById(R.id.rsignIn);
        lllogon=(LinearLayout)findViewById(R.id.rllogon);
        log_in_app=(LinearLayout)findViewById(R.id.log_in_app);
        btn_signIn=(Button) findViewById(R.id.btn_signIn);
        btn_login=(Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_signIn.setOnClickListener(this);
        log_in_app.setOnClickListener(this);

        llBackLI=(LinearLayout) findViewById(R.id.llBackLI);
        llBackSI=(LinearLayout) findViewById(R.id.llBackSI);
        llBackLI.setOnClickListener(this);
        llBackSI.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        presenter = new LoginPresenterImpl(this,new LoginInteractorImpl());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        //progressBar.addAnimatorListener(this);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {

        username.setError(getString(R.string.please_enter_your_name));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.text28));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void OnShowLOgin() {

        leftAnim(llBasic,lllogon);
    }

    @Override
    public void OnShowSignIn() {

        rightAnim(llsignIn,llBasic);

    }

    @Override
    public void OnBackLOgin() {

       rightAnim(llBasic,lllogon);
    }

    @Override
    public void OnBackSignIn() {
        leftAnim(llsignIn,llBasic);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

           //

            case R.id.llBackLI:
                OnBackLOgin();
                break;
                case R.id.log_in_app:
                    presenter.validateCredentials(username.getText().toString(), password.getText().toString());
                break;
            case R.id.llBackSI:
                OnBackSignIn();
                break;
            case R.id.btn_signIn:
              OnShowSignIn();
             break;

            case R.id.btn_login:
              OnShowLOgin();
             break;
        }

    }

    public void leftAnim(View out,View in){
        YoYo.with(Techniques.SlideOutLeft)
                .duration(300).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                in.setVisibility(View.VISIBLE);
                out.setVisibility(View.GONE);
                YoYo.with(Techniques.SlideInRight)
                        .duration(300)
                        .playOn(in);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        })
                .playOn(out);
    }
    public void rightAnim(View in,View out){
        YoYo.with(Techniques.SlideOutRight)
                .duration(300).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                in.setVisibility(View.VISIBLE);
                out.setVisibility(View.GONE);
                YoYo.with(Techniques.SlideInLeft)
                        .duration(300)
                        .playOn(in);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        })
                .playOn(out);
    }
}
