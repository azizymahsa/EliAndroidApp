package com.reserv.myapplicationeli.views.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.components.SimpleRecycleView;


/**
 * Created by elham.bonyani on 1/5/2018.
 */

public class SimpleRecycleDialog implements View.OnClickListener{

    private Context context;
    private AlertDialog alertDialog;
    private SimpleRecycleView rclDialog;
    private ListenerSimpleRecyclerDialog mListener;

    private ProgressBar progressBar;
    private TextView textView;
    private TextView txtTitle;

    public interface ListenerSimpleRecyclerDialog{
        void OnClickConfirmItem(String input, AlertDialog alertDialog);
    }

    public SimpleRecycleDialog(Context context) {
        if(context == null){
            return;
        }
        alertDialog = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_simple_rcl,null);
        rclDialog = view.findViewById(R.id.rcl_dialog);
        findViews(view);
        alertDialog.setView(view);
    }

    public void show(){
        if(alertDialog !=null && !alertDialog.isShowing()){
            alertDialog.show();
        }
    }

    public void dismiss(){
        if(alertDialog != null && alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }

    private void findViews(View view) {
        rclDialog = view.findViewById(R.id.rcl_dialog);
    }

    public SimpleRecycleDialog setListener(ListenerSimpleRecyclerDialog listener){
       mListener=listener;
        return this;
    }
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        rclDialog.setLayoutManager(layoutManager);
    }




    @Override
    public void onClick(View view) {

    }

}
