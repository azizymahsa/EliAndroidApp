package com.eligasht.reservation.views.activities.hotel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.InitUi;

public class CommentActivity extends AppCompatActivity {
TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "ارسال نظر");
        init_view();

    }
    public void init_view(){
        tvTitle=findViewById(R.id.tvTitle);
        tvTitle.setText(getIntent().getExtras().getString("HotelName"+"ثبت نظر برای "));
    }
}
