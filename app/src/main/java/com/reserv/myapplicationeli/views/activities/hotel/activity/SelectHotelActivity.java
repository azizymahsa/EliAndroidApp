package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels;
import com.reserv.myapplicationeli.views.adapters.hotel.LazyResoultHotelAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.dialog.FilterHotelDialog;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


public class SelectHotelActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListener, View.OnClickListener ,FilterHotelDialog.FilterHotelDialogListenerArray{


    private ListView list;
    private LazyResoultHotelAdapter adapter;
    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListBestSeler = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayLisOff = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArray1star = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArray2star = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArray3star = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArray4star = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArray5star = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayResort = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayApartment = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayBoutique = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayHotel = new ArrayList<>();
    private HotelAvailApi availApi;
    private List<Rooms> rooms = new ArrayList<>();
    RelativeLayout rlLoading, rlRoot;
    TextView tvAlert;
    Window window;
    RelativeLayout elNotFound;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog returnDatePicker;
    LinearLayout llBottom;
    boolean besetSeler = false;
    boolean bestOff = false;
    boolean remove = false;

    boolean b1star = false;
    boolean b2star = false;
    boolean b3star = false;
    boolean b4star = false;
    boolean b5star = false;

    boolean Resort = false;
    boolean Apartment = false;
    boolean Boutique = false;
    boolean Hotel = false;
    FancyButton btnOk;
    //TextView tvAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
        InitUi.Toolbar(this, false, R.color.flight_status, " چهارشنبه 28 اسفند-دوشنبه 5 فروردین ");
        window = getWindow();
        list = findViewById(R.id.lvHoteResult);
        llBottom = findViewById(R.id.llBottom);
        tvAlert = findViewById(R.id.tvAlert);
        elNotFound = findViewById(R.id.elNotFound);
        btnOk = findViewById(R.id.btnOk);



        llBottom.setOnClickListener(this);
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this);
        list.setAdapter(adapter);
        rooms.add(new Rooms(2, 0));


        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        Gson gson=new Gson();
       String data= gson.toJson(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                getIntent().getExtras().getString("CheckIn"), getIntent().getExtras().getString("CheckOut"), Prefs.getString("Value-Hotel-City-Code", ""), "DXB", rooms, "1,0,0,0,0,0", "fa-IR")));
        new GetHotelAsync().execute();
        Log.e("jjjjjjjjjjjjj", data);

        Log.e("raft", getIntent().getExtras().getString("CheckIn"));
        Log.e("bargasht", getIntent().getExtras().getString("CheckOut"));
        Log.e("cod", Prefs.getString("Value-Hotel-City-Code", ""));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SelectHotelActivity.this, DetailHotelActivity.class);
                i.putExtra("HotelId", selectHotelModelArrayList.get(position).geteHotelId());
                i.putExtra("ResultUniqID", selectHotelModelArrayList.get(position).getResultUniqID());
                i.putExtra("CheckIn", getIntent().getExtras().getString("CheckIn"));
                i.putExtra("CheckOut",getIntent().getExtras().getString("CheckOut"));

                startActivity(i);
            }
        });
        btnOk.setCustomTextFont("fonts/irsans.ttf");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }

    @Override
    public void onReturnValue(int type) {
       /* switch (type) {
            case 1:
                besetSeler = true;
                bestOff = false;
                remove = false;
                selectHotelModelArrayListBestSeler.clear();
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.isBestSell()) {
                        selectHotelModelArrayListBestSeler.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }


                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayListBestSeler, SelectHotelActivity.this, SelectHotelActivity.this);
                list.setAdapter(adapter);


                adapter.notifyDataSetChanged();

                break;
            case 2:
                besetSeler = false;
                remove = false;
                bestOff = true;
                selectHotelModelArrayLisOff.clear();
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.isOff()) {
                        selectHotelModelArrayLisOff.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }


                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayLisOff, SelectHotelActivity.this, SelectHotelActivity.this);
                list.setAdapter(adapter);


                adapter.notifyDataSetChanged();


                break;
            case 3:
                besetSeler = false;
                bestOff = false;
                remove = true;
                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;

            case 4:
                boolean b1star = true;

                filter_1star();

                break;
            case 5:
                b2star = true;


                filter_2star();
                break;

            case 6:
                b3star = true;

                filter_3star();
                break;
            case 7:
                b4star = true;

                filter_4star();

                break;

            case 8:
                b5star = true;

                filter_5star();
                break;
            case 9:
                filter_Resort();



                break;
            case 10:
                filter_Boutique();
                break;
            case 11:
                filter_Apartment();

                break;
            case 12:
                filter_Hotel();
                break;

        }*/


    }


    public void filter_1star() {
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getStar() == 1) {
                selectHotelModelArray1star.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }

        }
        adapter = new LazyResoultHotelAdapter(selectHotelModelArray1star, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void filter_2star() {

        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getStar() == 2) {
                selectHotelModelArray2star.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }

        }
        adapter = new LazyResoultHotelAdapter(selectHotelModelArray2star, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void filter_3star() {
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getStar() == 3) {
                selectHotelModelArray3star.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }

        }
        adapter = new LazyResoultHotelAdapter(selectHotelModelArray3star, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void filter_4star() {
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getStar() == 4) {
                selectHotelModelArray4star.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }

        }
        adapter = new LazyResoultHotelAdapter(selectHotelModelArray4star, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void filter_5star() {
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getStar() == 5) {
                selectHotelModelArray5star.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }

        }
        adapter = new LazyResoultHotelAdapter(selectHotelModelArray5star, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void filter_Resort() {
        selectHotelModelArrayResort.clear();
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getTypeText().equals("Resort")) {
                selectHotelModelArrayResort.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }


        }


        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayResort, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);


        adapter.notifyDataSetChanged();

    }

    public void filter_Apartment() {
        selectHotelModelArrayApartment.clear();
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getTypeText().equals("Apartment")) {
                selectHotelModelArrayApartment.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }


        }


        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayApartment, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }

    public void filter_Boutique() {
        selectHotelModelArrayBoutique.clear();
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getTypeText().equals("Boutique")) {
                selectHotelModelArrayBoutique.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }


        }


        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayBoutique, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }

    public void filter_Hotel() {
        selectHotelModelArrayHotel.clear();
        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

            if (selectHotelModel.getTypeText().equals("Hotel")) {
                selectHotelModelArrayHotel.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

            }


        }


        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayHotel, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBottom:
                new FilterHotelDialog(SelectHotelActivity.this, this, filterModels,this);


                break;
        }
    }

    @Override
    public void onReturnValue(ArrayList<FilterModel> type) {
        this.filterModels=type;
        selectHotelModelArrayListFilter.clear();
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayListFilter, SelectHotelActivity.this, SelectHotelActivity.this);
        list.setAdapter(adapter);

        for (FilterModel filterModel : filterModels) {
            if (filterModel.isStar1()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 1) {
                        selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }

                }

            }
            if (filterModel.isStar2()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 2) {
                        selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }

                }


            }
            if (filterModel.isStar3()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 3) {
                        selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }

                }


            }
            if (filterModel.isStar4()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 4) {
                        selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }

                }


            }
            if (filterModel.isStar5()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 5) {
                        selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }

                }


            }


            if (filterModel.isBestSeler()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.isBestSell()) {
                        selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }

                }


                if (filterModel.isBestOff()) {
                    for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                        if (selectHotelModel.isOff()) {
                            selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                    selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                    selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                    selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                        }

                    }


                    if (filterModel.isApartment()) {
                        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                            if (selectHotelModel.getTypeText().equals("Apartment")) {
                                selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                            }

                        }


                    }
                    if (filterModel.isBoutique()) {
                        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                            if (selectHotelModel.getTypeText().equals("Boutique")) {
                                selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                            }

                        }


                    }
                    if (filterModel.isHotel()) {
                        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                            if (selectHotelModel.getTypeText().equals("Hotel")) {
                                selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                            }

                        }


                    }


                    if (filterModel.isResort()) {
                        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                            if (selectHotelModel.getTypeText().equals("Resort")) {
                                selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                            }

                        }


                    }

                }
            }


            adapter.notifyDataSetChanged();


        }


    }


    private class GetHotelAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            window.setStatusBarColor(getColor(R.color.blue2));

            new InitUi().Loading(rlLoading, rlRoot, true);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                availApi = new HotelAvailApi(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        getIntent().getExtras().getString("CheckIn"), getIntent().getExtras().getString("CheckOut"), Prefs.getString("Value-Hotel-City-Code", ""), "DXB", rooms, "1,0,0,0,0,0", "fa-IR")));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(rlLoading, rlRoot, false);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));


            try {
                if (availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Hotels.isEmpty()){
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText("نتیجه ای برای جستجو شما حاصل نشد !");
                    list.setVisibility(View.GONE);
                }
                int i = 0;
                for (Hotels hotels : availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Hotels) {
                    String off = "";
                    boolean isOff = false;


                    if ((hotels.Availability.RoomLists.get(i).OldPrice > 0) &&
                            (hotels.Availability.RoomLists.get(i).OldPrice > Integer.valueOf(hotels.Availability.RoomLists.get(i).Price))) {


                        int p1 = hotels.Availability.RoomLists.get(i).OldPrice - Integer.valueOf(hotels.Availability.RoomLists.get(i).Price);
                        int p2 = p1 * 100;
                        int p3 = p2 / hotels.Availability.RoomLists.get(i).OldPrice;


                        if (p3 != 0) {
                            isOff = true;

                            off = p3 + "%\nتخفیف";


                        }
                    }


                    selectHotelModelArrayList.add(new SelectHotelModel(hotels.Name, hotels.City, hotels.Availability.RoomLists.get(i).Title,
                            hotels.Availability.RoomLists.get(i).Board, hotels.Availability.RoomLists.get(i).Price, hotels.MainImage, hotels.Location,
                            hotels.Availability.RoomLists.get(i).OldPrice, hotels.StarRating,
                            hotels.Availability.RoomLists.get(i).EHotelId, availApi.hotelAvailModelResponse.HotelAvailResult.ResultUniqID, hotels.BestSell, isOff, off, hotels.TypeText));


                    Log.e("hotels.BestSell", hotels.BestSell + "");
                    //   i++;

                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
               // Toast.makeText(SelectHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show()
list.setVisibility(View.GONE);
              elNotFound.setVisibility(View.VISIBLE);
               tvAlert.setText("خطا در برقراری ارتباط");
              //  finish();
            }


        }

    }

}
