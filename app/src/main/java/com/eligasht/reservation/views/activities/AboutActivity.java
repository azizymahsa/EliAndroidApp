package com.eligasht.reservation.views.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.SectionModel;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.AboutAdapter;
import com.eligasht.reservation.views.ticker.TickerView;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.about.request.RequestAbout;
import com.eligasht.service.model.about.response.GetAboutUsWithCultureResult;
import com.eligasht.service.model.about.response.ResponseAbout;
import com.eligasht.service.model.about.response.Section;
import com.eligasht.service.model.flight.request.contactUs.RequestContactUs;
import com.eligasht.service.model.flight.response.contactUs.ResponseContactUs;
import com.eligasht.service.part.AboutService;
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

import mehdi.sakout.fancybuttons.FancyButton;


public class AboutActivity extends BaseActivity implements View.OnClickListener , OnServiceStatus<ResponseAbout> {


    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    AboutAdapter mAdapter;
    TickerView v1, v2, v3;
    ImageView hotel;
    private FancyButton btnBack;
    private ProgressDialog pdLoading;
    RelativeLayout rlLoading,rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_new);

        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));

        v1 = findViewById(R.id.textView2);
        v2 = findViewById(R.id.textView8);
        v3 = findViewById(R.id.textView5);
        hotel = findViewById(R.id.hotel);
        YoYo.with(Techniques.Pulse).repeat(500)
                .duration(8000)
                .playOn(hotel);

        SendRequestAbout();

    }
    private void SendRequestAbout() {
        try {
            //this method will be running on UI thread
            //this method will be running on UI thread
            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        RequestAbout requestAbout = new RequestAbout();
        com.eligasht.service.model.about.request.RequestAbout request = new com.eligasht.service.model.about.request.RequestAbout();

        request.setCulture(getString(R.string.culture));

        SingletonService.getInstance().getAboutService().aboutAvail(this,request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }

    @Override
    public void onReady(ResponseAbout responseAbout) {
        try {
            rlLoading.setVisibility(View.GONE);
            Utility.disableEnableControls(true,rlRoot);

             NonScrollRecyclerView nonScrollList;
            //this method will be running on UI thread
            v1.setAnimationDuration(1000);
            v2.setAnimationDuration(1000);
            v3.setAnimationDuration(1000);
            v1.setText("1200", true);
            v2.setText("900", true);
            v3.setText("20000", true);
            List<SectionModel> data = new ArrayList<SectionModel>();




                GetAboutUsWithCultureResult GetAirportsResult = responseAbout.getGetAboutUsWithCultureResult();
                List<Section> jArray = GetAirportsResult.getSections();

                // Extract data from json and store into ArrayList as class objects
                for (int i = 0; i < jArray.size(); i++) {
                    Section json_data = jArray.get(i);
                    SectionModel sectionModel = new SectionModel();
                    sectionModel.setDescription(json_data.getDescription());
                    sectionModel.setSectionName(json_data.getSectionName());
                    sectionModel.setImageAddress(json_data.getImageAddress());

                    data.add(sectionModel);
                }


                nonScrollList = findViewById(R.id.lvExp);
                nonScrollList.addItemDecoration(new DividerItemDecoration(AboutActivity.this, 1));
                nonScrollList.setLayoutManager(new LinearLayoutManager(AboutActivity.this));
                mAdapter = new AboutAdapter(data);

                nonScrollList.setAdapter(mAdapter);
                nonScrollList.setClickable(false);
                nonScrollList.setEnabled(false);
                nonScrollList.setScrollContainer(false);

                nonScrollList.setOnFlingListener(new RecyclerView.OnFlingListener() {
                    @Override
                    public boolean onFling(int velocityX, int velocityY) {
                        nonScrollList.dispatchNestedFling(velocityX, velocityY, false);
                        return false;
                    }
                });
            } catch (Exception e) {
                Toast.makeText(AboutActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
            }

    }

    @Override
    public void onError(String message) {
        rlLoading.setVisibility(View.GONE);
        Utility.disableEnableControls(true,rlRoot);
        Toast.makeText(AboutActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();

    }





}
