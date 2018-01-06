package com.reserv.myapplicationeli.views.fragments.pack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.activities.pack.AddRoomActivity;
import com.reserv.myapplicationeli.views.activities.pack.SearchActivity;


/**
 * Created by elham.bonyani on 1/2/2018.
 */

public class PackageFragment extends Fragment implements View.OnClickListener{


    public ViewGroup view;
    public ViewGroup layout_room;
    private final int ADD_ROOM_REQUEST = 100;

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
        initViews();
        initParam();
        return view;
    }

    private void initViews() {

        layout_room = (ViewGroup) view.findViewById(R.id.layout_room);

        layout_room.setOnClickListener(this);
    }

    private void initParam() {}

    private void showDialog(){}


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_room:
                Intent intent = new Intent(getActivity(), AddRoomActivity.class);
                startActivityForResult(intent,ADD_ROOM_REQUEST);
                break;
            case R.id.searchPackage:
                Intent intentS = new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(intentS,ADD_ROOM_REQUEST);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
