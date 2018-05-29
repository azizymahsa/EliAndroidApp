package com.eligasht.reservation.views.fragments.hotelDetail;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;
/**
 * Created by Reza Nejati on 29,May,2018
 */
public class PanoramaFragment extends Fragment {
    // George St, Sydney
    private static final LatLng SYDNEY = new LatLng(35.6997, 51.3380);

    private StreetViewPanoramaView mStreetViewPanoramaView;

    private static final String STREETVIEW_BUNDLE_KEY = "StreetViewBundleKey";
    private FrameLayout frameLayout;
    View view;
    public static PanoramaFragment instance() {
        PanoramaFragment fragment = new PanoramaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
        if (savedInstanceState == null) {
            options.position(DetailHotelActivity.location);
        }

        mStreetViewPanoramaView = new StreetViewPanoramaView(getContext(), options);


        Bundle mStreetViewBundle = null;
        if (savedInstanceState != null) {
            mStreetViewBundle = savedInstanceState.getBundle(STREETVIEW_BUNDLE_KEY);
        }
        mStreetViewPanoramaView.onCreate(mStreetViewBundle);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_panorama, container, false);
        frameLayout=view.findViewById(R.id.panorama);
        frameLayout.addView(mStreetViewPanoramaView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public void onResume() {
        mStreetViewPanoramaView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mStreetViewPanoramaView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mStreetViewPanoramaView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mStreetViewBundle = outState.getBundle(STREETVIEW_BUNDLE_KEY);
        if (mStreetViewBundle == null) {
            mStreetViewBundle = new Bundle();
            outState.putBundle(STREETVIEW_BUNDLE_KEY, mStreetViewBundle);
        }

        mStreetViewPanoramaView.onSaveInstanceState(mStreetViewBundle);
    }

}
