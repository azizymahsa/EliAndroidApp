package com.eligasht.reservation.views.activities.addPassenger;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.eligasht.reservation.views.ui.InitUi;
public class SavePassengerActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,View.OnFocusChangeListener {


    private RadioButton btnzan,btnmard;
    private String Gensiyat="";
    private EditText txtnamem,txtfamilym,txtnumber_passport,txt_NationalCode_m;
    private LinearLayout linearMahaleeghamat,linearMeliyat,btn_nextm,linear_number_passport,linear_expdate;
    ScrollView myScrollView;
    private TextView txttavalodm,txtexp_passport,txtMore,txtmahale_eghamat,txtmeliyatm;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_passenger);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "اضافه کردن مسافر");


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

        linear_expdate=(LinearLayout)findViewById(R.id.linear_expdate);
        linear_expdate.setOnClickListener(this);

        txttavalodm = (TextView) findViewById(R.id.txttavalodm);
        txttavalodm.setOnClickListener(this);

        txtexp_passport = (TextView) findViewById(R.id.txtexp_passport);
        txtexp_passport.setOnClickListener(this);

        txtmahale_eghamat= (TextView)findViewById(R.id.txtmahale_eghamat);
        txtmahale_eghamat.setOnClickListener(this);

        txtmeliyatm= (TextView)findViewById(R.id.txtmeliyatm);
        txtmeliyatm.setOnClickListener(this);

        linear_number_passport=(LinearLayout)findViewById(R.id.linear_number_passport);
        linear_number_passport.setOnClickListener(this);

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


        switch (v.getId()) {


            case R.id.txtMore:

                linearMahaleeghamat.setVisibility(View.VISIBLE);
                linearMeliyat.setVisibility(View.VISIBLE);
                break;
                case R.id.btn_nextm:


                    String Gender= Gensiyat;
                    String Nationality=txtmahale_eghamat.getText().toString();// "ir";
                    String Nationality_ID= txtmeliyatm.getText().toString().toLowerCase();
                    String RqPassenger_Address= null;
                    String RqPassenger_Birthdate= txttavalodm.getText().toString();
                    String RqPassenger_Email= null;
                    String RqPassenger_FirstNameEn= txtnamem.getText().toString();
                    String RqPassenger_FirstNameFa=null;
                    String RqPassenger_LastNameEn=txtfamilym.getText().toString();
                    String RqPassenger_LastNameFa= null;
                    String RqPassenger_Mobile= null;
                    String RqPassenger_NationalCode= txt_NationalCode_m.getText().toString();//codemeli
                    String RqPassenger_PassExpDate= txtexp_passport.getText().toString();
                    String RqPassenger_PassNo=txtnumber_passport.getText().toString();
                    String RqPassenger_Tel= null;

                    String flagMosafer="T";

                    String errorMessagePartner="";
                    ///Validate
                    if (txt_NationalCode_m.getText().toString() != null && txt_NationalCode_m.getText().toString().length() == 10) {
                        ((EditText) findViewById(R.id.txt_NationalCode_m)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.The_national_code_is_not_correct);
                    }
                    if(linear_number_passport.getVisibility()==View.VISIBLE) {
                        if (RqPassenger_PassNo.trim().length() > 6 && RqPassenger_PassNo.trim().length() < 10 && (RqPassenger_PassNo.trim().substring(0, 1).matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) && RqPassenger_PassNo.trim().substring(1, RqPassenger_PassNo.length() - 1).matches("[0-9]+")) {
                            ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Enter_the_passport_number_correctly);
                        }
                    }
                    if(Nationality != null && Nationality.length()>1){
                        ((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{

                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_the_place_of_residence);
                    }
                    if(Nationality_ID != null && Nationality_ID.length()>1){
                        ((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_your_nationality);
                    }
                    if(RqPassenger_Birthdate != null && RqPassenger_Birthdate.length()>4){
                        ((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_the_date_of_birth);
                    }

                    if(RqPassenger_FirstNameEn != null)
                        if( RqPassenger_FirstNameEn.length()>1 && RqPassenger_FirstNameEn.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")){
                            ((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            flagMosafer=flagMosafer+"F";
                            errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Name_of_at_least_2_characters_and_maximum_100_characters);
                        }
                    if(RqPassenger_LastNameEn != null)
                        if( RqPassenger_LastNameEn.length()>1 && RqPassenger_LastNameEn.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$") ){
                            ((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            flagMosafer=flagMosafer+"F";
                            errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.The_last_name_is_at_least_2_characters_and_a_maximum_of_100_characters);
                        }
                    if(linear_expdate.getVisibility()==View.VISIBLE){
                        if(RqPassenger_PassExpDate != null && RqPassenger_PassExpDate.length()>4){
                            ((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            flagMosafer=flagMosafer+"F";
                            errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_the_passport_expiration_date);
                        }
                    }
                    if (Gensiyat.contains("true") || Gensiyat.contains("false")){
                        flagMosafer=flagMosafer+"T";
                    }else{
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Please_choose_a_gender);
                    }
                    ///endValidate


                   // db.insertData(counter-1,getString(R.string.First_passenger_information),"",Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);

                    ///pak kardan data haye mosafere ghabli:

                    txtnamem.setFocusable(true);
                    txttavalodm.setText("");
                    txtnamem.setText("");
                    txtfamilym.setText("");
                    txtexp_passport.setText("");
                    txtnumber_passport.getText().clear();
                    txt_NationalCode_m.getText().clear();
                    btnzan.setChecked(false);
                    btnmard.setChecked(false);
                    txtnamem.setFocusable(true);
                    Gensiyat="";



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