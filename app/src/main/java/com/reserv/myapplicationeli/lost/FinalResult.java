package com.reserv.myapplicationeli.lost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.List;

public class FinalResult extends BaseActivity {
    private String factorId;
    TextView tvFactor,tvFactor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);



        try {
            InitUi.Toolbar(this, false, R.color.toolbar_color, "تایید نهایی فاکتور");
            tvFactor = findViewById(R.id.tvFactor);
            tvFactor2 = findViewById(R.id.tvFactor2);
            if (getIntent().getAction().equals(Intent.ACTION_VIEW)) {
                final List<String> segments = getIntent().getData().getPathSegments();
                if (segments.size() > 0) {
                    factorId = segments.get(0);
                    tvFactor.setText(factorId + "");
                    tvFactor2.setText(factorId + "");
                }
            }
        } catch (Exception e) {
        }
    }
}