package com.eligasht.reservation.views.adapters.hotel.comment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.eligasht.reservation.R;

import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class CommentAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<CommentModel> commentModels = new ArrayList<>();
    ScrollView scrollView;
    NonScrollListView nonScrollListView;


    public CommentAdapter(Context context, ArrayList<CommentModel> commentModels, ScrollView scrollView, NonScrollListView nonScrollListView) {
        this.commentModels = commentModels;
        this.scrollView = scrollView;
        this.nonScrollListView = nonScrollListView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return commentModels.size();
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
            convertView = inflater.inflate(R.layout.comment_item, null);
            holder = new ViewHolder();
            //   holder.tvComment = (TextView) convertView.findViewById(R.id.tvComment);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvLike = (TextView) convertView.findViewById(R.id.tvLike);
            holder.tvDislike = (TextView) convertView.findViewById(R.id.tvDislike);
            holder.expandable_text = (TextView) convertView.findViewById(R.id.expandable_text);
            holder.expand_text_view = (ExpandableTextView) convertView.findViewById(R.id.expand_text_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.expand_text_view.setText(commentModels.get(position).getComment());
        holder.tvTitle.setText(commentModels.get(position).getTitle());
        holder.tvDate.setText(commentModels.get(position).getDate());
        holder.tvName.setText(commentModels.get(position).getName());
        holder.tvLike.setText(commentModels.get(position).getLike() + "");
        holder.tvDislike.setText(commentModels.get(position).getDisLike() + "");
        holder.expand_text_view.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                if (isExpanded) {
                    holder.expandable_text.setEllipsize(null);
                    nonScrollListView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
                            ViewGroup.LayoutParams layoutParams2 = nonScrollListView.getLayoutParams();

                            Log.e("height1", layoutParams.height+"");
                            Log.e("height2", layoutParams2.height+"");
                           /* scrollView.setLayoutParams(new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.FILL_PARENT, layoutParams2.height));*/
                        }
                    }, 1000);

                } else {
                    holder.expandable_text.setEllipsize(TextUtils.TruncateAt.END);
                    nonScrollListView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
                            ViewGroup.LayoutParams layoutParams2 = nonScrollListView.getLayoutParams();
                            Log.e("height3", layoutParams.height+"");
                            Log.e("height4", layoutParams2.height+"");
                           /* scrollView.setLayoutParams(new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.FILL_PARENT, layoutParams2.height));
*/
                        }
                    }, 1000);

                }
            }
        });


        return convertView;
    }


    public class ViewHolder {
        TextView tvComment, tvTitle, tvDate, tvName, tvLike, tvDislike, expandable_text;
        ExpandableTextView expand_text_view;

    }
}
