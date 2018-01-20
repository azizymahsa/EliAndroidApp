package com.reserv.myapplicationeli.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.models.model.pack.ChildModel;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.reserv.myapplicationeli.views.activities.hotel.activity.SelectHotelFlightActivity;
import com.reserv.myapplicationeli.views.activities.pack.AddRoomActivity;
import com.reserv.myapplicationeli.views.adapters.HotelCountRoomAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter.GetAirportHotelActivity;
import com.reserv.myapplicationeli.views.ui.dialog.app.CountTimeAlert;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.DatePickerDialogPrivate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Reza.nejati on 1/14/2018.
 */

public class HotelFlightFragment extends android.support.v4.app.Fragment implements View.OnClickListener,
        TimePickerDialog.OnTimeSetListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener,CountTimeAlert.TimerDialogListener {

    public static Button searchHotel, btnPlusB, btnMinesB, btnPlusK, btnMinesK, btnPlusN, btnMinesN;
    public TextView txtCity, lbl_city_english, tvMabda, tarikh_be, txtCountK, tvChild, lblRoomCount, txtRoomCount, tvAdult,tvMabdaEn;
    public static int countNafar = 1;
    LinearLayout btn_add_room, llRoom;
    public ListView listRoomItem;
    HotelCountRoomAdapter mAdapter;
    public List<ModelRowCountRoom> data;
    private View rootView;
    RelativeLayout citySearch;
    TextView tvRaft, tvBargasht;
    DatePickerDialogPrivate datePickerDialogRaft, datePickerDialogBargasht;
    private ArrayList<ModelRowCountRoom> roomsSelected;
    private final int ADD_ROOM_REQUEST = 100;
    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog2;
    int month;
    int year_;
    int day;
    int monthMin;
    int year_Min;
    int dayMin;
    String raft, bargasht;
    LinearLayout linearLayout_mabda,linearLayout_maghsad;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_flight_hotel2, container, false);

        //	rootView = inflater.inflate(R.layout.fragment_plane, container, false);


        //listRoomItem = (ListView)rootView.findViewById(R.id.listRoomItem);


        lblRoomCount = (TextView) rootView.findViewById(R.id.lblRoomCount);
        tarikh_be = (TextView) rootView.findViewById(R.id.tarikh_be);
        tvMabda = (TextView) rootView.findViewById(R.id.tvMabda);
        tvMabdaEn = (TextView) rootView.findViewById(R.id.tvMabdaEn);
        linearLayout_mabda = (LinearLayout) rootView.findViewById(R.id.linearLayout_mabda);
        //  lblRoomCount.setOnClickListener(this);
        tarikh_be.setOnClickListener(this);
        txtRoomCount = (TextView) rootView.findViewById(R.id.txtRoomCount);
        linearLayout_maghsad = (LinearLayout) rootView.findViewById(R.id.linearLayout_maghsad);
        tvRaft = (TextView) rootView.findViewById(R.id.tvRaft);
        tvBargasht = (TextView) rootView.findViewById(R.id.tvBargasht);
        tvAdult = (TextView) rootView.findViewById(R.id.tvAdult);
        tvChild = (TextView) rootView.findViewById(R.id.tvChild);
        tvRaft.setOnClickListener(this);
        tvBargasht.setOnClickListener(this);
        linearLayout_maghsad.setOnClickListener(this);

        btn_add_room = (LinearLayout) rootView.findViewById(R.id.btn_add_room);
        llRoom = (LinearLayout) rootView.findViewById(R.id.llRoom);
        llRoom.setOnClickListener(this);
        linearLayout_mabda.setOnClickListener(this);

        //txtTitle= (TextView) rootView.findViewById(R.id.txtTitle);
        citySearch = (RelativeLayout) rootView.findViewById(R.id.citySearch);

        lbl_city_english = (TextView) rootView.findViewById(R.id.lbl_city_english);
        txtCity = (TextView) rootView.findViewById(R.id.txtCity);


        citySearch.setOnClickListener(this);
        lbl_city_english.setOnClickListener(this);

        searchHotel = (Button) rootView.findViewById(R.id.searchHotel);
        searchHotel.setOnClickListener(this);


        data = new ArrayList<ModelRowCountRoom>();
        // for(int i=0;i<2;i++){
        ModelRowCountRoom model = new ModelRowCountRoom();
        model.setCountB(1);
        model.setCountK(0);
        model.setCountN(0);

        data.add(model);


        mAdapter = new HotelCountRoomAdapter(getActivity(), data);
        //mAdapter.setAdapter(mAdapter);
        mAdapter.setData(data);
        //   listRoomItem.setAdapter(mAdapter);


        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        tvBargasht.setText(persianCalendarDatePicker.getPersianLongDate());
        tvRaft.setText(persianCalendarDatePicker.getPersianLongDate());
        month = persianCalendarDatePicker.getPersianMonth();
        year_ = persianCalendarDatePicker.getPersianYear();
        day = persianCalendarDatePicker.getPersianDay();





        datePickerDialog = DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );

        datePickerDialog.setMinDate(persianCalendarDatePicker);


        datePickerDialog2 = DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );
        datePickerDialog2.setMinDate(persianCalendarDatePicker);


        raft=date_server(  persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay());
        bargasht=date_server(  persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay());


        return rootView;

    }//end oncreat

    @Override
    public void onResume() {
        super.onResume();
        try {


            Gson gson;

            gson = new GsonBuilder().create();
            roomsSelected = gson.fromJson(Prefs.getString("Rooms", "dd"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());

            Log.e("1243intent", Prefs.getString("Rooms", "dd"));

            tvAdult.setText(String.valueOf(getCountAdult(roomsSelected)));
            tvChild.setText(String.valueOf(getCountChild(roomsSelected)));
            txtRoomCount.setText(String.valueOf(getCountRooms(roomsSelected)));
        } catch (Exception e) {
        }

        txtCity.setText(Prefs.getString("Value-Hotel-City-Fa-HF-Raft", ""));
        lbl_city_english.setText(Prefs.getString("Value-Hotel-City-En-HF-Raft", ""));
        tvMabda.setText(Prefs.getString("Value-Hotel-City-Fa-HF-Source", ""));
        tvMabdaEn.setText(Prefs.getString("Value-Hotel-City-En-HF-Source", ""));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

   /*     Prefs.putString("Value-Hotel-City-Fa", "");
        Prefs.putString("Value-Hotel-City-En", "");
        Prefs.putString("Value-Hotel-City-Code", "");*/
    }

    public boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.linearLayout_mabda:
                //	new FilterHotelDialog(getActivity());
                Intent intent2 = new Intent(getActivity(), GetAirportHotelActivity.class);
                intent2.putExtra("type",1);;
                startActivity(intent2);
                break;

            case R.id.searchHotel:
                new CountTimeAlert(getActivity(),this);



                //   }

                break;
            case R.id.tvRaft:
                datePickerDialog.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
                    @Override
                    public void onCalendarChanged(boolean isGregorian) {
                    }
                });

                datePickerDialog.show(getActivity().getSupportFragmentManager(), "DatepickerdialogRaft");

                break;
            case R.id.tvBargasht:
                datePickerDialog2.setTitle("تاریخ برگشت را انتخاب نمایید");

                datePickerDialog2.show(getActivity().getSupportFragmentManager(), "DatepickerdialogBargasht");


                break;
            case R.id.llRoom:
                Intent room = new Intent(getActivity(), AddRoomActivity.class);

                room.putExtra("roomList",Prefs.getString("Rooms", "dd"));
                startActivity(room);

                break;
            case R.id.linearLayout_maghsad:
                //	new FilterHotelDialog(getActivity());
                Intent intent = new Intent(getActivity(), GetAirportHotelActivity.class);
                intent.putExtra("type",2);;
                startActivity(intent);

                break;


        }
    }

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

    private int getCountRooms(ArrayList<ModelRowCountRoom> rooms) {
        if (ValidationTools.isEmptyOrNull(rooms)) {
            return 0;
        }

        return rooms.size();
    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(year, month, day);


        Log.e("salam",date_server(year_, month, day));
        if (view.getTag().equals("DatepickerdialogBargasht")) {
            tvBargasht.setText(persianCalendar.getPersianLongDate());
            bargasht=date_server(year,monthOfYear,dayOfMonth);



        }


        if (view.getTag().equals("DatepickerdialogRaft")) {

            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            tvRaft.setText(persianCalendar.getPersianLongDate());
            tvBargasht.setText(persianCalendar.getPersianLongDate());
            raft=date_server(year,monthOfYear,dayOfMonth);
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(year_Min, monthMin, dayMin);
            datePickerDialog2.initialize(this, year_, month, day);
            datePickerDialog2.setMinDate(persianCalendarDatePicker2);


        }
    }

    public static String date_server(int y, int m, int d) {
        Date date = PersianCalendarUtils.ShamsiToMilady(y, m + 1, d);

        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
        String formatted = format1.format(date.getTime());
        String[] dateGrg = formatted.split("/");
        int monthS = Integer.valueOf(dateGrg[0]);
        long dayS = Long.valueOf(dateGrg[1]);
        int yearS = Integer.valueOf(dateGrg[2]);



        return yearS+"/"+"0"+monthS+"/"+dayS;
    }

    private void sendStartTimer() {
        Intent intent = new Intent("sendStartTimer");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onReturnValue(int type) {
        try {
            sendStartTimer();

            Intent intent = new Intent(getActivity(), SelectHotelFlightActivity.class);

            intent.putExtra("CheckInHF", raft);
            intent.putExtra("CheckOutHF",bargasht);
            intent.putExtra("CheckOutFaHF", tvBargasht.getText().toString());
            intent.putExtra("CheckInFaHF",tvRaft.getText().toString());
            intent.putExtra("Rooms", getRoomList(roomsSelected));
            intent.putExtra("Adult", Integer.valueOf(tvAdult.getText().toString()));
            intent.putExtra("Child", Integer.valueOf(tvChild.getText().toString()));
            Prefs.putInt("SumPass", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()));
            Log.e("test", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString())+1+"" );



            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "خطایی رخ داده است", Toast.LENGTH_SHORT).show();
        }

    }
}