package com.eligasht.reservation.views.activities.hotel.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.views.ui.HackyViewPager;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.github.chrisbanes.photoview.PhotoView;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.views.ui.InitUi;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import it.sephiroth.android.library.widget.HListView;

public class ImageViewActivity extends BaseActivity {
    ArrayList<String> images = new ArrayList<>();
    HListView thumbnails_scroll_view;
    private HackyViewPager viewPager;
    IntroAdapter introAdapter;
    ImageListAdapter imageListAdapter;
    boolean listChange = true;
    int pos;
    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);


        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);

        try{
            pos = getIntent().getExtras().getInt("pic");
            InitUi.Toolbar(this, false, R.color.toolbar_color, getIntent().getExtras().getString("hotelName"));
        }catch (Exception e){}


        thumbnails_scroll_view = findViewById(R.id.thumbnails_scroll_view);
        viewPager = findViewById(R.id.intro_view_pager);




      /*  viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);*/


        try {
            JSONArray jsonObj = new JSONArray(getIntent().getExtras().getString("list"));
          //  Log.e("testest", jsonObj.toString() );

         //   Log.e("piiccc", jsonObj.toString());
            for (int i = 0; i < jsonObj.length(); i++) {
                images.add(new String(jsonObj.getJSONObject(i).getString("image")));


            }
            introAdapter = new IntroAdapter();
            imageListAdapter = new ImageListAdapter();
            viewPager.setAdapter(new IntroAdapter());
            thumbnails_scroll_view.setAdapter(imageListAdapter);
            viewPager.setCurrentItem(getIntent().getExtras().getInt("pic"));
            if (pos == 0 || pos == 1) {

                thumbnails_scroll_view.setSelection(pos);


            } else if (pos == images.size()) {
                thumbnails_scroll_view.setSelection(pos);


            } else if (pos == images.size() - 1 || pos == images.size() - 2) {
                thumbnails_scroll_view.setSelection(pos);

            } else {

                thumbnails_scroll_view.setSelection(pos - 1);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                listChange = true;

                return false;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (listChange) {

                    thumbnails_scroll_view.smoothScrollToPosition(position );

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private class IntroAdapter extends PagerAdapter {
        public IntroAdapter() {

        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(container.getContext(), R.layout.list_image_item_gallary, null);
            PhotoView image = view.findViewById(R.id.photo_view);

            container.addView(view, 0);




            GlideApp.with(ImageViewActivity.this)
                    .load(images.get(position))
                    .centerCrop()
                    .error(R.drawable.not_found)
                    .into(image);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public class ImageListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ViewHolder holder;


        public ImageListAdapter() {
            inflater = LayoutInflater.from(ImageViewActivity.this);

        }

        @Override
        public int getCount() {
            return images.size();
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
                convertView = inflater.inflate(R.layout.row_item_image, null);
                holder = new ViewHolder();
                holder.ivImage = convertView.findViewById(R.id.ivImage);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GlideApp.with(ImageViewActivity.this)
                    .load(images.get(position))
                    .centerCrop()
                    .error(R.drawable.not_found)
                    .into(holder.ivImage);

            holder.ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listChange = false;
                    viewPager.setCurrentItem(position);
                }
            });

            return convertView;
        }


        public class ViewHolder {
            ImageView ivImage;

        }
    }
}