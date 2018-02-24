package com.eligasht.reservation.views.activities.hotel.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.api.hotel.comment.AddComment;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.addcomment.call.HotelReviewModel;
import com.eligasht.reservation.models.hotel.api.addcomment.call.RequestAdd;
import com.eligasht.reservation.models.hotel.api.addcomment.call.RequsetAddComment;
import com.eligasht.reservation.models.hotel.api.addcomment.call.ReviewComment;
import com.eligasht.reservation.models.hotel.api.addcomment.call.ReviewScores;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.dialog.hotel.AddCommnetDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertRating;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

public class CommentActivity extends BaseActivity implements AlertRating.RatingHotelDialogListener, View.OnClickListener {
    TextView tvTitle;
    ScrollView svRating;
    LinearLayout llComment;
    FancyButton btnToComment, btnConfirm, btnBack;
    AddComment addComment;
    EditText etName, etMail, etTitle, etMessage;
    String name, mail, title, message;
    RelativeLayout rlRoot,rlLoading;
    Float star;
    String hotelId;
    ArrayList<ReviewScores> reviewScores=new ArrayList<>();
    BubbleSeekBar ScoreParameterID1,ScoreParameterID2,ScoreParameterID3,ScoreParameterID4,ScoreParameterID5,ScoreParameterID6,ScoreParameterID7,ScoreParameterID8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "ارسال نظر");
        init_view();
        new AlertRating(this, this,star);
    }

    public void init_view() {
        Utility.setAnimLoading(this);
        tvTitle = findViewById(R.id.tvTitle);
        svRating = findViewById(R.id.svRating);
        llComment = findViewById(R.id.llComment);
        etMessage = findViewById(R.id.etMessage);
        btnConfirm = findViewById(R.id.btnConfirm);
        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etTitle = findViewById(R.id.etTitle);
        btnToComment = findViewById(R.id.btnToComment);
        rlRoot = findViewById(R.id.rlRoot);
        btnBack = findViewById(R.id.btnBack);
        ScoreParameterID1=findViewById(R.id.ScoreParameterID1);
        ScoreParameterID2=findViewById(R.id.ScoreParameterID2);
        ScoreParameterID3=findViewById(R.id.ScoreParameterID3);
        ScoreParameterID4=findViewById(R.id.ScoreParameterID4);
        ScoreParameterID5=findViewById(R.id.ScoreParameterID5);
        ScoreParameterID6=findViewById(R.id.ScoreParameterID6);
        ScoreParameterID7=findViewById(R.id.ScoreParameterID7);
        ScoreParameterID8=findViewById(R.id.ScoreParameterID8);
        rlLoading=findViewById(R.id.rlLoading);
        btnToComment.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        rlLoading.setOnClickListener(this);
        btnToComment.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnConfirm.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tvTitle.setText("ثبت نظر برای " + extras.getString("HotelName"));
            hotelId=extras.getString("hotelId");
        }

        if ( !Prefs.getString("uesrId","-1").equals("-1")){
            etMail.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
            etMail.setEnabled(false);
            etName.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF()+" "+WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
            etName.setEnabled(false);

        }




    }

    @Override
    public void onReturnValue(int type, Float rate) {
        this.star = rate;
        if (type == 1) {
            Log.e("rateTest", rate+"");


        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToComment:
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID1.getProgress()),"0","1","0"));
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID2.getProgress()),"0","2","0"));
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID3.getProgress()),"0","3","0"));
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID4.getProgress()),"0","4","0"));
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID5.getProgress()),"0","5","0"));
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID6.getProgress()),"0","6","0"));
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID7.getProgress()),"0","7","0"));
                reviewScores.add(new ReviewScores(hotelId,"0",String.valueOf(ScoreParameterID8.getProgress()),"0","8","0"));
                YoYo.with(Techniques.FadeOut).duration(200).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


                    @Override
                    public void onAnimationStart(android.animation.Animator animation) {


                    }

                    @Override
                    public void onAnimationEnd(android.animation.Animator animation) {


                        llComment.setVisibility(View.VISIBLE);
                        svRating.setVisibility(View.GONE);
                        YoYo.with(Techniques.FadeIn)
                                .duration(200)
                                .playOn(llComment);


                    }

                    @Override
                    public void onAnimationCancel(android.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(android.animation.Animator animation) {

                    }

                })
                        .playOn(svRating);

                break;
            case R.id.btnConfirm:

                boolean isOk = true;
                if (TextUtils.isEmpty(etName.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etName.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                } else {
                    GradientDrawable drawable = (GradientDrawable) etName.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));


                }

                if (TextUtils.isEmpty(etMail.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etMail.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                } else {
                    GradientDrawable drawable = (GradientDrawable) etMail.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));


                }
                if (TextUtils.isEmpty(etTitle.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etTitle.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                } else {
                    GradientDrawable drawable = (GradientDrawable) etTitle.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));


                }
                if (TextUtils.isEmpty(etMessage.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etMessage.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                } else {
                    GradientDrawable drawable = (GradientDrawable) etMessage.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));


                }


                if (isOk) {
                    name = etName.getText().toString();
                    mail = etMail.getText().toString();
                    title = etTitle.getText().toString();
                    message = etMessage.getText().toString();
                    new AddCommentAsync().execute();
                 //addCommnetDialog = new AddCommnetDialog(DetailHotelActivity.this, this);
                }
                break;
            case R.id.btnBack:

                if (llComment.getVisibility() == View.VISIBLE) {
                    YoYo.with(Techniques.FadeOut).duration(200).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


                        @Override
                        public void onAnimationStart(android.animation.Animator animation) {


                        }

                        @Override
                        public void onAnimationEnd(android.animation.Animator animation) {


                            svRating.setVisibility(View.VISIBLE);
                            llComment.setVisibility(View.GONE);
                            YoYo.with(Techniques.FadeIn)
                                    .duration(200)
                                    .playOn(svRating);


                        }

                        @Override
                        public void onAnimationCancel(android.animation.Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(android.animation.Animator animation) {

                        }

                    })
                            .playOn(llComment);


                } else if (svRating.getVisibility() == View.VISIBLE) {
                    new AlertRating(this, this, star);

                } else {
                    finish();
                }
                break;
        }

    }


    private class AddCommentAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            rlLoading.setVisibility(View.VISIBLE);
            Log.e("requestTest",new Gson().toJson(new RequestAdd(new Identity("EligashtMlb",
                    "123qwe!@#QWE", "Mobile"), "fa-IR", new HotelReviewModel(new ReviewComment(0, message,
                    0, 1, mail, name, title, Prefs.getString("uesrId","-1"),reviewScores),String.valueOf(star),hotelId,"0"))));

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                addComment = new AddComment(new RequsetAddComment(new RequestAdd(new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"), "fa-IR", new HotelReviewModel(new ReviewComment(0, message,
                        0, 1, mail, name, title, Prefs.getString("uesrId","-1"),reviewScores),String.valueOf(star),hotelId,"0"))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            rlLoading.setVisibility(View.GONE);

            try {


                if (addComment.addCommentsResult.AddHotelReviewCommentsResult.errors != null) {
                    //   addCommnetDialog.setTitle(addComment.addCommentsResult.AddHotelReviewCommentsResult.errors.get(0).Message);


                } else {

                    //  addCommnetDialog.setTitle(addComment.addCommentsResult.AddHotelReviewCommentsResult.ResultText);


                }


            } catch (Exception e) {
                // tvAlert.setText("در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ");

            }

        }

    }


    @Override
    public void onBackPressed() {

        if (llComment.getVisibility() == View.VISIBLE) {
            YoYo.with(Techniques.FadeOut).duration(200).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


                @Override
                public void onAnimationStart(android.animation.Animator animation) {


                }

                @Override
                public void onAnimationEnd(android.animation.Animator animation) {


                    svRating.setVisibility(View.VISIBLE);
                    llComment.setVisibility(View.GONE);
                    YoYo.with(Techniques.FadeIn)
                            .duration(200)
                            .playOn(svRating);


                }

                @Override
                public void onAnimationCancel(android.animation.Animator animation) {

                }

                @Override
                public void onAnimationRepeat(android.animation.Animator animation) {

                }

            })
                    .playOn(llComment);


        } else if (svRating.getVisibility() == View.VISIBLE) {
            new AlertRating(this, this, star);

        } else {
            super.onBackPressed();
        }


    }
}
