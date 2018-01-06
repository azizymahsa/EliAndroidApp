package com.reserv.myapplicationeli.views.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.tools.Utility;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/3/2018.
 */

public class InitUi {
    @SuppressLint("NewApi")
    public static void Toolbar(final Activity activity, boolean isMainActivity, int color, String title){
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        TextView tvTitle = activity.findViewById(R.id.tvTitle);
        FancyButton btnBack=activity.findViewById(R.id.btnBack);
        FancyButton btnMenu=activity.findViewById(R.id.btnMenu);


        toolbar.setBackgroundColor(ContextCompat.getColor(activity,color));
        tvTitle.setText(title);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnMenu.setCustomTextFont("fonts/icomoon.ttf");
        btnMenu.setText(activity.getString(R.string.icon_menu));
        btnBack.setText(activity.getString(R.string.search_back_right));



        if(isMainActivity){
            btnMenu.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.GONE);
        }else{

            btnMenu.setVisibility(View.GONE);
        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });





    }
    public  void Loading(final RelativeLayout rlLoading, final RelativeLayout root, boolean start ) {
        if (start) {
            if (rlLoading.getVisibility() != View.VISIBLE) {
                rlLoading.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(100)
                        .playOn(rlLoading);
                Utility.disableEnableControls(false, root);
            }
        } else {
            if (rlLoading.getVisibility() == View.VISIBLE) {

                YoYo.with(Techniques.FadeOut)
                        .duration(600).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(android.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(android.animation.Animator animation) {
                        rlLoading.setVisibility(View.GONE);
                        Utility.disableEnableControls(true, root);

                    }

                    @Override
                    public void onAnimationCancel(android.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(android.animation.Animator animation) {

                    }

                })
                        .playOn(rlLoading);


                //    rlLoading.setVisibility(View.GONE);
            }

        }


    }}
