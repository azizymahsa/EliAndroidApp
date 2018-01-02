package com.reserv.myapplicationeli.views.ui;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.base.BaseActivity;

public class PlanFragment extends Fragment implements OnClickListener {
    public PlanFragment() {
    }

    public static boolean flag;
    public static Button tarikh_az_picker;
    public static Button tarikh_be_picker;
    public TextView txtCountB, tvStart, tvEnd, txtCountK, txtCountN, txtOption, lbl_forudgah_maghsad, lbl_forudgah_mabda, txtKO, txtBO, txtNO, textView3;
    public Button btnPlusB, btnMinesB, btnPlusK, btnMinesK, btnPlusN, btnMinesN, btntwo, btnOne, searchPlan, tarikh_az, tarikh_be;
    private LinearLayout linear_picker_title, linear_picker;
    public int flagOneTwo = 2;
    private static String picker_be = "2017-12-29";
    private static String picker_az = "2017-12-25";
    private static String picker_az_format = "29 December 2017";
    private static String picker_be_format = "25 December 2017";
    public static int picker_az_year;
    public static int picker_az_month;
    public static int picker_az_day;
    private View rootView;


    public static int countNafar = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_plane, container, false);

        //	rootView = inflater.inflate(R.layout.fragment_plane, container, false);


        tarikh_az_picker = (Button) rootView.findViewById(R.id.tarikh_az_picker);
        tarikh_be_picker = (Button) rootView.findViewById(R.id.tarikh_be_picker);
    /*tarikh_az_picker.setTypeface(face);
    tarikh_be_picker.setTypeface(face);*/

        tarikh_az = (Button) rootView.findViewById(R.id.tarikh_az);
        tarikh_be = (Button) rootView.findViewById(R.id.tarikh_be);


        btnPlusB = (Button) rootView.findViewById(R.id.btnPlusB);
        btnMinesB = (Button) rootView.findViewById(R.id.btnMinesB);

        btnPlusK = (Button) rootView.findViewById(R.id.btnPlusK);
        btnMinesK = (Button) rootView.findViewById(R.id.btnMinesK);

        btnPlusN = (Button) rootView.findViewById(R.id.btnPlusN);
        btnMinesN = (Button) rootView.findViewById(R.id.btnMinesN);

        btntwo = (Button) rootView.findViewById(R.id.btntwo);
        btnOne = (Button) rootView.findViewById(R.id.btnOne);


        searchPlan = (Button) rootView.findViewById(R.id.searchPlan);
        txtBO = (TextView) rootView.findViewById(R.id.txtBO);
        txtKO = (TextView) rootView.findViewById(R.id.txtKO);
        txtNO = (TextView) rootView.findViewById(R.id.txtNO);

        textView3 = (TextView) rootView.findViewById(R.id.textView3);
        txtCountB = (TextView) rootView.findViewById(R.id.txtCountB);
        txtCountK = (TextView) rootView.findViewById(R.id.txtCountK);
        txtCountN = (TextView) rootView.findViewById(R.id.txtCountN);
        tvStart = (TextView) rootView.findViewById(R.id.tvStart);

        txtOption = (TextView) rootView.findViewById(R.id.txtOption);
        tvEnd = (TextView) rootView.findViewById(R.id.tvEnd);
        lbl_forudgah_mabda = (TextView) rootView.findViewById(R.id.lbl_forudgah_mabda);
        lbl_forudgah_maghsad = (TextView) rootView.findViewById(R.id.lbl_forudgah_maghsad);


        tarikh_az_picker.setOnClickListener(this);
        tarikh_be_picker.setOnClickListener(this);
        btnPlusB.setOnClickListener(this);
        btnMinesB.setOnClickListener(this);

        btnPlusK.setOnClickListener(this);
        btnMinesK.setOnClickListener(this);

        btnPlusN.setOnClickListener(this);
        btnMinesN.setOnClickListener(this);

        tvStart.setOnClickListener(this);
        tvEnd.setOnClickListener(this);
        txtOption.setOnClickListener(this);

        btntwo.setOnClickListener(this);
        btnOne.setOnClickListener(this);

        searchPlan.setOnClickListener(this);

        /////////
        Calendar c = Calendar.getInstance();//MMMM

        SimpleDateFormat dfm = new SimpleDateFormat("dd MMMM yyyy");
        picker_az_format = dfm.format(c.getTime());
        picker_be_format = dfm.format(c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//"2018-01-29"
        picker_az = df.format(c.getTime());
        picker_be = df.format(c.getTime());
        System.out.println("picker_az:" + picker_az + "Picker_az_format" + picker_az_format);

        tarikh_az_picker.setText(picker_az_format);
        tarikh_be_picker.setText(picker_be_format);
        //////


        //set value bundle
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("Value-Mabda-Airport") != null) {
                      /*i4.putExtra("Value-Mabda-City",current.getCityName());
					i4.putExtra("Value-Mabda-Airport",current.getAirportName());*/
                tvStart.setText("" + bundle.getString("Value-Mabda-City"));
                lbl_forudgah_mabda.setText("" + bundle.getString("Value-Mabda-Airport"));
            }
        }
        //	Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("Value-Maghsad-Airport") != null) {
      	      /*	i4.putExtra("Value-Maghsad-City",current.getCityName());
				i4.putExtra("Value-Maghsad-Airport",current.getAirportName());*/
                tvEnd.setText("" + bundle.getString("Value-Maghsad-City"));
                lbl_forudgah_maghsad.setText("" + bundle.getString("Value-Maghsad-Airport"));
            }
        }
        //return rootView;


        return rootView;
    }//end oncreat

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog dialog = null;
            if (flag) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                dialog = new DatePickerDialog(getActivity(), this, year, month, day);
                dialog.getDatePicker().setMinDate(c.getTimeInMillis());
            } else {
                int year = picker_az_year;
                int month = picker_az_month;
                int day = picker_az_day;
                dialog = new DatePickerDialog(getActivity(), this, year, month, day);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date mDate;
                try {
                    mDate = sdf.parse(year + "-" + month + "-" + day);

                    long timeInMilliseconds = mDate.getTime();
                    System.out.println("Date in milli :: " + timeInMilliseconds);

                    dialog.getDatePicker().setMinDate(timeInMilliseconds);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            //tarikh_be_picker.setText(year+ month + day+"");


            month = month + 1;
            if (flag) {
                tarikh_az_picker.setText(day + " " + new DateFormatSymbols().getMonths()[month - 1] + " " + year);
                picker_az = year + "-" + (month) + "-" + day;//"2018-01-29"
                picker_az_format = day + " " + new DateFormatSymbols().getMonths()[month - 1] + " " + year;

                picker_az_year = year;
                picker_az_month = month - 1;
                picker_az_day = day;


            } else {
                tarikh_be_picker.setText(day + " " + new DateFormatSymbols().getMonths()[month - 1] + " " + year);
                picker_be = year + "-" + (month) + "-" + day;//"2018-01-29"
                picker_be_format = day + " " + new DateFormatSymbols().getMonths()[month - 1] + " " + year;
            }
        }
    }//endDatepicker

    public boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tarikh_be_picker:
                DialogFragment newFragment = new DatePickerFragment();
             //   newFragment.show(getFragmentManager(), "datePicker");
                flag = false;

                break;
            case R.id.tarikh_az_picker:
                DialogFragment newFragment2 = new DatePickerFragment();
                //newFragment2.show(getFragmentManager(), "datePicker");
                flag = true;
                break;

            case R.id.btnPlusB:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String btnPlusBStr = txtCountB.getText().toString();
                        int btnPlusBIntVal = Integer.parseInt(btnPlusBStr);
                        if (isInRange(1, 8, btnPlusBIntVal))
                            btnPlusBIntVal = btnPlusBIntVal + 1;
                        txtCountB.setText(String.valueOf(btnPlusBIntVal));//}
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Some error :(", 2000).show();
                    }
                }
                break;
            case R.id.btnMinesB:
                try {
                    String btnMinesBValStr = txtCountB.getText().toString();
                    int btnMinesBIntVal = Integer.parseInt(btnMinesBValStr);
                    if (isInRange(2, 9, btnMinesBIntVal))
                        btnMinesBIntVal = btnMinesBIntVal - 1;
                    txtCountB.setText(String.valueOf(btnMinesBIntVal));//}
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                }
                break;

            case R.id.btnPlusK:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String btnPlusKValStr = txtCountK.getText().toString();
                        int btnPlisKIntVal = Integer.parseInt(btnPlusKValStr);
                        if (isInRange(0, 8, btnPlisKIntVal))
                            btnPlisKIntVal = btnPlisKIntVal + 1;
                        txtCountK.setText(String.valueOf(btnPlisKIntVal));//}
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                    }
                }
                break;
            case R.id.btnMinesK:
                try {
                    String btnMinesKValStr = txtCountK.getText().toString();
                    int btnMinesKIntVal = Integer.parseInt(btnMinesKValStr);
                    if (isInRange(1, 9, btnMinesKIntVal))
                        btnMinesKIntVal = btnMinesKIntVal - 1;
                    txtCountK.setText(String.valueOf(btnMinesKIntVal));//}
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                }
                break;

            case R.id.btnPlusN:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String presentValStr3 = txtCountN.getText().toString();
                        int presentIntVal3 = Integer.parseInt(presentValStr3);
                        if (isInRange(0, 8, presentIntVal3))
                            presentIntVal3 = presentIntVal3 + 1;
                        txtCountN.setText(String.valueOf(presentIntVal3));//}
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                    }
                }
                break;
            case R.id.btnMinesN:
                try {
                    String presentValStr4 = txtCountN.getText().toString();
                    int presentIntVal4 = Integer.parseInt(presentValStr4);
                    if (isInRange(1, 9, presentIntVal4))
                        presentIntVal4 = presentIntVal4 - 1;
                    txtCountN.setText(String.valueOf(presentIntVal4));//}
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                }
                break;
            case R.id.tvEnd:

                Intent i3 = new Intent(getActivity(), GetAirportMaghsadActivity.class);
                Bundle bundle = getActivity().getIntent().getExtras();
                if (bundle != null) {
                    i3.putExtra("Value-Mabda-City", bundle.getString("Value-Mabda-City"));
                    i3.putExtra("Value-Mabda-Airport", bundle.getString("Value-Mabda-Airport"));
                    i3.putExtra("Value-Mabda-Airport-Code", bundle.getString("Value-Mabda-Airport-Code"));//*THR
                }

                startActivityForResult(i3, 2);
                break;
            case R.id.tvStart:


                Intent intent = new Intent(getActivity(), GetAirportMabdaActivity.class);
                Bundle bundle2 = getActivity().getIntent().getExtras();
                if (bundle2 != null) {
                    intent.putExtra("Value-Maghsad-City", bundle2.getString("Value-Maghsad-City"));
                    intent.putExtra("Value-Maghsad-Airport", bundle2.getString("Value-Maghsad-Airport"));
                    intent.putExtra("Value-Maghsad-Airport-Code", bundle2.getString("Value-Maghsad-Airport-Code"));//*
                }

                startActivityForResult(intent, 2);
                break;
            case R.id.txtOption:
                String start = "";
                String end = "";
                String startF = "";
                String endF = "";

                start = tvStart.getText().toString();
                end = tvEnd.getText().toString();
                startF = lbl_forudgah_mabda.getText().toString();
                endF = lbl_forudgah_maghsad.getText().toString();

                tvStart.setText(end);
                tvEnd.setText(start);

                lbl_forudgah_mabda.setText(endF);
                lbl_forudgah_maghsad.setText(startF);
                break;
            case R.id.btntwo:
                flagOneTwo = 2;
                ((Button) rootView.findViewById(R.id.btntwo)).setBackgroundResource(R.drawable.purple_button_larg);
                ((Button) rootView.findViewById(R.id.btnOne)).setBackgroundResource(R.drawable.raft_big);
                ((Button) rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#ffffff"));
                ((Button) rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#d9d9d9"));

                linear_picker_title = (LinearLayout) rootView.findViewById(R.id.linear_picker_title);
                linear_picker = (LinearLayout) rootView.findViewById(R.id.linear_picker);

                linear_picker_title.setVisibility(View.VISIBLE);
                linear_picker.setVisibility(View.VISIBLE);
                //v.setBackgroundResource(R.drawable.btnwhite);
			/*((Button)rootView.findViewById(R.id.btnAward)).setBackgroundDrawable(R.drawable.background_back);
			((Button)rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#E06F3"));
			((Button)rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#ffffff"));*/
                break;
            case R.id.btnOne:
                flagOneTwo = 1;
                ((Button) rootView.findViewById(R.id.btnOne)).setBackgroundResource(R.drawable.purple_button_larg);
                ((Button) rootView.findViewById(R.id.btntwo)).setBackgroundResource(R.drawable.raft_big);
                ((Button) rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#ffffff"));
                ((Button) rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#d9d9d9"));

                linear_picker_title = (LinearLayout) rootView.findViewById(R.id.linear_picker_title);
                linear_picker = (LinearLayout) rootView.findViewById(R.id.linear_picker);

                linear_picker_title.setVisibility(View.INVISIBLE);
                linear_picker.setVisibility(View.INVISIBLE);
                break;
            case R.id.searchPlan:

                Intent intent1 = new Intent(getActivity(), SearchParvazActivity.class);
                //Intent intent1 = new Intent(this,LoadingParvazTwoActivity.class);
                Bundle bundleS = getActivity().getIntent().getExtras();
                if (bundleS != null) {
                    if (bundleS.getString("Value-Mabda-Airport-Code") != null) {
                        intent1.putExtra("Value-Mabda-City", bundleS.getString("Value-Mabda-City"));
                        intent1.putExtra("Value-Mabda-Airport", bundleS.getString("Value-Mabda-Airport"));
                        intent1.putExtra("Value-Mabda-Airport-Code", bundleS.getString("Value-Mabda-Airport-Code"));//*THR
                    } else {
                        intent1.putExtra("Value-Mabda-City", tvStart.getText().toString());
                        intent1.putExtra("Value-Mabda-Airport", lbl_forudgah_mabda.getText().toString());
                        intent1.putExtra("Value-Mabda-Airport-Code", "THR");//*THR
                    }
                    if (bundleS.getString("Value-Maghsad-Airport-Code") != null) {
                        intent1.putExtra("Value-Maghsad-City", bundleS.getString("Value-Maghsad-City"));
                        intent1.putExtra("Value-Maghsad-Airport", bundleS.getString("Value-Maghsad-Airport"));
                        intent1.putExtra("Value-Maghsad-Airport-Code", bundleS.getString("Value-Maghsad-Airport-Code"));//*

                    } else {
                        intent1.putExtra("Value-Maghsad-City", tvEnd.getText().toString());
                        intent1.putExtra("Value-Maghsad-Airport", lbl_forudgah_maghsad.getText().toString());
                        intent1.putExtra("Value-Maghsad-Airport-Code", "IST");//*
                    }
                    intent1.putExtra("Value-Mabda-City", tvStart.getText().toString());
                    intent1.putExtra("Value-Maghsad-City", tvEnd.getText().toString());

                    intent1.putExtra("Value-Flag-Two", Integer.toString(flagOneTwo));
                    intent1.putExtra("Value-AdlCount", txtCountB.getText().toString());
                    intent1.putExtra("Value-ChdCount", txtCountK.getText().toString());
                    intent1.putExtra("Value-InfCount", txtCountN.getText().toString());

                    intent1.putExtra("Value-DepartureDate", picker_az);//2017-11-24
                    intent1.putExtra("Value-ArrivalDate", picker_be);//2017-11-29

                    intent1.putExtra("Value-DepartureDate-format", picker_az_format);//2017-11-24
                    intent1.putExtra("Value-ArrivalDate-format", picker_be_format);//2017-11-29


                } else {//default
                    intent1.putExtra("Value-Mabda-City", tvStart.getText().toString());
                    intent1.putExtra("Value-Mabda-Airport", lbl_forudgah_mabda.getText().toString());
                    intent1.putExtra("Value-Mabda-Airport-Code", "THR");//*THR

                    intent1.putExtra("Value-Maghsad-City", tvEnd.getText().toString());
                    intent1.putExtra("Value-Maghsad-Airport", lbl_forudgah_maghsad.getText().toString());
                    intent1.putExtra("Value-Maghsad-Airport-Code", "IST");//*

                    intent1.putExtra("Value-Flag-Two", Integer.toString(flagOneTwo));
                    intent1.putExtra("Value-AdlCount", txtCountB.getText().toString());
                    intent1.putExtra("Value-ChdCount", txtCountK.getText().toString());
                    intent1.putExtra("Value-InfCount", txtCountN.getText().toString());

                    intent1.putExtra("Value-DepartureDate", picker_az);//2017-11-24
                    intent1.putExtra("Value-ArrivalDate", picker_be);//2017-11-29

                    intent1.putExtra("Value-DepartureDate-format", picker_az_format);//2017-11-24
                    intent1.putExtra("Value-ArrivalDate-format", picker_be_format);//2017-11-29
                }

                startActivity(intent1);

                break;

        }
    }
}
