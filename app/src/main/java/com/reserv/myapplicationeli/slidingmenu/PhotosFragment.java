package com.reserv.myapplicationeli.slidingmenu;


import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.ui.SearchParvazActivity;


public class PhotosFragment extends Fragment implements OnClickListener,DatePickerDialog.OnDateSetListener{
	
	public PhotosFragment(){}
	public Button request_dotarafe,request_yektarafe,parvaz_be_name,parvaz_az_name,count_person_b,count_person_k,tarikh_be;
	public static Button tarikh_az_picker;
	public static Button tarikh_be_picker;
	public  View rootView;
	public MainActivity context;
	public static boolean flag;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       
		rootView = inflater.inflate(R.layout.fragment_photos, container, false);
        
        request_dotarafe = (Button) rootView.findViewById(R.id.request_dotarafe);   
        request_yektarafe = (Button) rootView.findViewById(R.id.request_yektarafe);   
        parvaz_az_name= (Button) rootView.findViewById(R.id.parvaz_az_name);  
        parvaz_be_name= (Button) rootView.findViewById(R.id.parvaz_be_name);
        tarikh_az_picker= (Button) rootView.findViewById(R.id.tarikh_az_picker);
        tarikh_be_picker= (Button) rootView.findViewById(R.id.tarikh_be_picker);
        count_person_b= (Button) rootView.findViewById(R.id.count_person_b);
        count_person_k= (Button) rootView.findViewById(R.id.count_person_k);
        tarikh_be= (Button) rootView.findViewById(R.id.tarikh_be);
        
        request_yektarafe.setOnClickListener(this);
        request_dotarafe.setOnClickListener(this);
        parvaz_az_name.setOnClickListener(this);
        parvaz_be_name.setOnClickListener(this);
        tarikh_az_picker.setOnClickListener(this);
        tarikh_be_picker.setOnClickListener(this);
        count_person_b.setOnClickListener(this);
        count_person_k.setOnClickListener(this);
        tarikh_be.setOnClickListener(this);
        
        return rootView;
    }
	 void ClearMenu(View v){
			((Button)rootView.findViewById(R.id.request_dotarafe)).setBackgroundColor(Color.parseColor("#000080"));
			((Button)rootView.findViewById(R.id.request_yektarafe)).setBackgroundColor(Color.parseColor("#000080"));
		
			((Button)rootView.findViewById(R.id.request_yektarafe)).setTextColor(Color.parseColor("#ffffff"));
			((Button)rootView.findViewById(R.id.request_dotarafe)).setTextColor(Color.parseColor("#ffffff"));//
			
			if(v!=null)
			{
				//v.setBackgroundResource(R.drawable.tab);
				//v.setBackgroundResource(Color.parseColor("#000080"));
				((TextView)v).setTextColor(Color.parseColor("#ff8000"));
			}	
			
		}
	 private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		   @Override
		   public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
		      // arg1 = year
		      // arg2 = month
		      // arg3 = day		
		   }
		};
	@Override
	public void onClick(View v) {
	
		
        
		switch(v.getId()) {
          case R.id.request_yektarafe:
        	  ClearMenu(v);
        	  tarikh_be.setVisibility(View.INVISIBLE);
        	  tarikh_be_picker.setVisibility(View.INVISIBLE);
            break;
          case R.id.request_dotarafe:
        	  ClearMenu(v);
        	  tarikh_be.setVisibility(View.VISIBLE);
        	  tarikh_be_picker.setVisibility(View.VISIBLE);
            break;
          case R.id.parvaz_az_name:
        	 
			Intent i2 = new Intent(getActivity(),SearchParvazActivity.class);
				//i2.putExtra("CUSTOMER_ID", (int) customerID);
				startActivity(i2);
        	
            break;
          case R.id.parvaz_be_name:
        	  Intent i3 = new Intent(getActivity(),SearchParvazActivity.class);
				//i2.putExtra("CUSTOMER_ID", (int) customerID);
				startActivity(i3);
        	
            break;
          case R.id.tarikh_be_picker:
        	  DialogFragment newFragment = new DatePickerFragment();
              newFragment.show(getFragmentManager(), "datePicker");
              flag=false;
        	
            break;
          case R.id.tarikh_az_picker:
        	  DialogFragment newFragment2 = new DatePickerFragment();
              newFragment2.show(getFragmentManager(), "datePicker");
        	flag=true;
            break;
          case R.id.count_person_b:
        	  showNumberPicker(true);
            break;
          case R.id.count_person_k:
        	  showNumberPicker(false);
            break;
            
        }
	}
	public void showNumberPicker(final boolean f){
		final Dialog npDialog = new Dialog(getActivity());
		npDialog.setTitle("لطفا تعداد را وارد کنید :");
		npDialog.setContentView(R.layout.numberpicker_layout);
		Button setBtn = (Button)npDialog.findViewById(R.id.btn_set);
		Button cnlBtn = (Button)npDialog.findViewById(R.id.btn_cancle);
		
		final NumberPicker numberPicker = (NumberPicker)npDialog.findViewById(R.id.num_picker);
		numberPicker.setMaxValue(10);
		numberPicker.setMinValue(0);
		numberPicker.setWrapSelectorWheel(false);
		numberPicker.setOnValueChangedListener(new OnValueChangeListener(){
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
			}
		});
		
		setBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(f)
					count_person_b.setText(numberPicker.getValue()+"");
				else
					count_person_k.setText(numberPicker.getValue()+"");
				
				Toast.makeText(getActivity(), "Number selected: " + numberPicker.getValue() , Toast.LENGTH_SHORT).show();
				
				npDialog.dismiss();
			}
		});
		
		cnlBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				npDialog.dismiss();
			}
		});
		
		npDialog.show();
	}
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	}
    public static class DatePickerFragment extends DialogFragment
    			implements DatePickerDialog.OnDateSetListener {

			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
			    final Calendar c = Calendar.getInstance();
			    int year = c.get(Calendar.YEAR);
			    int month = c.get(Calendar.MONTH);
			    int day = c.get(Calendar.DAY_OF_MONTH);
			    DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
			    //dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
			    return  dialog;
			}
			
			public void onDateSet(DatePicker view, int year, int month, int day) {
				//tarikh_be_picker.setText(year+ month + day+"");
				month=month+1;
				if(flag)
					tarikh_az_picker.setText(year+"/"+ month +"/"+ day);
				else
					tarikh_be_picker.setText(year+"/"+ month +"/"+ day);
			}
			}

}
