package com.eligasht.reservation.views.activities.hotel.viewPagerFragmnets;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.eligasht.reservation.R;

/**
 * Created by Reza.nejati on 1/3/2018.
 */

public class MapFragment extends Fragment implements View.OnClickListener  {
    private String title;
    private int page;
    private GoogleMap map;
    private View mapView;
    MapView mMapView;

    private static final int GPS_ERRORDIALOG_REQUEST = 9001;


    // newInstance constructor for creating fragment with arguments
    public static MapFragment newInstance(int page, String title) {
        MapFragment fragmentFirst = new MapFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);



        if (serviceOK()) {
            FrameLayout containerMap = (FrameLayout) view.findViewById(R.id.container_map);
            mMapView = (MapView) view.findViewById(R.id.container_map);
            mMapView.onCreate(savedInstanceState);
//            containerMap.addView(mapView);
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    map = mMap;

                    // For showing a move to my location button
             //       map.setMyLocationEnabled(true);

                    // For dropping a marker at a point on the Map
                    LatLng sydney = new LatLng(35.6961, 51.4231);
                    map.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(11).build();
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });

        } else {
            // TODO: 9/18/2016
        }

        return view;
    }

    @Override
    public void onClick(View v) {

    }

  /*  @Override
    public void onCameraIdle() {

    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onCameraMoveStarted(int i) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //init Map for MyLocation - Default Camera - Default views----------------------------------
      //  map.setMyLocationEnabled(true);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.6961, 51.4231), 11));
      //  View locationButton = ((View) mapView.findViewById(1).getParent()).findViewById(2);
      //  RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
      //  rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
      //  rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
       // rlp.setMargins(0, 0, 30, 210);
        map.setTrafficEnabled(true);
        map.setOnMapClickListener(this);
        map.setOnCameraIdleListener(this);
        map.setOnCameraMoveListener(this);

    }
*/
    public boolean serviceOK() {
        try {
            int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
            if (isAvailable == ConnectionResult.SUCCESS) {
                return true;
            } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,
                        getActivity(), GPS_ERRORDIALOG_REQUEST);
                dialog.show();
            } else {
                Toast.makeText(getActivity(), "امکان دسترسی وجود ندارد", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }

}
