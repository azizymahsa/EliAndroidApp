package com.eligasht.reservation.views.activities;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.SectionModel;
import com.eligasht.reservation.tools.JustifiedTextView;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.views.adapters.AboutAdapter;
import com.eligasht.reservation.views.ui.SingletonContext;

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

import mehdi.sakout.fancybuttons.FancyButton;


public class ConditionActivity extends BaseActivity implements View.OnClickListener {


    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Handler handler;
    ProgressDialog progressBar;
    ArrayList<HashMap<String,String>> mylist=null;
    AboutAdapter mAdapter;
    private FancyButton btnBack;
    private Handler progressBarHandler = new Handler();
    private JustifiedTextView textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);


        textView12 = findViewById(R.id.textView12);
        Typeface face = Typeface.createFromAsset(this.getAssets(), SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        textView12.setTypeFace(face);
        textView12.setTextSize(1,16);
        textView12.setTextColor(ContextCompat.getColor(ConditionActivity.this,R.color.gray_dark_2));

        textView12.setLineSpacing(30);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));


       new GetAboutAsync().execute();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {

            case R.id.btnBack:

                finish();
                break;
        }
    }





    private class GetAboutAsync extends AsyncTask<String, Void, String> {

        ProgressDialog pdLoading = new ProgressDialog(ConditionActivity.this);
        HttpURLConnection conn;
        URL url = null;
        private NonScrollRecyclerView listAirPort;

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
                url = new URL("https://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetTermsAndConditions");

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


                String data = "";
                try {
                    errorObj.put("Success", false);

                    Class<?> c = Class.forName("android.os.SystemProperties");
                    Method get = c.getMethod("get", String.class);
                    serial = (String) get.invoke(c, "ro.serialno");//31007a81d4b22300
                } catch (Exception ignored) {
                }
                try{

                    if(Locale.getDefault().getLanguage().equals("en")){
                        JSONObject jsone = new JSONObject();
                        JSONObject manJson = new JSONObject();
                        manJson.put("culture", "en-");
                        // jsone.put("", manJson);
                        data=manJson.toString();
                    }else if(Locale.getDefault().getLanguage().equals("fa")) {
                        data = "";
                    }else if(Locale.getDefault().getLanguage().equals("tr")) {
                        JSONObject jsone = new JSONObject();
                        JSONObject manJson = new JSONObject();
                        manJson.put("culture", "tr-TR");
                        // jsone.put("", manJson);
                        data=manJson.toString();
                    }else if(Locale.getDefault().getLanguage().equals("ar")) {
                        JSONObject jsone = new JSONObject();
                        JSONObject manJson = new JSONObject();
                        manJson.put("culture", "ar-");
                        //jsone.put("", manJson);
                        data=manJson.toString();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("culture:"+data);


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("https://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetTermsAndConditions");
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
                JSONObject GetAirportsResult = jsonObj.getJSONObject("GetTermsAndConditionsResult");
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
                textView12.setText(data.get(0).getDescription()+"");
              /*  listAirPort.addItemDecoration(new DividerItemDecoration(ConditionActivity.this, 1));
                listAirPort.setLayoutManager(new LinearLayoutManager(ConditionActivity.this));
                listAirPort = findViewById(R.id.lvExp);
                mAdapter = new AboutAdapter(data);
                listAirPort.setAdapter(mAdapter);*/

            } catch (JSONException e) {
                Toast.makeText(ConditionActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
            }

        }

    }//end asynTask




}
