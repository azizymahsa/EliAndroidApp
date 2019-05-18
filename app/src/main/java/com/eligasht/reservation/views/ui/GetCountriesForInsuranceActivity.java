package com.eligasht.reservation.views.ui;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.model.pack.call.CountryListReq;
import com.eligasht.reservation.models.model.pack.call.CountryRequestModel;
import com.eligasht.reservation.models.model.pack.response.CountryListRes;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.adapters.GetAirPortMabdaAdapter;
import com.eligasht.reservation.views.adapters.GetCountriesForInsuranceAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.Identity;
import com.eligasht.service.model.flight.request.airPort.Request;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.insurance.request.GetCountry.RequestGetCountry;
import com.eligasht.service.model.insurance.response.GetCountry.ResponseGetCountry;
import com.eligasht.service.model.newModel.airport.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.insurance.response.InsuranceCountry.ResponseInsuranceCountry;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetCountriesForInsuranceActivity extends BaseActivity implements  OnClickListener , OnServiceStatus<List<ResponseInsuranceCountry>> {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public static String searchText = "";
  //  public ListView list_airport;
    public RecyclerView listAirPort;
    Handler handler;
    ProgressDialog progressBar;
    ArrayList<HashMap<String, String>> mylist = null;
    GetAirPortMabdaAdapter mAdapter;
    AVLoadingIndicatorView avi;
    FancyButton btnBack;
    TextView btnMic;
    private Handler progressBarHandler = new Handler();
    private ClientService service;
    private EditText searchtxt;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_airport);
        avi = findViewById(R.id.avi);
        btnBack = findViewById(R.id.btnBack);
        listAirPort = findViewById(R.id.listAirPort);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listAirPort.setLayoutManager(mLayoutManager);
        listAirPort.setItemAnimator(new DefaultItemAnimator());
        btnBack = findViewById(R.id.btnBack);
        btnMic = findViewById(R.id.btnMic);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
       // btnMic.setCustomTextFont("fonts/eligasht.ttf");
        btnBack.setText(getString(R.string.search_back_right));
      //  btnMic.setText("&#105;");

        btnBack.setOnClickListener(this);
        btnMic.setOnClickListener(this);
        service = ServiceGenerator.createService(ClientService.class);
        searchtxt = findViewById(R.id.searchtxt);

        if(getString(R.string.culture).contains("fa")){
            getCountries("+++");
        }else{
            getCountries("***");
        }
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
                        String d = s.toString().trim();
                        if (d.length() > 2) {
                        getCountries(input);
                        }else{
                            if(getString(R.string.culture).contains("fa")){
                                getCountries("+++");
                            }else{
                                getCountries("***");
                            }
                        }

                    }
                });
    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    @Override
    public void onReady(List<ResponseInsuranceCountry> responseGetCountry) {
        hideLoading();
        try {
            if (responseGetCountry == null) {
                searchtxt.setText("");
                needShowAlertDialog(getString(R.string.ErrorServer), true);
                return;
            }
            if (responseGetCountry == null ||  responseGetCountry.size()<1) {//.getCountries() == null ) {
                return;
            }
            GetCountriesForInsuranceAdapter adapter = new GetCountriesForInsuranceAdapter(GetCountriesForInsuranceActivity.this, responseGetCountry, GetCountriesForInsuranceActivity.this);
            onPostExecute(adapter);
           // mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String message) {
        Log.e("onError: " , message);
        try {
            searchtxt.setText("");
            needShowAlertDialog(getString(R.string.ErrorServer), true);
        } catch (Exception e) {
        }
    }
    private void getCountries(String cityCode) {
        showLoading();


        AutoCompleteParameterModel requestAutoCompleteParameterModel = new AutoCompleteParameterModel();

        requestAutoCompleteParameterModel.setPart(cityCode);

        SingletonService.getInstance().getInsurance().newInsuranceCountriesAvail(this, requestAutoCompleteParameterModel);

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


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnMic:
                searchtxt.setText("");
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Prefs.getString("lang","fa"));
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

    private void showLoading() {
        avi.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        avi.setVisibility(View.INVISIBLE);
    }


}