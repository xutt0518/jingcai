package com.jingcai.jingcaic.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class WalletActivity extends BaseActivity implements OnClickListener{
    private Button bt_chongzhi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mywallet);
		titleView.setText("我的钱包");
		bt_chongzhi=(Button)findViewById(R.id.bt_chongzhi);
		bt_chongzhi.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//进入注册界面
		case R.id.title_tv_right:
			Intent intent=new Intent(WalletActivity.this,RegistActivity.class);
			startActivity(intent);			
			break;
		case (R.id.bt_chongzhi):
			Intent intent1 =new Intent(WalletActivity.this,WalletChongzhiActivity.class);
		    startActivity(intent1);
			break;
		default:
			break;
		}
	}

}
