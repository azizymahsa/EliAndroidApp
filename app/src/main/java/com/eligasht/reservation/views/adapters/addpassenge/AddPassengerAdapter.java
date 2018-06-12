package com.eligasht.reservation.views.adapters.addpassenge;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.PassengerDBModel;
import com.eligasht.reservation.models.PassengerPreFactorModel;
import com.eligasht.reservation.views.activities.addPassenger.SavePassengerActivity;

import java.util.List;
/**
 * Created by Reza Nejati on 02,June,2018
 */
public class AddPassengerAdapter extends RecyclerView.Adapter<AddPassengerAdapter.ViewHolder> {

    private final List<PassengerDBModel> data;
    private Context context;
    boolean isPassReq;
    Activity activity;

    public AddPassengerAdapter(final List<PassengerDBModel> data,boolean isPassReq,Activity activity) {
        this.data = data;
        this.isPassReq = isPassReq;
        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_list_row_add_passenger, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PassengerDBModel item = data.get(position);

        holder.setIsRecyclable(false);
        holder.tvTitle.setText(item.getRqPassenger_FirstNameEn()+" "+item.getRqPassenger_LastNameEn());
        holder.tvDate.setText(item.getRqPassenger_Birthdate());
        holder.cvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPassReq){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Id",data.get(position).getId());
                    activity.setResult(Activity.RESULT_OK,returnIntent);
                    activity.finish();

                }else{
                    Intent intent = new Intent(context, SavePassengerActivity.class);
                    intent.putExtra("Id",data.get(position).getId());
                    context.startActivity(intent);
                }

            }
        });
        holder.tvTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    PassengerDBModel passengerDBModel = PassengerDBModel.findById(PassengerDBModel.class,data.get(position).getId());
                    passengerDBModel.delete();
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getId().equals(data.get(position).getId())){
                            data.remove(i);
                            notifyDataSetChanged();
                        }

                    }

                }catch (Exception e){}


            }
        });




    }



    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDate,tvTrash;
        CardView cvContent;


        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvDate = v.findViewById(R.id.tvDate);
            cvContent = v.findViewById(R.id.cvContent);
            tvTrash = v.findViewById(R.id.tvTrash);
        }
    }
}
