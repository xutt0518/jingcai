package com.jingcai.jingcaic.activity.me;

import android.os.Bundle;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class CouponActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon);
		titleView.setText("”≈ª›»Ø");		
		
	}

}
