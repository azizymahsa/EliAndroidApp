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
import com.eligasht.reservation.models.model.pack.call.CountryListReq;
import com.eligasht.reservation.models.model.pack.call.CountryRequestModel;
import com.eligasht.reservation.models.model.pack.response.CountryListRes;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.adapters.GetAirPortMabdaAdapter;
import com.eligasht.reservation.views.adapters.GetCountriesForInsuranceAdapter;
import com.eligasht.reservation.views.components.Header;
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


public class GetCountriesForInsuranceActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public static String searchText = "";
    public ListView list_airport;
    public ListView listAirPort;
    Handler handler;
    ProgressDialog progressBar;
    ArrayList<HashMap<String, String>> mylist = null;
    GetAirPortMabdaAdapter mAdapter;
    AVLoadingIndicatorView avi;
    FancyButton btnBack;
    private Handler progressBarHandler = new Handler();
    private ClientService service;
    private EditText searchtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_airport);
        avi = findViewById(R.id.avi);
        btnBack = findViewById(R.id.btnBack);
        listAirPort = findViewById(R.id.listAirPort);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(this);
        service = ServiceGenerator.createService(ClientService.class);
        searchtxt = findViewById(R.id.searchtxt);
        searchtxt.addTextChangedListener(
                new TextWatcher() {
                    private final long DELAY = 10; // milliseconds
                    private Timer timer = new Timer();

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(final Editable s) {
                        String input = searchtxt.getText().toString();
                        if (ValidationTools.isEmptyOrNull(input)) {
                            return;
                        }

                        getCountries(input);

                    }
                });
    }


    private void getCountries(String cityCode) {
        Call<CountryListRes> call = service.getCountryListResult(new CountryRequestModel(new CountryListReq("EligashtMlb", "123qwe!@#QWE", "Mobile", cityCode)));
        call.enqueue(new Callback<CountryListRes>() {
            @Override
            public void onResponse(Call<CountryListRes> call, Response<CountryListRes> response) {
                if (response == null || response.body() == null) {
                    searchtxt.setText("");
                    needShowAlertDialog(getString(R.string.ErrorServer), true);
                    return;
                }

                if (response.body().getCountryAjaxResult() == null || ValidationTools.isEmptyOrNull(response.body().getCountryAjaxResult().getCountries())) {
                    return;
                }
                try {
                    GetCountriesForInsuranceAdapter adapter = new GetCountriesForInsuranceAdapter(GetCountriesForInsuranceActivity.this, response.body().getCountryAjaxResult().getCountries(), GetCountriesForInsuranceActivity.this);
                    onPostExecute(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<CountryListRes> call, Throwable t) {
                try {
                    searchtxt.setText("");
                    needShowAlertDialog(getString(R.string.ErrorServer), true);
                } catch (Exception e) {
                }

            }
        });
    }

    public void needShowAlertDialog(String message, boolean canelable) {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            return;
        }
        mAlertDialog = new AlertDialog.Builder(this).create();
        final LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_net, null);
        mAlertDialog.setCancelable(canelable);
        FancyButton btnOk = view.findViewById(R.id.btnOk);
        TextView tvAlert = view.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.irsans_ttf));
        tvAlert.setText(message);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(true);
        mAlertDialog.show();
    }

    protected void onPostExecute(GetCountriesForInsuranceAdapter result) {

        listAirPort.setAdapter(result);
    }


    public String OrderToJson() {
        JSONObject jsone = new JSONObject();
        JSONObject manJson = new JSONObject();


        try {
            //{"CreatorUserId":0,"Id":"9a633b86-8735-4060-83a3-4797548f0203","Orderno":6,"CustomerCode":"","Visitor":34,"OrderDate":"1395\/08\/19","OrderTime":"11:25:20","IsEmergancy":0,"TimeCheck":30,"ByTel":0,"SaleType":0,"IsConvert":0,"OrderStatus":7,"SMSCounter":0,"FinishTime":"11:33:03","IsReceived":0,"IsSent":0,"WarehouseId":145,"IsTemp":0,"Serial":"31007a81d4b22300"}


            manJson.put("UserName", "EligashtMlb");
            manJson.put("Password", "123qwe!@#QWE");
            manJson.put("TermianlId", "Mobile");
            manJson.put("Code", GetCountriesForInsuranceActivity.searchText);
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