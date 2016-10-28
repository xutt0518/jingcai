package com.jingcai.jingcaic.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class WalletChongzhiActivity extends BaseActivity implements OnClickListener{
    private Button bt_chongzhi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wallet_chongzhi);
		titleView.setText("账户充值");
		templateTextViewRight.setVisibility(View.VISIBLE);
		templateTextViewRight.setText("明细");
		templateTextViewRight.setOnClickListener(this);
		
		//bt_chongzhi=(Button)findViewById(R.id.bt_chongzhi);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//进入明细界面
		case R.id.title_tv_right:
			Intent intent=new Intent(WalletChongzhiActivity.this,WalletInfoActivity.class);
			startActivity(intent);			
			break;
		case (R.id.bt_chongzhi):
			
			break;
		default:
			break;
		}
	}

}
