package com.jingcai.jingcaic.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class WalletInfoActivity extends BaseActivity implements OnClickListener{
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wallet_info);
		titleView.setText("余额明细");
		templateTextViewRight.setVisibility(View.VISIBLE);
		templateTextViewRight.setText("充值");
		templateTextViewRight.setOnClickListener(this);
		//bt_chongzhi=(Button)findViewById(R.id.bt_chongzhi);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//进入充值界面
		case R.id.title_tv_right:
			Intent intent=new Intent(WalletInfoActivity.this,WalletChongzhiActivity.class);
			startActivity(intent);			
			break;
		case (R.id.bt_chongzhi):
			
			break;
		default:
			break;
		}
	}

}
