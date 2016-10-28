package com.jingcai.jingcaic.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class SuggestActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		titleView.setText("意见反馈");
		templateTextViewRight.setVisibility(View.VISIBLE);
		templateTextViewRight.setText("提交");
		templateTextViewRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//提交意见反馈
		case R.id.title_tv_right:
					
			break;
		default:
			break;
		}
	}

}
