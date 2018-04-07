package com.eligasht.reservation.views.fragments.hotelDetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.models.eventbus.RoomsModelBus;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsAdapter;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class RoomHotelFragment extends Fragment {
    private View view;
    private NonScrollListView lvRooms;
    private RoomsAdapter roomsAdapter;
    private Window window;
    private TextView tvAlert;


    public static RoomHotelFragment instance() {
        RoomHotelFragment fragment = new RoomHotelFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_room_hotel, container, false);
        initView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public void initView() {
        lvRooms = view.findViewById(R.id.lvRooms);
        tvAlert = view.findViewById(R.id.tvAlert);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(RoomsModelBus roomsModels) {
        if (roomsModels.getRoomsModels().isEmpty()){
            tvAlert.setVisibility(View.VISIBLE);
            lvRooms.setVisibility(View.GONE);

        }else{
            roomsAdapter = new RoomsAdapter(roomsModels.getRoomsModels(), getActivity(), getActivity().findViewById(R.id.rlRoot), getActivity().findViewById(R.id.rlLoading), getActivity().getWindow());
            lvRooms.setAdapter(roomsAdapter);
        }



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
