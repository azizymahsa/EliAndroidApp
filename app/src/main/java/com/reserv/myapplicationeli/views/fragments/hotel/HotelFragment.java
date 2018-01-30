package com.reserv.myapplicationeli.views.fragments.hotel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.reserv.myapplicationeli.views.activities.AddRoomActivity;
import com.reserv.myapplicationeli.views.activities.hotel.activity.GetHotelCityActivity;
import com.reserv.myapplicationeli.views.activities.hotel.activity.SelectHotelActivity;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.adapters.HotelCountRoomAdapter;
import com.reserv.myapplicationeli.views.ui.dialog.app.CountTimeAlert;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.DatePickerDialogPrivate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HotelFragment extends Fragment implements OnClickListener,
        TimePickerDialog.OnTimeSetListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener,CountTimeAlert.TimerDialogListener{

    public static Button searchHotel, btnPlusB, btnMinesB, btnPlusK, btnMinesK, btnPlusN, btnMinesN;
    public TextView txtCity, lbl_city_english, txtTitle, tarikh_be, txtCountK, tvChild, lblRoomCount, txtRoomCount, tvAdult;
    public static int countNafar = 1;
    LinearLayout btn_add_room;
    CardView cvRoom;
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
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_hotel2, container, false);

        //	rootView = inflater.inflate(R.layout.fragment_plane, container, false);


        //listRoomItem = (ListView)rootView.findViewById(R.id.listRoomItem);

        lblRoomCount = (TextView) rootView.findViewById(R.id.lblRoomCount);
        tarikh_be = (TextView) rootView.findViewById(R.id.tarikh_be);
        //  lblRoomCount.setOnClickListener(this);
        tarikh_be.setOnClickListener(this);
        txtRoomCount = (TextView) rootView.findViewById(R.id.txtRoomCount);
        tvRaft = (TextView) rootView.findViewById(R.id.tvRaft);
        tvBargasht = (TextView) rootView.findViewById(R.id.tvBargasht);
        tvAdult = (TextView) rootView.findViewById(R.id.tvAdult);
        tvChild = (TextView) rootView.findViewById(R.id.tvChild);
        tvRaft.setOnClickListener(this);
        tvBargasht.setOnClickListener(this);

        btn_add_room = (LinearLayout) rootView.findViewById(R.id.btn_add_room);
        cvRoom = (CardView) rootView.findViewById(R.id.cvRoom);
        cvRoom.setOnClickListener(this);

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

//==================================================================================================
        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
      //  Date currentTime = Calendar.getInstance().getTime();
 //=================================================================================================
        PersianCalendar persianCalendar = new PersianCalendar();

        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay()+1);


        tvBargasht.setText(persianCalendar.getPersianLongDate());
        tvRaft.setText(persianCalendarDatePicker.getPersianLongDate());
        month = persianCalendarDatePicker.getPersianMonth();
        year_ = persianCalendarDatePicker.getPersianYear();
        day = persianCalendarDatePicker.getPersianDay();

        datePickerDialogGregorian1 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();
        datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();

       // datePickerDialogGregorian2.setOnDateSetListener(this);
      //  datePickerDialogGregorian2.setOnCalandarChangeListener(this);

        datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        datePickerDialogGregorian2.setMinDate(persianCalendarDatePicker.toGregorianCalendar());

        datePickerDialog = DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );

        datePickerDialog.setMinDate(persianCalendarDatePicker);


        datePickerDialog2 = DatePickerDialog.newInstance(
                this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog2.setMinDate(persianCalendar);


        raft = date_server(persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay());



        bargasht = date_server(persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay());


        //=====================================================================================================


        datePickerDialog.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogGregorian1.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianRaft");
            }
        });


        datePickerDialogGregorian1.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialog.show(getActivity().getSupportFragmentManager(), "DatepickerdialogRaft");


            }
        });


//=====================================================================================================

        datePickerDialog2.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogGregorian2.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianBargasht");
            }
        });


        datePickerDialogGregorian2.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialog2.show(getActivity().getSupportFragmentManager(), "DatepickerdialogBargasht");


            }
        });


//=====================================================================================================


        datePickerDialogGregorian1.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {


                Log.e("GGGGGGGRaft", year+"=="+monthOfYear+1+"=="+dayOfMonth);






                String str_date =  year+"/"+  (monthOfYear+1)+"/"+ dayOfMonth;//2018-01-16
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    date = (Date) formatter.parse(str_date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    datePickerDialogGregorian2.setMinDate(cal);


                    tvRaft.setText(Utility.dateShowView(year+"/"+ (monthOfYear+1)+"/"+ dayOfMonth));

                    raft =year+"/"+ monthOfYear+1+"/"+ dayOfMonth;
                    Log.e("GGGGGGG", raft);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tvBargasht.setText(tvRaft.getText().toString());



            }
        });
        datePickerDialogGregorian2.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
                Log.e("GGGGGGGBar", year+"=="+(monthOfYear+1)+"=="+dayOfMonth);

                tvBargasht.setText(Utility.dateShowView(year+"/"+ (monthOfYear+1)+"/"+ dayOfMonth));
                bargasht =year+"/"+ monthOfYear+1+"/"+ dayOfMonth;



            }


        });



//=====================================================================================================


        return rootView;

    }//end oncreat

    @Override
    public void onResume() {
        super.onResume();
        try {
        //    Log.e("citycode",  Prefs.getString("Value-Hotel-City-Code", ""));


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

        if (!Prefs.getString("Value-Hotel-City-Fa", "").equals("")) {
            txtCity.setText(Prefs.getString("Value-Hotel-City-Fa", ""));
            lbl_city_english.setText(Prefs.getString("Value-Hotel-City-En", ""));

        }
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

            case R.id.citySearch:
                Intent intent2 = new Intent(getActivity(), GetHotelCityActivity.class);
                intent2.putExtra("type",0);;
                startActivity(intent2);
                break;

            case R.id.searchHotel:
               // new CountTimeAlert(getActivity(),this);
                try {

                    sendStartTimer();
                    Intent intent = new Intent(getActivity(), SelectHotelActivity.class);

                    intent.putExtra("CheckIn", raft);
                    intent.putExtra("CheckOut", bargasht);
                    intent.putExtra("CheckOutFa", tvBargasht.getText().toString());
                    intent.putExtra("CheckInFa", tvRaft.getText().toString());
                    intent.putExtra("Rooms", getRoomList(roomsSelected));
                    intent.putExtra("Adult", Integer.valueOf(tvAdult.getText().toString()));
                    intent.putExtra("Child", Integer.valueOf(tvChild.getText().toString()));
                    Prefs.putInt("SumPass", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()));
                    Log.e("test", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()) + 1 + "");


                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "خطایی رخ داده است", Toast.LENGTH_SHORT).show();
                    Prefs.putBoolean("onTimer",false);

                }



                break;
            case R.id.tvRaft:


                datePickerDialog.show(getActivity().getSupportFragmentManager(), "DatepickerdialogRaft");

                break;
            case R.id.tvBargasht:
                datePickerDialog2.setTitle("تاریخ برگشت را انتخاب نمایید");

                datePickerDialog2.show(getActivity().getSupportFragmentManager(), "DatepickerdialogBargasht");


                break;
            case R.id.cvRoom:
                Intent room = new Intent(getActivity(), AddRoomActivity.class);

                room.putExtra("roomList", Prefs.getString("Rooms", "dd"));
                Log.e("roomm",  Prefs.getString("Rooms", "dd"));
                startActivity(room);

                break;
            case R.id.tarikh_be:
                //	new FilterHotelDialog(getActivity());


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


        Log.e("salam", date_server(year_, month, day));
        if (view.getTag().equals("DatepickerdialogBargasht")) {
            tvBargasht.setText(persianCalendar.getPersianLongDate());
            bargasht = date_server(year, monthOfYear, dayOfMonth);


        }


        if (view.getTag().equals("DatepickerdialogRaft")) {

            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            tvRaft.setText(persianCalendar.getPersianLongDate());
            tvBargasht.setText(persianCalendar.getPersianLongDate());
            raft = date_server(year, monthOfYear, dayOfMonth);
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


        return yearS + "/"+ monthS + "/" + dayS;
    }







    @Override
    public void onReturnValue(int type) {





    }
    private void sendStartTimer() {
        Intent intent = new Intent("sendStartTimer");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    //Gregorian==============================================Gregorian=============================Gregorian


}
