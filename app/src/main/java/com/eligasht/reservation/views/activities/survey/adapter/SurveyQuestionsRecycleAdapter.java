package com.eligasht.reservation.views.activities.survey.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.eligasht.R;
import com.eligasht.reservation.views.activities.survey.SurveyActivity;
import com.eligasht.service.model.survey.response.detail.Question;
import com.eligasht.service.model.survey.response.detail.SurveySection;

import java.util.List;

public class SurveyQuestionsRecycleAdapter extends RecyclerView.Adapter<SurveyQuestionsRecycleAdapter.ViewHolder> {

    private List<Question> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public SurveyQuestionsRecycleAdapter(Context context, List<Question> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }




    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_details_rv_survey, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = mData.get(position).getText();
        String description = mData.get(position).getDescription();
        holder.txtTitle.setText(title);
        holder.tvDesc.setText(description);
        holder.tvNumber.setText(position+1+"");
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle,tvDesc,tvNumber;
        RadioGroup radioGroup;
        ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getID()+"";
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}