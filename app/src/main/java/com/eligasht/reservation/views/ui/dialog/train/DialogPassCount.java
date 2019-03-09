package com.eligasht.reservation.views.ui.dialog.train;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.fragments.train.TrainFragment;
import com.eligasht.reservation.views.ticker.TickerView;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/21/2018.
 */
public class DialogPassCount implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView  tvTitle;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk;
    String title;
    Typeface type;
    boolean isFinish;
    public static int countNafar = 1;
    public Button btnPlusB, btnMinesB, btnPlusK, btnMinesK, btnPlusN, btnMinesN;
    private TickerView txtCountB, txtCountK, txtCountN;

    public DialogPassCount(final Activity activity, boolean warning, boolean isFinish) {
         type = Typeface.createFromAsset(activity.getAssets(), "fonts/iran_sans_normal.ttf");
        this.activity = activity;
        this.isFinish = isFinish;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.dialog_pass_count, null);
        initView();

        builder.setView(dialogView);
        dialog = builder.create();
        dialog.setCancelable(true);
        try {
            dialog.show();
        } catch (Exception e) {
        }
    }

    private void initView() {
        btnPlusB = dialogView.findViewById(R.id.btnPlusB);
        btnMinesB = dialogView.findViewById(R.id.btnMinesB);
        btnPlusK = dialogView.findViewById(R.id.btnPlusK);
        btnMinesK = dialogView.findViewById(R.id.btnMinesK);
        btnPlusN = dialogView.findViewById(R.id.btnPlusN);
        btnMinesN = dialogView.findViewById(R.id.btnMinesN);

        txtCountB = dialogView.findViewById(R.id.txtCountB);
        txtCountK = dialogView.findViewById(R.id.txtCountK);
        txtCountN = dialogView.findViewById(R.id.txtCountN);

        btnOk = dialogView.findViewById(R.id.btnOk);
        tvTitle = dialogView.findViewById(R.id.tvTitle);
        tvTitle.setTypeface(type);

        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);

        btnPlusB.setOnClickListener(this);
        btnMinesB.setOnClickListener(this);
        btnPlusK.setOnClickListener(this);
        btnMinesK.setOnClickListener(this);
        btnPlusN.setOnClickListener(this);
        btnMinesN.setOnClickListener(this);
    }

    public void setText( String title) {

        this.title = title;
        tvTitle.setText(title);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                if (isFinish) {
                    activity.finish();
                }else{
                    Prefs.putString("Value_Pass_CountB", txtCountB.getText().toString().trim());
                    Prefs.putString("Value_Pass_CountK", txtCountK.getText().toString().trim());
                    Prefs.putString("Value_Pass_CountN", txtCountN.getText().toString().trim());
                    TrainFragment.updateCount();
                dialog.cancel();
                }
                break;
            case R.id.btnPlusB:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String btnPlusBStr = txtCountB.getText().toString();
                        int btnPlusBIntVal = Integer.parseInt(btnPlusBStr);
                        if (isInRange(1, 8, btnPlusBIntVal))
                            btnPlusBIntVal = btnPlusBIntVal + 1;
                        txtCountB.setText(String.valueOf(btnPlusBIntVal));//}

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
                break;
            case R.id.btnMinesB:
                try {
                    String btnMinesBValStr = txtCountB.getText().toString();
                    int btnMinesBIntVal = Integer.parseInt(btnMinesBValStr);
                    if (isInRange(2, 9, btnMinesBIntVal))
                        btnMinesBIntVal = btnMinesBIntVal - 1;
                    txtCountB.setText(String.valueOf(btnMinesBIntVal));//}

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnPlusK:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String btnPlusKValStr = txtCountK.getText().toString();
                        int btnPlisKIntVal = Integer.parseInt(btnPlusKValStr);
                        if (isInRange(0, 8, btnPlisKIntVal))
                            btnPlisKIntVal = btnPlisKIntVal + 1;
                        txtCountK.setText(String.valueOf(btnPlisKIntVal));//}
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
                break;
            case R.id.btnMinesK:
                try {
                    String btnMinesKValStr = txtCountK.getText().toString();
                    int btnMinesKIntVal = Integer.parseInt(btnMinesKValStr);
                    if (isInRange(1, 9, btnMinesKIntVal))
                        btnMinesKIntVal = btnMinesKIntVal - 1;
                    txtCountK.setText(String.valueOf(btnMinesKIntVal));//}
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnPlusN:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String presentValStr3 = txtCountN.getText().toString();
                        int presentIntVal3 = Integer.parseInt(presentValStr3);
                        if (isInRange(0, 8, presentIntVal3))
                            presentIntVal3 = presentIntVal3 + 1;
                        txtCountN.setText(String.valueOf(presentIntVal3));//}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btnMinesN:
                try {
                    String presentValStr4 = txtCountN.getText().toString();
                    int presentIntVal4 = Integer.parseInt(presentValStr4);
                    if (isInRange(1, 9, presentIntVal4))
                        presentIntVal4 = presentIntVal4 - 1;
                    txtCountN.setText(String.valueOf(presentIntVal4));//}
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    public boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}

