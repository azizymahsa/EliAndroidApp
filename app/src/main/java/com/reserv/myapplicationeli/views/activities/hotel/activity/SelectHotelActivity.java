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
import com.reserv.myapplicationeli.models.model.pack.SearchXPackageResult;
import com.reserv.myapplicationeli.models.model.pack.call.PackageListReq;
import com.reserv.myapplicationeli.models.model.pack.call.PackageRequestModel;
import com.reserv.myapplicationeli.models.model.pack.response.PackageListRes;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.activities.pack.SearchPackActivity;
import com.reserv.myapplicationeli.views.adapters.hotel.LazyResoultHotelAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.FilterHotelDialog;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectHotelActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListenerArray, View.OnClickListener {


    private ListView list;
    private LazyResoultHotelAdapter adapter;
    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private HotelAvailApi availApi;
    private List<Rooms> rooms = new ArrayList<>();
    RelativeLayout rlLoading, rlRoot;
    TextView tvAlert, tvTitle, tvDate,tvCount;
    Window window;
    RelativeLayout elNotFound;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog returnDatePicker;
    LinearLayout llBottom;

    FancyButton btnOk, btnBack,btnHome;
    //TextView tvAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
        //InitUi.Toolbar(this, false, R.color.flight_status, " چهارشنبه 28 اسفند-دوشنبه 5 فروردین ");
        window = getWindow();
        list = findViewById(R.id.lvHoteResult);
        llBottom = findViewById(R.id.llBottom);
        tvAlert = findViewById(R.id.tvAlert);
        tvTitle = findViewById(R.id.tvTitle);
        tvCount = findViewById(R.id.tvCount);
        btnBack = findViewById(R.id.btnBack);
        btnHome = findViewById(R.id.btnHome);
        elNotFound = findViewById(R.id.elNotFound);
        btnOk = findViewById(R.id.btnOk);
        tvDate = findViewById(R.id.tvDate);
        btnHome.setOnClickListener(this);


        llBottom.setOnClickListener(this);
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this);
        list.setAdapter(adapter);

        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(this);

        tvDate.setText("از تاریخ: "+getIntent().getExtras().getString("CheckInFa")+" تا تاریخ: "+getIntent().getExtras().getString("CheckOutFa"));
        rooms.add(new Rooms(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));


        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        Gson gson = new Gson();

        new GetHotelAsync().execute();

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
                i.putExtra("CheckOut", getIntent().getExtras().getString("CheckOut"));

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


//        Log.e("testtt",getIntent().getExtras().getString("RoomList"));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBottom:
                new FilterHotelDialog(SelectHotelActivity.this, filterModels, this);


                break;
            case R.id.btnBack:
                finish();
                break;
                case R.id.btnHome:
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onReturnValue(ArrayList<FilterModel> type) {
        this.filterModels = type;
        selectHotelModelArrayListFilter.clear();


        for (FilterModel filterModel : filterModels) {

            Log.e("test", filterModel.isStar3() + "===" + filterModel.isRemove());
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



            if (filterModel.isRemove()) {
                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this);
            }


        }

        if (selectHotelModelArrayListFilter.isEmpty()) {
            Toast.makeText(this, "موردی یافت نشد", Toast.LENGTH_SHORT).show();

            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this);

        } else {
            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayListFilter, SelectHotelActivity.this, SelectHotelActivity.this);
            tvCount.setText("("+selectHotelModelArrayListFilter.size()+"مورد یافت شد"+")");


        }
        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }


    private class GetHotelAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            window.setStatusBarColor(getColor(R.color.blue2));

            new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                availApi = new HotelAvailApi(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        getIntent().getExtras().getString("CheckIn"), getIntent().getExtras().getString("CheckOut"), Prefs.getString("Value-Hotel-City-Code", ""), "DXB", rooms, getIntent().getExtras().getString("Rooms"), "fa-IR")));


                Gson gson= new Gson();

                Log.e("test", gson.toJson(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        getIntent().getExtras().getString("CheckIn"), getIntent().getExtras().getString("CheckOut"), Prefs.getString("Value-Hotel-City-Code", ""), "DXB", rooms, getIntent().getExtras().getString("Rooms"), "fa-IR"))));
            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));


            try {
                if (availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Hotels.isEmpty()) {
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

                tvTitle.setText(Prefs.getString("Value-Hotel-City-Fa", ""));
                tvCount.setText("("+selectHotelModelArrayList.size()+"مورد یافت شد"+")");
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
    private void getPackages(String country, String departureFrom, String departureTo, String roomList, String culture) {

        PackageListReq packageListReq = new PackageListReq();

        packageListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        packageListReq.setCountry(country);
        packageListReq.setRoomList(roomList);
        packageListReq.setDepartureFrom(departureFrom);
        packageListReq.setDepartureTo(departureTo);
        packageListReq.setCulture(culture);
        Log.e("roomlist", roomList);

    }

}
