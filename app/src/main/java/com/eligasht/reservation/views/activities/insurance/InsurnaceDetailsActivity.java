package com.eligasht.reservation.views.activities.insurance;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.eligasht.R;
import com.eligasht.reservation.models.model.insurance.DetailsModel;
import com.eligasht.reservation.views.adapters.insurance.InsurnaceDetailAdapter;
import com.eligasht.reservation.views.ui.InitUi;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Reza.nejati on 2/12/2018.
 */
public class InsurnaceDetailsActivity extends AppCompatActivity {
    ListView lvDetails;
    ArrayList<DetailsModel> arrayList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurnace_details);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "جزئیات بیمه");
        lvDetails=findViewById(R.id.lvDetails);

        try {
            JSONArray jsonObj = new JSONArray(getIntent().getExtras().getString("details"));
            Log.e("testttt", jsonObj.toString() );

            if (getIntent().getExtras().getBoolean("plan")){
                for (int i = 0; i < jsonObj.length(); i++) {
                    arrayList.add(new DetailsModel(jsonObj.getJSONObject(i).getString("TitleFa").toString(),jsonObj.getJSONObject(i).getString("CoverLimit").toString()));


                }
                lvDetails.setAdapter(new InsurnaceDetailAdapter(this,arrayList));

            }else{
                for (int i = 0; i < jsonObj.length(); i++) {
                    arrayList.add(new DetailsModel(jsonObj.getJSONObject(i).getString("TravelInsuranceCoverageTile").toString(),jsonObj.getJSONObject(i).getString("TravelInsuranceCoverageVal").toString()));


                }
                lvDetails.setAdapter(new InsurnaceDetailAdapter(this,arrayList));
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
}
