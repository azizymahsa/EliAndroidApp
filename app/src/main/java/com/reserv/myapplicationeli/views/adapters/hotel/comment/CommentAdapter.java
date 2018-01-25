package com.reserv.myapplicationeli.views.adapters.hotel.comment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelCheckBox;
import com.reserv.myapplicationeli.views.adapters.hotel.FilterAdapter;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class CommentAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<CommentModel> commentModels = new ArrayList<>();



    public CommentAdapter(Context context, ArrayList<CommentModel> commentModels ) {
        this.commentModels = commentModels;
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
            holder.tvComment = (TextView) convertView.findViewById(R.id.tvComment);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvLike = (TextView) convertView.findViewById(R.id.tvLike);
            holder.tvDislike = (TextView) convertView.findViewById(R.id.tvDislike);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvComment.setText(commentModels.get(position).getComment());
        holder.tvTitle.setText(commentModels.get(position).getTitle());
        holder.tvDate.setText(commentModels.get(position).getDate());
        holder.tvName.setText(commentModels.get(position).getName());
        holder.tvLike.setText(commentModels.get(position).getLike()+"");
        holder.tvDislike.setText(commentModels.get(position).getDisLike()+"");



        return convertView;
    }


    public class ViewHolder {
        TextView tvComment,tvTitle,tvDate,tvName,tvLike,tvDislike;
    }
}
