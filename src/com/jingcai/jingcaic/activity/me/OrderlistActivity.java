package com.jingcai.jingcaic.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class OrderlistActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		titleView.setText("��½");
		templateTextViewRight.setVisibility(View.VISIBLE);
		templateTextViewRight.setText("ע��");
		templateTextViewRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//����ע�����
		case R.id.title_tv_right:
			Intent intent=new Intent(OrderlistActivity.this,SigninActivity.class);
			startActivity(intent);			
			break;
		default:
			break;
		}
	}

}
