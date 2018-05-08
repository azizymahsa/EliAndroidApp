package com.eligasht.reservation.views.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.eligasht.reservation.tools.Utility;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.airPort.Identity;
import com.eligasht.service.model.flight.request.airPort.Request;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.reservation.tools.Prefs;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;
import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.views.adapters.GetAirPortMaghsadAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

public class GetAirportMaghsadActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener, OnServiceStatus<ResponsAirports> {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    Handler handler;
    ProgressDialog progressBar;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport;
    ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    FancyButton btnBack, btnMic;

    GetAirPortMaghsadAdapter mAdapter;
    private EditText searchtxt;
    AVLoadingIndicatorView avi;
    Activity activity;
    private static final String TAG = "GetAirport";

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
        btnMic.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnMic.setText(getString(R.string.icon_mic));
        btnBack.setOnClickListener(this);
        btnMic.setOnClickListener(this);
        //////////////////show recent

        ListView listAirPort = findViewById(R.id.listAirPort);
        List<Country> data = new ArrayList<>();
        RecentCity_Table recentCity_table = new RecentCity_Table(this);
        CursorManager cursorManager = recentCity_table.getAll(2);//maghsad
        if (cursorManager != null) {
            for (int i = 0; i < cursorManager.getCount(); i++) {
                cursorManager.moveToPosition(i);
                Country fishData = new Country();
                fishData.setCityName(cursorManager.getString(RecentCity_Table.Columns.CityName.value()));
                fishData.setAirportName(cursorManager.getString(RecentCity_Table.Columns.AirPortName.value()));
                fishData.setAirportCode(cursorManager.getString(RecentCity_Table.Columns.AirPortCode.value()));
                fishData.setAirportID(cursorManager.getString(RecentCity_Table.Columns.AirPortCode.value()));
                fishData.setParentId(cursorManager.getString(RecentCity_Table.Columns.CityName.value()));

                data.add(fishData);
            }
        }
        String Value_Mabda_City = "";
        String Value_Mabda_Airport = "";
        String Value_Mabda_Airport_Code = "";
        ////

        if (Prefs.getString("Value-Mabda-City", "") != null) {

            Value_Mabda_City = Prefs.getString("Value-Mabda-City", "");//Prefs.getString("Value-Maghsad-City", "");
            Value_Mabda_Airport = Prefs.getString("Value-Mabda-Airport", "");
            Value_Mabda_Airport_Code = Prefs.getString("Value-Mabda-Airport-Code", "");
        }

        listAirPort = findViewById(R.id.listAirPort);
        mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, Value_Mabda_City, Value_Mabda_Airport, Value_Mabda_Airport_Code, GetAirportMaghsadActivity.this);

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
                        db.insertData(cursorType1.getString(RecentCity_Table.Columns.AirPortName.value()), cursorType1.getString(RecentCity_Table.Columns.CityName.value()), cursorType1.getString(RecentCity_Table.Columns.AirPortCode.value()), 1);//mabda
                    }
                if (cursorType2 != null)
                    for (int t = cursorType2.getCount() - 1; t >= 0; t--) {
                        cursorType2.moveToPosition(t);
                        db.insertData(cursorType2.getString(RecentCity_Table.Columns.AirPortName.value()), cursorType2.getString(RecentCity_Table.Columns.CityName.value()), cursorType2.getString(RecentCity_Table.Columns.AirPortCode.value()), 2);//maghsad
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

                                                }
                                                if (d.length() < 0 || d.length() == 0) {
                                                    ////
                                                    ListView listAirPort = findViewById(R.id.listAirPort);
                                                    List<Country> data = null;
                                                    mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, GetAirportMaghsadActivity.this);

                                                    mAdapter.setData(data);
                                                    listAirPort.setAdapter(mAdapter);

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

    private void sendRequest(String searchText) {
        avi.setVisibility(View.VISIBLE);

        RequestAirports requestAirports = new RequestAirports();
        Request request = new Request();

        Identity identity = new Identity();
        identity.setCode(searchText);
        request.setIdentity(identity);

        request.setCity("");
        request.setCulture(getString(R.string.culture));
        requestAirports.setRequest(request);

        SingletonService.getInstance().getFlight().airPortsAvail(this, requestAirports);
    }

    @Override
    public void onReady(ResponsAirports responsAirports) {
        avi.setVisibility(View.GONE);
        String GetError = "";
        List<Country> data = new ArrayList<Country>();
        ListView listAirPort;
        try {
            if (responsAirports.getGetAirportWithParentsWithCultureResult().getErrors() != null) {
                GetError = responsAirports.getGetAirportWithParentsWithCultureResult().getErrors().get(0).getMessage();
            }
            if (GetError.length() > 1) {
                AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this,false,false);
                AlertDialogPassenger.setText(GetError, getString(R.string.massege));

            } else {

                //AirportCode //AirportName//CityName ":
                // responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().
                for (int i = 0; i < responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().size(); i++) {
                    Country fishData = new Country();
                    fishData.setCityName(responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().get(i).getCityName());
                    fishData.setAirportName(responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().get(i).getAirportName());
                    fishData.setAirportCode(responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().get(i).getAirportCode());
                    fishData.setAirportID(responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().get(i).getAirportID());
                    fishData.setParentId(responsAirports.getGetAirportWithParentsWithCultureResult().getAirports().get(i).getParentId());

                    data.add(fishData);
                }


                String Value_Mabda_City = "";
                String Value_Mabda_Airport = "";
                String Value_Mabda_Airport_Code = "";
                ////

                if (Prefs.getString("Value-Mabda-City", "") != null) {

                    Value_Mabda_City = Prefs.getString("Value-Mabda-City", "");//Prefs.getString("Value-Maghsad-City", "");
                    Value_Mabda_Airport = Prefs.getString("Value-Mabda-Airport", "");
                    Value_Mabda_Airport_Code = Prefs.getString("Value-Mabda-Airport-Code", "");
                }


                listAirPort = findViewById(R.id.listAirPort);
                mAdapter = new GetAirPortMaghsadAdapter(GetAirportMaghsadActivity.this, data, Value_Mabda_City, Value_Mabda_Airport, Value_Mabda_Airport_Code, GetAirportMaghsadActivity.this);
                //mAdapter.setAdapter(mAdapter);
                mAdapter.setData(data);
                listAirPort.setAdapter(mAdapter);
            }
        } catch (Exception e) {
            if (!Utility.isNetworkAvailable(GetAirportMaghsadActivity.this)) {
                AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this,true,false);
                AlertDialogPassenger.setText(getString(R.string.InternetError), getString(R.string.massege));


            } else {
                AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this,true,false);
                AlertDialogPassenger.setText(getString(R.string.ErrorServer), getString(R.string.massege));


            }
        }
    }

    @Override
    public void onError(String message) {

        if (!Utility.isNetworkAvailable(GetAirportMaghsadActivity.this)) {
            AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this,true,false);
            AlertDialogPassenger.setText(getString(R.string.InternetError), getString(R.string.massege));


        } else {
            AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMaghsadActivity.this,true,false);
            AlertDialogPassenger.setText(getString(R.string.ErrorServer), getString(R.string.massege));


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