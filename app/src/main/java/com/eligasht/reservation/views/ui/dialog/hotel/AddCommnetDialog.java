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
    boolean isFinish=false;
    OnCommentDialogListenerArray OnCommentDialogListenerArray;

    public AddCommnetDialog(final Activity activity,OnCommentDialogListenerArray OnCommentDialogListenerArray) {
        this.activity = activity;
        this.comment = comment;
        this.OnCommentDialogListenerArray = OnCommentDialogListenerArray;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_comment, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);
        etTitle = (EditText) dialogView.findViewById(R.id.etTitle);
        etName = (EditText) dialogView.findViewById(R.id.etName);
        avi = (AVLoadingIndicatorView) dialogView.findViewById(R.id.avi);
        //tvAlert.setText(text);
        //Typeface typeface=Typeface.createFromAsset(activity.getAssets(),"fonts/iran_sans_bold.ttf");
   /*     tvAlert.setTextSize(2,12);
        tvAlert.setLineSpacing(5);
        tvAlert.setTypeFace(typeface);*/
        btnOk.setCustomTextFont("iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                if (!isFinish){
                    boolean isOk=true;

                    if (TextUtils.isEmpty(etName.getText())){
                        isOk=false;
                        GradientDrawable drawable = (GradientDrawable)etName.getBackground();
                        drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    }
                    if (TextUtils.isEmpty(etTitle.getText())){
                        isOk=false;
                        GradientDrawable drawable = (GradientDrawable)etTitle.getBackground();
                        drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    }


                    if (isOk){

                        userName=etName.getText().toString();
                        title=etTitle.getText().toString();
                        Utility.hideKeyboard(activity,etTitle);


                        OnCommentDialogListenerArray.onReturnValue(userName,title);

                    }

                }else{

                   dialog.cancel();

                }



                break;

        }
    }


    public void setTitle(String title) {
        this.title = title;
        tvAlert.setVisibility(View.VISIBLE);

        tvAlert.setText(title);

    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public void onPost(){
    avi.setVisibility(View.VISIBLE);
    etName.setVisibility(View.GONE);
    etTitle.setVisibility(View.GONE);
    btnOk.setEnabled(false);
    btnOk.setClickable(false);

}
public void onEx(){
    btnOk.setEnabled(true);
    btnOk.setClickable(true);
    avi.setVisibility(View.GONE);

    btnOk.setText("بازگشت");
    isFinish=true;
}
    public interface OnCommentDialogListenerArray {
        public void onReturnValue(String userName,String title);
    }
}