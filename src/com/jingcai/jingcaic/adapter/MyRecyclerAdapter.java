package com.jingcai.jingcaic.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jingcai.jingcaic.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
	private static final int TYPE_HEADER=0;
	private static final int TYPE_NORMAL=1;
	private View mHeaderView;
	private List<String> list=new ArrayList<String>();
	private Context context;
	public MyRecyclerAdapter(List<String> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		System.out.println("ø™ º÷¥––RecyclerView  ≈‰∆˜");
	}
    public boolean isHeader(int position){
    	return position==0;
    }
	public View getmHeaderView() {
		return mHeaderView;
	}

	public void setmHeaderView(View mHeaderView) {
		this.mHeaderView = mHeaderView;
	    notifyItemInserted(0);
	}
	@Override
	public int getItemCount() {
		return  mHeaderView == null ? list.size() : list.size() + 1;
	}

	@Override
	public void onBindViewHolder(MyViewHolder arg0, int arg1) {
		//arg0.textView.setText(list.get(arg1));
		if(getItemViewType(arg1) == TYPE_HEADER) return;

        final int pos = getRealPosition(arg0);
        final String data = list.get(pos);
        arg0.titlehome.setText(data);           
        }
	 public int getRealPosition(MyViewHolder holder) {
	        int position = holder.getPosition();
	        return mHeaderView == null ? position : position - 1;
	    }	  
	@Override
	public int getItemViewType(int position) {
		if(mHeaderView==null) return TYPE_NORMAL; 
		if(position==0) return TYPE_HEADER;
		return TYPE_NORMAL;
	}
    
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		if(mHeaderView != null && arg1 == TYPE_HEADER) return new MyViewHolder(mHeaderView);
		View view=LayoutInflater.from(context).inflate(R.layout.recycler_item, arg0, false);
		return new MyViewHolder(view);
	}
	public static class MyViewHolder extends RecyclerView.ViewHolder{
		private TextView jiagehome;
		private TextView titlehome;
		private ImageView imagehome;
		public MyViewHolder(View arg0) {
			super(arg0);
		//	if(arg0 == mHeaderView) return;
//			jiagehome=(TextView)arg0.findViewById(R.id.jiage_home);
     		titlehome=(TextView)arg0.findViewById(R.id.jiage_home);
//			imagehome=(ImageView)arg0.findViewById(R.id.imge_home);
		}
	}
	
}
