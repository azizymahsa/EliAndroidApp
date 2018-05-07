package com.eligasht.reservation.views.activities.hotel.activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.api.hotel.comment.AddComment;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.addcomment.call.ReviewComment;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.dialog.hotel.AddCommnetDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertRating;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.addReview.request.AddHReviewReq;
import com.eligasht.service.model.hotel.addReview.request.AddHotelReviewRequest;
import com.eligasht.service.model.hotel.addReview.request.HotelReviewModel;
import com.eligasht.service.model.hotel.addReview.request.Review;
import com.eligasht.service.model.hotel.addReview.request.ReviewScore;
import com.eligasht.service.model.hotel.addReview.response.AddHotelReviewResponse;
import com.eligasht.service.model.identity.Identity;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.eligasht.reservation.tools.Prefs;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
public class CommentActivity extends BaseActivity implements AlertRating.RatingHotelDialogListener,
        View.OnClickListener,OnServiceStatus<AddHotelReviewResponse> {
    TextView tvTitle;
    ScrollView svRating;
    LinearLayout llComment;
    FancyButton btnToComment, btnConfirm, btnBack;
    AddComment addComment;
    EditText etName, etMail, etTitle, etMessage;
    String name, mail, title, message;
    RelativeLayout rlRoot, rlLoading;
    Float star;
    String hotelId;
    List<ReviewScore> reviewScores = new ArrayList<>();
    ArrayList<ReviewComment> ReviewComment = new ArrayList<>();
    List<Review> reviews = new ArrayList<>();
    BubbleSeekBar ScoreParameterID1, ScoreParameterID2, ScoreParameterID3, ScoreParameterID4, ScoreParameterID5, ScoreParameterID6, ScoreParameterID7, ScoreParameterID8;
    cn.refactor.library.SmoothCheckBox cbSubmitName, cbIsRecommended;
    boolean isRecommended = false;
    AddCommnetDialog addCommnetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.PostComment));
        init_view();
        new AlertRating(this, this, star);
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
        cbSubmitName = findViewById(R.id.cbSubmitName);
        cbIsRecommended = findViewById(R.id.cbIsRecommended);
        btnToComment = findViewById(R.id.btnToComment);
        rlRoot = findViewById(R.id.rlRoot);
        btnBack = findViewById(R.id.btnBack);
        ScoreParameterID1 = findViewById(R.id.ScoreParameterID1);
        ScoreParameterID2 = findViewById(R.id.ScoreParameterID2);
        ScoreParameterID3 = findViewById(R.id.ScoreParameterID3);
        ScoreParameterID4 = findViewById(R.id.ScoreParameterID4);
        ScoreParameterID5 = findViewById(R.id.ScoreParameterID5);
        ScoreParameterID6 = findViewById(R.id.ScoreParameterID6);
        ScoreParameterID7 = findViewById(R.id.ScoreParameterID7);
        ScoreParameterID8 = findViewById(R.id.ScoreParameterID8);
        rlLoading = findViewById(R.id.rlLoading);
        btnToComment.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        rlLoading.setOnClickListener(this);
        btnToComment.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnConfirm.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tvTitle.setText(getString(R.string.CommentFor) + " " + extras.getString("HotelName"));
            hotelId = extras.getString("HotelId");
            Log.e("UserID", Prefs.getString("uesrId", "-1"));
        }
        if (!Prefs.getString("userId", "-1").equals("-1")) {
            etMail.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
            etMail.setEnabled(false);
            etName.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF() + " " + WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
            etName.setEnabled(false);
        }
        addCommnetDialog = new AddCommnetDialog(CommentActivity.this);
    }

    @Override
    public void onReturnValue(int type, Float rate) {
        this.star = rate;
        if (type == 1) {
            Log.e("rateTest", rate + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToComment:
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID1.getProgress()), "0", "1", "0"));
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID2.getProgress()), "0", "2", "0"));
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID3.getProgress()), "0", "3", "0"));
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID4.getProgress()), "0", "4", "0"));
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID5.getProgress()), "0", "5", "0"));
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID6.getProgress()), "0", "6", "0"));
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID7.getProgress()), "0", "7", "0"));
                reviewScores.add(new ReviewScore(hotelId, "0", String.valueOf(ScoreParameterID8.getProgress()), "0", "8", "0"));
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
                String errorMessage = "";
                boolean isOk = true;
                if (TextUtils.isEmpty(etName.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etName.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                    errorMessage = errorMessage + "\n" + getString(R.string.Please_enter_the_correct_name);
                } else {
                    GradientDrawable drawable = (GradientDrawable) etName.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));
                }
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!etMail.getText().toString().matches(emailPattern) || TextUtils.isEmpty(etMail.getText())) {
                    //if( Patterns.EMAIL_ADDRESS.matcher(text).matches() ){
                    GradientDrawable drawable = (GradientDrawable) etMail.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                    errorMessage = errorMessage + "\n" + getString(R.string.Email_format_is_correct);
                } else {
                    //((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
                    GradientDrawable drawable = (GradientDrawable) etMail.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));
                }
                if (TextUtils.isEmpty(etTitle.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etTitle.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                    errorMessage = errorMessage + "\n" + getString(R.string.Please_enter_the_title_correctly);
                } else {
                    GradientDrawable drawable = (GradientDrawable) etTitle.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));
                }
                if (TextUtils.isEmpty(etMessage.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etMessage.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                    isOk = false;
                    errorMessage = errorMessage + "\n" + getString(R.string.Please_enter_the_correct_message);
                } else {
                    GradientDrawable drawable = (GradientDrawable) etMessage.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.strokeGray));
                }
                if (isOk) {
                    name = etName.getText().toString();
                    mail = etMail.getText().toString();
                    title = etTitle.getText().toString();
                    message = etMessage.getText().toString();
                    if (cbIsRecommended.isChecked()) {
                        isRecommended = true;
                    }
                    if (cbSubmitName.isChecked()) {
                        name = "";
                    }
                    Review review = new Review();
                    review.setAgcUserID(0);
                    review.setContent(message);
                    review.setIsRecommended(String.valueOf(isRecommended));
                    review.setReviewCommentID(0);
                    review.setReviewID(1);
                    review.setReviewScores(reviewScores);
                    review.setSubmitEmail(mail);
                    review.setSubmitName(name);
                    review.setTitle(title);
                    review.setWebUserID(Prefs.getString("userId", "-1"));
                    reviews.add(review);
                    request();
                } else {
                    AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(CommentActivity.this,false,false);
                    alertDialogPassenger.setText("" + "  " + errorMessage, getString(R.string.massege));
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


    public void request(){
        AddHotelReviewRequest addHotelReviewRequest = new AddHotelReviewRequest();

        AddHReviewReq addHReviewReq = new AddHReviewReq();
        HotelReviewModel hotelReviewModel = new HotelReviewModel();

        hotelReviewModel.setAverageScore(String.valueOf(star));
        hotelReviewModel.setHotelID(hotelId);
        hotelReviewModel.setRecommendedPercent("0");
        hotelReviewModel.setReviews(reviews);

        Identity identity = new Identity();
        identity.setPassword("123qwe!@#QWE");
        identity.setUserName("EligashtMlb");
        identity.setTermianlId("Mobile");

        addHReviewReq.setCulture(getString(R.string.culture));
        addHReviewReq.setHotelReviewModel(hotelReviewModel);
        addHReviewReq.setIdentity(identity);

        addHotelReviewRequest.setRequest(addHReviewReq);
        SingletonService.getInstance().getHotelService().addHotelReview(this, addHotelReviewRequest);

    }

    @Override
    public void onReady(AddHotelReviewResponse addHotelReviewResponse) {
        rlLoading.setVisibility(View.GONE);
        try {
            if (addHotelReviewResponse.getAddHotelReviewResult().getErrors() != null) {
                addCommnetDialog.setTitle(addHotelReviewResponse.getAddHotelReviewResult().getErrors().get(0).getDetailedMessage(), false);
            } else {
                addCommnetDialog.setTitle(addHotelReviewResponse.getAddHotelReviewResult().getResultText(), true);
            }
        } catch (Exception e) {
            if (!Utility.isNetworkAvailable(CommentActivity.this)) {
                addCommnetDialog.setTitle(getString(R.string.InternetError), false);
            } else {
                addCommnetDialog.setTitle(getString(R.string.ErrorServer), false);
            }
        }
    }
    @Override
    public void onError(String message) {
        rlLoading.setVisibility(View.GONE);

        if (!Utility.isNetworkAvailable(CommentActivity.this)) {
            addCommnetDialog.setTitle(getString(R.string.InternetError), false);
        } else {
            addCommnetDialog.setTitle(getString(R.string.ErrorServer), false);
        }
    }

}
