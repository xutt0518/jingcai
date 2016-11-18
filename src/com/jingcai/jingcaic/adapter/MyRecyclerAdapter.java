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
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
	private static final int TYPE_HEADER=0;
	private static final int TYPE_NORMAL=1;
	private View mHeaderView;
	private List<String> list=new ArrayList<String>();
	private Context context;
	//头布局中四按钮
	private LinearLayout Search_food;
	private LinearLayout Search_outing;
	private LinearLayout Search_hotel;
	private LinearLayout Search_hotell;
	private static OnItemClickListener mOnItemClickListener;
	//设置点击事件
		public interface OnItemClickListener{
			void onItemClick(View v,int position);//设置点击事件
		}
		public static void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
			MyRecyclerAdapter.mOnItemClickListener = mOnItemClickListener;
		}
	public MyRecyclerAdapter(List<String> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		System.out.println("开始执行RecyclerView适配器");
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
	/**
	 * 把现在的数据绑定到原有的viewHolder之上
	 */
	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		//holder.textView.setText(list.get(arg1));
	
		if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        final String data = list.get(pos);
        holder.titlehome.setText(data);  
        Search_food=(LinearLayout)mHeaderView.findViewById(R.id.Search_food);
        Search_outing=(LinearLayout)mHeaderView.findViewById(R.id.Search_outing);
        Search_hotel=(LinearLayout)mHeaderView.findViewById(R.id.Search_hotel);
        Search_hotell=(LinearLayout)mHeaderView.findViewById(R.id.Search_hotell);
        if(mOnItemClickListener!=null){
        	Search_food.setOnClickListener(new View.OnClickListener() {		
    			@Override
    			public void onClick(View v) {
    		    mOnItemClickListener.onItemClick(Search_food, position);    		    
    			}
    		});
        	Search_outing.setOnClickListener(new View.OnClickListener() {		
    			@Override
    			public void onClick(View v) {
    		    mOnItemClickListener.onItemClick(Search_outing, position);
    			}
    		});
        	Search_hotel.setOnClickListener(new View.OnClickListener() {		
    			@Override
    			public void onClick(View v) {
    		    mOnItemClickListener.onItemClick(Search_hotel, position);
    			}
    		});
        	Search_hotell.setOnClickListener(new View.OnClickListener() {		
    			@Override
    			public void onClick(View v) {
    		    mOnItemClickListener.onItemClick(Search_hotell, position);
    			}
    		});
        	holder.recycler.setOnClickListener(new View.OnClickListener() {		
    			@Override
    			public void onClick(View v) {
    		    mOnItemClickListener.onItemClick(holder.recycler, position);
    			}
    		});
        }
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
	/**
	 * 相当于在listview中，onCreatView为空时，需要去创建viewHolder
	 */
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(mHeaderView != null && viewType == TYPE_HEADER) return new MyViewHolder(mHeaderView);
		View view=LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
		return new MyViewHolder(view);
	}
	public static class MyViewHolder extends RecyclerView.ViewHolder{
		private TextView jiagehome;
		private TextView titlehome;
		private ImageView imagehome;
		private LinearLayout recycler;
		public MyViewHolder(View itemView) {
			super(itemView);
     		titlehome=(TextView)itemView.findViewById(R.id.jiage_home);
     		recycler=(LinearLayout)itemView.findViewById(R.id.ly_recycler);
		}
	}
	
}
