package com.eligasht.reservation.views.ui.dialog;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.views.activities.insurance.PassengerInsuranceActivity;
import com.eligasht.reservation.views.activities.login.LogInActivity;
import com.eligasht.reservation.views.activities.pack.PassengerPackageActivity;
import com.eligasht.reservation.views.activities.train.PassengerTrainActivity;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.PassengerHotelActivity;
import com.eligasht.reservation.views.ui.PassengerHotelFlightActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza Nejati on 12,May,2018
 */

@SuppressLint("ValidFragment")
public class PromotionCodeDialog extends DialogFragment implements View.OnClickListener {
    private static int flagActivity;
    TextView  tvTitle, tvRoomName;
    EditText tvAlert;
    Activity activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    Dialog dialog;
    Boolean isFinish=false;

    public static PromotionCodeDialog newInstance(final Activity activity,Boolean isFinish,int flagActivityy) {
       isFinish=isFinish;
        flagActivity=flagActivityy;
        PromotionCodeDialog resultGiftDialog = new PromotionCodeDialog();
        resultGiftDialog.initialize(activity);
        return resultGiftDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dialog = new Dialog(activity, R.style.MyAlertDialogStyle);
        dialog.setContentView(R.layout.alert_dialog_promotion_code);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        btnOk = (FancyButton) dialog.findViewById(R.id.btnOk);
        btnCancel = (FancyButton) dialog.findViewById(R.id.btnCancel);
        avi = (AVLoadingIndicatorView) dialog.findViewById(R.id.avi);
        tvAlert = (EditText) dialog.findViewById(R.id.tvAlert);
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        tvRoomName = (TextView) dialog.findViewById(R.id.tvRoomName);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) dialog.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("lottie/discount-icon.json");
        lottieAnimationView.playAnimation();
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnCancel.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            revealShow();
        }else {
            View view = dialog.findViewById(R.id.dialog);
            view.setVisibility(View.VISIBLE);

        }
        return dialog;
    }

    private void initialize(Activity activity) {
        this.activity = activity;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                activity.startActivity(new Intent(activity, LogInActivity.class));
                break;
            case R.id.btnCancel:
               if (isFinish) {
                   activity.finish();
               }else{
                    dialog.cancel();
                   if (flagActivity == 1) {
                       PassengerActivity.updateFactorByPromotion(tvAlert.getText().toString().trim(),activity,getContext());

                }else if (flagActivity==2){
                       PassengerHotelActivity.updateFactorByPromotion(tvAlert.getText().toString().trim(),activity,getContext());

                 }else if (flagActivity==3){
                       PassengerHotelFlightActivity.updateFactorByPromotion(tvAlert.getText().toString().trim(),activity,getContext());

                  }else if (flagActivity==4){
                       PassengerPackageActivity.updateFactorByPromotion(tvAlert.getText().toString().trim(),activity,getContext());

                   }else if (flagActivity==5){
                       PassengerInsuranceActivity.updateFactorByPromotion(tvAlert.getText().toString().trim(),activity,getContext());

                   }else if (flagActivity==6){
                       PassengerTrainActivity.updateFactorByPromotion(tvAlert.getText().toString().trim(),activity,getContext());

                   }
               }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealShow() {

        View view = dialog.findViewById(R.id.dialog);
        new Handler().postDelayed(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int x = view.getRight();
                Rect bounds = new Rect();
                view.getDrawingRect(bounds);
                int centerX = bounds.centerX();
                int centerY = bounds.centerY();
                int finalRadius = Math.max(bounds.width(), bounds.height());
                Animator anim  = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, finalRadius);
                view.setVisibility(View.VISIBLE);
                anim.setDuration(500);
                anim.start();
            }

        }, 200);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

