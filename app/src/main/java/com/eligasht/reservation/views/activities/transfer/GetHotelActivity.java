package com.eligasht.reservation.views.activities.transfer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.eligasht.R;
import com.eligasht.reservation.api.hotel.hotelName.HotelNameApi;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.api.hotelName.request.HotelNameRequest;
import com.eligasht.reservation.models.hotel.api.hotelName.request.HotelNameRequestModel;
import com.eligasht.reservation.models.hotel.api.hotelName.response.Hotels;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;

import mehdi.sakout.fancybuttons.FancyButton;

public class GetHotelActivity extends BaseActivity {
    FancyButton btnBack;
    ListView listAirPort;
    EditText searchtxt;
    HotelNameApi hotelNameApi;
    ArrayList<HotelCityModel> hotelCityModels = new ArrayList<>();
    GetCityHotelAdapter getCityHotelAdapter;
    String search;
    AVLoadingIndicatorView avLoadingIndicatorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_hotel);
        initViews();


    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    public void initViews() {
        btnBack = findViewById(R.id.btnBack);
        listAirPort = findViewById(R.id.listAirPort);
        searchtxt = findViewById(R.id.searchtxt);
        avLoadingIndicatorView = findViewById(R.id.avi);

        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        Collections.reverse(hotelCityModels);

        getCityHotelAdapter = new GetCityHotelAdapter(GetHotelActivity.this, hotelCityModels);
        listAirPort.setAdapter(getCityHotelAdapter);
        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String d = s.toString().trim();
                if (d.length() > 1) {
                    search = d;

                    new GetNameAsync().execute();

                }

            }
        });


    }

    private class GetNameAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            avLoadingIndicatorView.setVisibility(View.VISIBLE);
            Log.e("test", new Gson().toJson(new HotelNameRequest(new HotelNameRequestModel(search, getString(R.string.culture), new Identity("EligashtMlb",
                    "123qwe!@#QWE", "Mobile")))));
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                hotelNameApi = new HotelNameApi(new HotelNameRequest(new HotelNameRequestModel(search, getString(R.string.culture), new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            avLoadingIndicatorView.setVisibility(View.GONE);


            try {
                for (Hotels hotels : hotelNameApi.hotelAjaxResult.GetHotelAjaxResult.Hotels) {
                    hotelCityModels.add(new HotelCityModel(hotels.HotelID, hotels.HotelNameEn, hotels.HotelNameFa));
                    Log.e("hotelid", hotels.HotelID);

                }
                getCityHotelAdapter.notifyDataSetChanged();

            } catch (Exception e) {

            }


           /*

        }*/

        }
    }
}
