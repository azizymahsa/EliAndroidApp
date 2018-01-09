package com.reserv.myapplicationeli.views.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.tools.AwardSelectedItem;
import com.reserv.myapplicationeli.tools.Utility;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class AwardsSelectedsAdapter extends BaseAdapter {
    private int proPayment;
	private LayoutInflater myInflater;
	public static ArrayList<AwardSelectedItem> items = new ArrayList<AwardSelectedItem>();

	public AwardsSelectedsAdapter(Context c) {
		myInflater = LayoutInflater.from(c);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).prizeID;
	}
	
	public static long getItemPrizeDetailId(int position) {
	    return items.get(position).PrizeDetailID;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.lv_rows, null);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.item_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		proPayment=items.get(position).amount;
		String proPayments="_ "+proPayment+"روز _";
		
		String goodNameValue=items.get(position).goodName;
		
		if(goodNameValue==null ||  goodNameValue.isEmpty()){
			
			String ProAmount =Integer.toString(items.get(position).ProAmount);
			double amount = Double.parseDouble(ProAmount);
			DecimalFormat formatter = new DecimalFormat("#,###");

			if(amount>0){
				//holder.text.setText(String.format("%s (%s) _ %s",
				//	items.get(position).ProPayment, formatter.format(amount), items.get(position).PrizeDetailID));
				if(proPayment>0){
					
					holder.text.setText(Html.fromHtml(
							proPayment+"( "+ formatter.format(amount)+" ریال )"+"<font color='#d0d8f6'>"+"_"+items.get(position).PrizeDetailID+"</font>"+items.get(position).ProPayment+"روز"));
				}else{
				holder.text.setText(Html.fromHtml(items.get(position).ProPayment+"( "+ formatter.format(amount)+" ریال )"+"<font color='#d0d8f6'>"+"_"+items.get(position).PrizeDetailID+"</font>"));}
			}else{
				if(proPayment>0){
					holder.text.setText(Html.fromHtml("<font color='#d0d8f6'>"+"_"+items.get(position).PrizeDetailID+"</font>"+"("+items.get(position).ProPayment+")"+proPayment+"روز"));
				}else{
				holder.text.setText(Html.fromHtml("<font color='#d0d8f6'>"+"_"+items.get(position).PrizeDetailID+"</font>"+"("+items.get(position).ProPayment+")"));}
				}
			
		}else{
	
			String ProAmount =Integer.toString(items.get(position).ProAmount);
			double amount = Double.parseDouble(ProAmount);
			DecimalFormat formatter = new DecimalFormat("#,###");

			if(amount>0){
				holder.text.setText(Html.fromHtml(items.get(position).goodName+"( "+items.get(position).ProQty+" تعداد )"+"_ "+ formatter.format(amount)+" ریال "+"<font color='#d0d8f6'>"+"_"+items.get(position).PrizeDetailID+"</font>"));
			}else{
			holder.text.setText(Html.fromHtml(items.get(position).goodName+"( "+items.get(position).ProQty+" تعداد )"+"<font color='#d0d8f6'>"+"_"+items.get(position).PrizeDetailID+"</font>"));}
		}
/*//////////////////////????????????????????????????????????????????????????????????????????????????????????????????
String s=AddRequestActivity.SELECTED_DETAIL;

String[] separatedPrizeId =s.split("@");
//int a=Integer.parseInt(separatedPrizeId[0]); 
for (String value : separatedPrizeId) {
System.out.println(value);
if(items.get(position).PrizeDetailID == Integer.parseInt(value))
 {
      // set your selected Item Color
	holder.text.setText(Html.fromHtml("<font color='#d0d8f6'>"+"_"+items.get(position).PrizeDetailID+"</font>"+"("+items.get(position).ProPayment+")"));
      // holder.text.setBackgroundResource(R.drawable.check__);
 }
}
//////////////////////????????????????????????????????????????????????????????????????????????????????????????????
*/		Utility.applyFonts(convertView);
		return convertView;
	}

	static class ViewHolder {
		TextView text;
	}

	public void addItemToList(AwardSelectedItem item, boolean replace) {
		if (!replace)
			items.add(item);
		else {
			boolean insert = false;
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).prizeID == item.prizeID) {
					insert = true;
					items.remove(i);
					items.add(i, item);
					break;
				}
			}
			if (!insert)
				items.add(item);
		}
	}
	public void removeAllItem(boolean remove) {
		if (remove){
			if(items.size()!=0){
				items.removeAll(items);}
		}
	}
}