package com.reserv.myapplicationeli.views.adapters.hotel.rooms;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.reserv.myapplicationeli.R;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class ImageAdapter extends BaseAdapter {
    private ArrayList<ImageModel> imageModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;
    ImageLoader imageLoader;

    public ImageAdapter(ArrayList<ImageModel> imageModels, Context context) {
        this.imageModels = imageModels;
        inflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
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
        // String imageUri = "drawable://" + productModels.get(position).getProductImage();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // this will make circle, pass the width of image
                .displayer(new RoundedBitmapDisplayer(5))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)

                .build();

        imageLoader.displayImage(imageModels.get(position).getImage(), holder.ivImage, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                //  holder.pb.setVisibility(View.VISIBLE);


            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {


            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {



            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {



            }
        });


        return convertView;
    }


    public class ViewHolder {
        ImageView ivImage;
    }
    }
