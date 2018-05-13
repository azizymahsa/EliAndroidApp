package com.eligasht.reservation.views.ui.dialog;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.views.activities.login.LogInActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza Nejati on 12,May,2018
 */
@SuppressLint("ValidFragment")
public class ResultGiftDialog extends DialogFragment implements View.OnClickListener {
    TextView tvAlert, tvTitle, tvRoomName;
    Activity activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    Dialog dialog;


    public static ResultGiftDialog newInstance(final Activity activity) {
        ResultGiftDialog resultGiftDialog = new ResultGiftDialog();
        resultGiftDialog.initialize(activity);
        return resultGiftDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* View rootView = inflater.inflate(R.layout.alert_dialog_gift_result, container,
                false);*/


    /*    dialog = new MaterialDialog.Builder(activity)
                .customView(R.layout.alert_dialog_gift_result, false)
                .autoDismiss(false)
                .build();


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));






        btnOk = (FancyButton) dialog.findViewById(R.id.btnOk);
        btnCancel = (FancyButton) dialog.findViewById(R.id.btnCancel);
        avi = (AVLoadingIndicatorView) dialog.findViewById(R.id.avi);
        tvAlert = (TextView) dialog.findViewById(R.id.tvAlert);
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        tvRoomName = (TextView) dialog.findViewById(R.id.tvRoomName);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) dialog.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("lottie/restless_gift_ii.json");
        lottieAnimationView.playAnimation();
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnCancel.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(dialog.getCustomView());
*/



        dialog = new Dialog(activity, R.style.MyAlertDialogStyle);
        dialog.setContentView(R.layout.alert_dialog_gift_result);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        btnOk = (FancyButton) dialog.findViewById(R.id.btnOk);
        btnCancel = (FancyButton) dialog.findViewById(R.id.btnCancel);
        avi = (AVLoadingIndicatorView) dialog.findViewById(R.id.avi);
        tvAlert = (TextView) dialog.findViewById(R.id.tvAlert);
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        tvRoomName = (TextView) dialog.findViewById(R.id.tvRoomName);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) dialog.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("lottie/restless_gift_ii.json");
        lottieAnimationView.playAnimation();
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnCancel.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
   /*     YoYo.with(Techniques.FadeIn)
                .duration(500)
                .playOn(dialog());*/
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
                activity.finish();
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

