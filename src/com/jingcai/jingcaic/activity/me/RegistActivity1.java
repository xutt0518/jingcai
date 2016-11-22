package com.jingcai.jingcaic.activity.me;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jingcai.jingcac.biz.CodeBiz;
import com.jingcai.jingcac.biz.RegisterBiz;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.R.color;
import com.jingcai.jingcaic.activity.BaseActivity;
import com.jingcai.jingcaic.entity.UserEntity;
import com.jingcai.jingcaic.util.Const;
import com.jingcai.jingcaic.util.UserUtil;

public class RegistActivity1 extends BaseActivity implements OnClickListener{
	
	private TextView text_yzm;//发送
	private EditText edit_mobile;//手机号
	private EditText edit_yzm;//验证码
	private EditText edit_pwd;//手机密码
	private EditText edit_pwdconfirm;//确认密码
	private Button btn_login;//注册按钮
	private TimeCount time;
	private String newyzm ;
	private int status;
	private UserEntity userEntity;
	private CodeReceiver codeReceiver;
	
	private String textphone;//手机号
	private String verify;//邀请码
	private String pwd;//密码
	private String confirmpwd;//确认密码
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		titleView.setText("注册");	
		try {
			init();
			
			codeReceiver=new CodeReceiver();
			this.registerReceiver(codeReceiver, new IntentFilter(
					Const.ACTION__CODE));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(codeReceiver);
	}
	
	private void init() {
		time=new TimeCount(60000, 1000);
		text_yzm=(TextView)findViewById(R.id.text_yzm);
		edit_mobile=(EditText)findViewById(R.id.edit_mobile1);
		edit_yzm=(EditText)findViewById(R.id.edit_yzm);
		edit_pwd=(EditText)findViewById(R.id.edit_pwd);
		edit_pwdconfirm=(EditText)findViewById(R.id.edit_pwdconfirm);
		btn_login=(Button)findViewById(R.id.btn_signin);
		text_yzm.setOnClickListener(this);
		btn_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//获得手机号码
		textphone=edit_mobile.getText().toString();
	    verify=edit_yzm.getText().toString().trim();
	    pwd=edit_pwd.getText().toString().trim();
	    confirmpwd=edit_pwdconfirm.getText().toString().trim();
	
		switch (v.getId()) {
		case R.id.text_yzm:
			//发送验证码
			sendcode();	 
			break;
			
		case R.id.btn_signin:
			//登录
			signin();
			break;
			
		default:
			break;
		}
		
	}


	/**
	 * 注册
	 */
	private void signin() {
		if(!pwd.equals(confirmpwd)){
			  showShortToastMessage("密码与确认密码不一致");
		}
		if(textphone.equals("")){
			showShortToastMessage("手机号为空");
		}
		if(verify.equals("")){
			showShortToastMessage("验证码为空");
		}
		if(pwd.equals("")){
			showShortToastMessage("密码为空");
		}
		if(confirmpwd.equals("")){
			showShortToastMessage("确认密码为空");
		}
		if(!UserUtil.isMobileNO(textphone)){
			showShortToastMessage("手机号码格式错误");
		}
		System.out.println("11111"+"注册");
		userEntity=new UserEntity();
		//手机号
		userEntity.setUsername(textphone);
		//密码
		userEntity.setUserpassword(pwd);
		//验证码
		userEntity.setVerifycode(verify);

		RegisterBiz registerBiz=new RegisterBiz();
		registerBiz.register(userEntity);
	}
	/**
	 * 发送短信验证码
	 */
	private void sendcode() {
		  //开始计时
		   time.start();
		  //验证手机号格式
		   if(UserUtil.isMobileNO(textphone)){
			   //格式正确,发送验证码
			   userEntity=new UserEntity();
			   userEntity.setUsername(textphone);
			   CodeBiz codeBiz=new CodeBiz();
			   codeBiz.code(userEntity);   
		   }else{
			   //格式不正确
			   showShortToastMessage("手机号码格式不正确");
		   }
		
	}

	// 倒计时
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}
		@Override
		public void onFinish() {					// 计时完毕时触发
			text_yzm.setText("重新发送");
			text_yzm.setBackgroundColor(color.orange);
			text_yzm.setTextColor(Color.WHITE);
			text_yzm.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			text_yzm.setBackgroundColor(color.gray);
			text_yzm.setClickable(false);
			text_yzm.setTextColor(Color.argb(255, 187, 187, 187));
			text_yzm.setText(millisUntilFinished / 1000 + "秒后重发");
		}
	}
	
	//验证码接收
	class CodeReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			
			int status = intent.getIntExtra(Const.KEY_DATA, 0);
			//成功接收
			if (status == Const.STATUS_OK) {
				showShortToastMessage("请查看手机验证码");
			}
		}
	}

	

}
