package com.eligasht.reservation.views.fragments.hotel;

import android.content.Intent;
import android.os.Bundle;
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

import com.eligasht.R;
import com.eligasht.reservation.models.model.ModelRowCountRoom;
import com.eligasht.reservation.models.model.pack.ChildModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.eligasht.reservation.views.activities.AddRoomActivity;
import com.eligasht.reservation.views.activities.hotel.activity.GetHotelCityActivity;
import com.eligasht.reservation.views.activities.hotel.activity.SelectHotelActivity;
import com.eligasht.reservation.views.adapters.HotelCountRoomAdapter;
import com.eligasht.reservation.views.picker.global.enums.TypeUsageOfCalendar;
import com.eligasht.reservation.views.picker.global.listeners.ICallbackCalendarDialog;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.picker.utils.CalendarDialog;
import com.eligasht.reservation.views.ui.dialog.app.CountTimeAlert;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HotelFragment extends Fragment implements OnClickListener,
        TimePickerDialog.OnTimeSetListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener, CountTimeAlert.TimerDialogListener
        , ICallbackCalendarDialog {

    public static Button btnPlusB, btnMinesB, btnPlusK, btnMinesK, btnPlusN, btnMinesN;
    public static int countNafar = 1;
    private final int ADD_ROOM_REQUEST = 100;
    public TextView txtCity, lbl_city_english, txtTitle, tarikh_be, txtCountK, tvChild, lblRoomCount, txtRoomCount, tvAdult, searchHotel;
    public ListView listRoomItem;
    public List<ModelRowCountRoom> data;
    LinearLayout btn_add_room, llRaft, llBargasht;
    CardView cvRoom;
    HotelCountRoomAdapter mAdapter;
    RelativeLayout citySearch;
    TextView tvRaft, tvBargasht;
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
    boolean geo = false;
    CalendarDialog calendarDialog;
    private View rootView;
    private ArrayList<ModelRowCountRoom> roomsSelected;
    CustomDate startDate;
    CustomDate endDate;

    public static String date_server(int y, int m, int d) {
        Date date = PersianCalendarUtils.ShamsiToMilady(y, m + 1, d);

        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
        String formatted = format1.format(date.getTime());
        String[] dateGrg = formatted.split("/");
        int monthS = Integer.valueOf(dateGrg[0]);
        long dayS = Long.valueOf(dateGrg[1]);
        int yearS = Integer.valueOf(dateGrg[2]);


        return yearS + "/" + monthS + "/" + dayS;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_hotel2, container, false);
        calendarDialog = new CalendarDialog();

        Utility.sendTag("H", true, false);
        geo = Prefs.getBoolean("geo", false);
        //	rootView = inflater.inflate(R.layout.fragment_plane, container, false);


        //listRoomItem = (ListView)rootView.findViewById(R.id.listRoomItem);

        // lblRoomCount = (TextView) rootView.findViewById(R.id.lblRoomCount);
        tarikh_be = rootView.findViewById(R.id.tarikh_be);
        //  lblRoomCount.setOnClickListener(this);
        tarikh_be.setOnClickListener(this);
        txtRoomCount = rootView.findViewById(R.id.txtRoomCount);
        tvRaft = rootView.findViewById(R.id.tvRaft);
        tvBargasht = rootView.findViewById(R.id.tvBargasht);
        tvAdult = rootView.findViewById(R.id.tvAdult);
        tvChild = rootView.findViewById(R.id.tvChild);
        llRaft = rootView.findViewById(R.id.llRaft);
        llBargasht = rootView.findViewById(R.id.llBargasht);
        // tvRaft.setOnClickListener(this);
        llRaft.setOnClickListener(this);
        llBargasht.setOnClickListener(this);
        btn_add_room = rootView.findViewById(R.id.btn_add_room);
        cvRoom = rootView.findViewById(R.id.cvRoom);
        cvRoom.setOnClickListener(this);

        //txtTitle= (TextView) rootView.findViewById(R.id.txtTitle);
        citySearch = rootView.findViewById(R.id.citySearch);

        lbl_city_english = rootView.findViewById(R.id.lbl_city_english);
        txtCity = rootView.findViewById(R.id.txtCity);


        citySearch.setOnClickListener(this);
        lbl_city_english.setOnClickListener(this);

        searchHotel = rootView.findViewById(R.id.searchHotel);
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

        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), (persianCalendarDatePicker.getPersianDay() + 1));


        month = persianCalendarDatePicker.getPersianMonth();
        year_ = persianCalendarDatePicker.getPersianYear();
        day = persianCalendarDatePicker.getPersianDay();

        datePickerDialogGregorian1 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(2);
        datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(2);

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
            /*    Log.e("caltes", datePickerDialog.getSelectedDay().getYear()+"");



                String str_date = datePickerDialog.getSelectedDay().getYear() + "/" + (datePickerDialog.getSelectedDay().getMonth()) + "/" + datePickerDialog.getSelectedDay().getDay();//2018-01-16
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    date = (Date) formatter.parse(str_date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    datePickerDialogGregorian1.initialize(HotelFragment.this,cal.getTime().getYear(),cal.getTime().getMonth(),cal.getTime().getDay());
                    Log.e("caltes1", cal.getTime().getYear()+"");
                    Log.e("caltes2", cal.getTime().getMonth()+"");
                    Log.e("caltes3", cal.getTime().getDay()+"");


                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

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
        datePickerDialog2.setTitle(getString(R.string.select_return_date));


//=====================================================================================================


        datePickerDialogGregorian1.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

                geo = true;
                Log.e("GGGGGGGRaft", year + "==" + monthOfYear + 1 + "==" + dayOfMonth);


                String str_date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;//2018-01-16
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    date = formatter.parse(str_date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    datePickerDialogGregorian2.setMinDate(cal);


                    tvRaft.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));

                    raft = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                    Log.e("GGGGGGG", raft);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tvBargasht.setText(tvRaft.getText().toString());

                Prefs.putString("bargashtfa", tvRaft.getText().toString());

                Prefs.putString("raft", raft);
                Prefs.putString("raftfa", tvRaft.getText().toString());


            }
        });
        datePickerDialogGregorian2.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
                Log.e("GGGGGGGBar", year + "==" + (monthOfYear + 1) + "==" + dayOfMonth);
                geo = true;
                tvBargasht.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));

                bargasht = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;


                Prefs.putString("bargasht", bargasht);
                Prefs.putString("bargashtfa", tvBargasht.getText().toString());

            }


        });


//=====================================================================================================


//=====================================================================================================
        if (Prefs.getString("bargashtfa", "null").equals("null")) {

            tvBargasht.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());

        } else {
            try {
                tvBargasht.setText(Prefs.getString("bargashtfa", "null"));
                bargasht = Prefs.getString("bargasht", "null").replaceAll("-", "/");


                Log.e("testdate", bargasht);

                String[] dateSplite2 = bargasht.split("/");

                String dayMF = dateSplite2[2];
                String monthMF = dateSplite2[1];
                String yearMF = dateSplite2[0];
                String[] dateSplite3 = SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF), Integer.valueOf(monthMF) - 1, Integer.valueOf(dayMF) + 1).split("/");

                String dayMF1 = dateSplite3[2];
                String monthMF1 = dateSplite3[1];
                String yearMF1 = dateSplite3[0];


                PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
                persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
                Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
                datePickerDialog2.initialize(this, persianCalendarDatePicker2.getPersianYear(), persianCalendarDatePicker2.getPersianMonth(), persianCalendarDatePicker2.getPersianDay());


            } catch (Exception e) {
            }


        }


        if (Prefs.getString("raftfa", "null").equals("null")) {
            tvRaft.setText(persianCalendarDatePicker.getPersianWeekDayName() + " " + persianCalendarDatePicker.getPersianDay() + " " + persianCalendarDatePicker.getPersianMonthName());

        } else {
            tvRaft.setText(Prefs.getString("raftfa", "null"));
            raft = Prefs.getString("raft", "null").replaceAll("-", "/");
            Log.e("testdate", raft);

            String[] dateSplite2 = raft.split("/");

            String dayMF = dateSplite2[2];
            String monthMF = dateSplite2[1];
            String yearMF = dateSplite2[0];
            String[] dateSplite3 = SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF), Integer.valueOf(monthMF) - 1, Integer.valueOf(dayMF) + 1).split("/");

            String dayMF1 = dateSplite3[2];
            String monthMF1 = dateSplite3[1];
            String yearMF1 = dateSplite3[0];


            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
            Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
            datePickerDialog.initialize(this, persianCalendarDatePicker2.getPersianYear(), persianCalendarDatePicker2.getPersianMonth(), persianCalendarDatePicker2.getPersianDay());


        }

        return rootView;

    }//end oncreat

    @Override
    public void onResume() {
        super.onResume();
        Prefs.putBoolean("geo", geo);
        try {
            //    Log.e("citycode",  Prefs.getString("Value-Hotel-City-Code", ""));


            Gson gson;

            gson = new GsonBuilder().create();
            roomsSelected = gson.fromJson(Prefs.getString("Rooms", "[{\"CountB\":1,\"CountK\":0,\"CountN\":0,\"childModels\":[]}]"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());
            Log.e("testroom", Prefs.getString("Rooms", "[{\"CountB\":1,\"CountK\":0,\"CountN\":0,\"childModels\":[]}]"));

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
        Prefs.putBoolean("geo", geo);

   /*     Prefs.putString("Value-Hotel-City-Fa", "");
        Prefs.putString("Value-Hotel-City-En", "");
        Prefs.putString("Value-Hotel-City-Code", "");*/
    }

    public boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Prefs.putBoolean("geo", geo);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.citySearch:
                Intent intent2 = new Intent(getActivity(), GetHotelCityActivity.class);
                intent2.putExtra("type", 0);
                intent2.putExtra("position", "H");

                startActivity(intent2);
                break;

            case R.id.searchHotel:
                // new CountTimeAlert(getActivity(),this);
                try {
                    if (txtCity.getText().toString().contains(getString(R.string.please_select_one))) {
                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(getActivity());
                        AlertDialogPassenger.setText(getString(R.string.please_select_destination_city));
                    } else {
                        sendStartTimer();
                        Intent intent = new Intent(getActivity(), SelectHotelActivity.class);

                        intent.putExtra("CheckIn", raft);
                        intent.putExtra("CheckOut", bargasht);
                        intent.putExtra("CheckOutFa", tvBargasht.getText().toString());
                        intent.putExtra("CheckInFa", tvRaft.getText().toString());

                        SingletonDate.getInstance().setReverseDate(startDate, endDate);


                        intent.putExtra("Rooms", getRoomList(roomsSelected));
                        intent.putExtra("Adult", Integer.valueOf(tvAdult.getText().toString()));
                        intent.putExtra("Child", Integer.valueOf(tvChild.getText().toString()));
                        Prefs.putInt("SumPass", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()));
                        Log.e("test", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()) + 1 + "");
                        intent.putExtra("Geo", geo);


                        startActivity(intent);
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.something_went_wron, Toast.LENGTH_SHORT).show();
                    Prefs.putBoolean("onTimer", false);

                }


                break;
            case R.id.llRaft:

                if (startDate != null && endDate != null) {
                    calendarDialog.create(getActivity(), getContext(), this, startDate, endDate, TypeUsageOfCalendar.HOTEL);

                } else {
                    calendarDialog.create(getActivity(), getContext(), this, true, TypeUsageOfCalendar.HOTEL);

                }


                    /*    if (geo) {
                            if (!datePickerDialogGregorian1.isAdded()){

                                datePickerDialogGregorian1.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianRaft");

                            }

                        } else {
                            if(!datePickerDialog.isAdded()){
                                datePickerDialog.show(getActivity().getSupportFragmentManager(), "DatepickerdialogRaft");

                            }

                        }

*/


                break;
            case R.id.llBargasht:
                if (startDate != null && endDate != null) {
                    calendarDialog.create(getActivity(), getContext(), new ICallbackCalendarDialog() {
                        @Override
                        public void onDateSelected(CustomDate start, CustomDate end, boolean isGeo) {
//                            if () {
//                                endDate = start;
//                                tvBargasht.setText(endDate.getDescription());
//                            }else {
//                                Toast.makeText(getActivity(), R.string.end_date_must_be_more_than_start_date, Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }, endDate, TypeUsageOfCalendar.HOTEL);

                } else {
                    calendarDialog.create(getActivity(), getContext(), new ICallbackCalendarDialog() {
                        @Override
                        public void onDateSelected(CustomDate start, CustomDate end, boolean isGeo) {
                            endDate = start;
                            tvBargasht.setText(endDate.getDescription());

                        }
                    }, false, TypeUsageOfCalendar.HOTEL);

                }
/*

                if (geo) {
                    if (!datePickerDialogGregorian2.isAdded()){

                        datePickerDialogGregorian2.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianBargasht");

                    }

                } else {
                    if(!datePickerDialog2.isAdded()){
                        datePickerDialog2.show(getActivity().getSupportFragmentManager(), "DatepickerdialogBargasht");

                    }

                }
*/


                break;
            case R.id.cvRoom:
                Intent room = new Intent(getActivity(), AddRoomActivity.class);

                room.putExtra("roomList", Prefs.getString("Rooms", "dd"));
                Log.e("roomm", Prefs.getString("Rooms", "dd"));
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
        geo = false;
        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(year, month, day);


        Log.e("salam", date_server(year_, month, day));
        if (view.getTag().equals("DatepickerdialogBargasht")) {
            tvBargasht.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            bargasht = date_server(year, monthOfYear, dayOfMonth);
            Prefs.putString("bargashtfa", tvBargasht.getText().toString());
            Prefs.putString("bargasht", bargasht);


            if (Utility.campareDate(raft, bargasht)) {
                tvRaft.setText(persianCalendar.getPersianLongDate());

            }


        }


        if (view.getTag().equals("DatepickerdialogRaft")) {

            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            tvRaft.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            //  tvBargasht.setText(persianCalendar.getPersianLongDate());
            raft = date_server(year, monthOfYear, dayOfMonth);
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(year_Min, monthMin, dayMin);


            if (Utility.campareDate(raft, bargasht)) {
                //  persianCalendar.set(year, month, day+1);

                tvBargasht.setText(persianCalendarDatePicker2.getPersianWeekDayName() + " " + persianCalendarDatePicker2.getPersianDay() + " " + persianCalendarDatePicker2.getPersianMonthName());
                datePickerDialog2.initialize(this, year_, month, day);
                datePickerDialog2.setMinDate(persianCalendarDatePicker2);
                //   bargasht = date_server(year, monthOfYear, dayOfMonth+1);

            } else {

                datePickerDialog2.setMinDate(persianCalendarDatePicker2);
            }


            Prefs.putString("bargashtfa", tvBargasht.getText().toString());

            Prefs.putString("raft", raft);
            Prefs.putString("raftfa", tvRaft.getText().toString());

        }
    }

    @Override
    public void onReturnValue(int type) {


    }

    private void sendStartTimer() {
        Intent intent = new Intent("sendStartTimer");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onDateSelected(CustomDate startDate, CustomDate endDate, boolean isGeo) {
        this.startDate = startDate;
        this.endDate = endDate;
        geo = isGeo;
        Prefs.putBoolean("geo", isGeo);
        Log.e("Date", startDate.toString());
        tvRaft.setText(startDate.getDescription());
        tvBargasht.setText(endDate.getDescription());
        raft = startDate.getFullGeo();
        bargasht = endDate.getFullGeo();


        Prefs.putString("raft", raft);
        Prefs.putString("bargasht", bargasht);
        Prefs.putString("raftfa", tvRaft.getText().toString());
        Prefs.putString("bargashtfa", tvBargasht.getText().toString());

    }


    //Gregorian==============================================Gregorian=============================Gregorian


}
