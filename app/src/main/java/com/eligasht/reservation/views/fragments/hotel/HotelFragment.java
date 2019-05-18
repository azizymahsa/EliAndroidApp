package com.eligasht.reservation.views.fragments.hotel;

import android.animation.Animator;
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

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.models.model.ModelRowCountRoom;
import com.eligasht.reservation.models.model.pack.ChildModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
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
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.eligasht.reservation.tools.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HotelFragment extends Fragment implements OnClickListener,
    CountTimeAlert.TimerDialogListener
        , ICallbackCalendarDialog {
    public TextView txtCity, txtTitle, tarikh_be, txtCountK, tvChild, lblRoomCount, txtRoomCount, tvAdult, searchHotel;
    public List<ModelRowCountRoom> data;
    LinearLayout btn_add_room, llBargasht;
    CardView llRaft;
    LinearLayout cvRoom;
    HotelCountRoomAdapter mAdapter;
    LinearLayout citySearch;
    TextView tvRaft, tvBargasht,tvBargasht_day,tvRaft_day;
    String raft, bargasht;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
    boolean geo = false;
    CalendarDialog calendarDialog;
    private View rootView;
    private ArrayList<ModelRowCountRoom> roomsSelected;
  //  private LottieAnimationView lottieCheckin, lottieCheckout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_hotel2, container, false);
        calendarDialog = new CalendarDialog();
        SingletonDate.getInstance().checkConflictDate();
        if (CustomDate.compareTwoDays(SingletonDate.getInstance().getStartDate().getCalendar(), SingletonDate.getInstance().getEndDate().getCalendar()) == 0) {
            SingletonDate.getInstance().getEndDate().addOneDay();
        }

        Utility.sendTag("H", true, false);
        geo = Prefs.getBoolean("geo", false);
        tarikh_be = rootView.findViewById(R.id.tarikh_be);
       /* lottieCheckin = rootView.findViewById(R.id.lottie_checkin);
        lottieCheckout = rootView.findViewById(R.id.lottie_checkout);
        lottieCheckin.setSpeed(2f);
        lottieCheckout.setSpeed(2f);*/
        tarikh_be.setOnClickListener(this);
        txtRoomCount = rootView.findViewById(R.id.txtRoomCount);
        tvRaft = rootView.findViewById(R.id.tvRaft);
        tvBargasht = rootView.findViewById(R.id.tvBargasht);

        tvBargasht_day = rootView.findViewById(R.id.tvBargasht_day);
        tvRaft_day = rootView.findViewById(R.id.tvRaft_day);
        tvAdult = rootView.findViewById(R.id.tvAdult);
        tvChild = rootView.findViewById(R.id.tvChild);
        llRaft = rootView.findViewById(R.id.llRaft);
        llBargasht = rootView.findViewById(R.id.llBargasht);
        llRaft.setOnClickListener(this);
        llBargasht.setOnClickListener(this);
        btn_add_room = rootView.findViewById(R.id.btn_add_room);
        cvRoom = rootView.findViewById(R.id.cvRoom);
        cvRoom.setOnClickListener(this);
        citySearch = rootView.findViewById(R.id.citySearch);
      //  lbl_city_english = rootView.findViewById(R.id.lbl_city_english);
        txtCity = rootView.findViewById(R.id.txtCity);
        citySearch.setOnClickListener(this);
       // lbl_city_english.setOnClickListener(this);
        searchHotel = rootView.findViewById(R.id.searchHotel);
        searchHotel.setOnClickListener(this);
        data = new ArrayList<ModelRowCountRoom>();
        ModelRowCountRoom model = new ModelRowCountRoom();
        model.setCountB(1);
        model.setCountK(0);
        model.setCountN(0);
        data.add(model);
        mAdapter = new HotelCountRoomAdapter(getActivity(), data);
        mAdapter.setData(data);
        tvBargasht.setText(SingletonDate.getInstance().getEndDate().getDescription());
        tvBargasht_day.setText(SingletonDate.getInstance().getEndDate().getDescriptionTak());

        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
        tvRaft.setText(SingletonDate.getInstance().getStartDate().getDescription());
        tvRaft_day.setText(SingletonDate.getInstance().getStartDate().getDescriptionTak());

        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        return rootView;

    }//end oncreat
    private void initCheckInCheckOutAnim(){} /*{
        lottieCheckin.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieCheckin.setFrame(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        lottieCheckout.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieCheckout.setFrame(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lottieCheckin.playAnimation();
        lottieCheckout.playAnimation();
    }*/

    @Override
    public void onResume() {
        super.onResume();
        Prefs.putBoolean("geo", geo);
        try {
            Gson gson;
            gson = new GsonBuilder().create();
            roomsSelected = gson.fromJson(Prefs.getString("Rooms", "[{\"CountB\":1,\"CountK\":0,\"CountN\":0,\"childModels\":[]}]"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());
            tvAdult.setText(String.valueOf(getCountAdult(roomsSelected)));
            tvChild.setText(String.valueOf(getCountChild(roomsSelected)));
            txtRoomCount.setText(String.valueOf(getCountRooms(roomsSelected)));
        } catch (Exception e) {
        }

        if (!Prefs.getString("Value-Hotel-City-Fa", "").equals("")) {
            txtCity.setText(Prefs.getString("Value-Hotel-City-En", ""));
           // lbl_city_english.setText(Prefs.getString("Value-Hotel-City-En", ""));
          //  lbl_city_english.setText(Prefs.getString("Value-Hotel-Country-Code", ""));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Prefs.putBoolean("geo", geo);
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
                SwipeBackActivityHelper.activityBuilder(getActivity())
                        .intent(intent2)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
                break;
            case R.id.searchHotel:
                try {
                    if (txtCity.getText().toString().contains(getString(R.string.please_select_destination_city))) {
                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(getActivity(),false,false);
                        AlertDialogPassenger.setText(getString(R.string.please_select_destination_city),getString(R.string.massege));
                    } else {
                        sendStartTimer();
                        Intent intent = new Intent(getActivity(), SelectHotelActivity.class);
                        intent.putExtra("CheckIn", raft);
                        intent.putExtra("CheckOut", bargasht);
                        intent.putExtra("CheckOutFa", tvBargasht_day.getText().toString()+""+tvBargasht.getText().toString());
                        intent.putExtra("CheckInFa", tvRaft_day.getText().toString()+""+tvRaft.getText().toString());
                        intent.putExtra("Rooms", getRoomList(roomsSelected));
                        intent.putExtra("Adult", Integer.valueOf(tvAdult.getText().toString()));
                        intent.putExtra("Child", Integer.valueOf(tvChild.getText().toString()));
                        Prefs.putInt("SumPass", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()));
                        Log.e("test", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()) + 1 + "");
                        intent.putExtra("Geo", geo);
                        startActivity(intent);
                      /*  SwipeBackActivityHelper.activityBuilder(getActivity())
                                .intent(intent)
                                .needParallax(true)
                                .needBackgroundShadow(true)
                                .startActivity();*/
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(), R.string.something_went_wron, Toast.LENGTH_SHORT).show();
                    Prefs.putBoolean("onTimer", false);

                }


                break;
            case R.id.llRaft:
                calendarDialog.create(getActivity(), getContext(), this, SingletonDate.getInstance().getStartDate(), SingletonDate.getInstance().getEndDate(), TypeUsageOfCalendar.HOTEL);
                break;
            case R.id.llBargasht:
                calendarDialog.create(getActivity(), getContext(), this, SingletonDate.getInstance().getStartDate(), SingletonDate.getInstance().getEndDate(), TypeUsageOfCalendar.HOTEL);
                tvRaft.setText(SingletonDate.getInstance().getStartDate().getDescription());
                tvRaft_day.setText(SingletonDate.getInstance().getStartDate().getDescriptionTak());

                tvBargasht.setText(SingletonDate.getInstance().getEndDate().getDescription());
                tvBargasht_day.setText(SingletonDate.getInstance().getEndDate().getDescriptionTak());
                raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                break;
            case R.id.cvRoom:
                Intent room = new Intent(getActivity(), AddRoomActivity.class);
                room.putExtra("roomList", Prefs.getString("Rooms", "dd"));

                SwipeBackActivityHelper.activityBuilder(getActivity())
                        .intent(room)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
                break;
            case R.id.tarikh_be:
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
        SingletonDate.getInstance().setReverseDate(startDate, endDate);
        tvRaft.setText(startDate.getDescription());
        tvRaft_day.setText(startDate.getDescriptionTak());
        tvBargasht.setText(endDate.getDescription());
        tvBargasht_day.setText(endDate.getDescriptionTak());
        initCheckInCheckOutAnim();
        raft = startDate.getFullGeo();
        bargasht = endDate.getFullGeo();
    }


}
