package com.eligasht.reservation.views.fragments.hotelDetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.views.fragments.profile.EditProfileFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class MapHotelFragment extends Fragment implements OnMapReadyCallback {
    MapView mMapView;
    private GoogleMap googleMap;
    View view;


    public static MapHotelFragment instance() {
        MapHotelFragment fragment = new MapHotelFragment();
        return fragment;
    }

    public static MapHotelFragment newInstance(String param1, String param2) {
        MapHotelFragment fragment = new MapHotelFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_map_hotel, container, false);
        if (serviceOK()) {
            mMapView = (MapView) view.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
        }
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;
        ;

        googleMap.getUiSettings().setTiltGesturesEnabled(false);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

    }


    /*  public void initMap() {


          if (serviceOK()) {
              SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().fin(R.id.map);
              mapFragment.getMapAsync(this);


          } else {
          }


      }
  */
    public boolean serviceOK() {
        try {
            int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
            if (isAvailable == ConnectionResult.SUCCESS) {
                return true;
            } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {

            } else {
                Toast.makeText(getActivity(), R.string.AccessError, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
