package com.eligasht.reservation.views.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.pack.GetPackageRoutesResult;
import com.eligasht.reservation.models.model.pack.call.CountryListReq;
import com.eligasht.reservation.models.model.pack.call.CountryRequestModel;
import com.eligasht.reservation.models.model.pack.response.CountryListRes;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.adapters.GeCitiesForPackAdapter;
import com.eligasht.reservation.views.adapters.GetAirPortMabdaAdapter;
import com.eligasht.reservation.views.adapters.GetCountriesForInsuranceAdapter;
import com.eligasht.reservation.views.components.Header;
import com.orhanobut.hawk.Hawk;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetCitiesForPackActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Handler handler;
    ProgressDialog progressBar;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport;
    ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    private ClientService service;
    public ListView listAirPort;

    GetAirPortMabdaAdapter mAdapter;
    private EditText searchtxt;
    AVLoadingIndicatorView avi;
    FancyButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_city_for_pack);
        avi = findViewById(R.id.avi);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "شهر مقصد را انتخاب کنید");
        btnBack = findViewById(R.id.btnBack);
        listAirPort = findViewById(R.id.listAirPort);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(this);
        service = ServiceGenerator.createService(ClientService.class);
        onPostExecute(new GeCitiesForPackAdapter(this, (GetPackageRoutesResult) Hawk.get("PackCityData"),this));
    }


    public void needShowAlertDialog(String message, boolean canelable) {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            return;
        }
        mAlertDialog = new AlertDialog.Builder(this).create();
        final LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_net, null);
        mAlertDialog.setCancelable(canelable);
        FancyButton btnOk = (FancyButton) view.findViewById(R.id.btnOk);
        TextView tvAlert = (TextView) view.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont("irsans.ttf");
        tvAlert.setText(message);
        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(true);
        mAlertDialog.show();
    }

    protected void onPostExecute(GeCitiesForPackAdapter result) {

        listAirPort.setAdapter(result);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
        // TODO Auto-generated method stub

    }

    @Override
    public void searchTextChanged(String searchText) {
            /*this.searchText = searchText;
            if(searchText.length()>2)
			new AsyncFetch().execute();*/
        //mAdapter.setData(searchText);

    }

}