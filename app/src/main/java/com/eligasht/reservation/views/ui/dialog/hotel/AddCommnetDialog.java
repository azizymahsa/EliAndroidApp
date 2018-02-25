package com.eligasht.reservation.views.ui.dialog.hotel;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.api.hotel.comment.AddComment;
import com.eligasht.reservation.tools.Utility;

import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class AddCommnetDialog implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk;
    EditText etTitle, etName;
    AVLoadingIndicatorView avi;
    AddComment addComment;
    String comment,userName,title;
    boolean isFinish;
    OnCommentDialogListenerArray OnCommentDialogListenerArray;

    public AddCommnetDialog(final Activity activity) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_comment, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);


        btnOk.setCustomTextFont("iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                if (isFinish){
                    dialog.cancel();
                    activity.finish();
                }else{
                    dialog.cancel();

                }







                break;

        }
    }


    public void setTitle(String title,boolean isFinish) {
        this.title = title;
        this.isFinish = isFinish;
        tvAlert.setText(title);
        dialog.show();

    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }



    public interface OnCommentDialogListenerArray {
        public void onReturnValue(String userName,String title);
    }
}