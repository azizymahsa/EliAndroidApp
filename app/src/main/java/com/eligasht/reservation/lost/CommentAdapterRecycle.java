package com.eligasht.reservation.lost;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.CommentModel;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

/**
 * Created by Reza.nejati on 2/4/2018.
 */

public class CommentAdapterRecycle extends RecyclerView.Adapter<CommentAdapterRecycle.ViewHolder> {

    private final List<CommentModel> data;
    private Context context;

    public CommentAdapterRecycle(final List<CommentModel> data) {
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.comment_item, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CommentModel item = data.get(position);

        holder.expand_text_view.setText(item.getComment());
        holder.tvTitle.setText(item.getTitle());
        holder.tvDate.setText(item.getDate());
        holder.tvName.setText(item.getName());
        holder.tvLike.setText(item.getLike() + "");
        holder.tvDislike.setText(item.getDisLike() + "");
        holder.expand_text_view.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                if (isExpanded) {
                    holder.expandable_text.setEllipsize(TextUtils.TruncateAt.END);
                    Log.e("testExpand", "onExpandStateChanged: 1");


                } else {
                    holder.expandable_text.setEllipsize(TextUtils.TruncateAt.END);
                    Log.e("testExpand", "onExpandStateChanged: 2");
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvComment, tvTitle, tvDate, tvName, tvLike, tvDislike, expandable_text;
        ExpandableTextView expand_text_view;

        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvDate = v.findViewById(R.id.tvDate);
            tvName = v.findViewById(R.id.tvName);
            tvLike = v.findViewById(R.id.tvLike);
            tvDislike = v.findViewById(R.id.tvDislike);
            expandable_text = v.findViewById(R.id.expandable_text);
            expand_text_view = v.findViewById(R.id.expand_text_view);

        }
    }


}
