package com.reserv.myapplicationeli.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.lazyloading.ImageLoader;


public class LazyResoultHotelAdapter extends BaseAdapter {
    
    private Activity activity;
    private String[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    
    public LazyResoultHotelAdapter(Activity a, String[] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.row_select_hotel_result, null);

        TextView tv_name_hotel=(TextView)vi.findViewById(R.id.tv_name_hotel);;
        ImageView image=(ImageView)vi.findViewById(R.id.img_hotel);
        tv_name_hotel.setText("item "+position);
        imageLoader.DisplayImage(data[position], image);
        return vi;
    }
}