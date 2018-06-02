package com.eligasht.reservation.views.activities.addPassenger;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;

public class AddPassenger  extends BaseActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,View.OnFocusChangeListener {

    private RadioButton btnzan,btnmard;
    private String Gensiyat="";
    private EditText txtnamem,txtfamilym,txtnumber_passport,txt_NationalCode_m;
    private LinearLayout linearMahaleeghamat,linearMeliyat,btn_nextm;
    ScrollView myScrollView;
    private TextView txttavalodm,txtexp_passport,txtMore;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_passenger);

        myScrollView = (ScrollView) findViewById(R.id.scrolMosafer);
        linearMeliyat= (LinearLayout) findViewById(R.id.linearMeliyat);

        txtMore = (TextView) findViewById(R.id.txtMore);
        txtMore.setOnClickListener(this);

        txtnamem = (EditText) findViewById(R.id.txtnamem);
        txtnamem.setOnClickListener(this);
        txtnamem.setOnFocusChangeListener(this);

        txtfamilym = (EditText) findViewById(R.id.txtfamilym);
        txtfamilym.setOnClickListener(this);
        txtfamilym.setOnFocusChangeListener(this);

        linearMahaleeghamat= (LinearLayout) findViewById(R.id.linearMahaleeghamat);
        linearMeliyat= (LinearLayout) findViewById(R.id.linearMeliyat);

        txt_NationalCode_m= (EditText) findViewById(R.id.txt_NationalCode_m);
        txt_NationalCode_m.setOnClickListener(this);
        txt_NationalCode_m.setImeOptions(EditorInfo.IME_ACTION_DONE);
        txt_NationalCode_m.setOnFocusChangeListener(this);

        txtnumber_passport = (EditText) findViewById(R.id.txtnumber_passport);
        txtnumber_passport.setOnClickListener(this);
        txtnumber_passport.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        txtnumber_passport.setOnFocusChangeListener(this);

        txttavalodm = (TextView) findViewById(R.id.txttavalodm);
        txttavalodm.setOnClickListener(this);

        txtexp_passport = (TextView) findViewById(R.id.txtexp_passport);
        txtexp_passport.setOnClickListener(this);

        btn_nextm=(LinearLayout)findViewById(R.id.btn_nextm);
        btn_nextm.setOnClickListener(this);

        btnzan = (RadioButton) findViewById(R.id.zan);
        btnzan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(btnzan.isChecked()){
                    btnmard.setChecked(false);
                    System.out.println("zan");
                    Gensiyat="false";
                }
            }
        });
        btnmard = (RadioButton) findViewById(R.id.mard);
        btnmard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(btnmard.isChecked()){
                    btnzan.setChecked(false);
                    System.out.println("mard");
                    Gensiyat="true";
                }
            }
        });

    }
        @Override
    public boolean needTerminate() {
        return false;
    }

    @Override
    public void onClick(View v) {
        https:

        switch (v.getId()) {


            case R.id.txtMore:

                linearMahaleeghamat.setVisibility(View.VISIBLE);
                linearMeliyat.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}