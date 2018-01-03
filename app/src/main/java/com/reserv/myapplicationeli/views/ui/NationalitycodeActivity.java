package com.reserv.myapplicationeli.views.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.adapters.NationalityListArrayAdapter;

public class NationalitycodeActivity extends ListActivity {

    public static String RESULT_NATIONALITYCODE = "nationalitycode";
    public static String RESULT_NATIONALITYNAME = "nationalityname";
    public String[] nationalitynames, nationalitycodes;
    private TypedArray imgs;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateCountryList();
        ArrayAdapter<Country> adapter = new NationalityListArrayAdapter(this, countryList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country c = countryList.get(position);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT_NATIONALITYCODE, c.getCode());
                returnIntent.putExtra(RESULT_NATIONALITYNAME, c.getName());
                setResult(RESULT_OK, returnIntent);
                imgs.recycle(); //recycle images
                finish();
            }
        });
    }

    private void populateCountryList() {
        countryList = new ArrayList<Country>();
        nationalitynames = getResources().getStringArray(R.array.nationality_names);
        nationalitycodes = getResources().getStringArray(R.array.nationality_codes);
        imgs = getResources().obtainTypedArray(R.array.country_flags);
        for(int i = 0; i < nationalitycodes.length; i++){
            countryList.add(new Country(nationalitynames[i], nationalitycodes[i], imgs.getDrawable(i)));
        }
    }

    public class Country {
        private String name;
        private String code;
        private Drawable flag;
        public Country(String name, String code, Drawable flag){
            this.name = name;
            this.code = code;
            this.flag = flag;
        }
        public String getName() {
            return name;
        }
        public Drawable getFlag() {
            return flag;
        }
        public String getCode() {
            return code;
        }
    }
}