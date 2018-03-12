package com.eligasht.reservation.views.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eligasht.R;

public class SpinnerCustomAdapter extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;
    boolean isLanguage;

    public SpinnerCustomAdapter(Context applicationContext, int[] flags, String[] countryNames, boolean isLanguage) {
        this.context = applicationContext;
        this.flags = flags;
        this.isLanguage = isLanguage;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_item, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        if (isLanguage){
            icon.setImageResource(flags[i]);

        }else{
            icon.setVisibility(View.GONE);
        }
        names.setText(countryNames[i]);
        names.setTypeface(Typeface.createFromAsset(context.getAssets(),context.getString(R.string.iran_sans_normal_ttf)));
        return view;
    }
}
