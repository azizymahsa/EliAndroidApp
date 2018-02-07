package com.reserv.myapplicationeli.views.fragments.pack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.onesignal.OneSignal;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.HotelCity;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.models.model.pack.ChildModel;
import com.reserv.myapplicationeli.models.model.pack.call.CityListRq;
import com.reserv.myapplicationeli.models.model.pack.call.CityRequestModel;
import com.reserv.myapplicationeli.models.model.pack.response.CityListRes;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.WebUserTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.activities.AddRoomActivity;
import com.reserv.myapplicationeli.views.activities.pack.SearchPackActivity;
import com.reserv.myapplicationeli.views.adapters.pack.CitySpinnerAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * Created by elham.bonyani on 1/2/2018.
 */

public class PackageFragment extends Fragment implements View.OnClickListener,
        TimePickerDialog.OnTimeSetListener,
        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener,
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {


    public ViewGroup view;
    public ViewGroup layout_room;
    public Spinner spn_cities;
    public AVLoadingIndicatorView prg_cities;
    public Button btnSearchPackage;
    public LinearLayout btn_return_date;
    public LinearLayout btn_depart_date,linear_picker_depart,linear_picker_return;
    private final int ADD_ROOM_REQUEST = 100;
    private ClientService service;
    DatePickerDialog datePickerDialogDepart, datePickerDialogReturn;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogDepartgGregorian, datePickerDialogReturnGregorian;
    private Gson gson;
    private ArrayList<ModelRowCountRoom> roomsSelected;
    private TextView txt_count_adult;
    private TextView txt_count_child;
    private TextView txt_count_room;
    private HotelCity hotelCity;
    private String departureFrom;
    private String departureTo;
    private CitySpinnerAdapter citySpinnerAdapter;
    int month;
    int year_;
    int day;
    int monthMin;
    int year_Min;
    int dayMin;
    TextView txt_return_date;
    TextView txt_depart_date;
    boolean geo = false;

    public static PackageFragment instance() {
        PackageFragment fragment = new PackageFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = (ViewGroup) inflater.inflate(R.layout.fragment_package, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        Utility.sendTag("P",true,false);

        initViews();
        initParam();
        service = ServiceGenerator.createService(ClientService.class);
        getCities();
        try {
            roomsSelected = gson.fromJson(Prefs.getString("Rooms", "dd"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());

            txt_count_adult.setText(String.valueOf(getCountAdult(roomsSelected)));
            txt_count_child.setText(String.valueOf(getCountChild(roomsSelected)));
            txt_count_room.setText(String.valueOf(getCountRooms(roomsSelected)));

        }catch (Exception e){}

        return view;
    }
    //send request to server for get cities os spinner
    private void getCities() {
        showLoading();
        Call<CityListRes> call = service.getCityListResult(new CityRequestModel(new CityListRq(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile",Prefs.getString("userId","-1")))));
        call.enqueue(new Callback<CityListRes>() {
            @Override
            public void onResponse(Call<CityListRes> call, Response<CityListRes> response) {
                hideLoading();
                if (response == null || response.body() == null) {
                    needShowAlertDialog("خطا در ارتباط", true);
                    return;
                }

                if (response.body().getGetHotelListResult() == null || response.body().getGetHotelListResult().getCities() == null) {
                    needShowAlertDialog("شهری برای نمایش وجود ندارد", true);
                    return;
                }
try{  citySpinnerAdapter = new CitySpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, response.body().getGetHotelListResult().getCities());
    spn_cities.setAdapter(citySpinnerAdapter);}catch (Exception e){}

            }

            @Override
            public void onFailure(Call<CityListRes> call, Throwable t) {
                try {
                    hideLoading();
                    needShowAlertDialog("خطا در ارتباط", true);
                } catch (Exception e) {
                }
            }
        });


    }

    private void initViews() {

        layout_room = (ViewGroup) view.findViewById(R.id.layout_room);
        spn_cities = (Spinner) view.findViewById(R.id.spn_cities);
        prg_cities = (AVLoadingIndicatorView) view.findViewById(R.id.prg_cities);
        btnSearchPackage = (Button) view.findViewById(R.id.btnSearchPackage);
        btn_return_date = (LinearLayout) view.findViewById(R.id.btn_return_date);
        btn_depart_date = (LinearLayout) view.findViewById(R.id.btn_depart_date);
        txt_count_adult = (TextView) view.findViewById(R.id.txt_count_adult);
        txt_count_child = (TextView) view.findViewById(R.id.txt_count_child);
        txt_count_room = (TextView) view.findViewById(R.id.txt_count_room);
        txt_return_date = view.findViewById(R.id.txt_return_date);
        txt_depart_date = view.findViewById(R.id.txt_depart_date);
        linear_picker_depart = view.findViewById(R.id.linear_picker_depart);
        linear_picker_return = view.findViewById(R.id.linear_picker_return);


        String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy/MM/dd");
        departureFrom = currentDateTime;
        departureTo = currentDateTime;

        int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy/M/dd", true);
        int currentYear = DateUtil.getYear(currentDateTime, "yyyy/MM/dd", true);
        int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy/MM/dd", true) - 1;



        if (Prefs.getString("bargashtfa","null").equals("null")){
            txt_return_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", true));

        }else{
            txt_return_date.setText(Prefs.getString("bargashtfa","null"));

            departureTo=Prefs.getString("bargasht","null");

        }




        if (Prefs.getString("raftfa","null").equals("null")){
            txt_depart_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", true));


        }else{
            txt_depart_date.setText(Prefs.getString("raftfa","null"));

            departureFrom=Prefs.getString("raft","null");
        }





        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        persianCalendarDatePicker.setPersianDate(currentYear, currentMonth, currentDay);

        //milladi
        datePickerDialogReturnGregorian = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();
        datePickerDialogDepartgGregorian = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();
        //milladi
        datePickerDialogReturnGregorian.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        datePickerDialogDepartgGregorian.setMinDate(persianCalendarDatePicker.toGregorianCalendar());

        datePickerDialogDepart = DatePickerDialog.newInstance(
                this,
                currentYear,
                currentMonth,
                currentDay
        );
        datePickerDialogReturn = DatePickerDialog.newInstance(
                this,
                currentYear,
                currentMonth,
                currentDay
        );

        ///milladi
        datePickerDialogDepart.setMinDate(persianCalendarDatePicker);
        datePickerDialogReturn.setMinDate(persianCalendarDatePicker);

        datePickerDialogDepart.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogDepartgGregorian.show(getActivity().getFragmentManager(), "DepartureFromGregorian");
            }
        });

        datePickerDialogReturn.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogReturnGregorian.show(getActivity().getFragmentManager(), "DepartureToGregorian");
            }
        });





        datePickerDialogDepartgGregorian.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogDepart.show(getActivity().getSupportFragmentManager(), "DepartureFrom");
            }
        });

        datePickerDialogReturnGregorian.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogReturn.show(getActivity().getSupportFragmentManager(), "DepartureTo");
            }
        });







        datePickerDialogReturnGregorian.setOnDateSetListener(this);
        datePickerDialogDepartgGregorian.setOnDateSetListener(this);


        gson = new GsonBuilder().create();

        layout_room.setOnClickListener(this);
        btnSearchPackage.setOnClickListener(this);
        linear_picker_return.setOnClickListener(this);
        linear_picker_depart.setOnClickListener(this);

        spn_cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                hotelCity = (HotelCity) spn_cities.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        datePickerDialogReturn.setTitle("تاریخ برگشت را انتخاب نمایید");

    }

    private void initParam() {

    }

    private void showLoading() {
        spn_cities.setVisibility(View.GONE);
        prg_cities.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        prg_cities.setVisibility(View.GONE);
        spn_cities.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_room:
                Gson gson = new GsonBuilder().create();
                Intent intent = new Intent(getActivity(), AddRoomActivity.class);
                intent.putExtra("roomList", Prefs.getString("Rooms", "dd"));
                startActivityForResult(intent, ADD_ROOM_REQUEST);
                break;

            case R.id.btnSearchPackage:
                if (citySpinnerAdapter == null) {
                    Toast.makeText(getActivity(), "ابتدا شهر مورد نظر را انتخاب نمایید", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txt_depart_date.getText().toString().equals("انتخاب کنید") && txt_return_date.getText().toString().equals("انتخاب کنید")) {
                    Toast.makeText(getActivity(), "تاریخ رفت و برگشت را انتخاب کنید", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (txt_depart_date.getText().toString().equals("انتخاب کنید")) {
                        Toast.makeText(getActivity(), "تاریخ رفت ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (txt_return_date.getText().toString().equals("انتخاب کنید")) {
                        Toast.makeText(getActivity(), "تاریخ برگشت ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Intent _intent = new Intent(getActivity(), SearchPackActivity.class);
                _intent.putExtra("DepartureFrom", departureFrom);
                _intent.putExtra("DepartureTo", departureTo);
                _intent.putExtra("RoomList", getRoomList(roomsSelected));
                _intent.putExtra("Culture", "fa-IR");
                _intent.putExtra("Country", String.valueOf(hotelCity.getCityID()));
                _intent.putExtra("CityName", String.valueOf(ValidationTools.isEmptyOrNull(hotelCity.getCityNameFa()) ? hotelCity.getCityNameEn() : hotelCity.getCityNameFa()));
                startActivity(_intent);
                break;

            case R.id.linear_picker_depart:
                if (geo){

                    datePickerDialogDepartgGregorian.show(getActivity().getFragmentManager(), "DepartureFromGregorian");

                }else{
                    datePickerDialogDepart.show(getActivity().getSupportFragmentManager(), "DepartureFrom");

                }
                break;

            case R.id.linear_picker_return:
                if (geo){

                    datePickerDialogReturnGregorian.show(getActivity().getFragmentManager(), "DepartureToGregorian");


                }else{
                    datePickerDialogReturn.show(getActivity().getSupportFragmentManager(), "DepartureTo");

                }

                break;
        }
    }

    //for get list that rooms contents of adult's count and age of children
    private String getRoomList(ArrayList<ModelRowCountRoom> roomsSelected) {
        String roomList = "";
        if (ValidationTools.isEmptyOrNull(roomsSelected)) {
            return "1,0,0,0,0,0";
        }

        for (ModelRowCountRoom room : roomsSelected) {
            roomList = roomList + room.getCountB() + ",";

            if (ValidationTools.isEmptyOrNull(room.getChildModels())) {
                roomList = roomList + "0,0,0,0,0";
                if (roomsSelected.indexOf(room) != (roomsSelected.size() - 1)) {
                    roomList = roomList + "|";
                }
                continue;
            }
            for (ChildModel childModel : room.getChildModels()) {
                roomList = roomList + childModel.getChildAgeRange().getValue();
                if (room.getChildModels().indexOf(childModel) != (room.getChildModels().size() - 1)) {
                    roomList = roomList + ",";
                }
            }
            if (room.getChildModels().size() < 5) {
                for (int i = 0; i < (5 - room.getChildModels().size()); i++) {
                    roomList = roomList + ",0";
                }
            }

            if (roomsSelected.indexOf(room) != (roomsSelected.size() - 1)) {
                roomList = roomList + "|";
            }

        }

        return roomList;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == ADD_ROOM_REQUEST && resultCode == RESULT_OK && intent != null) {
            Bundle bundle = intent.getExtras();
            roomsSelected = gson.fromJson(bundle.getString("Rooms"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());

            txt_count_adult.setText(String.valueOf(getCountAdult(roomsSelected)));
            txt_count_child.setText(String.valueOf(getCountChild(roomsSelected)));
            txt_count_room.setText(String.valueOf(getCountRooms(roomsSelected)));

        }
    }

    private int getCountRooms(ArrayList<ModelRowCountRoom> rooms) {
        if (ValidationTools.isEmptyOrNull(rooms)) {
            return 0;
        }

        return rooms.size();
    }

    private int getCountAdult(ArrayList<ModelRowCountRoom> rooms) {
        if (ValidationTools.isEmptyOrNull(rooms)) {
            return 0;
        }

        int count = 0;
        for (ModelRowCountRoom room : rooms) {
            count = count + room.getCountB();
        }
        return count;
    }

    private int getCountChild(ArrayList<ModelRowCountRoom> rooms) {
        if (ValidationTools.isEmptyOrNull(rooms)) {
            return 0;
        }

        int count = 0;
        for (ModelRowCountRoom room : rooms) {
            count = count + room.getCountK();
        }
        return count;
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }


    AlertDialog mAlertDialog;

    public void needShowAlertDialog(String message, boolean canelable) {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            return;
        }
        mAlertDialog = new AlertDialog.Builder(getActivity()).create();
        final LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_net, null);
        mAlertDialog.setCancelable(canelable);
        FancyButton btnOk = (FancyButton) view.findViewById(R.id.btnOk);
        TextView tvAlert = (TextView) view.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont("iran_sans_normal.ttf");
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

    //Shamsi
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        geo = false;

        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;

        long milis = DateUtil.getMiliSecondPersianDateTime(year, monthOfYear, dayOfMonth);
        String currentDateTime = DateUtil.getDateTime(String.valueOf(milis), "yyyy/MM/dd");

        if (view.getTag().equals("DepartureTo")) {
            txt_return_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", true));
            departureTo = currentDateTime;

            Prefs.putString("bargashtfa",txt_return_date.getText().toString());
            Prefs.putString("bargasht", departureTo);
        }


        if (view.getTag().equals("DepartureFrom")) {
            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            txt_depart_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", true));
        //    txt_return_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy-MM-dd", true));
            departureFrom = currentDateTime;
            PersianCalendar persianCalendarDatePicker = new PersianCalendar();
            persianCalendarDatePicker.setPersianDate(year_Min, monthMin, dayMin);



            if (Utility.campareDate(departureFrom,departureTo)){
                txt_return_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", true));
                //     datePickerDialog2.initialize(this, year_, month, day);

                datePickerDialogReturn = DatePickerDialog.newInstance(
                        this,
                        year_Min,
                        monthMin,
                        dayMin
                );
                datePickerDialogReturn.setMinDate(persianCalendarDatePicker);
            }






            Prefs.putString("bargashtfa",departureFrom);

            Prefs.putString("raft",  departureFrom);
            Prefs.putString("raftfa",  txt_depart_date.getText().toString());
        }
    }


    // miladi
    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {


        geo = true;
        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;

        String currentDateTime = year + "-" + monthOfYear + 1 + "-" + dayOfMonth;//2018-01-16;

        if (view.getTag().equals("DepartureToGregorian")) {
            txt_return_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", false));
            departureTo = currentDateTime;
            Prefs.putString("bargashtfa",txt_return_date.getText().toString());
            Prefs.putString("bargasht", departureTo);
        }


        if (view.getTag().equals("DepartureFromGregorian")) {
            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            txt_depart_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", false));
            txt_return_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy/MM/dd", false));
            departureFrom = currentDateTime;
            Calendar cal = Calendar.getInstance();
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("yyyy/MM/dd");
            try {
                date = (Date) formatter.parse(currentDateTime);
                cal.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            datePickerDialogReturnGregorian.initialize(  this,
                    year_Min,
                    monthMin,
                    dayMin);

            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(year_Min, monthMin, dayMin);
            datePickerDialogReturnGregorian.setMinDate(persianCalendarDatePicker2);

            Prefs.putString("bargashtfa",departureFrom);

            Prefs.putString("raft",  departureFrom);
            Prefs.putString("raftfa",  txt_depart_date.getText().toString());


        }
    }
}
