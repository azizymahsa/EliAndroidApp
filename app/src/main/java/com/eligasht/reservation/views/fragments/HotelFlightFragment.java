package com.eligasht.reservation.views.fragments;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.models.model.ModelRowCountRoom;
import com.eligasht.reservation.models.model.pack.ChildModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.activities.hotel.activity.SelectHotelFlightActivity;
import com.eligasht.reservation.views.activities.AddRoomActivity;
import com.eligasht.reservation.views.adapters.HotelCountRoomAdapter;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.GetAirportHotelActivity;
import com.eligasht.reservation.views.picker.global.enums.TypeUsageOfCalendar;
import com.eligasht.reservation.views.picker.global.listeners.ICallbackCalendarDialog;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.picker.utils.CalendarDialog;
import com.eligasht.reservation.views.ui.GetAirportMabdaActivity;
import com.eligasht.reservation.views.ui.GetAirportMaghsadActivity;
import com.eligasht.reservation.views.ui.dialog.app.CountTimeAlert;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.eligasht.reservation.tools.Prefs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 1/14/2018.
 */

public class HotelFlightFragment extends android.support.v4.app.Fragment implements View.OnClickListener, CountTimeAlert.TimerDialogListener
        , ICallbackCalendarDialog {

    public TextView  tarikh_be, txtCountK, tvChild, lblRoomCount, txtRoomCount, tvAdult, searchHotel;
    public List<ModelRowCountRoom> data;
    LinearLayout btn_add_room, llRoom;
    HotelCountRoomAdapter mAdapter;

    TextView tvRaft, tvBargasht,tvRaft_day, tvBargasht_day;
    String raft, bargasht;
    LinearLayout linearLayout_mabda, linearLayout_maghsad, llBargasht;
    CardView llRaft;
  //  ImageView ivImage;
  public TextView txtOption;
    boolean geo = false;
    CalendarDialog calendarDialog;
    private View rootView;
    private ArrayList<ModelRowCountRoom> roomsSelected;
    //private LottieAnimationView lottieCheckin, lottieCheckout;
    public TextView tvStart, tvEnd, lbl_forudgah_maghsad, lbl_forudgah_mabda;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_flight_hotel2, container, false);
        Utility.sendTag("HF", true, false);
        geo = Prefs.getBoolean("geo", false);
        calendarDialog = new CalendarDialog();
        SingletonDate.getInstance().checkConflictDate();
        if (CustomDate.compareTwoDays(SingletonDate.getInstance().getStartDate().getCalendar(), SingletonDate.getInstance().getEndDate().getCalendar()) == 0) {
            SingletonDate.getInstance().getEndDate().addOneDay();
        }

        /*tvMabda = rootView.findViewById(R.id.tvMabda);
        tvMabdaEn = rootView.findViewById(R.id.tvMabdaEn);*/
        tvStart = rootView.findViewById(R.id.tvStart);
        lbl_forudgah_mabda = rootView.findViewById(R.id.lbl_forudgah_mabda);
        /*lbl_city_english = rootView.findViewById(R.id.lbl_city_english);
        txtCity = rootView.findViewById(R.id.txtCity);*/
        tvEnd = rootView.findViewById(R.id.tvEnd);
        lbl_forudgah_maghsad = rootView.findViewById(R.id.lbl_forudgah_maghsad);



        tarikh_be = rootView.findViewById(R.id.tarikh_be);

        linearLayout_mabda = rootView.findViewById(R.id.linearLayout_mabda);
       /* lottieCheckin = rootView.findViewById(R.id.lottie_checkin);
        lottieCheckout = rootView.findViewById(R.id.lottie_checkout);
        lottieCheckin.setSpeed(2f);
        lottieCheckout.setSpeed(2f);*/
        tarikh_be.setOnClickListener(this);
        txtRoomCount = rootView.findViewById(R.id.txtRoomCount);
        linearLayout_maghsad = rootView.findViewById(R.id.linearLayout_maghsad);
        tvRaft = rootView.findViewById(R.id.tvRaft);
        tvBargasht = rootView.findViewById(R.id.tvBargasht);
        tvBargasht_day = rootView.findViewById(R.id.tvBargasht_day);
        tvRaft_day = rootView.findViewById(R.id.tvRaft_day);
        tvAdult = rootView.findViewById(R.id.tvAdult);
        tvChild = rootView.findViewById(R.id.tvChild);
     //   ivImage = rootView.findViewById(R.id.ivImage);
       txtOption = rootView.findViewById(R.id.txtOption);
        llRaft = rootView.findViewById(R.id.llRaft);
        llBargasht = rootView.findViewById(R.id.llBargasht);
        llRaft.setOnClickListener(this);
        llBargasht.setOnClickListener(this);
        linearLayout_maghsad.setOnClickListener(this);
        btn_add_room = rootView.findViewById(R.id.btn_add_room);
        llRoom = rootView.findViewById(R.id.llRoom);
        llRoom.setOnClickListener(this);
        linearLayout_mabda.setOnClickListener(this);

        //lbl_city_english.setOnClickListener(this);
        searchHotel = rootView.findViewById(R.id.searchHotel);
        searchHotel.setOnClickListener(this);
       txtOption.setOnClickListener(this);
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

        if (Prefs.getString("Value-Mabda-City", "") != null && Prefs.getString("Value-Mabda-City", "").length() > 1) {
            tvStart.setText(Prefs.getString("Value-Mabda-City", "")+" , "+Prefs.getString("Value-Mabda-Airport-Code",""));
            tvStart.setTextColor(Color.parseColor(getString(R.color.n_black_dark)));
            tvStart.setTypeface(tvStart.getTypeface(), Typeface.BOLD);
            lbl_forudgah_mabda.setText(Prefs.getString("Value-Mabda-Airport", ""));
        }
        if (Prefs.getString("Value-Maghsad-Airport", "") != null && Prefs.getString("Value-Maghsad-Airport", "").length() > 1) {
            lbl_forudgah_maghsad.setText(Prefs.getString("Value-Maghsad-Airport", ""));
            tvEnd.setText(Prefs.getString("Value-Maghsad-City", "")+" , "+Prefs.getString("Value-Maghsad-Airport-Code",""));
            tvEnd.setTextColor(Color.parseColor(getString(R.color.n_black_dark)));
            tvEnd.setTypeface(tvEnd.getTypeface(), Typeface.BOLD);
        }
        return rootView;
    }

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

        /*tvEnd.setText(Prefs.getString("Value-Hotel-City-Fa-HF-Raft", getString(R.string.select_destination_city_or_airport)));
        lbl_forudgah_maghsad.setText(Prefs.getString("Value-Hotel-City-En-HF-Raft", ""));
        tvStart.setText(Prefs.getString("Value-Hotel-City-Fa-HF-Source", getString(R.string.select_origin_city_or_airport)));
        lbl_forudgah_mabda.setText(Prefs.getString("Value-Hotel-City-En-HF-Source", ""));
     */
        if (Prefs.getString("Value-Mabda-City", "") != null && Prefs.getString("Value-Mabda-City", "").length() > 1) {
            // tvStart.setText(Prefs.getString("Value-Mabda-City", ""));
            tvStart.setText(Prefs.getString("Value-Mabda-City", "")+" , "+Prefs.getString("Value-Mabda-Airport-Code",""));
            tvStart.setTextColor(Color.parseColor(getString(R.color.n_black_dark)));
            tvStart.setTypeface(tvStart.getTypeface(), Typeface.BOLD);
            lbl_forudgah_mabda.setText(Prefs.getString("Value-Mabda-Airport", ""));
        }
        if (Prefs.getString("Value-Maghsad-Airport", "") != null && Prefs.getString("Value-Maghsad-Airport", "").length() > 1) {
            lbl_forudgah_maghsad.setText(Prefs.getString("Value-Maghsad-Airport", ""));
            //  tvEnd.setText(Prefs.getString("Value-Maghsad-City", ""));
            tvEnd.setText(Prefs.getString("Value-Maghsad-City", "")+" , "+Prefs.getString("Value-Maghsad-Airport-Code",""));
            tvEnd.setTextColor(Color.parseColor(getString(R.color.n_black_dark)));
            tvEnd.setTypeface(tvEnd.getTypeface(), Typeface.BOLD);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Prefs.putBoolean("geo", geo);
    }

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
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Log.e("idididid", v.getId()+"");

        switch (v.getId()) {

            case R.id.linearLayout_mabda:
               /* Intent intent2 = new Intent(getActivity(), GetAirportHotelActivity.class);
                intent2.putExtra("type", 1);
                intent2.putExtra("position", "HF");
                startActivity(intent2);*/
                Intent intent2 = new Intent(getActivity(), GetAirportMabdaActivity.class);
                startActivity(intent2);
                break;

            case R.id.searchHotel:
                try {
                    if (tvStart.getText().toString().contains(getString(R.string.select_origin_city_or_airport)) || tvEnd.getText().toString().contains(getString(R.string.select_destination_city_or_airport))) {
                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(getActivity(),false,false);
                        AlertDialogPassenger.setText(getString(R.string.please_select_destination_and_origin),getString(R.string.massege));
                    } else {
                        sendStartTimer();
                        Intent intent = new Intent(getActivity(), SelectHotelFlightActivity.class);
                        intent.putExtra("CheckInHF", raft);
                        intent.putExtra("CheckOutHF", bargasht);
                        intent.putExtra("CheckOutFaHF", tvBargasht_day.getText().toString()+""+tvBargasht.getText().toString());
                        intent.putExtra("CheckInFaHF", tvRaft_day.getText().toString()+""+tvRaft.getText().toString());
                        intent.putExtra("Rooms", getRoomList(roomsSelected));
                        intent.putExtra("Adult", Integer.valueOf(tvAdult.getText().toString()));
                        intent.putExtra("Child", Integer.valueOf(tvChild.getText().toString()));
                        intent.putExtra("Geo", geo);
                        Prefs.putInt("SumPass", Integer.valueOf(tvAdult.getText().toString()) + Integer.valueOf(tvChild.getText().toString()));
            /*            SwipeBackActivityHelper.activityBuilder(getActivity())
                                .intent(intent)
                                .needParallax(true)
                                .needBackgroundShadow(true)
                                .startActivity();*/
                        startActivity(intent);
                    }


                } catch (Exception e) {
                    Toast.makeText(getActivity(), getString(R.string.something_went_wron), Toast.LENGTH_SHORT).show();
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
            case R.id.llRoom:
                Intent room = new Intent(getActivity(), AddRoomActivity.class);
                room.putExtra("roomList", Prefs.getString("Rooms", "dd"));
                SwipeBackActivityHelper.activityBuilder(getActivity())
                        .intent(room)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();

                break;
            case R.id.linearLayout_maghsad:
               /* Intent intent = new Intent(getActivity(), GetAirportHotelActivity.class);
                intent.putExtra("type", 2);
                intent.putExtra("position", "HF");
                startActivity(intent); */
                Intent intent = new Intent(getActivity(), GetAirportMaghsadActivity.class);

                startActivity(intent);

                break;
            case R.id.txtOption:
                anim();
                break;


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Prefs.putBoolean("geo", geo);


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

    private void sendStartTimer() {
        Intent intent = new Intent("sendStartTimer");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onReturnValue(int type) {
    }

    public void anim() {
        YoYo.with(Techniques.SlideOutDown).duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                YoYo.with(Techniques.SlideOutDown)
                        .duration(500)
                        .playOn(lbl_forudgah_mabda);
                YoYo.with(Techniques.SlideOutUp)
                        .duration(500)
                        .playOn(lbl_forudgah_maghsad);
                YoYo.with(Techniques.SlideOutUp)
                        .duration(500)
                        .playOn(tvEnd);

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                String start = "";
                String end = "";
                String startF = "";
                String endF = "";

                start = tvStart.getText().toString();//رفت
                end = tvEnd.getText().toString();//برگشت

                startF = lbl_forudgah_mabda.getText().toString();
                endF = lbl_forudgah_maghsad.getText().toString();

                tvStart.setText(end);
                tvEnd.setText(start);

                lbl_forudgah_mabda.setText(endF);
                lbl_forudgah_maghsad.setText(startF);
                if (start.contains(getString(R.string.origin)) && end.contains(getString(R.string.destination))) {
                    tvStart.setText(getString(R.string.select_origin_city));
                    tvEnd.setText(getString(R.string.select_destination_city));
                } else if (start.contains(getString(R.string.origin))) {
                    tvEnd.setText(getString(R.string.select_destination_city));
                    lbl_forudgah_maghsad.setText("");
                } else if (end.contains(getString(R.string.destination))) {
                    tvStart.setText(getString(R.string.select_origin_city));
                    lbl_forudgah_mabda.setText("");
                }

                String m3 = Prefs.getString("Value-Hotel-City-Code-HF-Raft", "");
                String m4 = Prefs.getString("Value-Hotel-City-Code-HF-Source", "");
                Prefs.putString("Value-Hotel-City-Code-HF-Raft", m4);
                Prefs.putString("Value-Hotel-City-Code-HF-Source", m3);
                Prefs.putString("Value-Hotel-City-Fa-HF-Raft", start);
                Prefs.putString("Value-Hotel-City-En-HF-Raft", startF);
                Prefs.putString("Value-Hotel-City-Fa-HF-Source", end);
                Prefs.putString("Value-Hotel-City-En-HF-Source", endF);
                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .playOn(lbl_forudgah_mabda);
                YoYo.with(Techniques.SlideInDown)
                        .duration(500)
                        .playOn(lbl_forudgah_maghsad);

                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .playOn(tvStart);
                YoYo.with(Techniques.SlideInDown)
                        .duration(500)
                        .playOn(tvEnd);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        }).playOn(tvStart);
        final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_around_center_point);
      txtOption.startAnimation(animation);
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

