package com.reserv.myapplicationeli.lost;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.app.GetPreFactor;
import com.reserv.myapplicationeli.api.hotel.room.GetRoomsList;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.RoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.response.RoomList;
import com.reserv.myapplicationeli.models.hotel.getprefactor.call.RequestPre;
import com.reserv.myapplicationeli.models.hotel.getprefactor.call.RequestPrefactor;
import com.reserv.myapplicationeli.models.hotel.getprefactor.response.BookingActionsList;
import com.reserv.myapplicationeli.models.hotel.getprefactor.response.PreFactorBookingLogs;
import com.reserv.myapplicationeli.tools.Prefs;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.activities.hotel.activity.DetailHotelActivity;
import com.reserv.myapplicationeli.views.activities.insurance.PassengerInsuranceActivity;
import com.reserv.myapplicationeli.views.adapters.AfterPaymentAdapter;
import com.reserv.myapplicationeli.views.adapters.AfterPaymentModel;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsModel;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

import java.util.ArrayList;
import java.util.List;

public class FinalResult extends BaseActivity {
    private String factorId;
    TextView tvFactor, tvFactor2, tvPaymen, tvPrice, tvPeygiri, tvStatusFactor,tvSuccses,tvNumberPeygiri;
    GetPreFactor getPreFactor;
    RelativeLayout rlPrice, rlPeygiri, rlStatus, rlIv, rlLoading;
    ImageView ivImage;
    GradientDrawable drawable;
    Button btnRPayment;
    NonScrollListView lvLog;
    AfterPaymentAdapter afterPaymentAdapter;
    private ArrayList<AfterPaymentModel> afterPaymentModels = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        init_view();

        try {
            InitUi.Toolbar(this, false, R.color.toolbar_color, "تایید نهایی فاکتور");


            if (getIntent().getAction().equals(Intent.ACTION_VIEW)) {
                final List<String> segments = getIntent().getData().getPathSegments();
                if (segments.size() > 0) {
                    factorId = segments.get(0);
                    tvFactor.setText(factorId + "");
                    tvFactor2.setText(factorId + "");


                    new GetRoomsAsync().execute();
                }
            }
        } catch (Exception e) {
        }
    }


    public void init_view() {

        tvFactor = findViewById(R.id.tvFactor);
        tvFactor2 = findViewById(R.id.tvFactor2);
        tvPaymen = findViewById(R.id.tvPaymen);
        rlPrice = findViewById(R.id.rlPrice);
        tvPrice = findViewById(R.id.tvPrice);
        rlPeygiri = findViewById(R.id.rlPeygiri);
        rlStatus = findViewById(R.id.rlStatus);
        tvStatusFactor = findViewById(R.id.tvStatusFactor);
        tvPeygiri = findViewById(R.id.tvPeygiri);
        rlIv = findViewById(R.id.rlIv);
        btnRPayment = findViewById(R.id.btnRPayment);
        rlLoading = findViewById(R.id.rlLoading);
        ivImage = findViewById(R.id.ivImage);
        lvLog = findViewById(R.id.lvLog);
        tvSuccses = findViewById(R.id.tvSuccses);
        tvNumberPeygiri = findViewById(R.id.tvNumberPeygiri);
        drawable = (GradientDrawable) rlStatus.getBackground();

        btnRPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.openUrlCustomTab(FinalResult.this, Prefs.getString("PaymentUrl", ""));
                finish();

            }
        });

    }


    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            rlLoading.setVisibility(View.VISIBLE);


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getPreFactor = new GetPreFactor(new RequestPrefactor(new RequestPre("fa-IR", Prefs.getString("TypeGetPre", ""), factorId + "", new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"))));
                Log.e("okokokok", new Gson().toJson(new RequestPrefactor(new RequestPre("fa-IR", Prefs.getString("TypeGetPre", ""), factorId + "", new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile")))));
            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);
            rlLoading.setVisibility(View.GONE);


            try {

                if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.errors != null) {
                    Toast.makeText(FinalResult.this, getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.errors.get(0).getDetailedMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.ContractNo <= 0) {
                        rlStatus.setVisibility(View.VISIBLE);
                        rlIv.setVisibility(View.VISIBLE);
                        rlIv.setBackgroundColor(ContextCompat.getColor(FinalResult.this, R.color.red));
                        ivImage.setImageResource(R.drawable.close);
                        tvStatusFactor.setText("قرار داد ثبت نشد");
                        drawable.setStroke(3, ContextCompat.getColor(FinalResult.this, R.color.red));
                        rlPeygiri.setVisibility(View.GONE);
                        tvPaymen.setText("مبلغی برای این سبد خرید پرداخت نشده است");
                        tvPaymen.setTextColor(ContextCompat.getColor(FinalResult.this, R.color.red));
                        tvFactor2.setVisibility(View.GONE);
                    }
                    if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.ContractNo <= 0
                            && getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.isEmpty() &&
                            getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.isEmpty()) {
                        btnRPayment.setVisibility(View.VISIBLE);

                    }



                    if (getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.FactorSummary.ContractNo <= 0 &&
                            !getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.isEmpty()){
                        lvLog.setVisibility(View.VISIBLE);
                        for (BookingActionsList preFactorBookingLogs: getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.get(0).BookingActionsList){

                            afterPaymentModels.add(new AfterPaymentModel(preFactorBookingLogs.ActionStep,preFactorBookingLogs.MsgTextFa));


                        }
                        lvLog.setAdapter(new AfterPaymentAdapter(FinalResult.this,afterPaymentModels));
                    }
                    if(!getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.PreFactorBookingLogs.get(0).SuccessBooking){

                        tvSuccses.setVisibility(View.VISIBLE);
                    }
                    if (!getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.isEmpty()){
                        rlPeygiri.setVisibility(View.VISIBLE);
                        rlPrice.setVisibility(View.VISIBLE);
                        tvPrice.setText(getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.get(0).PaymentAmount);
                        tvNumberPeygiri.setText(getPreFactor.getPrefactorResponse.GetPreFactorDetailsResult.PreFactor.RequestPayment.get(0).PaymentSaleReferenceId);

                    }





                }


                //  setListViewHeightBasedOnChildren(lvRooms);
            } catch (Exception e) {

            }

        }

    }

}