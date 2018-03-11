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
      CountTimeAlert.TimerDialogListener
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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_hotel2, container, false);
        calendarDialog = new CalendarDialog();
        SingletonDate.getInstance().checkConflictDate();
        if (CustomDate.compareTwoDays( SingletonDate.getInstance().getStartDate().getCalendar(), SingletonDate.getInstance().getEndDate().getCalendar())==0){
            SingletonDate.getInstance().getEndDate().addOneDay();
        }

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
        mAdapter.setData(data);

//=====================================================================================================
        tvBargasht.setText(SingletonDate.getInstance().getEndDate().getDescription());
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
        tvRaft.setText(SingletonDate.getInstance().getStartDate().getDescription());
        raft = SingletonDate.getInstance().getStartDate().getFullGeo();


//=====================================================================================================

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
                calendarDialog.create(getActivity(), getContext(), new ICallbackCalendarDialog() {
                    @Override
                    public void onDateSelected(CustomDate startDate, CustomDate endDate, boolean isGeo) {
                        SingletonDate.getInstance().setStartDate(startDate);
                        SingletonDate.getInstance().setEndDate(endDate);
                        tvBargasht.setText(SingletonDate.getInstance().getEndDate().getDescription());
                        tvRaft.setText(SingletonDate.getInstance().getStartDate().getDescription());

                    }
                },SingletonDate.getInstance().getStartDate(),SingletonDate.getInstance().getEndDate(),TypeUsageOfCalendar.HOTEL);
                break;
            case R.id.llBargasht:
                calendarDialog.create(getActivity(), getContext(), new ICallbackCalendarDialog() {
                    @Override
                    public void onDateSelected(CustomDate start, CustomDate end, boolean isGeo) {


                        if (CustomDate.compareTwoDays(SingletonDate.getInstance().getStartDate().getCalendar(), start.getCalendar())==0){
                            SingletonDate.getInstance().setEndDate(start);
                            SingletonDate.getInstance().getEndDate().addOneDay();

                            Toast.makeText(getActivity(), getString(R.string.canot_inout)+getString(R.string.so_one_day_has_been_added_to_your_end_date), Toast.LENGTH_SHORT).show();

                            tvBargasht.setText(SingletonDate.getInstance().getEndDate().getDescription());
                            return;

                        }
                        if (CustomDate.isOlderThan(SingletonDate.getInstance().getStartDate().getCalendar(), start.getCalendar())) {
                            SingletonDate.getInstance().setEndDate(start);
                            tvBargasht.setText(SingletonDate.getInstance().getEndDate().getDescription());
                        } else {
                            Toast.makeText(getActivity(), R.string.end_date_must_be_more_than_start_date, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, SingletonDate.getInstance().getEndDate(), TypeUsageOfCalendar.HOTEL);




                tvRaft.setText(SingletonDate.getInstance().getStartDate().getDescription());
                tvBargasht.setText(SingletonDate.getInstance().getEndDate().getDescription());
                raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();


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
    public void onReturnValue(int type) {


    }

    private void sendStartTimer() {
        Intent intent = new Intent("sendStartTimer");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onDateSelected(CustomDate startDate, CustomDate endDate, boolean isGeo) {
        SingletonDate.getInstance().setReverseDate(startDate,endDate);
        tvRaft.setText(startDate.getDescription());
        tvBargasht.setText(endDate.getDescription());
        raft = startDate.getFullGeo();
        bargasht = endDate.getFullGeo();


    }




}
