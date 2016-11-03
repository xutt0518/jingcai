package com.jingcai.jingcaic.fragment.shoppingcat;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.me.LoginActivity;
import com.jingcai.jingcaic.fragment.BaseFragment;
import com.jingcai.jingcaic.util.UserUtil;

public class ShoppingCatFragment extends BaseFragment implements OnClickListener {
    private ListView listview;
    private List<String> cardata;
    private CarAdapter carAdapter;
    private LinearLayout ly_notlogin;
    private LinearLayout ly_login1;
    private LinearLayout ly_login2;
    private Button bt_notlogin;
    private String userId;
    

	/**
	 * 选择所有
	 */
	private Button btnSelectAll;
	
    
	@Override
	public View initView(LayoutInflater inflater) {
		
		View view=inflater.inflate(R.layout.activity_shoppingcar, null);
		return view;
	}
	@Override
	protected void initFindViewById(View view) {
	 	listview=(ListView)view.findViewById(R.id.list_gwc);
    	ly_notlogin=(LinearLayout)view.findViewById(R.id.ly_notlogin);
    	ly_login1=(LinearLayout)view.findViewById(R.id.ly_login1);
    	ly_login2=(LinearLayout)view.findViewById(R.id.ly_login2);
    	bt_notlogin=(Button)view.findViewById(R.id.bt_notlogin);
	}
	@Override
	protected void initData(View view) {
    	cardata=new ArrayList<String>();
    	for(int i=0;i<9;i++){
    		String data="西红柿";
    		cardata.add(data);
    	}  
    	//下方全选框
    	ly_login2.setVisibility(View.VISIBLE);
    	//购物车为空
		ly_login1.setVisibility(View.GONE);
		//购物车未登录
	    ly_notlogin.setVisibility(View.GONE);
		carAdapter=new CarAdapter(cardata,getActivity());
     	listview.setAdapter(carAdapter);
    
//    	if(isLogin()){
//    		if(cardata.size()>0){
//    			ly_login2.setVisibility(View.VISIBLE);
//    			ly_login1.setVisibility(View.GONE);
//    			ly_notlogin.setVisibility(View.GONE);
//    			carAdapter=new CarAdapter(cardata,getActivity());
//  	    	listview.setAdapter(carAdapter);
//    		}else{
//    			ly_login2.setVisibility(View.GONE);
//    			ly_login1.setVisibility(View.VISIBLE);
//    			ly_notlogin.setVisibility(View.GONE);
//    		}
//    	}else{
//    		ly_login2.setVisibility(View.GONE);
//			ly_login1.setVisibility(View.GONE);
//			ly_notlogin.setVisibility(View.VISIBLE);
//    	}
    
		
	}
	@Override
	protected void setLinstener() {
		// TODO Auto-generated method stub
		super.setLinstener();
		bt_notlogin.setOnClickListener(this);
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
	 public boolean isLogin(){
		 userId=UserUtil.getUsrId(getActivity());
		 if(!"no".equals(userId)){
			 return true;
		 }else{
			 return false; 
		 }
	 }
	@Override
	public void onClick(View v) {
     switch(v.getId()){
     case R.id.bt_notlogin:
    	 Intent intent1=new Intent(getActivity(),LoginActivity.class);
    	 startActivity(intent1);
    	 break;
    default:
    	break;
     }
	}
	
}
