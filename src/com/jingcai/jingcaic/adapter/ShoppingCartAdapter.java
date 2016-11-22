package com.jingcai.jingcaic.adapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.entity.GoodsEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ShoppingCartAdapter extends BaseAdapter{
	
	private Context context;
	private List<GoodsEntity> datas = null;
	LayoutInflater inflater;
	
	/**
	 * ѡ��ť�Ƿ�ѡ��Ĵ洢����,key �� position , value �Ǹ�position�Ƿ�ѡ��
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	
	/**
	 * �����ӿڶ���
	 */
	private onItemClickSelectLinstner listnener;
	
	
	/**
	 * ���ѡ��ť����¼�
	 */
	public interface onItemClickSelectLinstner{
		
		void onChildClick(View view,int position,GoodsEntity item);
	
	}
	
	// ���set��������,�Է�ֹ��ϵͳ�������޸ķ�����setOnChildListnener
	public void setOnItemClickSelectLinstner(onItemClickSelectLinstner listnener) {
		this.listnener = listnener;
		
	}
	
	
	public ShoppingCartAdapter(Context context, List<GoodsEntity> datas) {
		this.context = context;
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		// ��ʼ��,Ĭ�϶�û��ѡ��
		 configCheckMap(false);
	}
	
	/**
	 * ����,Ĭ�������,������Ŀ����û��ѡ�е�.������г�ʼ��
	 */
	public void configCheckMap(boolean bool) {

		for (int i = 0; i < datas.size(); i++) {
			isCheckMap.put(i, bool);
		}

	}
	
	
	//�������ݵĸ���
	@Override
	public int getCount() {
		
		return datas == null ? 0 : datas.size();
	}
	//����λ�÷���item�����Ӧ������
	@Override
	public Object getItem(int position) {
	
		return datas.get(position);
	}
	 //�˷���һ�㷵��item��λ��
	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(final  int  position,View convertView, ViewGroup parent) {
		ViewHolder holder =null;
		if(convertView==null){
			convertView = inflater.inflate(R.layout.car_item1, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView) convertView.findViewById(R.id.text_goodstitle);
			holder.tvAmount= (TextView) convertView.findViewById(R.id.tv_amount);
			holder.cbCheck=(CheckBox) convertView.findViewById(R.id.cbCheckBox);
			convertView.setTag(holder);  
		}else{
			
			holder = (ViewHolder)convertView.getTag();
		}
		
			//���item
			final GoodsEntity item=datas.get(position);
			//��ʶ����ɾ��
			boolean canRemove = item.isCanRemove();
			
			holder.tvTitle.setText(item.getTitle());
			holder.tvAmount.setText(String.valueOf(item.getAmount()));
			
			//ѡ��ť
			holder.cbCheck.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//CheckBox�ؼ�
					CheckBox cb = (CheckBox)v;
					//CheckBox�ؼ���ѡ��
					item.checked=cb.isChecked();
					
					/*
					 * ��ѡ������ص�map����Ĵ�
					 */
					isCheckMap.put(position, item.checked);
					//���ӿ�
					listnener.onChildClick(v, position, item);
					
				}
			});
//			holder.cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//			
//			}
//		});
			//�ж�ѡ��ť�Ƿ��ɾ��
			if(!canRemove){
			// ���ص�ѡ��ť,��Ϊ�ǲ���ɾ����
				holder.cbCheck.setVisibility(View.GONE);
				holder.cbCheck.setChecked(false);
			}else{
				holder.cbCheck.setVisibility(View.VISIBLE);
				if (isCheckMap.get(position) == null) {
					isCheckMap.put(position, false);
				}
				holder.cbCheck.setChecked(isCheckMap.get(position));
			}
			
			
			return convertView;
	}
	
	public static class ViewHolder {
			TextView tvTitle;
			TextView tvAmount;
			CheckBox cbCheck;
	}
	
	
}
