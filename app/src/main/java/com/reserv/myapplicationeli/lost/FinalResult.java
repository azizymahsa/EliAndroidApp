package com.reserv.myapplicationeli.lost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reserv.myapplicationeli.R;

import java.util.List;

public class FinalResult extends AppCompatActivity {
    private String factorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        if (getIntent().getAction().equals(Intent.ACTION_VIEW)) {
            final List<String> segments = getIntent().getData().getPathSegments();
            if (segments.size() > 0) {
                factorId = segments.get(0);
            }
        }
    }
}
