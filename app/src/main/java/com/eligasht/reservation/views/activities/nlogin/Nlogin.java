package com.eligasht.reservation.views.activities.nlogin;
import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.views.activities.ShakeActivity;
import com.eligasht.reservation.views.activities.hotel.activity.SelectHotelActivity;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.jaeger.library.StatusBarUtil;

import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza Nejati on 15,May,2018
 */
public class Nlogin extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener {
    RelativeLayout llHome;
    EditText etMobileNumber;
    LinearLayout llLayout;
    TextView tvLogin, tvRegister;
    String mobileNumber, code, token;
    RelativeLayout rlCode;
    LinearLayout rlMobile;
    CardView cvLogin, cvRegister;
    RelativeLayout rlLoading;
    boolean isCode = false;
    LinearLayout llMobileNumber, llCode, llRegister;
    PinEntryEditText codeView;
    ImageView ivImage;
    Button btnLogIn;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nlogin);

        InitUi.Toolbar(this, false, R.color.TRANSPARENT, " ");
        StatusBarUtil.setTranslucent(this, 2);
        initView();
    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    public void initView() {
        llHome = findViewById(R.id.llHome);
        llRegister = findViewById(R.id.llRegister);
        llHome.setVisibility(View.GONE);
        llLayout = (LinearLayout) findViewById(R.id.llLayout);
        btnLogIn = findViewById(R.id.btnLogIn);
        codeView = (PinEntryEditText) findViewById(R.id.codeView);
        llMobileNumber = (LinearLayout) findViewById(R.id.llMobileNumber);
        llCode = (LinearLayout) findViewById(R.id.llCode);
        rlMobile = (LinearLayout) findViewById(R.id.rlMobile);
        etMobileNumber = (EditText) findViewById(R.id.input_mobile);
        cvLogin = (CardView) findViewById(R.id.cvLogin);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        rlLoading = (RelativeLayout) findViewById(R.id.rlLoading);
        btnLogIn.setOnClickListener(this);
        etMobileNumber.clearFocus();
        etMobileNumber.setOnEditorActionListener(this);
        codeView.setOnEditorActionListener(this);
        llLayout.requestFocus();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onClick(View v) {
        code = codeView.getText().toString();
        if (type == 2) {
            startActivity(new Intent(Nlogin.this, ShakeActivity.class));
            finish();
        }
        if (type == 1) {
            type = 2;
            code = codeView.getText().toString();
            if (code == null || code.length() != 6) {
                Toast.makeText(this, "لطفا کد را وارد نمایید", Toast.LENGTH_SHORT).show();
            } else {
                llMobileNumber.setVisibility(View.GONE);
                llCode.setVisibility(View.VISIBLE);
                btnLogIn.setText("ورود");
                YoYo.with(Techniques.SlideOutLeft)
                        .duration(300).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        llRegister.setVisibility(View.VISIBLE);
                        llCode.setVisibility(View.GONE);
                        YoYo.with(Techniques.SlideInRight)
                                .duration(300)
                                .playOn(llRegister);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                })
                        .playOn(llCode);
            }
        }
        if (type == 0) {
            type = 1;
            btnLogIn.setText("ارسال کد");
            mobileNumber = "09" + etMobileNumber.getText().toString();
            YoYo.with(Techniques.SlideOutLeft)
                    .duration(300).withListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    llCode.setVisibility(View.VISIBLE);
                    llMobileNumber.setVisibility(View.GONE);
                    YoYo.with(Techniques.SlideInRight)
                            .duration(300)
                            .playOn(llCode);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            })
                    .playOn(llMobileNumber);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}
