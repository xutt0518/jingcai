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
	
	//上下文对象
	private Context context;
    //数据集合
	private List<GoodsEntity> datas = null;
	
	/**
	 * CheckBox 是否选择的存储集合,key 是 position , value 是该position是否选中
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	
	
	
	public ShoppingCartAdapter(Context context, List<GoodsEntity> datas) {
		this.context = context;
		this.datas = datas;
		// 初始化,默认都没有选中
		configCheckMap(false);
	}
	
	

	/**
	 * 首先,默认情况下,所有项目都是没有选中的.这里进行初始化
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
		 * 进行ListView 的优化
		 */
		if (convertView == null) {
			layout = (ViewGroup) LayoutInflater.from(context).inflate(
					R.layout.car_item, parent, false);
		} else {
			layout = (ViewGroup) convertView;
		}
		GoodsEntity bean = datas.get(position);
		/**
		 * 获得该item 是否允许删除
		 */
		boolean canRemove = bean.isCanRemove();
		/**
		 * 设置每一个item的文本
		 */
		//商品名称
		TextView tvTitle = (TextView) layout.findViewById(R.id.text_goodstitle);
		tvTitle.setText(bean.getTitle());
		//总价
		TextView tvamount = (TextView) layout.findViewById(R.id.tv_amount);
		tvamount.setText(bean.getAmount());
		//获得单选按钮
		CheckBox cbCheck = (CheckBox) layout.findViewById(R.id.cbCheckBox);
		
		/*
		 * 设置单选按钮的选中
		 */
		cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				/*
				 * 将选择项加载到map里面寄存
				 */
				isCheckMap.put(position, isChecked);
			}
		});
		
		if (!canRemove) {
			// 隐藏单选按钮,因为是不可删除的
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
			 * 将数据保存到tag
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
	
	// 移除一个项目的时候
	public void remove(int position) {
		this.datas.remove(position);
	}

	public List<GoodsEntity> getDatas() {
		return datas;
	}
	
}
