package com.eligasht.reservation.views.adapters.hotel.rooms;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eligasht.R;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class ImageAdapter extends BaseAdapter {
    private ArrayList<ImageModel> imageModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;
    Context context;

    public ImageAdapter(ArrayList<ImageModel> imageModels, Context context) {
        this.imageModels = imageModels;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return imageModels.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_image_item, null);
            holder = new ViewHolder();
            holder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Glide
                .with(context)
                .load(imageModels.get(position).getImage())
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivImage);



        return convertView;
    }


    public class ViewHolder {
        ImageView ivImage;
    }
    }
