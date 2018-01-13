package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.reserv.myapplicationeli.views.ui.dialog.hotel.SortDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectHotelActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListenerArray, View.OnClickListener,SortDialog.SortHotelDialogListener {


    private ListView list;
    private LazyResoultHotelAdapter adapter;
    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListFilter1 = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private HotelAvailApi availApi;
    private List<Rooms> rooms = new ArrayList<>();
    RelativeLayout rlLoading, rlRoot;
    TextView tvAlert, tvTitle, tvDate, tvCount, tvFilterIcon, tvFilter,tvSortIcon,tvSort;
    Window window;
    RelativeLayout elNotFound;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog returnDatePicker;
    LinearLayout llBottom,llSort;

    FancyButton btnOk, btnBack, btnHome;
    private Runnable runnable,runnable2;
    private Handler handler,handler2;
    ImageView ivLoading;

    boolean isFilter=false;
    //TextView tvAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
        //InitUi.Toolbar(this, false, R.color.flight_status, " چهارشنبه 28 اسفند-دوشنبه 5 فروردین ");
        window = getWindow();
        list = findViewById(R.id.lvHoteResult);
        llBottom = findViewById(R.id.llBottom);
        llSort = findViewById(R.id.llSort);
        tvAlert = findViewById(R.id.tvAlert);
        tvTitle = findViewById(R.id.tvTitle);
        tvCount = findViewById(R.id.tvCount);
        btnBack = findViewById(R.id.btnBack);
        btnHome = findViewById(R.id.btnHome);
        ivLoading = findViewById(R.id.ivLoading);
        tvFilterIcon = findViewById(R.id.tvFilterIcon);
        btnHome = findViewById(R.id.btnHome);
        tvSortIcon = findViewById(R.id.tvSortIcon);
        tvSort = findViewById(R.id.tvSort);
        tvFilter = findViewById(R.id.tvFilter);
        btnOk = findViewById(R.id.btnOk);
        tvDate = findViewById(R.id.tvDate);
        btnHome.setOnClickListener(this);


        llBottom.setOnClickListener(this);
        llSort.setOnClickListener(this);
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this);
        list.setAdapter(adapter);

        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(this);

        tvDate.setText("از تاریخ: " + getIntent().getExtras().getString("CheckInFa") + " تا تاریخ: " + getIntent().getExtras().getString("CheckOutFa"));
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


// TODO: 1/12/2018 change this







        final int[] imageArray2 = new int[]{
                R.drawable.small_01,
                R.drawable.small_02,
                R.drawable.small_03,
                R.drawable.small_04,
                R.drawable.small_05,
                R.drawable.small_06,
                R.drawable.small_07,
                R.drawable.small_08,
                R.drawable.small_09,
                R.drawable.small_10,
                R.drawable.small_11,
                R.drawable.small_12,
                R.drawable.small_13,
                R.drawable.small_14,
                R.drawable.small_15,
                R.drawable.small_16,
                R.drawable.small_17,
                R.drawable.small_18,
                R.drawable.small_19,
                R.drawable.small_20,
                R.drawable.small_21,
                R.drawable.small_22,
                R.drawable.small_23,
                R.drawable.small_24};



        handler2 = new Handler();
        runnable2 = new Runnable() {
            int i = 0;

            public void run() {
                ivLoading.setImageResource(imageArray2[i]);
                i++;
                if (i > imageArray2.length - 1) {
                    i = 0;

                }

                handler2.postDelayed(this, 50);  //for interval...
            }
        };
        handler2.postDelayed(runnable2, 100); //for initial delay..






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler2.removeCallbacks(runnable2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBottom:
                new FilterHotelDialog(SelectHotelActivity.this, filterModels, this);


                break;
                case R.id.llSort:
                new SortDialog(SelectHotelActivity.this, this);


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
    public void onReturnValue(ArrayList<FilterModel> type,String search) {







        this.filterModels = type;
        selectHotelModelArrayListFilter=new ArrayList<>();
        selectHotelModelArrayListFilter1=new ArrayList<>();


        for (FilterModel filterModel : filterModels) {

            Log.e("test", filterModel.isStar3() + "===" + filterModel.isRemove());


            top_filter(filterModel);
            star_filter(filterModel);
            type_location_filter(filterModel);


            if ((filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4() || filterModel.isStar5()) && (filterModel.isBestSeler() || filterModel.isBestOff())) {

                isBestOff_and_isStar(filterModel);
                isBestSell_and_isStar(filterModel);
               selectHotelModelArrayListFilter=new ArrayList<>();
               selectHotelModelArrayListFilter = selectHotelModelArrayListFilter1;



            }




            if ((filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4()
                    || filterModel.isStar5()||filterModel.isBestSeler() || filterModel.isBestOff())
                    ||filterModel.isBoutique()||filterModel.isApartment()||filterModel.isHotel()||filterModel.isBoutique() ) {


            }










            if ((filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4() || filterModel.isStar5()||filterModel.isBestSeler() || filterModel.isBestOff())
                    ||filterModel.isBoutique()||filterModel.isApartment()||filterModel.isHotel()||filterModel.isBoutique() ) {

                if (search!=null) {
                    for (Iterator<SelectHotelModel> it = selectHotelModelArrayListFilter.iterator(); it.hasNext(); ) {
                        if (!it.next().getName().contains(search)) {
                            it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
                        }


                    }
                }
            }else{
                if (search!=null) {
                    for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                        if (selectHotelModel.getName().contains(search)) {
                            selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                    selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                    selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                    selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));
                        }

                    }
                }

            }



            if (filterModel.isRemove()) {
                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this);
            }


        }

        if (selectHotelModelArrayListFilter.isEmpty()) {
            isFilter=false;
            Toast.makeText(this, "موردی یافت نشد", Toast.LENGTH_SHORT).show();
            tvFilter.setTextColor(ContextCompat.getColor(this,R.color.textColorR));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this,R.color.textColorR));

            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this);

        } else {
            isFilter=true;
            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayListFilter, SelectHotelActivity.this, SelectHotelActivity.this);
            tvCount.setText("(" + selectHotelModelArrayListFilter.size() + "مورد یافت شد" + ")");
            tvFilter.setTextColor(ContextCompat.getColor(this,R.color.red));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this,R.color.red));


        }
        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();



    }

    public void top_filter(FilterModel filterModel) {
        if (filterModel.isBestSeler()) {
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.isBestSell()) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));
                }


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


    }

    public void type_location_filter(FilterModel filterModel) {
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

    public void star_filter(FilterModel filterModel) {


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
    }

    public void isBestOff_and_isStar(FilterModel filterModel) {
        if (filterModel.isBestOff()) {


            if (filterModel.isStar1()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 1 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }
            }
            //===============================================================================
            if (filterModel.isStar2()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 2 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }
            }
            //===============================================================================
            if (filterModel.isStar3()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 3 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }


            }


            //================================================================================
            if (filterModel.isStar4()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 4 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }

            }
            //===============================================================================

            if (filterModel.isStar5()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 5 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }

            }


            //===============================================================================


        }



    }

    public void isBestSell_and_isStar(FilterModel filterModel) {
        if (filterModel.isBestSeler()) {


            if (filterModel.isStar1()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 1 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }


            }
            //===============================================================================
            if (filterModel.isStar2()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 2 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }


            }
            //===============================================================================
            if (filterModel.isStar3()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 3 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }


            }


            //================================================================================
            if (filterModel.isStar4()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 4 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }

            }
            //===============================================================================

            if (filterModel.isStar5()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 5 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText()));

                    }


                }

            }


            //===============================================================================


        }


    }

    @Override
    public void onReturnValue(int type) {
        tvSort.setTextColor(ContextCompat.getColor(this,R.color.red));
        tvSortIcon.setTextColor(ContextCompat.getColor(this,R.color.red));
        switch (type){
            case 1:
                Collections.sort(selectHotelModelArrayList, new Comparator< SelectHotelModel >() {
                    @Override public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p2.getPrice())- Integer.valueOf(p1.getPrice()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator< SelectHotelModel >() {
                    @Override public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p2.getPrice())- Integer.valueOf(p1.getPrice()); // Ascending
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(selectHotelModelArrayList, new Comparator< SelectHotelModel >() {
                    @Override public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice())- Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator< SelectHotelModel >() {
                    @Override public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice())- Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
                adapter.notifyDataSetChanged();
                break;
        }
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


                Gson gson = new Gson();

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
                tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");
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
