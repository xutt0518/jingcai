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
	 * 选择按钮是否选择的存储集合,key 是 position , value 是该position是否选中
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	
	/**
	 * 创建接口对象
	 */
	private onItemClickSelectLinstner listnener;
	
	
	/**
	 * 添加选择按钮点击事件
	 */
	public interface onItemClickSelectLinstner{
		
		void onChildClick(View view,int position,GoodsEntity item);
	
	}
	
	// 添加set监听方法,以防止与系统重名，修改方法名setOnChildListnener
	public void setOnItemClickSelectLinstner(onItemClickSelectLinstner listnener) {
		this.listnener = listnener;
		
	}
	
	
	public ShoppingCartAdapter(Context context, List<GoodsEntity> datas) {
		this.context = context;
		this.datas = datas;
		inflater = LayoutInflater.from(context);
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
	
	
	//返回数据的个数
	@Override
	public int getCount() {
		
		return datas == null ? 0 : datas.size();
	}
	//根据位置返回item对象对应的数据
	@Override
	public Object getItem(int position) {
	
		return datas.get(position);
	}
	 //此方法一般返回item的位置
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
		
			//获得item
			final GoodsEntity item=datas.get(position);
			//标识可以删除
			boolean canRemove = item.isCanRemove();
			
			holder.tvTitle.setText(item.getTitle());
			holder.tvAmount.setText(String.valueOf(item.getAmount()));
			
			//选择按钮
			holder.cbCheck.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//CheckBox控件
					CheckBox cb = (CheckBox)v;
					//CheckBox控件被选中
					item.checked=cb.isChecked();
					
					/*
					 * 将选择项加载到map里面寄存
					 */
					isCheckMap.put(position, item.checked);
					//调接口
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
			//判断选择按钮是否可删除
			if(!canRemove){
			// 隐藏单选按钮,因为是不可删除的
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
