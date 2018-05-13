package com.eligasht.reservation.map;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.amalbit.trail.RouteOverlayView;
import com.amalbit.trail.TrailSupportMapFragment;
import com.eligasht.R;
import com.eligasht.reservation.map.data.Data;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
public class OverlayRouteActivity extends AppCompatActivity implements OnMapReadyCallback{
    private static final LatLng POINT_A = new LatLng(35.737670, 51.412907);

    private static final LatLng POINT_B = new LatLng(29.617061, 52.530605);
    private GoogleMap mMap;

    private MapStyleOptions mapStyle;

    private List<LatLng> route;

    private TrailSupportMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection_route);

        View view = new FrameLayout(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

     mapFragment = (TrailSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        route = new ArrayList<>();
        GoogleDirection.withServerKey("AIzaSyDHApRn9JkcdkHYOegd9Rt_MJBl4jofTQI")
                .from(POINT_A)
                .to(POINT_B)
                .transportMode(TransportMode.DRIVING)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        Log.e("Raw", "onDirectionSuccess: +" + rawBody);
                        if (direction.isOK()) {
                            for (LatLng latLng : direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint()) {
                                Log.e("Log", latLng.toString());
                                route.add(latLng);
                                //createRoute();
                            }

                        } else {
                            Log.e("tag", "onDirectionSuccess: ");
                            // Do something
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        // Do something
                        t.printStackTrace();
                    }
                });


     //   mapStyle = MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.mapstyle);
    }


    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        mMap.setMapStyle(mapStyle);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        mMap.setMaxZoomPreference(25);

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                if (route.size() > 0)
                    zoomRoute(route);
                mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {

                        mapFragment.onCameraMove(mMap);
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (route.size() > 0)
                            mapFragment.setUpPath(route, mMap, getCurrentAnimType());


                    }
                }, 1000);
            }
        });


        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        try {
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);

            mMap.moveCamera(cu);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        }
        catch (Exception e)
        {

        }

    }

    public void zoomRoute(List<LatLng> lstLatLngRoute) {

        if (mMap == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty())
            return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : lstLatLngRoute)
            boundsBuilder.include(latLngPoint);

        int routePadding = 100;
        LatLngBounds latLngBounds = boundsBuilder.build();

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
    }


    private RouteOverlayView.AnimType getCurrentAnimType() {

        return RouteOverlayView.AnimType.ARC;

    }
}
