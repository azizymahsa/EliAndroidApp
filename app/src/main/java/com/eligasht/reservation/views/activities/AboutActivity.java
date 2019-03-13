package com.eligasht.reservation.views.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;

import com.eligasht.reservation.tools.Prefs;

import com.eligasht.reservation.views.ui.SplashActivity;

import com.eligasht.service.model.newModel.startup.response.Branch;
import com.eligasht.service.model.newModel.startup.response.CommonUrl;

import java.util.ArrayList;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;



public class AboutActivity extends BaseActivity implements View.OnClickListener {


    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    private WebView webView;
    private ProgressBar progressBar;
    private ImageView imgHeader;

    private String postUrl;

    private List<Branch> branchesDef;
    CommonUrl commonUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_new);



        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imgHeader = (ImageView) findViewById(R.id.backdrop);

        // initializing toolbar
        initCollapsingToolbar();



       setDataCommonUrl();
    }
    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar txtPostTitle on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the txtPostTitle when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Web View");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    /*    // loading toolbar header image
        Glide.with(getApplicationContext()).load("https://api.androidhive.info/webview/nougat.jpg")
                .thumbnail(0.5f)
                //.crossFade()
               // .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgHeader);*/
    }

    //////////////////////////////
    private void setDataCommonUrl() {
        try {

            commonUrl=new CommonUrl();
            branchesDef=new ArrayList<>();

            branchesDef= SplashActivity.branchesDef;
        if (branchesDef != null){
                if (branchesDef.get(0).getIsDefault()){
                    if(Prefs.getBoolean("isChangeUrl", false)){
                       // branchesDef.clear();
                        branchesDef=new ArrayList<>();

                        branchesDef=SplashActivity.branches;

                        for (int i = 0; i < branchesDef.size(); i++) {
                            if(Prefs.getString("BASEURL", "").equals(branchesDef.get(i).getUrl())){
                                for (int j = 0; j < branchesDef.get(i).getCommonUrl().size(); j++) {
                                     if(branchesDef.get(i).getCommonUrl().get(j).getTitle().contains("aboutus")){
                                         commonUrl=branchesDef.get(i).getCommonUrl().get(j);
                                     }
                                }

                            }
                        }

                    }else {//default branch
                        for (int z = 0; z < branchesDef.get(0).getCommonUrl().size(); z++) {
                            if(branchesDef.get(0).getCommonUrl().get(z).getTitle().contains("aboutus")){
                                commonUrl=branchesDef.get(0).getCommonUrl().get(z);
                            }
                        }
                       // commonUrl= branchesDef.get(0).getCommonUrl();
                    }
                }

                setUrlInWebView();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private void setUrlInWebView() {
        postUrl = commonUrl.getUrl();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(postUrl);
        webView.setHorizontalScrollBarEnabled(false);

       // webView.loadUrl("http://www.google.com");
        Log.d( "setUrlInWebView: ",commonUrl.getUrl());
        //webView.loadUrl(commonUrl.getUrl());
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
            case R.id.btnBack:
                finish();
                break;
        }
    }









}
