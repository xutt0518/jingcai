package com.jingcai.jingcaic.fragment.shoppingcat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.jingcai.jingcaic.adapter.ShoppingCartAdapter;
import com.jingcai.jingcaic.entity.GoodsEntity;
import com.jingcai.jingcaic.fragment.BaseFragment;
import com.jingcai.jingcaic.util.UserUtil;
public class ShoppingCatFragment extends BaseFragment implements OnClickListener {
	
    private ListView listview;
    private LinearLayout ly_notlogin;
    private LinearLayout ly_login1;
    private LinearLayout ly_login2;
    private Button bt_notlogin;
    private String userId;
	/**
	 * ȷ��ѡ������
	 */
	private Button btnSelectAll;
	private List<String> cardata;
	private ShoppingCartAdapter shoppingCartAdapter;
	
   // private private List<Item> items;
	@Override
	public View initView(LayoutInflater inflater) {
		
		View view=inflater.inflate(R.layout.activity_shoppingcar, null);
		return view;
	}
	@Override
	protected void initFindViewById(View view) {
		btnSelectAll=(Button) view.findViewById(R.id.all_collect);
	 	listview=(ListView)view.findViewById(R.id.list_gwc);
    	ly_notlogin=(LinearLayout)view.findViewById(R.id.ly_notlogin);
    	ly_login1=(LinearLayout)view.findViewById(R.id.ly_login1);
    	ly_login2=(LinearLayout)view.findViewById(R.id.ly_login2);
    	bt_notlogin=(Button)view.findViewById(R.id.bt_notlogin);
	}
	@Override
	protected void initData(View view) {
	
		// ģ�������
		List<GoodsEntity> demoDatas = new ArrayList<GoodsEntity>();
		demoDatas.add(new GoodsEntity(null, "������", null, null, null, null, "99", null, null, null, null, true));
		demoDatas.add(new GoodsEntity(null, "���ܲ�", null, null, null, null, "69", null, null, null, null, true));
		demoDatas.add(new GoodsEntity(null, "����", null, null, null, null, "59", null, null, null, null, true));
		demoDatas.add(new GoodsEntity(null, "�Ϲ�", null, null, null, null, "49", null, null, null, null, true));
		demoDatas.add(new GoodsEntity(null, "������", null, null, null, null, "39", null, null, null, null, true));
		demoDatas.add(new GoodsEntity(null, "����", null, null, null, null, "29", null, null, null, null, true));
		demoDatas.add(new GoodsEntity(null, "������", null, null, null, null, "19", null, null, null, null, true));
		demoDatas.add(new GoodsEntity(null, "������", null, null, null, null, "89", null, null, null, null, true));
		
		shoppingCartAdapter = new ShoppingCartAdapter(context, demoDatas);

		listview.setAdapter(shoppingCartAdapter);
		
		
//    	//�·�ȫѡ��
//    	ly_login2.setVisibility(View.VISIBLE);
//    	//���ﳵΪ��
//		ly_login1.setVisibility(View.GONE);
//		//���ﳵδ��¼
//	    ly_notlogin.setVisibility(View.GONE);	    
     	
     	
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
		super.setLinstener();
		bt_notlogin.setOnClickListener(this);
		btnSelectAll.setOnClickListener(this);
	}
	@Override
	public void initData() {
		
	}
	//���ȫѡ
	@Override
	public void onClick(View v) {
		if (v == btnSelectAll){
			if (btnSelectAll.getText().toString().trim().equals("ȫѡ")) {

				// ������Ŀȫ��ѡ��
				shoppingCartAdapter.configCheckMap(true);

				shoppingCartAdapter.notifyDataSetChanged();

				btnSelectAll.setText("ȫ��ѡ");
				
				btnSelectAll.setSelected(true);
//				
//				for(int i=0;i<listview.getChildCount();i++){
//					LinearLayout layout = (LinearLayout)listview.getChildAt(i);
//					TextView textView=(TextView) layout.findViewById(R.id.tv_amount);
//		
//				}
//			
				
			} else {

				// ������Ŀȫ����ѡ��
				shoppingCartAdapter.configCheckMap(false);

				shoppingCartAdapter.notifyDataSetChanged();

				btnSelectAll.setText("ȫѡ");
			
				btnSelectAll.setSelected(false);
			}
		}
		
	}
	

}
