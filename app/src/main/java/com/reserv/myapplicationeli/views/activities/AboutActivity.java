package com.reserv.myapplicationeli.views.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.gson.Gson;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.Country;
import com.reserv.myapplicationeli.models.hotel.FilterPriceModel;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelTypes;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels;
import com.reserv.myapplicationeli.models.model.SectionModel;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.datetools.SolarCalendar;
import com.reserv.myapplicationeli.views.activities.hotel.activity.DetailHotelActivity;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.adapters.AboutAdapter;
import com.reserv.myapplicationeli.views.adapters.GetAirPortMabdaAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.LazyResoultHotelAdapter;
import com.reserv.myapplicationeli.views.ui.GetAirportMabdaActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.SearchParvazActivity;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.FilterHotelDialog;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.SortDialog;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


public class AboutActivity extends BaseActivity implements View.OnClickListener {


    private FancyButton btnBack;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Handler handler;
    ProgressDialog progressBar;
    private Handler progressBarHandler = new Handler();
    ArrayList<HashMap<String,String>> mylist=null;
    AboutAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_new);

        btnBack = (FancyButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));


        new GetAboutAsync().execute();

/////////////////
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.btnBack), "بازگشت به صفحه قبل", "با کلیک بر روی این دکمه به صفحه قبل بازگردید")
                        // All options below are optional
                        .outerCircleColor(R.color.focusColor)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.focusColor)  // Specify the color of the description text
                        .textColor(R.color.blue)            // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.blue)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        .icon(getResources().getDrawable(R.drawable.arw_lt))                     // Specify a custom drawable to draw as the target
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                      //  doSomething();
                    }
                });
/////////////////////
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.llBottom:
              //  new FilterHotelDialog(AboutActivity.this, filterModels, this, filterHotelTypeModel, filterHotelFacilitiesModels, filterHotelPriceModels);


                break;*/
            case R.id.btnBack:
				/*Intent intent = new Intent(this,PlanFragment.class);
				//i2.putExtra("CUSTOMER_ID", (int) customerID);
				startActivity(intent);*/
                finish();
                break;
        }
    }





    private class GetAboutAsync extends AsyncTask<String, Void, String> {

        ProgressDialog pdLoading = new ProgressDialog(AboutActivity.this);
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetAboutUs");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                // conn.setRequestMethod("GET");
                conn.setRequestMethod("POST");
                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                String serial = null;

                JSONObject errorObj = new JSONObject();

                try {
                    errorObj.put("Success", false);

                    Class<?> c = Class.forName("android.os.SystemProperties");
                    Method get = c.getMethod("get", String.class);
                    serial = (String) get.invoke(c, "ro.serialno");//31007a81d4b22300
                } catch (Exception ignored) {
                }


                String data ="";


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetAboutUs");
                post.setHeader("Content-Type", "application/json; charset=UTF-8");
                post.setHeader("Accept", "application/json; charset=UTF-8");


                StringEntity se = null;
                try {
                    se = new StringEntity(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                post.setEntity(se);
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                HashMap<String, String> airport = null;
                mylist = new ArrayList<HashMap<String, String>>();
                HttpResponse res = client.execute(post);
                String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


                return (retSrc);



            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<SectionModel> data=new ArrayList<SectionModel>();

            pdLoading.dismiss();
            try {
////////////////////////////
                JSONObject jsonObj = new JSONObject(result);

                // JSONObject jsonObj = new JSONObject(retSrc);

                // Getting JSON Array node
                JSONObject GetAirportsResult = jsonObj.getJSONObject("GetAboutUsResult");
                JSONArray jArray = GetAirportsResult.getJSONArray("Sections");
                //////////////////////////////

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    SectionModel sectionModel = new SectionModel();
                    sectionModel.setDescription(json_data.getString("Description"));
                    sectionModel.setSectionName(json_data.getString("SectionName"));
                    sectionModel.setImageAddress(json_data.getString("ImageAddress"));

                    data.add(sectionModel);
                }


                listAirPort = (ListView)findViewById(R.id.lvExp);
                mAdapter = new AboutAdapter(AboutActivity.this, data,AboutActivity.this);
                //mAdapter.setAdapter(mAdapter);
                mAdapter.setData(data);
                listAirPort.setAdapter(mAdapter);
                //mAdapter.setLayoutManager(new LinearLayoutManager(GetAirportActivity.this));

            } catch (JSONException e) {
                Toast.makeText(AboutActivity.this, "ارتباط با سرور برقرار نشد !!", Toast.LENGTH_LONG).show();
            }

        }

    }//end asynTask




}
