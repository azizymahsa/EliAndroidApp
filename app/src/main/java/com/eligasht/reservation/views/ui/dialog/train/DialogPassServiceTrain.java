package com.eligasht.reservation.views.ui.dialog.train;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.eligasht.R;
import com.eligasht.reservation.models.train.PassengerServiceModel;
import com.eligasht.reservation.views.activities.train.PassengerTrainActivity;
import com.eligasht.reservation.views.adapters.train.TrainServiceListArrayAdapter;
import com.eligasht.reservation.views.ui.SingletonContext;
import java.util.List;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/21/2018.
 */
public class DialogPassServiceTrain extends ListActivity implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView  tvTitle;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk;
    String title;
    Typeface type;
    boolean isFinish;



    public DialogPassServiceTrain(final Activity activity, boolean warning, boolean isFinish,List<PassengerServiceModel> passSeviceListRaft,boolean flagRaft) {
         type = Typeface.createFromAsset(activity.getAssets(), "fonts/iran_sans_normal.ttf");
        this.activity = activity;
        this.isFinish = isFinish;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.dialog_pass_service_train, null);


        initView();

        builder.setView(dialogView);
        dialog = builder.create();
        dialog.setCancelable(true);
        try {
            dialog.show();

            ListView lv =  dialogView.findViewById(R.id.listView1);
            TrainServiceListArrayAdapter adapter = new TrainServiceListArrayAdapter(activity,passSeviceListRaft,flagRaft,dialog);
            lv.setAdapter(adapter);
        } catch (Exception e) {
        }
    }

    private void initView() {

        tvTitle=dialogView.findViewById(R.id.tvTitle);
        btnOk=dialogView.findViewById(R.id.btnOk);
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnOk.setVisibility(View.INVISIBLE);

    }

    public void setText( String title) {

        this.title = title;
        tvTitle.setText(title);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                if (isFinish) {
                    activity.finish();
                }else{

                    PassengerTrainActivity.updateServicePass();
                dialog.cancel();
                }
                break;

        }
    }

}

