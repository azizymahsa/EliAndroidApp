package com.eligasht.reservation.views.fragments.hotelDetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eligasht.R;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsModel;

import java.util.ArrayList;


public class RoomHotelFragment extends Fragment {
    private View view;
    private NonScrollListView lvRooms;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();


    public static RoomHotelFragment instance() {
        RoomHotelFragment fragment = new RoomHotelFragment();
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_room_hotel, container, false);
        initView();
        return view;
    }


    public void initView(){
        lvRooms=view.findViewById(R.id.lvRooms);


    }



}
