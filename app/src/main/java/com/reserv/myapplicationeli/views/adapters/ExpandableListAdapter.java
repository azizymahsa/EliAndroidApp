package com.reserv.myapplicationeli.views.adapters;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;
import com.reserv.myapplicationeli.views.ui.SearchParvazActivity;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	Activity activity;

	List<SearchParvazActivity.ParentItemExpandingPlan>  dataExpandingList;

	public ExpandableListAdapter(Context context,List<SearchParvazActivity.ParentItemExpandingPlan> dataList) {
		this._context = context;

		this.dataExpandingList = dataList;




	}

/*
	public ExpandableListAdapter(
			Activity activity,
			//List<String> listDataHeaderExpanding,
			HashMap<String, HashMap<String, SearchParvazActivity.HeaderExpandingPlan>> listDataHeaderExpanding,
			HashMap<String, HashMap<String, SearchParvazActivity.ItemExpandingPlan>> listDataChildExpanding) {
		this._context = activity;
		
	}
*/




	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.dataExpandingList.get(groupPosition).Items.get(childPosititon);

	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		System.out.println("groupPosition:"+groupPosition+"childPosition:"+childPosition);
		final SearchParvazActivity.ItemExpandingPlan item = this.dataExpandingList.get(groupPosition).Items.get(childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.row_select_parvaz_two_detail, null);
		}


		TextView lblFlightTimeR = (TextView) convertView.findViewById(R.id.lblFlightTimeR);
		TextView lblFlightArrivalTimeR = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);
		TextView lblDepurtureAirportR = (TextView) convertView.findViewById(R.id.lblDepurtureAirportR);
		TextView lblArrivalAirportR=(TextView)convertView.findViewById(R.id.lblArrivalAirportR);
		TextView lblFlightNumberR = (TextView) convertView.findViewById(R.id.lblFlightNumberR);

		LinearLayout linearTableNerkh = (LinearLayout) convertView.findViewById(R.id.linearTableNerkh);//
		LinearLayout linearButton = (LinearLayout) convertView.findViewById(R.id.linearButton);
		//nerkh
		TextView txtAdlCostP = (TextView) convertView.findViewById(R.id.txtAdlCostP);
		TextView txtTaxes=(TextView)convertView.findViewById(R.id.txtTaxes);
		TextView txtTotalFareCost = (TextView) convertView.findViewById(R.id.txtTotalFareCost);



		Button btnSelect = (Button) convertView.findViewById(R.id.btnSelect);


		//nerkh
		txtAdlCostP.setText(String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)));
		txtTaxes.setText(String.valueOf(NumberFormat.getInstance().format(item.Taxes)));
		txtTotalFareCost.setText(String.valueOf(NumberFormat.getInstance().format(item.TotalFare)));

		lblFlightTimeR.setText(item.FlightTimeR+"");
		lblFlightArrivalTimeR.setText(item.FlightArrivalTimeR+"");
		lblDepurtureAirportR.setText(item.DepartureCityNameFa+" , "+item.DepartureAirportNameFaR);
		lblArrivalAirportR.setText(item.ArrivalCityNameFa+" , "+item.ArrivalAirportNameFaR);
		lblFlightNumberR.setText(item.AirlineCode+item.FlightNumberR+" , "+ item.AirlineNameFaR);
		//txtListChildSumPrice.setText("جمع :"+String.valueOf(NumberFormat.getInstance().format(item.fee*item.amount)));
		int size=this.dataExpandingList.get(groupPosition).Items.size();
		int childSize=childPosition+1;
		System.out.println(size +"ggg"+childPosition);
		if(childSize != size){
			btnSelect.setTag(childPosition);
			btnSelect.setVisibility(View.GONE);
			linearTableNerkh.setVisibility(View.GONE);
			linearButton.setVisibility(View.GONE);
		}else{
			btnSelect.setTag(childPosition);
			btnSelect.setVisibility(View.VISIBLE);
			linearTableNerkh.setVisibility(View.VISIBLE);
			linearButton.setVisibility(View.VISIBLE);
		}
		// this.dataExpandingList.get(groupPosition).Items.size();
		btnSelect.setTag(childPosition);
		btnSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i4 = new Intent(_context,PassengerActivity.class);

				//i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i4.putExtra("Flight_GUID",item.flGUID+"");//current.getCityName()
					/*i4.putExtra("Value-Mabda-Airport",current.getAirportName());
					i4.putExtra("Value-Mabda-Airport-Code",current.getAirportCode());*/
				_context.startActivity(i4);
				System.out.println("item.flGUID:"+item.flGUID);
				Toast.makeText(v.getContext(),"fffff"+childPosition+"GUID:"+item.flGUID,Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;

	}

	/*	public int getChildrenCount(int groupPosition) {
            // TODO Auto-generated method stub
            return this.listItem.get(groupPosition).size();
        }*/
	@Override
	public int getChildrenCount(int groupPosition) {
		//return this.dataExpandingList.get(groupPosition).Items.size();
		System.out.println("groupPositionRR"+groupPosition);
		System.out.println("groupPositionRR2"+this.dataExpandingList.get(groupPosition).Items.size());
		//return 10;
		return this.dataExpandingList.get(groupPosition).Items.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.dataExpandingList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.dataExpandingList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}



	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		ArrayList<SearchParvazActivity.HeaderExpandingPlan> item2 = this.dataExpandingList.get(groupPosition).Header;


		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_expanding, null);//list Group header//row_select_parvaz_two_header
		}





		TextView lblArrivalCityNameFaR = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaR);
		TextView lblFlightArrivalTimeR = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);



		TextView lblArrivalCityNameFaB = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaB);
		TextView lblFlightArrivalTimeB = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeB);

		TextView lblAdlCost = (TextView) convertView.findViewById(R.id.lblAdlCost);
		TextView lblAirline = (TextView) convertView.findViewById(R.id.lblAirline);

		ImageView lblProductrow= (ImageView) convertView.findViewById(R.id.lblProductrow);

		TextView txt_economi = (TextView) convertView.findViewById(R.id.txt_economi);
		//TextView textCharter = (TextView) convertView.findViewById(R.id.textCharter);
		TextView txttedad = (TextView) convertView.findViewById(R.id.txttedad);

		///
		lblArrivalCityNameFaB.setText(" برگشت به "+item2.get(0).DepartureCityNameFaB+"");
		lblFlightArrivalTimeB.setText(item2.get(0).FlightTimeB+"");


		//
		lblArrivalCityNameFaR.setText(" رفت به "+item2.get(0).DepartureCityNameFaR+"");
		lblFlightArrivalTimeR.setText(item2.get(0).FlightArrivalTimeR+"");

		lblAdlCost.setText(item2.get(0).AdlCost+"");
		lblAdlCost.setText(String.valueOf(NumberFormat.getInstance().format(item2.get(0).AdlCost)));

		lblAirline.setText(item2.get(0).AirlineNameFa);

		txt_economi.setText(item2.get(0).CabinClassNameFa);

		if(item2.get(0).RemainSeats ==0){
			txttedad.setText("نفر"+item2.get(0).RemainSeats+"فقط");
			txttedad.setVisibility(View.INVISIBLE);

		}else{
			txttedad.setText("فقط"+item2.get(0).RemainSeats+"نفر");
			txttedad.setVisibility(View.VISIBLE);
		}
	/*	if(item2.get(0).IsCharter)
		    textCharter.setText("چارتر"+"");
		else{
			textCharter.setText("سیستمی"+"");
			textCharter.setBackgroundResource(R.drawable.background_strock_green);
			textCharter.setTextColor(Color.parseColor("#23b574"));
			  		}*/

		//((Button)findViewById(R.id.btntwo)).setBackgroundResource(R.drawable.purple_button_larg);
		//lblProductrow.setBackgroundResource(R.drawable.ir);
		String s=item2.get(0).AirlineCode;
		if(new String(s).equals("IR"))
			lblProductrow.setBackgroundResource(R.drawable.ir);
		if(new String(s).equals("A3"))
			lblProductrow.setBackgroundResource(R.drawable.a);
		if(new String(s).equals("AF"))
			lblProductrow.setBackgroundResource(R.drawable.af);
		if(new String(s).equals("AZ"))
			lblProductrow.setBackgroundResource(R.drawable.az);
		if(new String(s).equals("EK"))
			lblProductrow.setBackgroundResource(R.drawable.ek);
		if(new String(s).equals("EY"))
			lblProductrow.setBackgroundResource(R.drawable.ey);
		if(new String(s).equals("FZ"))
			lblProductrow.setBackgroundResource(R.drawable.fz);
		if(new String(s).equals("G9"))
			lblProductrow.setBackgroundResource(R.drawable.g);
		if(new String(s).equals("J2"))
			lblProductrow.setBackgroundResource(R.drawable.j);
		if(new String(s).equals("JI"))
			lblProductrow.setBackgroundResource(R.drawable.ji);
		if(new String(s).equals("KK"))
			lblProductrow.setBackgroundResource(R.drawable.kk);
		if(new String(s).equals("KL"))
			lblProductrow.setBackgroundResource(R.drawable.kl);
		if(new String(s).equals("LH"))
			lblProductrow.setBackgroundResource(R.drawable.lh);
		if(new String(s).equals("PC"))
			lblProductrow.setBackgroundResource(R.drawable.pc);
		if(new String(s).equals("QR"))
			lblProductrow.setBackgroundResource(R.drawable.qr);
		if(new String(s).equals("SU"))
			lblProductrow.setBackgroundResource(R.drawable.su);
		if(new String(s).equals("TG"))
			lblProductrow.setBackgroundResource(R.drawable.tg);
		if(new String(s).equals("TK"))
			lblProductrow.setBackgroundResource(R.drawable.tk);
		if(new String(s).equals("W5"))
			lblProductrow.setBackgroundResource(R.drawable.w);
		if(new String(s).equals("WY"))
			lblProductrow.setBackgroundResource(R.drawable.wy);


		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
