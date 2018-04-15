package com.eligasht.reservation.views.adapters;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eligasht.R;
import com.eligasht.reservation.models.model.SectionModel;
import com.eligasht.reservation.tools.JustifiedTextView;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    public static String GET_FRAGMENT = null;
    //public CursorManager cursor;
    public int customerId;
    public String customerName;
    public int catt_ID = 0;
    public String value_Maghsad_City;
    public String value_Maghsad_Airport;
    public String value_Maghsad_Airport_Code;
    Activity activity;
    private Context context;
    private LayoutInflater myInflater;
    private LayoutInflater inflater;
    private List<SectionModel> data;
    private WebView view;


    public AboutAdapter(final List<SectionModel> data) {
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.row_about, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SectionModel item = data.get(position);

        holder.setIsRecyclable(false);
        Typeface face;
        if(Prefs.getString("lang","fa").equals("fa")){
             face = Typeface.createFromAsset(context.getAssets(),"fonts/iran_sans_normal.ttf");
            holder.txtSectionNameEn.setVisibility(View.GONE);
            holder.txtDescriptionEn.setVisibility(View.GONE);
        }else{
             face = Typeface.createFromAsset(context.getAssets(),"fonts/times.ttf");
            holder.txtSectionName.setVisibility(View.GONE);
            holder.txtDescription.setVisibility(View.GONE);

        }
        holder.txtDescription.setTypeFace(face);


        holder.txtDescription.setLineSpacing(30);

        holder.txtDescription.setTextSize(1,16);


        holder.txtSectionName.setTextColor(Color.parseColor("#000000"));
        holder.txtDescription.setTextColor(ContextCompat.getColor(context,R.color.gray_dark_2));


        if(item.getSectionName().contains(context.getString(R.string.lisences))){
            String[] value_split = item.getSectionName().split("\\|");
            holder.txtSectionName.setText(value_split[1]+"");
            holder.txtSectionNameEn.setText(value_split[1]+"");
        }else{
            holder.txtSectionName.setText(item.getSectionName()+"");
            holder.txtSectionNameEn.setText(item.getSectionName()+"");
        }

        holder.txtDescription.setText(item.getDescription()+ "");
        String youtContentStr = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;color:#222222; \">"
                        + item.getDescription()
                        + "</body>]]>"));
       holder.txtDescriptionEn.loadData(youtContentStr, "text/html", "utf-8");
        if(item.getImageAddress()!=null || !TextUtils.isEmpty(item.getImageAddress())){
            holder.iv_imageAddress.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getImageAddress()).into(holder.iv_imageAddress);
        }else{

            holder.iv_imageAddress.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ExpandableLinearLayout expandableLayout;
        JustifiedTextView txtDescription;
     TextView txtSectionName;
        WebView txtDescriptionEn;
        TextView txtSectionNameEn;
        ImageView iv_imageAddress;

        public ViewHolder(View v) {
            super(v);
            txtDescription = v.findViewById(R.id.txtDescription);
            txtSectionName = v.findViewById(R.id.txtSectionName);
            txtDescriptionEn = v.findViewById(R.id.txtDescriptionEn);
            txtSectionNameEn = v.findViewById(R.id.txtSectionNameEn);
            iv_imageAddress = v.findViewById(R.id.iv_imageAddress);

        }
    }
}




	/*private Context context;
    private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<SectionModel> data;
	public String value_Maghsad_City;
	public String value_Maghsad_Airport;
	public String value_Maghsad_Airport_Code;
	public static String GET_FRAGMENT = null;
	Activity activity;

	 // create constructor to innitilize context and data sent from MainActivity
    public AboutAdapter(Context context, List<SectionModel> data, Activity activity){
    	this.activity=activity;
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        myInflater = LayoutInflater.from(context);


    }
	public AboutAdapter(Activity activity){
		this.context=activity;
		myInflater = LayoutInflater.from(context);
	}

	public void setData(List<SectionModel> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public void setData(String searchText) {
		this.data = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}


	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
	 //data.moveToPosition(position);
	 //SectionModel current=data.get(position);
	long s =position+1;
		//return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
		return s;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_about, null);
			holder = new ViewHolder();

			holder.txtDescription = (JustifiedTextView) convertView.findViewById(R.id.txtDescription);
			holder.txtSectionName = (JustifiedTextView) convertView.findViewById(R.id.txtSectionName);
			holder.iv_imageAddress = (ImageView) convertView.findViewById(R.id.iv_imageAddress);


			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final SectionModel current=data.get(position);
		Typeface face = Typeface.createFromAsset(context.getAssets(),SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
		holder.txtDescription.setTypeFace(face);
		holder.txtSectionName.setTypeFace(face);

		holder.txtSectionName.setTextSize(2,18);
		holder.txtDescription.setTextSize(1,14);
		holder.txtSectionName.setTextColor(Color.parseColor("#000000"));
		holder.txtDescription.setTextColor(Color.parseColor("#4b494b"));
		if(current.getSectionName().contains("گواهینامه ها")){
		String[] value_split = current.getSectionName().split("\\|");
			holder.txtSectionName.setText(value_split[1]+"");
		}else{
			holder.txtSectionName.setText(current.getSectionName()+"");
		}
		holder.txtDescription.setText(current.getDescription()+ "");

		holder.txtDescription.setLineSpacing(10);
		holder.txtSectionName.setLineSpacing(10);
		//	holder.iv_imageAddress.setBackgroundResource();

	if(current.getImageAddress() != "null"){

		holder.iv_imageAddress.setVisibility(View.VISIBLE);
		Glide.with(context).load(current.getImageAddress()).into(holder.iv_imageAddress);
	}else{

		holder.iv_imageAddress.setVisibility(View.GONE);
	}	Typeface face = Typeface.createFromAsset(context.getAssets(),SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
		holder.txtDescription.setTypeFace(face);
		holder.txtSectionName.setTypeFace(face);

		holder.txtSectionName.setTextSize(2,18);
		holder.txtDescription.setTextSize(1,14);
		holder.txtSectionName.setTextColor(Color.parseColor("#000000"));
		holder.txtDescription.setTextColor(Color.parseColor("#4b494b"));
		if(current.getSectionName().contains("گواهینامه ها")){
		String[] value_split = current.getSectionName().split("\\|");
			holder.txtSectionName.setText(value_split[1]+"");
		}else{
			holder.txtSectionName.setText(current.getSectionName()+"");
		}
		holder.txtDescription.setText(current.getDescription()+ "");

		holder.txtDescription.setLineSpacing(10);
		holder.txtSectionName.setLineSpacing(10);
		//	holder.iv_imageAddress.setBackgroundResource();

	if(current.getImageAddress() != "null"){

		holder.iv_imageAddress.setVisibility(View.VISIBLE);
		Glide.with(context).load(current.getImageAddress()).into(holder.iv_imageAddress);
	}else{

		holder.iv_imageAddress.setVisibility(View.GONE);
	}

		return convertView;
		}

	static class ViewHolder {
		JustifiedTextView txtDescription;
		JustifiedTextView txtSectionName;


		 ImageView iv_imageAddress;
	}


*///}