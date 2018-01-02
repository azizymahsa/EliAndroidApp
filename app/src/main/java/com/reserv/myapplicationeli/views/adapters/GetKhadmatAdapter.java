package com.reserv.myapplicationeli.views.adapters;

import java.text.NumberFormat;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.ui.GetAirportMaghsadActivity;
import com.reserv.myapplicationeli.slidingmenu.ui.PassengerActivity;
import com.reserv.myapplicationeli.slidingmenu.ui.PlanFragment;
import com.reserv.myapplicationeli.slidingmenu.ui.PurchaseFlightResult;

public class GetKhadmatAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<PurchaseFlightResult> data;
	public String value_Maghsad_City;
	public String value_Maghsad_Airport;
	public String value_Maghsad_Airport_Code;

	public GetKhadmatAdapter() {
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}
	// create constructor to innitilize context and data sent from MainActivity
	public GetKhadmatAdapter(Context context, List<PurchaseFlightResult> data){
		this.context=context;
		inflater= LayoutInflater.from(context);
		this.data=data;
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
        
       /* this.value_Maghsad_City=value_Maghsad_City;
        this.value_Maghsad_Airport=value_Maghsad_Airport;
        this.value_Maghsad_Airport_Code=value_Maghsad_Airport_Code;*/
	}
	public GetKhadmatAdapter(Activity activity){
		this.context=activity;
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}

	public void setData(List<PurchaseFlightResult> data) {
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

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_khadamat, null);
			holder = new ViewHolder();

			holder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
			holder.txtServiceNameFa = (TextView) convertView.findViewById(R.id.txtServiceNameFa);

			holder.imageView1= (ImageView) convertView.findViewById(R.id.imageView1);

			holder.btnAddsabad= (Button) convertView.findViewById(R.id.btnAddsabad);
			holder.txtServiceTotalPrice= (TextView) convertView.findViewById(R.id.txtServiceTotalPrice);

			//holder.btnSwip = (Button) convertView.findViewById(R.id.swipe_button);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//cursor.moveToPosition(position);
		final PurchaseFlightResult current=data.get(position);
		holder.txtDescription.setText(current.getServiceDescFa()+ "");

		holder.txtServiceNameFa.setText(current.getServiceNameFa());
		holder.txtServiceTotalPrice.setText(String.valueOf(NumberFormat.getInstance().format(current.getServiceTotalPrice()))+"");
		if(current.getServiceTypeID().equals("4"))
			holder.imageView1.setBackgroundResource(R.drawable.cip_service_khadamat);

		if(current.getServiceTypeID().equals("-1515"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_bime_khadamat);

		if(current.getServiceTypeID().equals("1"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_transfer_forudgahi);

		if(current.getServiceTypeID().equals("9"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_transfer_forudgahi);

		holder.btnAddsabad.setTag(current.getServiceID());
		holder.btnAddsabad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//tick_round_button

				String buttonText =  holder.btnAddsabad.getText().toString();
				//Drawable icon= context.getResources(). getDrawable( R.drawable.tick_round_button);
				//show icon to the right of text
				//	holder.btnAddsabad.setCompoundDrawablesWithIntrinsicBounds( null, null, icon, null );
				//PassengerActivity.GET_PRICE_KHADAMAT=PassengerActivity.GET_PRICE_KHADAMAT+current.getServiceTotalPrice();
				Toast.makeText(v.getContext(),current.getServiceID()+" "+current.getServiceTotalPrice(),Toast.LENGTH_SHORT).show();
				if(!buttonText.contains("اضافه"))
					PassengerActivity.updateTotalInfos(current.getServiceTotalPrice());

				holder.btnAddsabad.setBackgroundResource(R.drawable.green_button);
				holder.btnAddsabad.setText("اضافه شد");
			}
		});
		/*holder.AirportName.setTag(current.getCurrency_ID());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						Intent i4 = new Intent(context,PlanFragment.class);
						
						i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						
						i4.putExtra("Value-Mabda-City",current.getCurrency_ID());//current.getCityName()
						i4.putExtra("Value-Mabda-Airport",current.getCurrency_ID());
						i4.putExtra("Value-Mabda-Airport-Code",current.getCurrency_ID());
					
						i4.putExtra("Value-Maghsad-City",value_Maghsad_City);//current.getCityName()
						i4.putExtra("Value-Maghsad-Airport",value_Maghsad_Airport);
						i4.putExtra("Value-Maghsad-Airport-Code",value_Maghsad_Airport_Code);
						context.startActivity(i4);
						
						

						Toast.makeText(v.getContext(),current.getCurrency_ID()+" "+current.getCurrency_ID(),Toast.LENGTH_SHORT).show();
					}
				});
		holder.CityName.setTag(current.getCurrency_ID());
		holder.CityName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						Intent i4 = new Intent(context,PlanFragment.class);
						
						i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						i4.putExtra("Value-Mabda-City",current.getCurrency_ID());//current.getCityName()
						i4.putExtra("Value-Mabda-Airport",current.getCurrency_ID());
						i4.putExtra("Value-Mabda-Airport-Code",current.getCurrency_ID());
						context.startActivity(i4);
						
						Toast.makeText(v.getContext(),current.getCurrency_ID()+" "+current.getCurrency_ID(),Toast.LENGTH_SHORT).show();
					}
				});*/
		return convertView;
	}

	static class ViewHolder {
		TextView txtServiceTotalPrice;
		TextView txtDescription;
		TextView txtServiceNameFa;
		ImageView imageView1;
		Button btnAddsabad;

	}


}