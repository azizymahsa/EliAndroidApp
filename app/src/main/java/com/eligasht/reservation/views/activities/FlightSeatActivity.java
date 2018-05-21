package com.eligasht.reservation.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.flightseat.FlightSeatView;

/**
 * Created by Ahmad.nemati on 5/21/2018.
 */
public class FlightSeatActivity  extends AppCompatActivity{
    FlightSeatView mFlightSeatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_seat);
        mFlightSeatView = (FlightSeatView) findViewById(R.id.fsv);
        mFlightSeatView.setMaxSelectStates(20);

        setTestData();
    }

    public void zoom(View v) {
        mFlightSeatView.startAnim(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void gotoposition(View v) {
        mFlightSeatView.goCabinPosition(FlightSeatView.CabinPosition.Middle);
    }


    public void clear(View v) {
        mFlightSeatView.setEmptySelecting();
    }


    private void setTestData() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j = j + 2) {
                mFlightSeatView.setSeatSelected(j, i);

            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j = j + 2) {
                mFlightSeatView.setSeatSelected(i + 20, j);

            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j = j + 3) {
                mFlightSeatView.setSeatSelected(i + 35, j);

            }
        }


        for (int i = 11; i < 20; i++) {
            for (int j = 0; j < 8; j = j + 4) {
                mFlightSeatView.setSeatSelected(i + 35, j);

            }
        }
        mFlightSeatView.invalidate();


    }



}
