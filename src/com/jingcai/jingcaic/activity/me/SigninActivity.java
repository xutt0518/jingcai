package com.jingcai.jingcaic.activity.me;

import android.os.Bundle;
import android.view.View;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class SigninActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		titleView.setText("¿ìËÙ×¢²á");
		
	}

}
