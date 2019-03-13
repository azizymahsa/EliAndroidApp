package com.eligasht.reservation.views.activities;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.SectionModel;
import com.eligasht.reservation.tools.JustifiedTextView;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.adapters.AboutAdapter;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.SplashActivity;
import com.eligasht.service.model.newModel.startup.response.Branch;
import com.eligasht.service.model.newModel.startup.response.CommonUrl;

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
    private WebView webView;
    private ProgressBar progressBar;
    private ImageView imgHeader;

    private String postUrl;

    private List<Branch> branchesDef;
    CommonUrl commonUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);

        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imgHeader = (ImageView) findViewById(R.id.backdrop);

        // initializing toolbar0
        initCollapsingToolbar();



        setDataCommonUrl();
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
                                    if(branchesDef.get(i).getCommonUrl().get(j).getTitle().contains("Termsconditions")){
                                        commonUrl=branchesDef.get(i).getCommonUrl().get(j);
                                    }
                                }

                            }
                        }

                    }else {//default branch
                        for (int z = 0; z < branchesDef.get(0).getCommonUrl().size(); z++) {
                            if(branchesDef.get(0).getCommonUrl().get(z).getTitle().contains("Termsconditions")){
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

}
