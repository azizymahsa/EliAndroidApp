package com.reserv.myapplicationeli.views.activities.transfer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.ui.InitUi;

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "محاسبه قیمت");

    }
}
