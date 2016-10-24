package com.jingcai.jingcaic.activity.type;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GoodsInfoActvity extends BaseActivity implements OnClickListener{
	private ImageView goodsfavor;
	private ImageView addcar;
	private boolean ischecked;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		titleView.setText("…Ã∆∑œÍ«È");
		setContentView(R.layout.goodds_info);
		init();
	}
	public void init(){
		goodsfavor=(ImageView)findViewById(R.id.goods_imge);
		addcar=(ImageView)findViewById(R.id.add_car);
		ischecked=true;
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.goods_favor:		
			break;
		case R.id.add_car:
			break;
		default:
		    break;
		}
	}
}
