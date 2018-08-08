package com.eligasht.reservation.views.ui.dialog;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.map.OverlayRouteActivity;
import com.eligasht.reservation.views.activities.login.LogInActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza Nejati on 14,May,2018
 */
@SuppressLint("ValidFragment")
public class LocationAlertDialog extends DialogFragment implements View.OnClickListener {
    TextView tvDesc, tvTitle;
    Activity activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    Dialog dialog;
    String lottie;
    String title, desc, btnOKtxt, btnCanceltxt;
    OnDialogClick onDialogClick;
    boolean hasCancelBtn;
    boolean pause = false;

    public LocationAlertDialog(Activity activity, String lottie, String title, String desc, String btnOKtxt, String btnCanceltxt, boolean hasCancelBtn, OnDialogClick onDialogClick) {
        this.activity = activity;
        this.lottie = lottie;
        this.title = title;
        this.desc = desc;
        this.onDialogClick = onDialogClick;
        this.btnOKtxt = btnOKtxt;
        this.btnCanceltxt = btnCanceltxt;
        this.hasCancelBtn = hasCancelBtn;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(activity, R.style.MyAlertDialogStyle);
        dialog.setContentView(R.layout.alert_dialog_location);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        btnOk = (FancyButton) dialog.findViewById(R.id.btnOk);
        btnCancel = (FancyButton) dialog.findViewById(R.id.btnCancel);
        avi = (AVLoadingIndicatorView) dialog.findViewById(R.id.avi);
        tvDesc = (TextView) dialog.findViewById(R.id.tvDesc);
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        tvDesc.setText(desc);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) dialog.findViewById(R.id.animation_view);
        lottieAnimationView.setImageAssetsFolder("lottie/images/");
        lottieAnimationView.setAnimation(lottie);
        lottieAnimationView.playAnimation();
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnCancel.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setText(btnOKtxt);
        btnCancel.setText(btnCanceltxt);
        if (!hasCancelBtn)
            btnCancel.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            revealShow();
        } else {
            View view = dialog.findViewById(R.id.dialog);
            view.setVisibility(View.VISIBLE);
        }
        return dialog;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Here", "On Pauese");
        pause = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (pause && getTag().equals("location")) {
            close();
        }
    }

    private void close() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            revealClose();
        } else {
            View view = dialog.findViewById(R.id.dialog);
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                onDialogClick.btnOk(getTag());
                if (getTag().equals("arrival"))
                    close();
                break;
            case R.id.btnCancel:
                onDialogClick.btnCancel(getTag());
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
                Animator anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, finalRadius);
                view.setVisibility(View.VISIBLE);
                anim.setDuration(500);
                anim.start();
            }
        }, 200);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealClose() {
        View view = dialog.findViewById(R.id.dialog);
        new Handler().postDelayed(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int x = view.getRight();
                Rect bounds = new Rect();
                view.getDrawingRect(bounds);
                int centerX = bounds.centerX();
                int centerY = bounds.centerY();
                Log.e("T", String.valueOf(centerX));
                int finalRadius = Math.max(bounds.width(), bounds.height());
                Animator anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, finalRadius, 0f);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        dialog.dismiss();
                        view.setVisibility(View.INVISIBLE);
                    }
                });
                anim.setDuration(500);
                anim.start();
            }
        }, 200);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface OnDialogClick {
        void btnOk(String tag);
        void btnCancel(String tag);
    }
}

