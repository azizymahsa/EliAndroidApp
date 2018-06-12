package com.eligasht.reservation.lost;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.rtp.RtpStream;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.api.app.GetPreFactor;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.base.SingletonAnalysis;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.getprefactor.call.RequestPre;
import com.eligasht.reservation.models.hotel.getprefactor.call.RequestPrefactor;
import com.eligasht.reservation.models.hotel.getprefactor.response.BookingActionsList;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.AfterPaymentAdapter;
import com.eligasht.reservation.views.adapters.AfterPaymentModel;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.ticker.TickerView;
import com.eligasht.reservation.views.ui.InitUi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import mehdi.sakout.fancybuttons.FancyButton;

public class FinalResult extends BaseActivity {
    TextView tvFactor, tvFactor2, tvPaymen, tvPeygiri, tvStatusFactor, tvSuccses, tvNumberPeygiri, tvMail, tvAlert;
    TickerView  tvPrice;
    GetPreFactor getPreFactor;
    RelativeLayout rlPrice, rlPeygiri, rlStatus, rlIv, rlLoading;
    ImageView ivImage;
    GradientDrawable drawable;
    Button btnRPayment;
    NonScrollListView lvLog;
    AfterPaymentAdapter afterPaymentAdapter;
    String url;
    CardView cvStatus, cv2, cv1;
    LinearLayout llButton;
    LinearLayout llBottom, llSort, llFilter;
    FancyButton btnOk, btnBack, btnHome;
    RelativeLayout elNotFound;
    private String factorId;
    private ArrayList<AfterPaymentModel> afterPaymentModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        init_view();
        Utility.setAnimLoading(this);
     //   SingletonAnalysis.getInstance().logBookingCompleted();

        try {
            InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.approve_factor));


            if (getIntent().getAction().equals(Intent.ACTION_VIEW)) {
                final List<String> segments = getIntent().getData().getPathSegments();
                if (segments.size() > 0) {
                    factorId = segments.get(0);
                    tvFactor.setText(factorId + "");
                    tvFactor2.setText(factorId + "");


                }
            }
        } catch (Exception e) {
        }
        new GetRoomsAsync().execute();
    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    public void init_view() {

        tvFactor = findViewById(R.id.tvFactor);
        tvFactor2 = findViewById(R.id.tvFactor2);
        tvPaymen = findViewById(R.id.tvPaymen);
        rlPrice = findViewById(R.id.rlPrice);
        tvPrice = findViewById(R.id.tvPrice);
        llButton = findViewById(R.id.llButton);
        rlPeygiri = findViewById(R.id.rlPeygiri);
        rlStatus = findViewById(R.id.rlStatus);
        tvStatusFactor = findViewById(R.id.tvStatusFactor);
        btnOk = findViewById(R.id.btnOk);
        elNotFound = findViewById(R.id.elNotFound);
        tvPeygiri = findViewById(R.id.tvPeygiri);
        rlIv = findViewById(R.id.rlIv);
        tvAlert = findViewById(R.id.tvAlert);
        btnRPayment = findViewById(R.id.btnRPayment);
        rlLoading = findViewById(R.id.rlLoading);
        ivImage = findViewById(R.id.ivImage);
        lvLog = findViewById(R.id.lvLog);
        tvSuccses = findViewById(R.id.tvSuccses);
        tvMail = findViewById(R.id.tvMail);
        tvNumberPeygiri = findViewById(R.id.tvNumberPeygiri);
        cvStatus = findViewById(R.id.cvStatus);
        cv2 = findViewById(R.id.cv2);
        cv1 = findViewById(R.id.cv1);
        drawable = (GradientDrawable) rlStatus.getBackground();

        btnRPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.openUrlCustomTab(FinalResult.this, url);
                finish();

            }
        });

    }


    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            elNotFound.setVisibility(View.GONE);
            rlLoading.setVisibility(View.VISIBLE);


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getPreFactor = new GetPreFactor(new RequestPrefactor(new RequestPre(getString(R.string.culture), Prefs.getString("TypeGetPre", ""), factorId + "", new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"))));
                Log.e("okokokok", new Gson().toJson(new RequestPrefactor(new RequestPre(getString(R.string.culture), Prefs.getString("TypeGetPre", ""), factorId + "", new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile")))));
            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);
            rlLoading.setVisibility(View.GONE);
            elNotFound.setVisibility(View.GONE);


            try {
                if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.errors != null) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText(getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.errors.get(0).getDetailedMessage());

                } else {

                    if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.ContractNo <= 0) {
                        rlStatus.setVisibility(View.VISIBLE);
                        rlIv.setVisibility(View.VISIBLE);
                        rlIv.setBackgroundColor(ContextCompat.getColor(FinalResult.this, R.color.red));
                        ivImage.setImageResource(R.drawable.close);
                        tvStatusFactor.setText(R.string.contract_didnt_registred);
                        drawable.setStroke(1, ContextCompat.getColor(FinalResult.this, R.color.red));
                        rlPeygiri.setVisibility(View.GONE);
                        tvPaymen.setText(R.string.no_money_has_been_payed_for_this_cart);
                        rlPrice.setVisibility(View.GONE);
                        tvPaymen.setTextColor(ContextCompat.getColor(FinalResult.this, R.color.red));
                        tvFactor2.setVisibility(View.GONE);
                        cvStatus.setVisibility(View.VISIBLE);
                        cv2.setVisibility(View.VISIBLE);
                        cv1.setVisibility(View.VISIBLE);
                        tvMail.setVisibility(View.GONE);


                        YoYo.with(Techniques.SlideInLeft)
                                .duration(800)
                                .playOn(cvStatus);
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(800)
                                .playOn(cv2);
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(800)
                                .playOn(cv1);
                    }


                    if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.ContractNo <= 0
                            && getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.isEmpty() &&
                            getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.isEmpty()) {
                        llButton.setVisibility(View.VISIBLE);
                        btnRPayment.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.SlideInUp)
                                .duration(1000)
                                .playOn(btnRPayment);
                        url = getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.OnlinePaymentURL;

                    }


                    if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.ContractNo <= 0 &&
                            !getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.isEmpty()) {
                        lvLog.setVisibility(View.VISIBLE);
                        for (BookingActionsList preFactorBookingLogs : getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.get(0).BookingActionsList) {

                            afterPaymentModels.add(new AfterPaymentModel(preFactorBookingLogs.ActionStep, preFactorBookingLogs.MsgTextFa));


                        }
                        lvLog.setAdapter(new AfterPaymentAdapter(FinalResult.this, afterPaymentModels));
                    }
                    try {
                        if (!getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.get(0).SuccessBooking) {

                            tvSuccses.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                    }


                    if (!getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.isEmpty()) {
                        rlPeygiri.setVisibility(View.VISIBLE);
                        rlPrice.setVisibility(View.VISIBLE);
                        tvPrice.setText(Utility.priceFormat(getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.get(0).PaymentAmount));
                        tvNumberPeygiri.setText(getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.get(0).PaymentSaleReferenceId);
                        ivImage.setImageResource(R.drawable.white_check);
                        tvMail.setVisibility(View.VISIBLE);
                        tvMail.setText(getString(R.string.needed_docs_to_mail) + getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPartner.get(0).RqPartner_Email + getString(R.string.has_been_sent));
                        GradientDrawable drawable = (GradientDrawable) rlStatus.getBackground();
                        drawable.setStroke(4, ContextCompat.getColor(FinalResult.this, R.color.green));
                        rlIv.setBackgroundColor(ContextCompat.getColor(FinalResult.this, R.color.green));
                        tvStatusFactor.setTextColor(ContextCompat.getColor(FinalResult.this, R.color.green));
                        tvPaymen.setTextColor(ContextCompat.getColor(FinalResult.this, R.color.green));
                        tvPaymen.setText(R.string.contract_has_been_registred_successfully);
                        tvPaymen.setVisibility(View.GONE);
                        tvStatusFactor.setText(getString(R.string.your_contract_with_number) + getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.ContractNo + getString(R.string.registred));

                        cv2.setVisibility(View.VISIBLE);
                        cv1.setVisibility(View.VISIBLE);
                        cvStatus.setVisibility(View.VISIBLE);

                        YoYo.with(Techniques.SlideInLeft)
                                .duration(800)
                                .playOn(cvStatus);
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(800)
                                .playOn(cv2);
                        YoYo.with(Techniques.SlideInLeft)
                                .duration(800)
                                .playOn(cv1);

                    }


                }


                //  setListViewHeightBasedOnChildren(lvRooms);
            } catch (Exception e) {
                llFilter.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                if (!Utility.isNetworkAvailable(FinalResult.this)) {

                    tvAlert.setText(R.string.your_internet_connection_is_lost);

                } else {

                    tvAlert.setText(getString(R.string.error_in_connection));

                }

            }
        }

    }


}