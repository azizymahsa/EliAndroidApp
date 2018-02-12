package com.reserv.myapplicationeli.views.activities.pack;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.adapters.AfterPaymentAdapter;
import com.reserv.myapplicationeli.views.adapters.pack.PackageServicesAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PackageServicesActivity extends BaseActivity {
    ListView lvServices;
    ArrayList<String> arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_services);
        lvServices=findViewById(R.id.lvServices);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "مشخصات خدمات");

        try {
            JSONArray jsonObj = new JSONArray(getIntent().getExtras().getString("services"));
            for (int i = 0; i < jsonObj.length(); i++) {
                Log.e("test", jsonObj.getJSONObject(i).getString("PRowServiceNameF"));
                arrayList.add(new String(jsonObj.getJSONObject(i).getString("PRowServiceNameF").toString()));


            }
            lvServices.setAdapter(new PackageServicesAdapter(this,arrayList));

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
}