package com.eligasht.reservation.views.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.ServiceApplication;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.service.helper.Const;

public class SpinnerCustomAdapter extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    String[] officeUrl;
    String[] cultureList;
    LayoutInflater inflter;
    boolean isLanguage;

    public SpinnerCustomAdapter(Context applicationContext, int[] flags, String[] countryNames, String[] officeUrl, String[] cultureList, boolean isLanguage) {
        this.context = applicationContext;
        this.flags = flags;
        this.isLanguage = isLanguage;
        this.countryNames = countryNames;
        this.officeUrl = officeUrl;
        this.cultureList = cultureList;
        inflter = (LayoutInflater.from(applicationContext));
    }


    @Override
    public int getCount() {
        return countryNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView names;
        if (isLanguage){
            view = inflter.inflate(R.layout.custom_spinner_item, null);
             names = view.findViewById(R.id.textView);

            ImageView icon = view.findViewById(R.id.imageView);
            icon.setImageResource(flags[i]);


            names.setText(countryNames[i]);

            names.setTypeface(Typeface.createFromAsset(context.getAssets(),context.getString(R.string.iran_sans_normal_ttf)));
        }else{
            view = inflter.inflate(R.layout.custom_spinner_item_2, null);
            names = view.findViewById(R.id.textView);

            names.setText(countryNames[i]);//lang
            /*names.setTag(countryNames[i]);
            names.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Prefs.putBoolean("isChangeBranch", true);
                    Prefs.putString("BranchDef", countryNames[i]);
                    Prefs.putBoolean("isChangeUrl", true);
                    Prefs.putString("BASEURL",officeUrl[i]);
                    Const.BASEURL=officeUrl[i];
                    Log.d("onClick:1 ",Const.BASEURL);
                    //update url base
                    ServiceApplication serviceApplication=new ServiceApplication() {
                        @Override
                        public void onCreate() {
                            super.onCreate();

                        }
                    };
                    serviceApplication.onCreate();

                }
            });*/


            // Prefs.putString();
            names.setTypeface(Typeface.createFromAsset(context.getAssets(),context.getString(R.string.iran_sans_normal_ttf)));
        }

        return view;
    }
}
