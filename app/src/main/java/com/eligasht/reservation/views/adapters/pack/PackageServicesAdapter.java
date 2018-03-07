package com.eligasht.reservation.views.adapters.pack;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.views.ui.SingletonContext;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/10/2018.
 */

public class PackageServicesAdapter extends BaseAdapter {
    Context context;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<String> arrayList = new ArrayList<>();



    public PackageServicesAdapter(Context context, ArrayList<String> arrayList ) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
            convertView = inflater.inflate(R.layout.services_item, null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.textView);
            holder.cv1 = convertView.findViewById(R.id.cv1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Typeface face = Typeface.createFromAsset(context.getAssets(), SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        holder.textView.setText(arrayList.get(position));
        holder.textView.setTypeface(face);


        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn( holder.cv1);

        return convertView;
    }


    public class ViewHolder {
        TextView textView;
        CardView cv1;
    }
}

