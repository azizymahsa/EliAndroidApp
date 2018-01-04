package com.reserv.myapplicationeli.views.ui;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/3/2018.
 */

public class InitUi {
    public static void Toolbar(final Activity activity, boolean isMainActivity, int color, String title){
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        TextView tvTitle = activity.findViewById(R.id.tvTitle);
        FancyButton btnBack=activity.findViewById(R.id.btnBack);
        FancyButton btnMenu=activity.findViewById(R.id.btnMenu);


        toolbar.setBackgroundColor(activity.getColor(color));
        tvTitle.setText(title);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnMenu.setCustomTextFont("fonts/icomoon.ttf");
        btnMenu.setText(activity.getString(R.string.icon_menu));
        btnBack.setText(activity.getString(R.string.icon_arrow));



        if(isMainActivity){
            btnMenu.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.GONE);
        }else{

            btnMenu.setVisibility(View.GONE);
            btnBack.setVisibility(View.VISIBLE);
        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });





    }
}
