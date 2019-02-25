package com.eligasht.reservation.views.activities.hotel.activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.HotelCity;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.db.local.RecentCityHotel_Table;
import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.views.adapters.GetHotelCityAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.getHotelList.request.GetHListRequest;
import com.eligasht.service.model.hotel.getHotelList.request.GetHotelListRequest;
import com.eligasht.service.model.hotel.getHotelList.response.City;
import com.eligasht.service.model.hotel.getHotelList.response.GetHotelListResponse;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.hotelpolicy.request.HotelPolicyRequest;
import com.eligasht.service.model.identity.Identity;
import com.eligasht.service.model.newModel.airport.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.airport.response.ResponseAirport;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import mehdi.sakout.fancybuttons.FancyButton;


public class GetHotelCityActivity extends BaseActivity implements  OnClickListener,OnServiceStatus<List<ResponseAirport>> {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public ListView listCityHotel;
    ArrayList<HashMap<String, String>> mylist = null;
    FancyButton btnBack,btnMic;

    GetHotelCityAdapter mAdapter;
    private EditText searchtxt;
    AVLoadingIndicatorView avLoadingIndicatorView;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    ListView listAirPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_city_hotel);

        SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
        helper.setEdgeMode(false)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);

        avLoadingIndicatorView = findViewById(R.id.avi);
        btnBack = findViewById(R.id.btnBack);
        btnMic = findViewById(R.id.btnMic);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnMic.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnMic.setText(getString(R.string.icon_mic));
        btnBack.setOnClickListener(this);
        btnMic.setOnClickListener(this);

        //////////////////show recent
        ListView listAirPort = findViewById(R.id.listCityHotel);
        List<HotelCity> data = new ArrayList<>();
        RecentCityHotel_Table recentCity_table = new RecentCityHotel_Table(this);
        CursorManager cursorManager = recentCity_table.getAll();
        if (cursorManager != null) {
            for (int i = 0; i < cursorManager.getCount(); i++) {
                cursorManager.moveToPosition(i);
                HotelCity hotelCity = new HotelCity();

                hotelCity.setCityCode(cursorManager.getString(RecentCityHotel_Table.Columns.CityCode.value()));
                hotelCity.setCityID(cursorManager.getInt(RecentCityHotel_Table.Columns.CityCode.value()));
                hotelCity.setCityNameEn(cursorManager.getString(RecentCityHotel_Table.Columns.CityNameEn.value()));
                hotelCity.setCityNameFa(cursorManager.getString(RecentCityHotel_Table.Columns.CityNameFa.value()));
                hotelCity.setCountryID(cursorManager.getInt(RecentCityHotel_Table.Columns.CityCode.value()));

                data.add(hotelCity);
            }
        }
        mAdapter = new GetHotelCityAdapter(GetHotelCityActivity.this, data, GetHotelCityActivity.this);
        mAdapter.setData(data);
        listAirPort.setAdapter(mAdapter);

        //////////////////////////

        searchtxt = findViewById(R.id.searchtxt);
        searchtxt.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    private Timer timer = new Timer();
                    private final long DELAY = 5;

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String d = s.toString().trim();
                                                if (d.length() > 1) {
                                                    request(searchtxt.getText().toString());

                                                } else {
                                                    if (d.length() < 0 || d.length() == 0) {
                                                        List<HotelCity> data = null;
                                                        ListView listAirPort = findViewById(R.id.listCityHotel);
                                                        mAdapter = new GetHotelCityAdapter(GetHotelCityActivity.this, data, GetHotelCityActivity.this);
                                                        mAdapter.setData(data);
                                                        listAirPort.setAdapter(mAdapter);
                                                    }
                                                }
                                            }
                                        });
                                    }
                                },
                                DELAY
                        );
                    }
                }
        );
    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onReady(List<ResponseAirport> getHotelListResponse) {

        avLoadingIndicatorView.setVisibility(View.INVISIBLE);
        List<HotelCity> data = new ArrayList<HotelCity>();
        try {
            Log.e("responseHotelCity: ", new Gson().toJson(getHotelListResponse));

            if (!TextUtils.isEmpty(searchtxt.getText())) {
              //  for (City city : getHotelListResponse.getCities() ) {
                 for (int i = 0; i <getHotelListResponse.size() ; i++) {

                    HotelCity hotelCity = new HotelCity();
                    hotelCity.setCityCode(getHotelListResponse.get(i).getCityCode());
                   // hotelCity.setCityID(getHotelListResponse.get(i).geteValue());//t//getCityID());
                    hotelCity.setCityNameEn(getHotelListResponse.get(i).getText());
                    hotelCity.setCityNameFa(getHotelListResponse.get(i).getTextFa());
                    //hotelCity.setCountryID(getHotelListResponse.get(i).getEValue());

                    data.add(hotelCity);
                }

                listAirPort = findViewById(R.id.listCityHotel);
                mAdapter = new GetHotelCityAdapter(GetHotelCityActivity.this, GetHotelCityActivity.this, data, getIntent().getExtras().getInt("type"));
                mAdapter.setData(data);
                listAirPort.setAdapter(mAdapter);
            }
        } catch (Exception e) {
            Toast.makeText(GetHotelCityActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onError(String message) {
        avLoadingIndicatorView.setVisibility(View.INVISIBLE);
        Toast.makeText(GetHotelCityActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_LONG).show();

    }




    public void request(String text){
        avLoadingIndicatorView.setVisibility(View.VISIBLE);

        AutoCompleteParameterModel hotelListRequest = new AutoCompleteParameterModel();
        hotelListRequest.setPart(text.toLowerCase());

        SingletonService.getInstance().getHotelService().newHotelCitiesAvail(this, hotelListRequest);
        Log.e("request: ", new Gson().toJson(hotelListRequest));

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnMic:
                searchtxt.setText("");
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Prefs.getString("lang","fa"));
                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(), "Error",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    searchtxt.setText(result.get(0));
                }
                break;
            }

        }
    }

}