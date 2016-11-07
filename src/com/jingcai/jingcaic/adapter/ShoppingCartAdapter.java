package com.jingcai.jingcaic.adapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.entity.GoodsEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ShoppingCartAdapter extends BaseAdapter{
	
	//�����Ķ���
	private Context context;
    //���ݼ���
	private List<GoodsEntity> datas = null;
	
	/**
	 * CheckBox �Ƿ�ѡ��Ĵ洢����,key �� position , value �Ǹ�position�Ƿ�ѡ��
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	
	
	
	public ShoppingCartAdapter(Context context, List<GoodsEntity> datas) {
		this.context = context;
		this.datas = datas;
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
	

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewGroup layout = null;
		/**
		 * ����ListView ���Ż�
		 */
		if (convertView == null) {
			layout = (ViewGroup) LayoutInflater.from(context).inflate(
					R.layout.car_item, parent, false);
		} else {
			layout = (ViewGroup) convertView;
		}
		GoodsEntity bean = datas.get(position);
		/**
		 * ��ø�item �Ƿ�����ɾ��
		 */
		boolean canRemove = bean.isCanRemove();
		/**
		 * ����ÿһ��item���ı�
		 */
		//��Ʒ����
		TextView tvTitle = (TextView) layout.findViewById(R.id.text_goodstitle);
		tvTitle.setText(bean.getTitle());
		//�ܼ�
		TextView tvamount = (TextView) layout.findViewById(R.id.tv_amount);
		tvamount.setText(bean.getAmount());
		//��õ�ѡ��ť
		CheckBox cbCheck = (CheckBox) layout.findViewById(R.id.cbCheckBox);
		
		/*
		 * ���õ�ѡ��ť��ѡ��
		 */
		cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				/*
				 * ��ѡ������ص�map����Ĵ�
				 */
				isCheckMap.put(position, isChecked);
			}
		});
		
		if (!canRemove) {
			// ���ص�ѡ��ť,��Ϊ�ǲ���ɾ����
			cbCheck.setVisibility(View.GONE);
			cbCheck.setChecked(false);
		} else {
			cbCheck.setVisibility(View.VISIBLE);

			if (isCheckMap.get(position) == null) {
				isCheckMap.put(position, false);
			}

			cbCheck.setChecked(isCheckMap.get(position));

			ViewHolder holder = new ViewHolder();

			holder.cbCheck = cbCheck;

			holder.tvTitle = tvTitle;

			/**
			 * �����ݱ��浽tag
			 */
			layout.setTag(holder);
		}

		return layout;
	}
	
	public static class ViewHolder {

		public TextView tvTitle = null;
		public TextView	tvamount=null;
		public CheckBox cbCheck = null;
		public Object data = null;

	}
	
	
	public Map<Integer, Boolean> getCheckMap() {
		return this.isCheckMap;
	}
	
	// �Ƴ�һ����Ŀ��ʱ��
	public void remove(int position) {
		this.datas.remove(position);
	}

	public List<GoodsEntity> getDatas() {
		return datas;
	}
	
}
