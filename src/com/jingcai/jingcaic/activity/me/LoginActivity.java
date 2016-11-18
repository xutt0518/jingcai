package com.jingcai.jingcaic.activity.me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;
import com.jingcai.jingcaic.activity.BaseFragmentActivity;
import com.jingcai.jingcaic.activity.JingCaiCAcitvity;
import com.jingcai.jingcaic.fragment.homepage.HomePageFragemnt;
import com.jingcai.jingcaic.fragment.me.MeFragment;
import com.jingcai.jingcaic.moble.LogBean;
import com.jingcai.jingcaic.util.ConstantUtils;
import com.jingcai.jingcaic.util.NetWorkUtil;
import com.jingcai.jingcaic.util.UserUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class LoginActivity extends BaseActivity implements OnClickListener{
   
	private EditText edit_phone;
	private EditText edit_password;
	private Button btn_login;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		titleView.setText("登陆");
		templateTextViewRight.setVisibility(View.VISIBLE);
		templateTextViewRight.setText("注册");
		templateTextViewRight.setOnClickListener(this);
		edit_phone=(EditText)findViewById(R.id.edit_phone);
		edit_password=(EditText)findViewById(R.id.edit_password);	    
		btn_login=(Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
	}
   public void login(final String mobile,final String password){
      new Thread(new Runnable(){
    	String code;
    	String userId;
		@Override
		public void run() {
          final String result=NetWorkUtil.GetDate(ConstantUtils.Log_in+"/mobile/"+mobile+"/password/"+password);
          Log.e("用户登陆","用户登陆"+result);
          runOnUiThread(new Runnable(){
			@Override
			public void run() {
            Gson gson=new Gson();
            final LogBean logbean=gson.fromJson(result,LogBean.class);
            code=logbean.code;
            if(code.equals("0")){
            	userId=logbean.result.id;
            	SharedPreferences userconfig=LoginActivity.this.getSharedPreferences("userconfig", 0);
            	Editor edit=userconfig.edit();
            	edit.putString("userId", userId).commit();
            	edit.putString("mobile", mobile).commit();
            	edit.putString("password", password);
            	Intent intent=new Intent(LoginActivity.this,JingCaiCAcitvity.class);
            	LoginActivity.this.startActivity(intent);
            	finish();
            	Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
            }else{
            	Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_LONG).show();
            }
			}       	  
          });
		}    	  
      }).start();
   }
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//进入注册界面
		case R.id.title_tv_right:
			Intent intent=new Intent(LoginActivity.this,SigninActivity.class);
			startActivity(intent);			
			break;
		case R.id.btn_login:
			String mobile=edit_phone.getText().toString();
			String password=edit_password.getText().toString();
			if(!UserUtil.isMobileNO(mobile)){
				showShortToastMessage("手机格式错误");
				return;
			}		
			login(mobile,password);
			break;
		default:
			break;
		}
	}
  
}
