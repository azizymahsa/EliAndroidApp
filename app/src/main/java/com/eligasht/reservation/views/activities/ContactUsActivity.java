package com.eligasht.reservation.views.activities;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.contactUs.RequestContactUs;
import com.eligasht.service.model.flight.response.contactUs.GetContactUsWithCutureResult;
import com.eligasht.service.model.flight.response.contactUs.ResponseContactUs;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.ContactInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import mehdi.sakout.fancybuttons.FancyButton;


public class ContactUsActivity extends BaseActivity implements View.OnClickListener, OnMapReadyCallback , OnServiceStatus<ResponseContactUs> {


    private FancyButton btnBack;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    ArrayList<HashMap<String, String>> mylist = null;
      TextView txtPhone,txtAddres,txtSocialFollow,textView15;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    private GoogleMap map;
    ExpandableRelativeLayout expandableLayout;
    public ImageView txtInstagram,txtAparat,txtTweeter,txtPintrest,txtLinkdin,txtGoogleP,txtFacebook,txtTelegram;
    ProgressDialog pdLoading;
    LatLng location;
    private ResponseContactUs responseContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        expandableLayout = findViewById(R.id.expandableLayout);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));

        textView15= findViewById(R.id.textView15);
        textView15.setOnClickListener(this);

        txtPhone = findViewById(R.id.txtPhone);
        txtPhone.setOnClickListener(this);

        txtAddres = findViewById(R.id.txtAddres);
        txtAddres.setOnClickListener(this);

        txtSocialFollow = findViewById(R.id.txtSocialFollow);
        txtSocialFollow.setOnClickListener(this);

        txtTelegram = findViewById(R.id.txtTelegram);
        txtTelegram.setOnClickListener(this);
        txtFacebook = findViewById(R.id.txtFacebook);
        txtFacebook.setOnClickListener(this);
        txtGoogleP = findViewById(R.id.txtGoogleP);
        txtGoogleP.setOnClickListener(this);
        txtInstagram = findViewById(R.id.txtInstagram);
        txtInstagram.setOnClickListener(this);
        txtLinkdin = findViewById(R.id.txtLinkdin);
        txtLinkdin.setOnClickListener(this);
        txtPintrest = findViewById(R.id.txtPintrest);
        txtPintrest.setOnClickListener(this);
        txtTweeter = findViewById(R.id.txtTweeter);
        txtTweeter.setOnClickListener(this);
        txtAparat = findViewById(R.id.txtAparat);
        txtAparat.setOnClickListener(this);

        initMap();

        SendRequestContactUs();

        findViewById(R.id.txt_hom).setVisibility(View.INVISIBLE);

    }

    private void SendRequestContactUs() {
        pdLoading = new ProgressDialog(ContactUsActivity.this);
        try {
            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        RequestContactUs requestContactUs = new RequestContactUs();
        com.eligasht.service.model.flight.request.contactUs.RequestContactUs request = new com.eligasht.service.model.flight.request.contactUs.RequestContactUs();
        request.setCulture(getString(R.string.culture));
        SingletonService.getInstance().getContactUs().contactUsAvail(this,request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txtInstagram:
                Uri uri = Uri.parse("https://instagram.com/eligashtco/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/eligashtco/")));
                }
                break;
            case R.id.txtAparat:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.aparat.com/eligasht")));
                break;
            case R.id.txtTweeter:
                Intent intent = null;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/eligasht"));
                this.startActivity(intent);
                break;
            case R.id.txtPintrest:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pinterest.com/eligasht/")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pinterest.com/eligasht/")));
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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/eligashtco")));
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.txtSocialFollow:
                if (expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                    //  tvArrow.setText(getString(R.string.icon_arrow_up));
                  /*  YoYo.with(Techniques.SlideOutUp)
                            .duration(600)
                            .playOn(expandableLayout);
                    textView15.setScroller(new Scroller(this));
                    ScrollView layout_scroll=(ScrollView)findViewById(R.id.layout_scroll);
                    layout_scroll.fullScroll(ScrollView.FOCUS_DOWN);*/
                } else {
                    expandableLayout.expand();
                     }

                break;
            case R.id.txtPhone:
                if (responseContactUs == null) {
                    Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "۰۲۱-۸۵۴۰", null));
                    startActivity(intent2);
                }else {
                    String[] phone = responseContactUs.getGetContactUsWithCutureResult().getPhoneNumber().split("\r\n");
                    String phoneCall = phone[0];
                    Log.e("phone", phoneCall+"" );
                    Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phoneCall, null));
                    startActivity(intent2);
                }

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
                Toast.makeText(this, R.string.there_is_no_access, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        googleMap.getUiSettings().setTiltGesturesEnabled(false);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        map.clear();
        if (responseContactUs != null) {
            location=new LatLng(responseContactUs.getGetContactUsWithCutureResult().getLatitude(),responseContactUs.getGetContactUsWithCutureResult().getLongitude());
        }else{
             location=new LatLng(35.737595,51.413388);
        }

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        map.addMarker(new MarkerOptions().position(location).title(getString(R.string.eligasht)));

    }

    @Override
    public void onReady(ResponseContactUs responseContactUs) {

         this.responseContactUs=responseContactUs;
            try {
                pdLoading.dismiss();
                List<ContactInfo> data=new ArrayList<ContactInfo>();

                pdLoading.dismiss();

                // Getting JSON Array node
                GetContactUsWithCutureResult GetAirportsResult = responseContactUs.getGetContactUsWithCutureResult();//
                String jsonAddress = GetAirportsResult.getAddress();

                List<com.eligasht.service.model.flight.response.contactUs.ContactInfo> jArray = GetAirportsResult.getContactInfos();

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.size();i++){
                    com.eligasht.service.model.flight.response.contactUs.ContactInfo json_data = jArray.get(i);
                    ContactInfo sectionModel = new ContactInfo();
                    sectionModel.setDescription(json_data.getDescription());
                    sectionModel.setIcon(json_data.getIcon());
                    sectionModel.setIconNumber(json_data.getIconNumber());
                    sectionModel.setTitle(json_data.getTitle());

                    data.add(sectionModel);
                }

                System.out.println("Image:"+GetAirportsResult.getAddress());//r\n\t\
                txtAddres.setText(GetAirportsResult.getAddress().replaceAll("\t","").replaceAll("\r"," ").replaceAll("\n"," "));
                txtPhone.setText(""+GetAirportsResult.getPhoneNumber());

                map.clear();
                if (responseContactUs != null) {
                    location=new LatLng(responseContactUs.getGetContactUsWithCutureResult().getLatitude(),responseContactUs.getGetContactUsWithCutureResult().getLongitude());
                    Log.i("Location1",location.toString() );
                }else{
                    location=new LatLng(35.737595,51.413388);
                    Log.i("Location2",location.toString() );
                }

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                map.addMarker(new MarkerOptions().position(location).title(getString(R.string.eligasht)));
            } catch (Exception e) {
                Toast.makeText(ContactUsActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
            }

    }

    @Override
    public void onError(String message) {
        Toast.makeText(ContactUsActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
    }


}

