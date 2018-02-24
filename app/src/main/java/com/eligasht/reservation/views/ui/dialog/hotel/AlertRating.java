package com.eligasht.reservation.views.ui.dialog.hotel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.eligasht.R;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 2/24/2018.
 */

public class AlertRating implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvMax,tvMin;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk,btnExit;
    RatingHotelDialogListener ratingHotelDialogListener;
    com.iarcuschin.simpleratingbar.SimpleRatingBar rbRating;
    Float star;


    public AlertRating(final Activity activity,RatingHotelDialogListener ratingHotelDialogListener,Float star) {
        this.activity = activity;
        this.star = star;
        this.ratingHotelDialogListener = ratingHotelDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_rating, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        btnExit = (FancyButton) dialogView.findViewById(R.id.btnExit);
        rbRating = (SimpleRatingBar) dialogView.findViewById(R.id.rbRating);
        if (star!=null){
           // rbRating.setRating(star);
            SimpleRatingBar.AnimationBuilder animationBuilder = rbRating.getAnimationBuilder()
                    .setRatingTarget(star)
                    .setDuration(700).setRepeatCount(0)
                    .setInterpolator(new AccelerateDecelerateInterpolator());
            animationBuilder.start();
        }else{
            SimpleRatingBar.AnimationBuilder animationBuilder = rbRating.getAnimationBuilder()
                    .setRatingTarget(3)
                    .setDuration(700).setRepeatCount(0)
                    .setInterpolator(new AccelerateDecelerateInterpolator());
            animationBuilder.start();
        }


        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnExit.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnExit.setOnClickListener(this);
        btnOk.setOnClickListener(this);


        dialog = builder.create();
        dialog.setCancelable(false);

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnOk:
                ratingHotelDialogListener.onReturnValue(1,rbRating.getRating());
                dialog.cancel();



                break;
            case R.id.btnExit:
                dialog.cancel();
                activity.finish();

                break;

        }
    }
    public interface RatingHotelDialogListener{
        public void onReturnValue(int type,Float rate);
    }

}
