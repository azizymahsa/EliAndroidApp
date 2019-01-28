package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.CountrycodeActivity;
import com.eligasht.reservation.views.ui.NationalitycodeActivity;

import java.util.List;

public class NationalityListArrayAdapter extends ArrayAdapter<NationalitycodeActivity.Country> {

    private final List<NationalitycodeActivity.Country> list;
    private final Activity context;

    static class ViewHolder {
        protected TextView name;
      //  protected ImageView flag;
    }

    public NationalityListArrayAdapter(Activity context, List<NationalitycodeActivity.Country> list) {
        super(context, R.layout.activity_countrycode_row, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.activity_countrycode_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* CountrycodeActivity.Country c = countryList.get(position);
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(RESULT_CONTRYCODE, c.getCode());
                    returnIntent.putExtra(RESULT_CONTRYNAME, c.getName());
                    setResult(RESULT_OK, returnIntent);
                    imgs.recycle(); //recycle images
                    finish();*/
                }
            });
          //  viewHolder.flag = view.findViewById(R.id.flag);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getName());
      //  holder.flag.setImageDrawable(list.get(position).getFlag());
        return view;
    }
}