package com.eligasht.reservation.views.activities.train;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;

import com.eligasht.reservation.views.adapters.train.GetTrainMabdaAdapter;

import com.eligasht.reservation.views.adapters.train.GetTrainMaghsadAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;

import com.eligasht.service.model.newModel.train.trainStation.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.train.trainStation.response.ResponseTrainStation;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


public class GetCityTrainMabdaActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener, OnServiceStatus<List<ResponseTrainStation>> {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private static final String TAG = "GetAirport";
    Handler handler;
    ProgressDialog progressBar;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport;
    ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    GetTrainMabdaAdapter mAdapter;
    private EditText searchtxt;
    AVLoadingIndicatorView avi;
    FancyButton btnBack, btnMic;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    LinearLayout lnrSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_train);

        SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
        helper.setEdgeMode(false)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);

        lnrSearch=findViewById(R.id.lnrSearch);
        lnrSearch.setVisibility(View.GONE);
        avi = findViewById(R.id.avi);
        btnBack = findViewById(R.id.btnBack);
        btnMic = findViewById(R.id.btnMic);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnMic.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnMic.setText(getString(R.string.icon_mic));
        btnBack.setOnClickListener(this);
        btnMic.setOnClickListener(this);
        sendRequest();


    }//end oncreate


    @Override
    public boolean needTerminate() {
        return false;
    }

    private void sendRequest() {
        avi.setVisibility(View.VISIBLE);

        AutoCompleteParameterModel requestAutoCompleteParameterModel = new AutoCompleteParameterModel();

        requestAutoCompleteParameterModel.setPart("");

        SingletonService.getInstance().getTrain().newGetTrainStatAvail(this, requestAutoCompleteParameterModel);
    }


    @Override
    public void onReady(List<ResponseTrainStation> responseTrainStations) {//get Response from api
        System.out.println("ResponseTrainStation"+responseTrainStations);
        avi.setVisibility(View.GONE);

        List<Country> data = new ArrayList<Country>();
        if (responseTrainStations !=null){
            for (int i = 0; i < responseTrainStations.size(); i++) {
                Country fishData = new Country();
                fishData.setCityName(responseTrainStations.get(i).getTextFa());
                fishData.setText(responseTrainStations.get(i).getText());//.getAirports().get(i).getAirportName());
                fishData.setValue(responseTrainStations.get(i).getValue()); //.get(i).getAirportCode());


                data.add(fishData);
            }
        }

        ListView listAirPort = findViewById(R.id.listAirPort);
        mAdapter = new GetTrainMabdaAdapter(GetCityTrainMabdaActivity.this,data, GetCityTrainMabdaActivity.this);

        mAdapter.setData(data);
        listAirPort.setAdapter(mAdapter);

    }

    @Override
    public void onError(String message) {//get Errors from api

        if (!Utility.isNetworkAvailable(GetCityTrainMabdaActivity.this)) {
            AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetCityTrainMabdaActivity.this,true,false);
            AlertDialogPassenger.setText(getString(R.string.InternetError), getString(R.string.massege));
        } else {
            Toast.makeText(this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnMic:
                searchtxt.setText("");
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Prefs.getString("lang", "fa"));

                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
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

                    ArrayList<String> result = data .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    searchtxt.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public void searchTextChanged(String searchText) {


    }
}