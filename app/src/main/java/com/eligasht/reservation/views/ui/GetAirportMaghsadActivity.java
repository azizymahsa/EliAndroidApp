package com.eligasht.reservation.views.ui;

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
import java.util.Timer;
import java.util.TimerTask;

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

import android.app.Activity;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.pixplicity.easyprefs.library.Prefs;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;
import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.views.adapters.GetAirPortMaghsadAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

public class GetAirportMaghsadActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    Handler handler;
    ProgressDialog progressBar;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport;
    ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    FancyButton btnBack,btnMic;

    GetAirPortMaghsadAdapter mAdapter;
    private EditText searchtxt;
    AVLoadingIndicatorView avi;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_airport);
        avi = findViewById(R.id.avi);
        //searchtxt = (EditText) findViewById(R.id.searchtxt);
        //Make call to AsyncTask
        //new AsyncFetch().execute();
        btnBack = findViewById(R.id.btnBack);
        btnMic = findViewById(R.id.btnMic);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnMic.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnMic.setText(getString(R.string.icon_mic));
        btnBack.setOnClickListener(this);
        btnMic.setOnClickListener(this);
        //////////////////show recent
        
        ListView listAirPort = findViewById(R.id.listAirPort);
        List<Country> data = new ArrayList<>();
        RecentCity_Table recentCity_table = new RecentCity_Table(this);
        CursorManager cursorManager = recentCity_table.getAll(2);//maghsad
        if (cursorManager != null) {
            for (int i = 0; i < cursorManager.getCount(); i++) {
                cursorManager.moveToPosition(i);
                Country fishData = new Country();
                fishData.setCityName(cursorManager.getString(RecentCity_Table.Columns.CityName.value()));
                fishData.setAirportName(cursorManager.getString(RecentCity_Table.Columns.AirPortName.value()));
                fishData.setAirportCode(cursorManager.getString(RecentCity_Table.Columns.AirPortCode.value()));
                fishData.setAirportID(cursorManager.getString(RecentCity_Table.Columns.AirPortCode.value()));
                fishData.setParentId(cursorManager.getString(RecentCity_Table.Columns.CityName.value()));

                data.add(fishData);
            }
        }
        String Value_Mabda_City = "";
        String Value_Mabda_Airport = "";
        String Value_Mabda_Airport_Code = "";
        ////

        if (Prefs.getString("Value-Mabda-City", "") != null) {

            Value_Mabda_City = Prefs.getString("Value-Mabda-City", "");//Prefs.getString("Value-Maghsad-City", "");
            Value_Mabda_Airport = Prefs.getString("Value-Mabda-Airport", "");
            Value_Mabda_Airport_Code = Prefs.getString("Value-Mabda-Airport-Code", "");
        }


        listAirPort = findViewById(R.id.listAirPort);
        mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, Value_Mabda_City, Value_Mabda_Airport, Value_Mabda_Airport_Code, GetAirportMaghsadActivity.this);

        //mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data,  GetAirportMaghsadActivity.this);

        mAdapter.setData(data);
        listAirPort.setAdapter(mAdapter);

        //////////////////////////
        //////////////////////////Remove recent
        CursorManager cursorManager1 = recentCity_table.getCountRow();
        System.out.println("count:" + cursorManager1.getInt("COUNT(Id)"));
        if (cursorManager1 != null) {
            if (cursorManager1.getInt("COUNT(Id)") >= 10) {

                CursorManager cursorType1 = recentCity_table.getAll(1);//mabda
                CursorManager cursorType2 = recentCity_table.getAll(2);//maghsad

                RecentCity_Table db = new RecentCity_Table(this);
                db.dropTable();
                db.openDB();


                if (cursorType1 != null)
                    for (int e = cursorType1.getCount() - 1; e >= 0; e--) {
                        cursorType1.moveToPosition(e);
                        db.insertData(cursorType1.getString(RecentCity_Table.Columns.AirPortName.value()), cursorType1.getString(RecentCity_Table.Columns.CityName.value()), cursorType1.getString(RecentCity_Table.Columns.AirPortCode.value()), 1);//mabda
                    }
                if (cursorType2 != null)
                    for (int t = cursorType2.getCount() - 1; t >= 0; t--) {
                        cursorType2.moveToPosition(t);
                        db.insertData(cursorType2.getString(RecentCity_Table.Columns.AirPortName.value()), cursorType2.getString(RecentCity_Table.Columns.CityName.value()), cursorType2.getString(RecentCity_Table.Columns.AirPortCode.value()), 2);//maghsad
                    }
                db.closeDB();

            }
        }

        /////////////////////////////
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
                    private final long DELAY = 10; // milliseconds

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

                                                    GetAirportMaghsadActivity.searchText = d.toLowerCase();
                                                    new AsyncFetch().execute();

                                                }
                                                if (d.length() < 0 || d.length() == 0) {
                                                    ////
                                                    ListView listAirPort = findViewById(R.id.listAirPort);
                                                    List<Country> data = null;
                                                    mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, GetAirportMaghsadActivity.this);

                                                    mAdapter.setData(data);
                                                    listAirPort.setAdapter(mAdapter);

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
    }//end oncreate

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(GetAirportMaghsadActivity.this);
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            avi.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://mobilews.eligasht.com/Services/CommonRest/StaticDataService.svc/GetAirportWithParents");

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

                // Check if successful connection made
                // if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                       /* InputStream input = conn.getInputStream();
	                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	                    StringBuilder result = new StringBuilder();
	                    String line;

	                    while ((line = reader.readLine()) != null) {
	                        result.append(line);
	                    }
					*/
                String serial = null;

                JSONObject errorObj = new JSONObject();

                try {
                    errorObj.put("Success", false);

                    Class<?> c = Class.forName("android.os.SystemProperties");
                    Method get = c.getMethod("get", String.class);
                    serial = (String) get.invoke(c, "ro.serialno");//31007a81d4b22300
                } catch (Exception ignored) {
                }


                String data = OrderToJson();


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("http://mobilews.eligasht.com/Services/CommonRest/StaticDataService.svc/GetAirportWithParents");
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
                //{"GetAirportWithParentsResult":{"Errors":[],"List":[{"Key":"IST|Istanbul, Turkey (IST-All Airports)","Value":"استانبول ( همه فرودگاه ها ),نزدیک استانبول, ترکیه"},{"Key":"IST|Istan
                //try {
                HashMap<String, String> airport = null;
                mylist = new ArrayList<HashMap<String, String>>();
                HttpResponse res = client.execute(post);
                String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


                return (retSrc);
					
						                /*} else {
					
						                    return ("unsuccessful");
						                }
					*/
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

            avi.setVisibility(View.INVISIBLE);
            List<Country> data = new ArrayList<Country>();


            try {
                if (!TextUtils.isEmpty(searchtxt.getText())) {
                    JSONObject jsonObj = new JSONObject(result);

                    /////////////////////////////////////
                    String GetError = "";
                    JSONArray jError = null;
                    // Getting JSON Array node
                    JSONObject GetAirportsResult = jsonObj.getJSONObject("GetAirportWithParentsResult");//Error
                    if (!GetAirportsResult.getString("Errors").equals("null")) {
                        jError = GetAirportsResult.getJSONArray("Errors");//
                        JSONObject jPricedItinerary = jError.getJSONObject(0);
                        GetError = jPricedItinerary.getString("Message");
                    }
                    if (GetError.length() > 1) {
                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this);
                        AlertDialogPassenger.setText(GetError);

                    } else {


                        ////////////////////
                        JSONArray jArray = GetAirportsResult.getJSONArray("Airports");

                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject json_data = jArray.getJSONObject(i);
                            Country fishData = new Country();
                            fishData.setCityName(json_data.getString("CityName"));
                            fishData.setAirportName(json_data.getString("AirportName"));
                            fishData.setAirportCode(json_data.getString("AirportCode"));
                            fishData.setAirportID(json_data.getString("AirportID"));
                            fishData.setParentId(json_data.getString("ParentId"));

                            data.add(fishData);
                        }

                        // Setup and Handover data to recyclerview

                        String Value_Mabda_City = "";
                        String Value_Mabda_Airport = "";
                        String Value_Mabda_Airport_Code = "";
                        ////

                        if (Prefs.getString("Value-Mabda-City", "") != null) {

                            Value_Mabda_City = Prefs.getString("Value-Mabda-City", "");//Prefs.getString("Value-Maghsad-City", "");
                            Value_Mabda_Airport = Prefs.getString("Value-Mabda-Airport", "");
                            Value_Mabda_Airport_Code = Prefs.getString("Value-Mabda-Airport-Code", "");
                        }


                        listAirPort = findViewById(R.id.listAirPort);
                        mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, Value_Mabda_City, Value_Mabda_Airport, Value_Mabda_Airport_Code, GetAirportMaghsadActivity.this);
                        //mAdapter.setAdapter(mAdapter);
                        mAdapter.setData(data);
                        listAirPort.setAdapter(mAdapter);
                        //mAdapter.setLayoutManager(new LinearLayoutManager(GetAirportActivity.this));
                    }
                }
            } catch (JSONException e) {
                AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this);
                AlertDialogPassenger.setText(getString(R.string.ErrorServer));
            }

        }

    }

    public String OrderToJson() {
        JSONObject jsone = new JSONObject();
        JSONObject manJson = new JSONObject();


        try {
            //{"CreatorUserId":0,"Id":"9a633b86-8735-4060-83a3-4797548f0203","Orderno":6,"CustomerCode":"","Visitor":34,"OrderDate":"1395\/08\/19","OrderTime":"11:25:20","IsEmergancy":0,"TimeCheck":30,"ByTel":0,"SaleType":0,"IsConvert":0,"OrderStatus":7,"SMSCounter":0,"FinishTime":"11:33:03","IsReceived":0,"IsSent":0,"WarehouseId":145,"IsTemp":0,"Serial":"31007a81d4b22300"}


            manJson.put("UserName", "EligashtMlb");
            manJson.put("Password", "123qwe!@#QWE");
            manJson.put("TermianlId", "Mobile");
            manJson.put("Code", GetAirportMaghsadActivity.searchText);
            //manJson.put("CityCode",URLEncoder.encode(GetAirportActivity.searchText,"UTF-8"));
            jsone.put("request", manJson);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsone.toString();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnMic:
                searchtxt.setText("");

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, com.eligasht.reservation.tools.Prefs.getString("lang","fa"));
        /*        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                        "لطفا مکان مورد نظر را اعلام نمایید...");*/
                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Error",
                            Toast.LENGTH_SHORT).show();
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



                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    searchtxt.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public void searchTextChanged(String searchText) {
			/*this.searchText = searchText;
			if(searchText.length()>2)
			new AsyncFetch().execute();*/
        //mAdapter.setData(searchText);

    }
}