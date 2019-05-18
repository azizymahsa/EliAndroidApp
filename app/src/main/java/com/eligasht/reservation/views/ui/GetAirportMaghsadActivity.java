package com.eligasht.reservation.views.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import android.speech.RecognizerIntent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.eligasht.reservation.models.Airport;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.GetAirPortMabdaAdapter;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;

import com.eligasht.reservation.tools.Prefs;
import com.eligasht.service.model.newModel.airport.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.airport.response.ResponseAirport;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.Airport;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;
import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.views.adapters.GetAirPortMaghsadAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

public class GetAirportMaghsadActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener, OnServiceStatus<List<ResponseAirport>> {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    public RecyclerView listAirPort;
    ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    FancyButton btnBack;
    TextView btnMic;

    GetAirPortMaghsadAdapter mAdapter;
    private EditText searchtxt;
    AVLoadingIndicatorView avi;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_airport);
        avi = findViewById(R.id.avi);

        SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
        helper.setEdgeMode(false)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);

        btnBack = findViewById(R.id.btnBack);
        btnMic = findViewById(R.id.btnMic);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
       // btnMic.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        //btnMic.setText(getString(R.string.icon_mic));
        btnBack.setOnClickListener(this);
       // btnMic.setOnClickListener(this);
        //////////////////show recent
        if(getString(R.string.culture).contains("fa")){
            sendRequest("+++");
        }else{
            sendRequest("***");
        }
         listAirPort = findViewById(R.id.listAirPort);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listAirPort.setLayoutManager(mLayoutManager);
        listAirPort.setItemAnimator(new DefaultItemAnimator());
       // listAirPort.setAdapter(mAdapter);


        List<Airport> data = new ArrayList<>();
        RecentCity_Table recentCity_table = new RecentCity_Table(this);
        CursorManager cursorManager = recentCity_table.getAll(2);//maghsad
        if (cursorManager != null) {
            for (int i = 0; i < cursorManager.getCount(); i++) {
                cursorManager.moveToPosition(i);
                Airport airport = new Airport();

                airport.setTextFa(cursorManager.getString(RecentCity_Table.Columns.TextFa.value()));
                airport.setTesxt(cursorManager.getString(RecentCity_Table.Columns.Tesxt.value()));
                airport.setShortDes(cursorManager.getString(RecentCity_Table.Columns.ShortDes.value()));
                airport.setTag(cursorManager.getString(RecentCity_Table.Columns.Tag.value()));
                airport.setLongDes(cursorManager.getString(RecentCity_Table.Columns.LongDes.value()));
                airport.setIcon(cursorManager.getString(RecentCity_Table.Columns.Icon.value()));
                airport.setValue(cursorManager.getString(RecentCity_Table.Columns.AirPortCode.value()));
                if(cursorManager.getInt(RecentCity_Table.Columns.IsSelectable.value())==1)
                    airport.setSelectable(true);
                else
                    airport.setSelectable(false);

                data.add(airport);
            }
        }
        String Value_Mabda_City = "";
        String Value_Mabda_Airport = "";
        String Value_Mabda_Airport_Code = "";
        String Value_Mabda_Airport_Code2 = "";
        ////

        if (Prefs.getString("Value-Mabda-City", "") != null) {

            Value_Mabda_City = Prefs.getString("Value-Mabda-City", "");//Prefs.getString("Value-Maghsad-City", "");
            Value_Mabda_Airport = Prefs.getString("Value-Mabda-Airport", "");
            Value_Mabda_Airport_Code = Prefs.getString("Value-Mabda-Airport-Code", "");
            Value_Mabda_Airport_Code2 = Prefs.getString("Value-Mabda-Airport-Code2", "");
        }

        listAirPort = findViewById(R.id.listAirPort);
        mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, Value_Mabda_City, Value_Mabda_Airport,Value_Mabda_Airport_Code ,Value_Mabda_Airport_Code2, GetAirportMaghsadActivity.this);

        mAdapter.setData(data);
        listAirPort.setAdapter(mAdapter);

        //////////////////////////Remove recent
        CursorManager cursorManager1 = recentCity_table.getCountRow();
        System.out.println("count:" + cursorManager1.getInt("COUNT(Id)"));
        if (cursorManager1 != null) {
            if (cursorManager1.getInt("COUNT(Id)") >= 10) {

                CursorManager cursorType1 = recentCity_table.getAll(1);//mabda
                CursorManager cursorType2 = recentCity_table.getAll(2);//maghsad

                RecentCity_Table db = new RecentCity_Table(this);
                db.dropTable();
                db.openDB();

                if (cursorType1 != null)
                    for (int e = cursorType1.getCount() - 1; e >= 0; e--) {
                        cursorType1.moveToPosition(e);
                        //db.insertData(cursorType1.getString(RecentCity_Table.Columns.AirPortName.value()), cursorType1.getString(RecentCity_Table.Columns.CityName.value()), cursorType1.getString(RecentCity_Table.Columns.AirPortCode.value()), 1);//mabda
                        db.insertData(cursorType1.getString(RecentCity_Table.Columns.Tesxt.value()), cursorType1.getString(RecentCity_Table.Columns.TextFa.value()), cursorType1.getString(RecentCity_Table.Columns.ShortDes.value())
                                , cursorType1.getString(RecentCity_Table.Columns.LongDes.value()), cursorType1.getString(RecentCity_Table.Columns.Tag.value()), cursorType1.getString(RecentCity_Table.Columns.Icon.value())
                                , cursorType1.getString(RecentCity_Table.Columns.IconDown.value()), cursorType1.getInt(RecentCity_Table.Columns.IsSelectable.value())
                                , cursorType1.getString(RecentCity_Table.Columns.AirPortCode.value()),1);//mabda

                    }
                if (cursorType2 != null)
                    for (int t = cursorType2.getCount() - 1; t >= 0; t--) {
                        cursorType2.moveToPosition(t);
                       // db.insertData(cursorType2.getString(RecentCity_Table.Columns.AirPortName.value()), cursorType2.getString(RecentCity_Table.Columns.CityName.value()), cursorType2.getString(RecentCity_Table.Columns.AirPortCode.value()), 2);//maghsad
                        db.insertData(cursorType1.getString(RecentCity_Table.Columns.Tesxt.value()), cursorType1.getString(RecentCity_Table.Columns.TextFa.value()), cursorType1.getString(RecentCity_Table.Columns.ShortDes.value())
                                , cursorType1.getString(RecentCity_Table.Columns.LongDes.value()), cursorType1.getString(RecentCity_Table.Columns.Tag.value()), cursorType1.getString(RecentCity_Table.Columns.Icon.value())
                                , cursorType1.getString(RecentCity_Table.Columns.IconDown.value()), cursorType1.getInt(RecentCity_Table.Columns.IsSelectable.value())
                                , cursorType1.getString(RecentCity_Table.Columns.AirPortCode.value()),2);//maghsad
                }
                db.closeDB();

            }
        }

        /////////////////////////////
        searchtxt = findViewById(R.id.searchtxt);
        searchtxt.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    private Timer timer = new Timer();
                    private final long DELAY = 10; // milliseconds

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String d = s.toString().trim();
                                                if (d.length() > 2) {

                                                    GetAirportMaghsadActivity.searchText = d.toLowerCase();
                                                    //  new AsyncFetch().execute();
                                                    sendRequest(GetAirportMaghsadActivity.searchText);

                                                }else{
                                               
                                                    if(getString(R.string.culture).contains("fa")){
                                                        sendRequest("+++");
                                                    }else{
                                                        sendRequest("***");
                                                    }
                                                    if (d.length() < 0 || d.length() == 0) {
                                                        ////
                                                       // RecyclerView listAirPort = findViewById(R.id.listAirPort);
                                                        List<Airport> data = null;
                                                        mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, GetAirportMaghsadActivity.this);

                                                        mAdapter.setData(data);
                                                        listAirPort.setAdapter(mAdapter);

                                                    }
                                                }
                                            }
                                        });
                                    }
                                },
                                DELAY
                        );
                    }
                }
        );
    }//end oncreate

    @Override
    public boolean needTerminate() {
        return false;
    }

    private void sendRequest(String searchText) {
        avi.setVisibility(View.VISIBLE);

        AutoCompleteParameterModel requestAutoCompleteParameterModel = new AutoCompleteParameterModel();

        requestAutoCompleteParameterModel.setPart(searchText);

        SingletonService.getInstance().getFlight().newAirportsAvail(this, requestAutoCompleteParameterModel);
    }

    @Override
    public void onReady(List<ResponseAirport> responsAirports) {

        avi.setVisibility(View.GONE);
        String GetError = "";
        List<Airport> data = new ArrayList<Airport>();
     //   RecyclerView listAirPort;
        try {
           /* if (responsAirports != null) {
                GetError = responsAirports.get(0).get().get(0).getMessage();
            }*/
//            if (GetError.length() > 1) {
//
//                Toast.makeText(activity, GetError, Toast.LENGTH_SHORT).show();
//
//
//            } else {
            if (responsAirports !=null)
                for (int i = 0; i < responsAirports.size(); i++) {
                   // Airport fishData = new Airport();
                    Airport airport = new Airport();
                    airport.setTextFa(responsAirports.get(i).getTextFa());
                    airport.setTesxt(responsAirports.get(i).getText());//.getAirports().get(i).getAirportName());
                    airport.setShortDes(responsAirports.get(i).getShortDescription()); //.get(i).getAirportCode());
                    airport.setTag(responsAirports.get(i).getTag()); //.get(i).getAirportCode());
                    airport.setLongDes(responsAirports.get(i).getLongDescription()); //.get(i).getAirportCode());
                    airport.setIcon(responsAirports.get(i).getIcon()); //.get(i).getAirportCode());
                    airport.setSelectable(responsAirports.get(i).getIsSelectable()); //.get(i).getAirportCode());
                    airport.setValue(responsAirports.get(i).getValue()); //.get(i).getAirportCode());
                    // fishData.setAirportID(responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().get(i).getAirportID());
                  //  fishData.setParentId(responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().get(i).getParentId());

                    data.add(airport);
                }

                String Value_Mabda_City = "";
                String Value_Mabda_Airport = "";
                String Value_Mabda_Airport_Code = "";
                String Value_Mabda_Airport_Code2 = "";

                if (Prefs.getString("Value-Mabda-City", "") != null) {

                    Value_Mabda_City = Prefs.getString("Value-Mabda-City", "");//Prefs.getString("Value-Maghsad-City", "");
                    Value_Mabda_Airport = Prefs.getString("Value-Mabda-Airport", "");
                    Value_Mabda_Airport_Code = Prefs.getString("Value-Mabda-Airport-Code", "");
                    Value_Mabda_Airport_Code2 = Prefs.getString("Value-Mabda-Airport-Code2", "");
                }
               // listAirPort = findViewById(R.id.listAirPort);
                mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, Value_Mabda_City, Value_Mabda_Airport, Value_Mabda_Airport_Code,Value_Mabda_Airport_Code2, GetAirportMaghsadActivity.this);
                //mAdapter.setAdapter(mAdapter);
                mAdapter.setData(data);
                listAirPort.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
           // }
        } catch (Exception e) {
            if (!Utility.isNetworkAvailable(GetAirportMaghsadActivity.this)) {
                AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this,true,false);
                AlertDialogPassenger.setText(getString(R.string.InternetError), getString(R.string.massege));

            } else {
                try {
                    Toast.makeText(GetAirportMaghsadActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e2)
                {

                }
            }
        }
    }

    @Override
    public void onError(String message) {
        if (!Utility.isNetworkAvailable(GetAirportMaghsadActivity.this)) {
            AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this,true,false);
            AlertDialogPassenger.setText(getString(R.string.InternetError), getString(R.string.massege));
        } else {
            try {
                Toast.makeText(GetAirportMaghsadActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {

            }


        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnMic:
                searchtxt.setText("");

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, com.eligasht.reservation.tools.Prefs.getString("lang", "fa"));
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

    @Override
    public void searchTextChanged(String searchText) {


    }
}