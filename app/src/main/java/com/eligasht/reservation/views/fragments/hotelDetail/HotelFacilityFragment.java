package com.eligasht.reservation.views.fragments.hotelDetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.eventbus.HotelProprtiesBus;
import com.eligasht.reservation.models.hotel.api.detail.call.HotelProprties;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelFacilityAdapter;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesAdapter;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesModels;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsAdapter;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsModel;
import com.eligasht.reservation.views.ui.NonScrollGridView;
import com.eligasht.reservation.views.ui.PassengerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Reza.nejati on 4/7/2018.
 */

public class HotelFacilityFragment extends Fragment {
    private View view;
    private ArrayList<String> arrayStringList = new ArrayList<>();
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();
    private LinearLayout llAroundHotel, llPolicy, llInformation, llFacility;
    TextView tvFacility,tvPolicy,tvAroundHotel,tvInformation;
    RecyclerView rvFacility;
    NestedScrollView nestedSv;


    public static HotelFacilityFragment instance() {
        HotelFacilityFragment fragment = new HotelFacilityFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_room_facility, container, false);
        initView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public void initView() {
        llFacility = view.findViewById(R.id.llFacility);
        llAroundHotel = view.findViewById(R.id.llAroundHotel);
        llPolicy = view.findViewById(R.id.llPolicy);
        llInformation = view.findViewById(R.id.llInformation);
        tvFacility = view.findViewById(R.id.tvFacility);
        rvFacility = view.findViewById(R.id.rvFacility);
        nestedSv = view.findViewById(R.id.nestedSv);
        tvPolicy = view.findViewById(R.id.tvPolicy);
        tvAroundHotel = view.findViewById(R.id.tvAroundHotel);
        tvInformation = view.findViewById(R.id.tvInformation);


    }

    @Override
    public void onResume() {
        super.onResume();
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                nestedSv.smoothScrollTo(0, 0);

            }
        }, 200);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setDataFacility(HotelProprtiesBus hotel) {
        for (HotelProprties hotelProprties : hotel.getHotel()) {
            if (hotelProprties.CategoryID != 4) {


                arrayStringList.add(hotelProprties.Category);
                if (hotelProprties.CategoryID != 2) {
                    hotelProprtiesModels.add(new HotelProprtiesModels(hotelProprties.PropertyTitle, hotelProprties.Category, hotelProprties.PropertyIconFont, hotelProprties.PropertyDescription, hotelProprties.CategoryID));


                } else {

                    if (hotelProprties.PropertyDescription.equals("0") || hotelProprties.PropertyDescription.equals(" ") ||
                            hotelProprties.PropertyDescription.equals("") || TextUtils.isEmpty(hotelProprties.PropertyDescription)) {

                    } else {
                        hotelProprtiesModels.add(new HotelProprtiesModels(hotelProprties.PropertyTitle, hotelProprties.Category, hotelProprties.PropertyIconFont, hotelProprties.PropertyDescription, hotelProprties.CategoryID));

                    }
                }


            }


            //add_textView(hotelProprties.PropertyTitle);

        }

        Set<String> hs = new HashSet<>();
        hs.addAll(arrayStringList);
        arrayStringList.clear();
        arrayStringList.addAll(hs);
        hs.size();


              /*  String toMoveUp = "امکانات هتل";
                while (arrayStringList.indexOf(toMoveUp) != 0) {
                    int i = arrayStringList.indexOf(toMoveUp);
                    Collections.swap(arrayStringList, i, i - 2);
                }
*/
        HashMap<String, ArrayList<HotelProprtiesModels>> myMap = new HashMap<String, ArrayList<HotelProprtiesModels>>();
        for (int i = 0; i < arrayStringList.size(); i++) {
            ArrayList<HotelProprtiesModels> test = new ArrayList<>();

            for (int j = 0; j < hotelProprtiesModels.size(); j++) {

                if (arrayStringList.get(i).equals(hotelProprtiesModels.get(j).getPropertyCat())) {
                    test.add(new HotelProprtiesModels(hotelProprtiesModels.get(j).getPropertyTitle(), hotelProprtiesModels.get(j).getPropertyCat(),
                            hotelProprtiesModels.get(j).getImage(), hotelProprtiesModels.get(j).getPropertyDescription(), hotelProprtiesModels.get(j).getCategoryID()));


                }


            }
            myMap.put(arrayStringList.get(i), test);

        }


        for (Map.Entry<String, ArrayList<HotelProprtiesModels>> entry : myMap.entrySet()) {
            String key = entry.getKey();
            ArrayList<HotelProprtiesModels> value = entry.getValue();
            if (key.contains("امکانات")|| key.toLowerCase().contains("facil")) {
                llFacility.setVisibility(View.VISIBLE);
                tvFacility.setText(key);
                rvFacility.addItemDecoration(new DividerItemDecoration(getContext(), 1));
                rvFacility.setLayoutManager(new GridLayoutManager(getContext(), 3));
                rvFacility.setHasFixedSize(true);
                rvFacility.setAdapter(new HotelFacilityAdapter(value, getActivity()));
                rvFacility.setNestedScrollingEnabled(false);


            }
            if (key.contains("اطراف")|| key.toLowerCase().contains("near by")) {

                llAroundHotel.setVisibility(View.VISIBLE);
                tvAroundHotel.setText(key);
                NonScrollGridView nonScrollGridView = new NonScrollGridView(getContext());

                nonScrollGridView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                nonScrollGridView.setAdapter(new HotelProprtiesAdapter(value, getActivity(), false));
                nonScrollGridView.setFocusable(false);
                llAroundHotel.addView(nonScrollGridView);
            }
            if (key.contains("قوانین")|| key.toLowerCase().contains("policies")) {
                llPolicy.setVisibility(View.VISIBLE);
                tvPolicy.setText(key);


                NonScrollListView nonScrollGridView = new NonScrollListView(getContext());
                nonScrollGridView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                // nonScrollGridView.setNumColumns(2);

                nonScrollGridView.setAdapter(new HotelProprtiesAdapter(value, getActivity(), true));
                nonScrollGridView.setFocusable(false);
                llPolicy.addView(nonScrollGridView);
            }
            if (key.contains("اطلاعات")|| key.toLowerCase().contains("information")) {

                llInformation.setVisibility(View.VISIBLE);

                tvInformation.setText(key);


                NonScrollListView nonScrollGridView = new NonScrollListView(getContext());
                nonScrollGridView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                // nonScrollGridView.setNumColumns(2);

                nonScrollGridView.setAdapter(new HotelProprtiesAdapter(value, getActivity(), false));
                nonScrollGridView.setFocusable(false);
                llInformation.addView(nonScrollGridView);
            }


        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    public void add_view(String key, ArrayList<HotelProprtiesModels> hotelProprtiesModels, LinearLayout linearLayout) {


    }

}
