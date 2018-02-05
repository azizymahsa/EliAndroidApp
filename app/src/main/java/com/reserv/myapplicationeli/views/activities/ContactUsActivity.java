package com.reserv.myapplicationeli.views.activities;

import android.Manifest;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableWeightLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.onesignal.OneSignal;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.model.ContactInfo;
import com.reserv.myapplicationeli.models.model.SectionModel;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.adapters.AboutAdapter;
import com.reserv.myapplicationeli.views.ui.SplashFragment;
import com.reserv.myapplicationeli.views.ui.dialog.app.InternetAlert;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.AlertDialog;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.SocialFollowDialog;

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


public class ContactUsActivity extends BaseActivity implements View.OnClickListener, OnMapReadyCallback {


    private FancyButton btnBack;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Handler handler;
    ProgressDialog progressBar;
    private Handler progressBarHandler = new Handler();
    ArrayList<HashMap<String, String>> mylist = null;
    AboutAdapter mAdapter;
    TextView txtPhone,txtAddres,txtSocialFollow;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    private GoogleMap map;
    ExpandableWeightLayout expandableLayout;
    public ImageView txtInstagram,txtAparat,txtTweeter,txtPintrest,txtLinkdin,txtGoogleP,txtFacebook,txtTelegram;
   // private TextView tvTitle, tvArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        OneSignal.sendTag("position", "isContactUs");


        //tvArrow = findViewById(R.id.tvArrow);
        expandableLayout = findViewById(R.id.expandableLayout);

        btnBack = (FancyButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));

        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtPhone.setOnClickListener(this);

        txtAddres = (TextView) findViewById(R.id.txtAddres);
        txtAddres.setOnClickListener(this);

        txtSocialFollow = (TextView) findViewById(R.id.txtSocialFollow);
        txtSocialFollow.setOnClickListener(this);

        txtTelegram = (ImageView) findViewById(R.id.txtTelegram);
        txtTelegram.setOnClickListener(this);
        txtFacebook = (ImageView) findViewById(R.id.txtFacebook);
        txtFacebook.setOnClickListener(this);
        txtGoogleP = (ImageView) findViewById(R.id.txtGoogleP);
        txtGoogleP.setOnClickListener(this);
        txtInstagram = (ImageView) findViewById(R.id.txtInstagram);
        txtInstagram.setOnClickListener(this);
        txtLinkdin = (ImageView) findViewById(R.id.txtLinkdin);
        txtLinkdin.setOnClickListener(this);
        txtPintrest = (ImageView) findViewById(R.id.txtPintrest);
        txtPintrest.setOnClickListener(this);
        txtTweeter = (ImageView) findViewById(R.id.txtTweeter);
        txtTweeter.setOnClickListener(this);
        txtAparat = (ImageView) findViewById(R.id.txtAparat);
        txtAparat.setOnClickListener(this);


        initMap();
         new GetContactUsAsync().execute();
// add PhoneStateListener


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txtInstagram:
                Uri uri = Uri.parse("http://www.aparat.com/eligasht");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.aparat.com/eligasht")));
                }
                break;
            case R.id.txtAparat:

                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.aparat.com/eligasht")));
                break;
            case R.id.txtTweeter:

                Intent intent = null;

                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/eligasht"));

                this.startActivity(intent);
                break;
            case R.id.txtPintrest:

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("pinterest://www.pinterest.com/eligasht")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pinterest.com/eligasht")));
                }
                break;
            case R.id.txtLinkdin:

                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/eligasht")));
                break;
            case R.id.txtGoogleP:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+Eligasht")));

                break;
            case R.id.txtFacebook:

                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/eligashtco")));
                break;
            case R.id.txtTelegram:

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/eligashtco")));
                break;
            case R.id.btnBack:

                finish();
                break;
            case R.id.txtSocialFollow:
               // expandableLayout.setDuration(1000);
               // expandableLayout.setInterpolator(new AnticipateInterpolator());

                //new AlertDialog(this, "شبکه های اجتماعی ما");
                if (expandableLayout.isExpanded()) {

                    expandableLayout.collapse();
                  //  tvArrow.setText(getString(R.string.icon_arrow_up));

                } else {
                    expandableLayout.expand();
                  //  tvArrow.setText(getString(R.string.icon_arrow_down));

                }

                break;
            case R.id.txtPhone:

                new TedPermission(ContactUsActivity.this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {


                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:"+"۰۲۱-۸۵۴۰"));

                                startActivity(callIntent);



                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                            }
                        })
                        .setDeniedMessage("If you reject permission,you can not call, Please turn on permissions at [Setting] > [Permission]")
                        .setPermissions( Manifest.permission.CALL_PHONE)
                        .check();









        }
    }

    public void initMap() {


        if (serviceOK()) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {
            // TODO: 9/18/2016
        }


    }


    public boolean serviceOK() {
        try {
            int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
            if (isAvailable == ConnectionResult.SUCCESS) {
                return true;
            } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,
                        this, GPS_ERRORDIALOG_REQUEST);
                dialog.show();
            } else {
                Toast.makeText(this, "امکان دسترسی وجود ندارد", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        map = googleMap;
        map.getUiSettings().setScrollGesturesEnabled(false);
        // Add a marker in Sydney, Australia, and move the camera.
    /*    LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.ca(CameraUpdateFactory.newLatLng(sydney));
*/

        LatLng location=new LatLng(35.737643,51.4107692);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        map.addMarker(new MarkerOptions().position(location).title("الی گشت"));


    }



    private class GetContactUsAsync extends AsyncTask<String, Void, String> {

        ProgressDialog pdLoading = new ProgressDialog(ContactUsActivity.this);
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
                url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetContactUs");

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
                post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetContactUs");
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
            List<ContactInfo> data=new ArrayList<ContactInfo>();

            pdLoading.dismiss();
            try {
////////////////////////////
                JSONObject jsonObj = new JSONObject(result);

                // Getting JSON Array node
                JSONObject GetAirportsResult = jsonObj.getJSONObject("GetContactUsResult");
                String jsonAddress = GetAirportsResult.getString("Address");

                JSONArray jArray = GetAirportsResult.getJSONArray("ContactInfos");


                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    ContactInfo sectionModel = new ContactInfo();
                    sectionModel.setDescription(json_data.getString("Description"));
                    sectionModel.setIcon(json_data.getString("Icon"));
                    sectionModel.setIconNumber(json_data.getString("IconNumber"));
                    sectionModel.setTitle(json_data.getString("Title"));

                    data.add(sectionModel);
                }
                System.out.println("Image:"+GetAirportsResult.getString("Address"));//r\n\t\
                txtAddres.setText(GetAirportsResult.getString("Address").replaceAll("\t","").replaceAll("\r"," ").replaceAll("\n"," "));
                 txtPhone.setText(""+GetAirportsResult.getString("PhoneNumber"));


                //mAdapter.setLayoutManager(new LinearLayoutManager(GetAirportActivity.this));

            } catch (JSONException e) {
                Toast.makeText(ContactUsActivity.this, "ارتباط با سرور برقرار نشد !!", Toast.LENGTH_LONG).show();
            }

        }

    }//end asynTask




}
