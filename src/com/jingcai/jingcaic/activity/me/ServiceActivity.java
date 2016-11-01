package com.jingcai.jingcaic.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class ServiceActivity extends BaseActivity implements OnClickListener{
	private LinearLayout ly_tk;
	private LinearLayout ly_sh;
	private LinearLayout ly_aboutus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		titleView.setText("·þÎñÐëÖª");
		ly_tk=(LinearLayout)findViewById(R.id.ly_tk);
		ly_sh=(LinearLayout)findViewById(R.id.ly_sh);
		ly_aboutus=(LinearLayout)findViewById(R.id.ly_aboutus);
		ly_tk.setOnClickListener(this);
		ly_sh.setOnClickListener(this);
		ly_aboutus.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ly_tk:
			 Intent tk=new Intent(ServiceActivity.this,TiaokuanActivity.class);
			 startActivity(tk);
			break;
		case R.id.ly_sh:
			Intent sh=new Intent(ServiceActivity.this,ShouhouActivity.class);
			startActivity(sh);
			break;
		case R.id.ly_aboutus:
			Intent aboutus=new Intent(ServiceActivity.this,AboutusActivity.class);
			startActivity(aboutus);
			break;
		default:
			break;
		}
	}
}
