package com.eligasht.reservation.map;
import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.amalbit.trail.RouteOverlayView;
import com.amalbit.trail.TrailSupportMapFragment;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.views.activities.hotel.activity.SelectHotelActivity;
import com.eligasht.reservation.views.ui.dialog.LocationAlertDialog;
import com.eligasht.reservation.views.ui.dialog.ResultGiftDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.SphericalUtil;

import mehdi.sakout.fancybuttons.FancyButton;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

import static com.eligasht.reservation.base.GlobalApplication.getActivity;
public class OverlayRouteActivity extends BaseActivity implements OnMapReadyCallback {
    private static final LatLng POINT_A = new LatLng(35.737670, 51.412907);
    private static final LatLng POINT_B = new LatLng(41.015137, 28.97953);
    private static final LatLng POINT_C = new LatLng(41.716667, 44.7833);
    private static final LatLng POINT_D = new LatLng(30.315879761591844, 32.72402286529541);
    private GoogleMap mMap;
    private MapStyleOptions mapStyle;
    FancyButton btnBack;
    LocationAlertDialog locationAlertDialog;
    LottieAnimationView animation_view;
    TextView tvStart;
    int test = 0;
    android.support.v4.app.FragmentManager fm;
    View markerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection_route);
        new Handler().postDelayed(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fm = getSupportFragmentManager();
                locationAlertDialog = LocationAlertDialog.newInstance(this);
                locationAlertDialog.show(fm, "test");
            }
        }, 800);
        btnBack = findViewById(R.id.btnBack);
        tvStart = findViewById(R.id.tvStart);
        animation_view = findViewById(R.id.animation_view);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (test == 1) {
                    animation_view.setVisibility(View.VISIBLE);
                    animation_view.playAnimation();
                    animation_view.setMaxFrame((int) (animation_view.getMaxFrame() - 7));
                } else if (test == 2) {
                    ResultGiftDialog resultGiftDialog = ResultGiftDialog.newInstance(OverlayRouteActivity.this);
                    resultGiftDialog.show(fm, "dialog");
                }else if(test == 0){
                    test++;
                    mMap.addMarker(new MarkerOptions().position(POINT_A)
                            .draggable(false).visible(true).title("Marker 1") );
                /*    .icon(BitmapDescriptorFactory
                            .fromBitmap(createDrawableFromView(
                                    OverlayRouteActivity.this,
                                    markerView)))*/
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(POINT_A, 4));
                    tvStart.setText("پایان سفر");
                }

                animation_view.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animation_view.setVisibility(View.GONE);
                        if (test == 1) {
                            test++;
                            mMap.addMarker(new MarkerOptions().position(POINT_B)
                                    .draggable(false).visible(true).title("Marker 2"));
                            tvStart.setText("جایز تو بگیر!");
                            drawElementsOnMap(POINT_A, POINT_B);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (locationAlertDialog != null) {
            locationAlertDialog.dismiss();
        }
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mapStyle = MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.mapstyle);
        mMap.setMapStyle(mapStyle);
    //     markerView = LayoutInflater.from(this).inflate(R.layout.map_marker, null);







        // Add a marker in Sydney and move the camera
      /*  mMap.addMarker(new MarkerOptions().position(POINT_A)
                .draggable(false).visible(true).title("Marker 1"));
        mMap.addMarker(new MarkerOptions().position(POINT_B)
                .draggable(false).visible(true).title("Marker 2"));
        mMap.addMarker(new MarkerOptions().position(POINT_C)
                .draggable(false).visible(true).title("Marker 3"));
        mMap.addMarker(new MarkerOptions().position(POINT_D)
                .draggable(false).visible(true).title("Marker 4"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(POINT_A, 3));
        drawElementsOnMap(POINT_A, POINT_D);
        drawElementsOnMap(POINT_A, POINT_C);
        drawElementsOnMap(POINT_A, POINT_B);*/
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Log.e("onMapClick", new LatLng(point.latitude, point.longitude) + "");
            }
        });
// showCurvedPolyline(POINT_A,POINT_B, 0.5);
        LatLngBounds bounds = new LatLngBounds(POINT_A, POINT_B);
        //  Log.e("center1", bounds.getCenter()+"");
        Log.e("center", midPoint(POINT_A.latitude, POINT_A.longitude, POINT_B.latitude, POINT_B.longitude) + "");
    }

    public static LatLng midPoint(double lat1, double lon1, double lat2, double lon2) {
        double dLon = Math.toRadians(lon2 - lon1);
        //convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);
        return new LatLng(Math.toDegrees(lat3), Math.toDegrees(lon3));
    }

    private void gmapsCubicBezier(LatLng p1, LatLng p2, LatLng pA, LatLng pB) {
        //Polyline options
        PolylineOptions options = new PolylineOptions();
        LatLng curveLatLng = null;
        for (double t = 0.0; t < 1.01; t += 0.01) {
            // P = (1−t)3P1 + 3(1−t)2tP2 +3(1−t)t2P3 + t3P4; for 4 points
            double arcX = (1 - t) * (1 - t) * (1 - t) * p1.latitude
                    + 3 * (1 - t) * (1 - t) * t * pA.latitude
                    + 3 * (1 - t) * t * t * pB.latitude
                    + t * t * t * p2.latitude;
            double arcY = (1 - t) * (1 - t) * (1 - t) * p1.longitude
                    + 3 * (1 - t) * (1 - t) * t * pA.longitude
                    + 3 * (1 - t) * t * t * pB.longitude
                    + t * t * t * p2.longitude;
            curveLatLng = new LatLng(arcX, arcY);
            options.add(curveLatLng);


            //Draw polyline
            mMap.addPolyline(options.width(5).color(ContextCompat.getColor(this, R.color.app_base_color)).geodesic(false));
        }
  //      mMap.addMarker(new MarkerOptions().position(curveLatLng));
    }

    private void drawElementsOnMap(LatLng init, LatLng end) {

    /*    mMap.addMarker(new MarkerOptions().position(init));
        mMap.addMarker(new MarkerOptions().position(end));
*/
        mMap.addMarker(new MarkerOptions().position(end));
        LatLngBounds.Builder bc = new LatLngBounds.Builder();
        bc.include(init);
        bc.include(end);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 100));
/*
        PolylineOptions line = new PolylineOptions();
        line.add(init);
        line.add(end);
        mMap.addPolyline(line);*/
        double distanceBetween = SphericalUtil.computeDistanceBetween(init, end);
        double lineHeadingInit = SphericalUtil.computeHeading(init, end);
        //   double lineHeadingEnd = bearing(end, init)
        double lineHeading1, lineHeading2;
        if (lineHeadingInit < 0) {
            lineHeading1 = lineHeadingInit + 45;
            lineHeading2 = lineHeadingInit + 135;
        } else {
            lineHeading1 = lineHeadingInit + -45;
            lineHeading2 = lineHeadingInit + -135;
        }
        LatLng pA = SphericalUtil.computeOffset(init, distanceBetween / 2.5, lineHeading1);
        LatLng pB = SphericalUtil.computeOffset(end, distanceBetween / 2.5, lineHeading2);
        // Beizer curves with 4 points
        gmapsCubicBezier(init, end, pA, pB);
    }





    public static Bitmap createDrawableFromView(Activity context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels,
                displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}
