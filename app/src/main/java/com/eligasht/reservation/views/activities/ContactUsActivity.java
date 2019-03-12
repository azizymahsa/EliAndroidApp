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

import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.views.ui.SplashActivity;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.helper.Const;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.contactUs.RequestContactUs;
import com.eligasht.service.model.flight.response.contactUs.GetContactUsWithCutureResult;
import com.eligasht.service.model.flight.response.contactUs.ResponseContactUs;
import com.eligasht.service.model.newModel.startup.response.Branch;
import com.eligasht.service.model.newModel.startup.response.ContactUs;
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


public class ContactUsActivity extends BaseActivity implements View.OnClickListener, OnMapReadyCallback  {


    private FancyButton btnBack;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    ArrayList<HashMap<String, String>> mylist = null;
      TextView txtPhone,txtAddres,txtSocialFollow,textView15;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    private GoogleMap map;
    ExpandableRelativeLayout expandableLayout;
    public ImageView txtInstagram,txtAparat,txtTweeter,txtPintrest,txtLinkdin,txtGoogleP,txtFacebook,txtTelegram;
    public String strInstagram,strAparat,strTweeter,strPintrest,strLinkdin,strGoogleP,strFacebook,strTelegram;
    public String strPhone;
    ProgressDialog pdLoading;
    LatLng location;
    private ResponseContactUs responseContactUs;
    private List<Branch> branchesDef;
    ContactUs contactUs;
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

        SetDataContactUs();

        findViewById(R.id.txt_hom).setVisibility(View.INVISIBLE);


    }

    private void SetDataContactUs() {

        contactUs=new ContactUs();
        branchesDef=new ArrayList<>();

        branchesDef= SplashActivity.branchesDef;

        if (branchesDef.get(0).getIsDefault()){
            if(Prefs.getBoolean("isChangeUrl", false)){
                branchesDef.clear();
                branchesDef=new ArrayList<>();

                branchesDef=SplashActivity.branches;

                for (int i = 0; i < branchesDef.size(); i++) {
                    if(Prefs.getString("BASEURL", "").equals(branchesDef.get(i).getUrl())){
                        contactUs= branchesDef.get(i).getContactUs();
                    }
                }

            }else {
                contactUs= branchesDef.get(0).getContactUs();
            }
        }
        setUrlSocialNet();
        setDatainTxt();
    }

    private void setDatainTxt() {
        txtAddres.setText(contactUs.getAddress());//GetAirportsResult.getAddress().replaceAll("\t","").replaceAll("\r"," ").replaceAll("\n"," "));
        txtPhone.setText(contactUs.getPhoneNumber());
        try{
            map.clear();

            location=new LatLng(Double.parseDouble(contactUs.getLatitude()),Double.parseDouble(contactUs.getLongitude()));

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            map.addMarker(new MarkerOptions().position(location).title(getString(R.string.eligasht)));
        } catch (Exception e) {
            Toast.makeText(ContactUsActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void setUrlSocialNet() {

        for (int i = 0; i <contactUs.getContactInfo().size() ; i++) {
            if (contactUs.getContactInfo().get(i).contains("instagram"))
                strInstagram=contactUs.getContactInfo().get(i);
            if (contactUs.getContactInfo().get(i).contains("aparat"))
                strAparat=contactUs.getContactInfo().get(i);
            if (contactUs.getContactInfo().get(i).contains("twitter"))
                strTweeter=contactUs.getContactInfo().get(i);
            if (contactUs.getContactInfo().get(i).contains("pinterest"))
                strPintrest=contactUs.getContactInfo().get(i);
            if (contactUs.getContactInfo().get(i).contains("linkedin"))
                strLinkdin=contactUs.getContactInfo().get(i);
            if (contactUs.getContactInfo().get(i).contains("plus"))
                strGoogleP=contactUs.getContactInfo().get(i);
            if (contactUs.getContactInfo().get(i).contains("facebook"))
                strFacebook=contactUs.getContactInfo().get(i);
            if (contactUs.getContactInfo().get(i).contains("telegram"))
                strTelegram=contactUs.getContactInfo().get(i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txtInstagram:
                Uri uri = Uri.parse(strInstagram);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strInstagram)));
                }
                break;
            case R.id.txtAparat:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strAparat)));
                break;
            case R.id.txtTweeter:
                Intent intent = null;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strTweeter));
                this.startActivity(intent);
                break;
            case R.id.txtPintrest:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strPintrest)));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strPintrest)));
                }
                break;
            case R.id.txtLinkdin:
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse(strLinkdin)));
                break;
            case R.id.txtGoogleP:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strGoogleP)));
                break;
            case R.id.txtFacebook:

                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse(strFacebook)));
                break;
            case R.id.txtTelegram:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strTelegram)));
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
                    Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactUs.getPhoneNumber(), null));
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
        //if (responseContactUs != null) {
            location=new LatLng(Double.parseDouble(contactUs.getLatitude()),Double.parseDouble(contactUs.getLongitude()));
        /*}else{
             location=new LatLng(35.737595,51.413388);
        }*/

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        map.addMarker(new MarkerOptions().position(location).title(getString(R.string.eligasht)));

    }




}

