package com.jingcai.jingcaic.fragment.shoppingcat;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.adapter.ShoppingCartAdapter;
import com.jingcai.jingcaic.adapter.ShoppingCartAdapter.onItemClickSelectLinstner;
import com.jingcai.jingcaic.entity.GoodsEntity;
import com.jingcai.jingcaic.fragment.BaseFragment;

public class ShoppingCatFragment extends BaseFragment implements onItemClickSelectLinstner{
	
	private ListView listview;
	//全选
	private Button btnSelectAll;
	private List<GoodsEntity>items;
	private ShoppingCartAdapter shoppingCartAdapter;
	//总价
	private TextView text_price;
	//总价格
	private static int allMoney;
	//单个价格
	private static int oneMoney;
	//判断是否全选(默认false)
	private static boolean quanxuan;
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.activity_shoppingcar, null);
		return view;
	}

	@Override
	protected void initFindViewById(View view) {
		listview=(ListView)view.findViewById(R.id.list_gwc);
		btnSelectAll=(Button) view.findViewById(R.id.all_collect);
		text_price=(TextView) view.findViewById(R.id.text_price);
	
	try {
			oneMoney=Integer.parseInt(text_price.getText().toString());
			allMoney=Integer.parseInt(text_price.getText().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void initData(View view) {
		items=new ArrayList<GoodsEntity>();
		items.add(new GoodsEntity(null, "西红柿", null, null, null, 0, 100, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "西瓜", null, null, null, 0, 90, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "萝卜", null, null, null, 0, 80, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "土豆", null, null, null, 0, 70, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "黄瓜", null, null, null, 0, 60, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "白菜", null, null, null, 0, 50, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "苹果", null, null, null, 0, 40, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "香蕉", null, null, null, 0, 30, null, 0, null, null,true));
		
		shoppingCartAdapter = new ShoppingCartAdapter(context, items);

		shoppingCartAdapter.setOnItemClickSelectLinstner(this);
		
		listview.setAdapter(shoppingCartAdapter);
	}

	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	
	//选择按钮点击事件
	@Override
	public void onChildClick(View view, int position, GoodsEntity item) {
	
		if(quanxuan==true)	             //全选
		{
			if(!item.isChecked()){//选择框全满，点击取消
				allMoney=allMoney-items.get(position).getAmount();
				
			}else{				  //选择框全满，点击取消,再点击增加
				allMoney=allMoney+items.get(position).getAmount();
	
			}
		}else if(quanxuan==false)        //全空
		{
			if(item.isChecked()){//选择框全空，点击增加
				allMoney=allMoney+items.get(position).getAmount();

			}else{				 //选择框全空，点击增加,再点击减少
				allMoney=allMoney-items.get(position).getAmount();
	
			}
			
		}
		//赋值
		text_price.setText(String.valueOf(allMoney));

	}

	@Override
	protected void setLinstener() {
		// TODO Auto-generated method stub
		super.setLinstener();
		//全选按钮
		btnSelectAll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(btnSelectAll.getText().toString().trim().equals("全选")){

					// 所有项目全部选中
					shoppingCartAdapter.configCheckMap(true);
					shoppingCartAdapter.notifyDataSetChanged();
					btnSelectAll.setText("全不选");
					btnSelectAll.setSelected(true);	
					
					allMoney=0;
					for(int i=0;i<items.size();i++){
						
						allMoney+=items.get(i).getAmount();
					}
					//总价赋值
					text_price.setText(String.valueOf(allMoney));
					
					quanxuan=true;
				
				}else{

					allMoney=0;
					// 所有项目全部不选中
					shoppingCartAdapter.configCheckMap(false);
					shoppingCartAdapter.notifyDataSetChanged();
					btnSelectAll.setText("全选");
					btnSelectAll.setSelected(false);
					//总价赋值
					text_price.setText(String.valueOf(allMoney));
					
					quanxuan=false;
				}
				
			}
		});
		
	}

}
