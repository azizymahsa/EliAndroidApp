package com.reserv.myapplicationeli.views.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.GlobalApplication;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;


public class HotelCountRoomAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<ModelRowCountRoom> data;
	public String value_Maghsad_City;
	public String value_Maghsad_Airport;
	public String value_Maghsad_Airport_Code;
	 ArrayList<ModelRowCountRoom> itemModelList;
	 
	public HotelCountRoomAdapter() {
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}
	 // create constructor to innitilize context and data sent from MainActivity
    public HotelCountRoomAdapter(Context context, List<ModelRowCountRoom> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        myInflater = LayoutInflater.from(GlobalApplication.getActivity());
        
       /* this.value_Maghsad_City=value_Maghsad_City;
        this.value_Maghsad_Airport=value_Maghsad_Airport;
        this.value_Maghsad_Airport_Code=value_Maghsad_Airport_Code;*/
    }
	public HotelCountRoomAdapter(Activity activity){
		this.context=activity;
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}

	public HotelCountRoomAdapter(Activity context2, List<ModelRowCountRoom> data2) {
		this.context=context2;
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
		this.data = data2;
		notifyDataSetChanged();
	}
	public void setData(List<ModelRowCountRoom> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public void setData(String searchText) {
		this.data = data;
		notifyDataSetChanged();
	}
	/*
	public void setData(String searchText) {
		this.cursor = new Customers_Table().getCustomersFilter(searchText);
		//initiated = true;
		notifyDataSetChanged();
	}*/
	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}


	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
	 //data.moveToPosition(position);
	 //Country current=data.get(position);
	long s =position+1;
		//return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
		return s;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		
		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_count_room, null);
			holder = new ViewHolder();

			///
			holder.btnPlusB= (Button) convertView.findViewById(R.id.btnPlusB);
			holder.txtCountB= (TextView) convertView.findViewById(R.id.txtCountB);
			holder.btnMinesB= (Button) convertView.findViewById(R.id.btnMinesB);
			
			holder.btnPlusK= (Button) convertView.findViewById(R.id.btnPlusK);
			holder.txtCountK= (TextView) convertView.findViewById(R.id.txtCountK);
			holder.btnMinesK= (Button) convertView.findViewById(R.id.btnMinesK);
			
			holder.btnPlusN= (Button) convertView.findViewById(R.id.btnPlusN);
			holder.txtCountN= (TextView) convertView.findViewById(R.id.txtCountN);
			holder.btnMinesN= (Button) convertView.findViewById(R.id.btnMinesN);
			
			holder.txtDelete= (TextView) convertView.findViewById(R.id.txtDelete);
		
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//cursor.moveToPosition(position);
		final ModelRowCountRoom current=data.get(position);
		//holder.txtDescription.setText(current.getServiceDescFa()+ "");
	
		// click listiner for remove button  
		holder.txtDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try
		        {
					data.remove(position);  
					notifyDataSetChanged();  
		         }
		         catch(Exception e)
		         {
		             e.printStackTrace();
		            
		         }
					
			
			}
		});
		
		holder.btnPlusB.setTag(current.getCountB());
		holder.btnPlusB.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						try
				        {
				            String btnPlusBStr=holder.txtCountB.getText().toString();
				            int btnPlusBIntVal=Integer.parseInt(btnPlusBStr);
				            if (isInRange(1, 8, btnPlusBIntVal))
				            btnPlusBIntVal=btnPlusBIntVal+1;
				            holder.txtCountB.setText(String.valueOf(btnPlusBIntVal));
				         }
				         catch(Exception e)
				         {
				             e.printStackTrace();
				            
				         }
							
					
					}
				});
		holder.btnPlusK.setTag(current.getCountB());
		holder.btnPlusK.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						try
				        {
				            String btnPlusBStr=holder.txtCountK.getText().toString();
				            int btnPlusBIntVal=Integer.parseInt(btnPlusBStr);
				            if (isInRange(0, 8, btnPlusBIntVal))
				            btnPlusBIntVal=btnPlusBIntVal+1;
				            holder.txtCountK.setText(String.valueOf(btnPlusBIntVal));
				         }
				         catch(Exception e)
				         {
				             e.printStackTrace();
				            
				         }
							
					
					}
				});
		holder.btnPlusN.setTag(current.getCountB());
		holder.btnPlusN.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						try
				        {
				            String btnPlusBStr=holder.txtCountN.getText().toString();
				            int btnPlusBIntVal=Integer.parseInt(btnPlusBStr);
				            if (isInRange(0, 8, btnPlusBIntVal))
				            btnPlusBIntVal=btnPlusBIntVal+1;
				            holder.txtCountN.setText(String.valueOf(btnPlusBIntVal));
				         }
				         catch(Exception e)
				         {
				             e.printStackTrace();
				            
				         }
							
					
					}
				});
		holder.btnMinesB.setTag(current.getCountB());
		holder.btnMinesB.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						try
				        {
				            String btnMinesBValStr=holder.txtCountB.getText().toString();
				            int btnMinesBIntVal=Integer.parseInt(btnMinesBValStr);
				            if (isInRange(2, 9, btnMinesBIntVal))
				            btnMinesBIntVal=btnMinesBIntVal-1;
				            holder.txtCountB.setText(String.valueOf(btnMinesBIntVal));
				         }
				         catch(Exception e)
				         {
				             e.printStackTrace();
				            
				         }
							
					
					}
				});
		holder.btnMinesK.setTag(current.getCountB());
		holder.btnMinesK.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						try
				        {
				            String btnMinesBValStr=holder.txtCountK.getText().toString();
				            int btnMinesBIntVal=Integer.parseInt(btnMinesBValStr);
				            if (isInRange(1, 9, btnMinesBIntVal))
				            btnMinesBIntVal=btnMinesBIntVal-1;
				            holder.txtCountK.setText(String.valueOf(btnMinesBIntVal));
				         }
				         catch(Exception e)
				         {
				             e.printStackTrace();
				            
				         }
							
					
					}
				});
		holder.btnMinesN.setTag(current.getCountB());
		holder.btnMinesN.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						try
				        {
				            String btnMinesBValStr=holder.txtCountN.getText().toString();
				            int btnMinesBIntVal=Integer.parseInt(btnMinesBValStr);
				            if (isInRange(1, 9, btnMinesBIntVal))
				            btnMinesBIntVal=btnMinesBIntVal-1;
				            holder.txtCountN.setText(String.valueOf(btnMinesBIntVal));
				         }
				         catch(Exception e)
				         {
				             e.printStackTrace();
				            
				         }
							
					
					}
				});
		return convertView;
		}
	 public boolean isInRange(int a, int b, int c) {
	        return b > a ? c >= a && c <= b : c >= b && c <= a;
	 }
	static class ViewHolder {
		
		public Button btnMinesN;
		public TextView txtCountN;
		public Button btnPlusN;
		public Button btnMinesK;
		public TextView txtCountK;
		public Button btnPlusK;
		public TextView txtCountB;
		public Button btnMinesB;
		public Button btnPlusB;
		
		public TextView txtDelete;
		
		
	}


}