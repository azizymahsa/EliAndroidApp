package com.eligasht.reservation.views.adapters.train;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.train.PassengerServiceModel;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.activities.train.PassengerTrainActivity;
import com.eligasht.reservation.views.ui.CountrycodeActivity;

import java.util.List;

public class TrainServiceListArrayAdapter extends ArrayAdapter<PassengerServiceModel> {

    private final List<PassengerServiceModel> list;
    private final Activity context;
    private final  AlertDialog dialog;
    private boolean flagRaft=true;

    static class ViewHolder {
        protected TextView name;
    //    protected ImageView flag;
    }

    public TrainServiceListArrayAdapter(Activity context, List<PassengerServiceModel> list,boolean flagRaft, AlertDialog dialog) {
        super(context, R.layout.activity_countrycode_row, list);
        this.context = context;
        this.list = list;
        this.flagRaft = flagRaft;
        this.dialog = dialog;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.activity_service_train_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.name);
         //   viewHolder.flag = view.findViewById(R.id.flag);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getName());
        holder.name.setBackgroundColor(Color.WHITE);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.name.setBackgroundColor(ContextCompat.getColor(context, R.color.toolbar_color_press));
                if (flagRaft) {
                    Prefs.putString("Pass_service_Train_True", list.get(position).getCode() + "|" + list.get(position).getName());
                    Prefs.putString("Value_Train_True",  list.get(position).getName());
                }else{
                    Prefs.putString("Pass_service_Train_False", list.get(position).getCode() + "|" + list.get(position).getName());
                    Prefs.putString("Value_Train_False", list.get(position).getName());
                }
                PassengerTrainActivity.updateServicePass();
                dialog.cancel();
            }
        });
      //  holder.flag.setImageDrawable(list.get(position).getFlag());
        return view;
    }
}