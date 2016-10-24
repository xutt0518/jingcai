package com.jingcai.jingcaic.fragment.shoppingcat;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.fragment.BaseFragment;

public class ShoppingCatFragment extends BaseFragment{
    private ListView listview;
    private List<String> cardata;
    private CarAdapter carAdapter;
	@Override
	public View initView(LayoutInflater inflater) {
		
		View view=inflater.inflate(R.layout.activity_shoppingcar, null);
		init(view);
		return view;
	}
    public void init(View view){
    	listview=(ListView)view.findViewById(R.id.list_gwc);
    	carAdapter=new CarAdapter(cardata,getActivity());
    	cardata=new ArrayList<String>();
    	for(int i=0;i<9;i++){
    		String data="Î÷ºìÊÁ";
    		cardata.add(data);
    	}
    	carAdapter=new CarAdapter(cardata,getActivity());
    	listview.setAdapter(carAdapter);
    }
	@Override
	protected void initFindViewById(View view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
   private class CarAdapter extends BaseAdapter{
    private List<String> cardata=new ArrayList<String>();
    private Context context;
	public CarAdapter(List<String> cardata, Context context) {
		super();
		this.cardata = cardata;
		this.context = context;
	}

	@Override
	public int getCount() {
		return cardata.size();
	}

	@Override
	public Object getItem(int position) {
		return cardata.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=LayoutInflater.from(context).inflate(R.layout.car_item, null);
		TextView text_goodstitle=(TextView)view.findViewById(R.id.text_goodstitle);
		
		text_goodstitle.setText(cardata.get(position));
		return view;
	}
	   
   }
}
