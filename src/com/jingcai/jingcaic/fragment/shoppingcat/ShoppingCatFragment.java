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
	//ȫѡ
	private Button btnSelectAll;
	private List<GoodsEntity>items;
	private ShoppingCartAdapter shoppingCartAdapter;
	//�ܼ�
	private TextView text_price;
	//�ܼ۸�
	private static int allMoney;
	//�����۸�
	private static int oneMoney;
	//�ж��Ƿ�ȫѡ(Ĭ��false)
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
		items.add(new GoodsEntity(null, "������", null, null, null, 0, 100, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "����", null, null, null, 0, 90, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "�ܲ�", null, null, null, 0, 80, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "����", null, null, null, 0, 70, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "�ƹ�", null, null, null, 0, 60, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "�ײ�", null, null, null, 0, 50, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "ƻ��", null, null, null, 0, 40, null, 0, null, null,true));
		items.add(new GoodsEntity(null, "�㽶", null, null, null, 0, 30, null, 0, null, null,true));
		
		shoppingCartAdapter = new ShoppingCartAdapter(context, items);

		shoppingCartAdapter.setOnItemClickSelectLinstner(this);
		
		listview.setAdapter(shoppingCartAdapter);
	}

	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	
	//ѡ��ť����¼�
	@Override
	public void onChildClick(View view, int position, GoodsEntity item) {
	
		if(quanxuan==true)	             //ȫѡ
		{
			if(!item.isChecked()){//ѡ���ȫ�������ȡ��
				allMoney=allMoney-items.get(position).getAmount();
				
			}else{				  //ѡ���ȫ�������ȡ��,�ٵ������
				allMoney=allMoney+items.get(position).getAmount();
	
			}
		}else if(quanxuan==false)        //ȫ��
		{
			if(item.isChecked()){//ѡ���ȫ�գ��������
				allMoney=allMoney+items.get(position).getAmount();

			}else{				 //ѡ���ȫ�գ��������,�ٵ������
				allMoney=allMoney-items.get(position).getAmount();
	
			}
			
		}
		//��ֵ
		text_price.setText(String.valueOf(allMoney));

	}

	@Override
	protected void setLinstener() {
		// TODO Auto-generated method stub
		super.setLinstener();
		//ȫѡ��ť
		btnSelectAll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(btnSelectAll.getText().toString().trim().equals("ȫѡ")){

					// ������Ŀȫ��ѡ��
					shoppingCartAdapter.configCheckMap(true);
					shoppingCartAdapter.notifyDataSetChanged();
					btnSelectAll.setText("ȫ��ѡ");
					btnSelectAll.setSelected(true);	
					
					allMoney=0;
					for(int i=0;i<items.size();i++){
						
						allMoney+=items.get(i).getAmount();
					}
					//�ܼ۸�ֵ
					text_price.setText(String.valueOf(allMoney));
					
					quanxuan=true;
				
				}else{

					allMoney=0;
					// ������Ŀȫ����ѡ��
					shoppingCartAdapter.configCheckMap(false);
					shoppingCartAdapter.notifyDataSetChanged();
					btnSelectAll.setText("ȫѡ");
					btnSelectAll.setSelected(false);
					//�ܼ۸�ֵ
					text_price.setText(String.valueOf(allMoney));
					
					quanxuan=false;
				}
				
			}
		});
		
	}

}
