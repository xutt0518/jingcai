package com.jingcai.jingcaic.activity.me;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class AboutusActivity extends BaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_aboutus);
		titleView.setText("关于我们");
	}
	@Override
	public void onClick(View v) {
		
	}
}
