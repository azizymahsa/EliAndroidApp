package com.eligasht.reservation.views.ui.dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.views.activities.ShakeActivity;
import com.eligasht.reservation.views.activities.login.LogInActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza Nejati on 12,May,2018
 */
public class GiftDialog implements View.OnClickListener{


    android.app.AlertDialog dialog;
    TextView tvAlert,tvTitle,tvRoomName;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    String text;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;


    public GiftDialog(final Context activity) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_gift, null);
        builder.setView(dialogView);
        btnOk = dialogView.findViewById(R.id.btnOk);
        btnCancel = dialogView.findViewById(R.id.btnCancel);
        avi = dialogView.findViewById(R.id.avi);
        tvAlert = dialogView.findViewById(R.id.tvAlert);
        tvTitle = dialogView.findViewById(R.id.tvTitle);
        tvRoomName = dialogView.findViewById(R.id.tvRoomName);
        LottieAnimationView lottieAnimationView = dialogView.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("lottie/restless_gift_ii.json");
        lottieAnimationView.playAnimation();
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnCancel.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
    //    dialog.show();
    }
    public android.app.AlertDialog alertDialog(){
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                activity.startActivity(new Intent(activity, LogInActivity.class));

                break;
            case R.id.btnCancel:
                dialog.cancel();

                break;

        }
    }


}

