package com.reserv.myapplicationeli.lost;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayoutItem;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.aakira.expandablelayout.ExpandableWeightLayout;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelCheckBox;
import com.reserv.myapplicationeli.views.adapters.hotel.FilterAdapter;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 1/22/2018.
 */

public class PassengerPreFactorAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<PassengerPreFactorModel> passengerPreFactorModels = new ArrayList<>();
    TextView tvName, tvGender, tvBrithday, tvPass;



    public PassengerPreFactorAdapter(Context context, ArrayList<PassengerPreFactorModel> passengerPreFactorModels) {
        this.passengerPreFactorModels = passengerPreFactorModels;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return passengerPreFactorModels.size();
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
            convertView = inflater.inflate(R.layout.passenger_prefactor_item, null);
            holder = new ViewHolder();
         //   holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            //holder.expandableLayout = (ExpandableLayoutItem) convertView.findViewById(R.id.expandableLayout);
       /*   tvName = holder.expandableLayout.getHeaderRelativeLayout().findViewById(R.id.tvName);
          tvGender = holder.expandableLayout.getContentRelativeLayout().findViewById(R.id.tvGender);
            tvBrithday = holder.expandableLayout.getContentRelativeLayout().findViewById(R.id.tvBrithday);
            tvPass = holder.expandableLayout.getContentRelativeLayout().findViewById(R.id.tvPass);
*/
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

     /* tvName.setText(passengerPreFactorModels.get(position).getRqPassenger_FirstNameFa() + " " + passengerPreFactorModels.get(position).getRqPassenger_LastNameFa());
       tvGender.setText(passengerPreFactorModels.get(position).getGender());
       tvBrithday.setText(passengerPreFactorModels.get(position).getRqPassenger_Birthdate());
        tvPass.setText(passengerPreFactorModels.get(position).getRqPassenger_PassNo());
*/

        return convertView;
    }


    public class ViewHolder {

       // ExpandableLayoutItem expandableLayout;

    }
}
