package com.eligasht.reservation.views.adapters.insurance;

import android.content.Context;
import android.graphics.Color;
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
import com.eligasht.reservation.models.model.insurance.DetailsModel;
import com.eligasht.reservation.tools.JustifiedTextView;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/12/2018.
 */

public class InsurnaceDetailAdapter  extends BaseAdapter {
    Context context;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<DetailsModel> arrayList = new ArrayList<>();



    public InsurnaceDetailAdapter(Context context, ArrayList<DetailsModel> arrayList ) {
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
            convertView = inflater.inflate(R.layout.details_item, null);
            holder =new ViewHolder();
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.cv1 = convertView.findViewById(R.id.cv1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Typeface face = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.iran_sans_bold_ttf));
        Typeface face2 = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.iran_sans_normal_ttf));
        holder.tvPrice.setText(arrayList.get(position).getPrice());
        holder.tvTitle.setText(arrayList.get(position).getTitle());
        holder.tvPrice.setTypeface(face);
        holder.tvTitle.setTypeFace(face2);

        holder.tvTitle.setTextSize(1,14);
        holder.tvTitle.setLineSpacing(30);
    holder.tvTitle.setTextColor(Color.parseColor("#2e3192"));
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn( holder.cv1);

        return convertView;
    }


    public class ViewHolder {
        TextView tvPrice;
        JustifiedTextView tvTitle;
        CardView cv1;
    }
}

