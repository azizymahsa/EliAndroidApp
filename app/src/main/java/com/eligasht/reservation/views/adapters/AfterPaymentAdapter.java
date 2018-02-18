package com.eligasht.reservation.views.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.R;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/31/2018.
 */

public class AfterPaymentAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<AfterPaymentModel> afterPaymentModels = new ArrayList<>();
    Context context;



    public AfterPaymentAdapter(Context context, ArrayList<AfterPaymentModel> afterPaymentModels ) {
        this.afterPaymentModels = afterPaymentModels;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return afterPaymentModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_payment_log, null);
            holder = new ViewHolder();
            holder.tvLog = (TextView) convertView.findViewById(R.id.tvLog);
            holder.tvStatusIcon = (TextView) convertView.findViewById(R.id.tvStatusIcon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvLog.setText(afterPaymentModels.get(position).getMsgTextFa());
        if (afterPaymentModels.get(position).getActionStep()==2){
            holder.tvLog.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvLog.setText(context.getString(R.string.cancel));

        }else{
            holder.tvLog.setTextColor(ContextCompat.getColor(context,R.color.green));

            holder.tvLog.setText(context.getString(R.string.ok));


        }





     /*   if (modelCheckBoxes.get(position).isCheck()){
            holder.cbCheck.setChecked(true);
            Log.e("trueadapter",modelCheckBoxes.get(position).isCheck()+"");
        }else{
            holder.cbCheck.setChecked(false);
            Log.e("falseadapter",modelCheckBoxes.get(position).isCheck()+"");

        }
*/

        return convertView;
    }


    public class ViewHolder {
        TextView tvLog,tvStatusIcon;

    }
}
