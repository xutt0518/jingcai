package com.jingcai.jingcaic.activity.me;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;
import com.jingcai.jingcaic.moble.LogBean;
import com.jingcai.jingcaic.moble.VerifyBean;
import com.jingcai.jingcaic.util.ConstantUtils;
import com.jingcai.jingcaic.util.NetWorkUtil;
import com.jingcai.jingcaic.util.UserUtil;

public class SigninActivity extends BaseActivity implements OnClickListener{

	private TextView text_yzm;//发送
	private EditText edit_mobile;//手机号
	private EditText edit_yzm;//验证码
	private EditText edit_pwd;//手机密码
	private EditText edit_pwdconfirm;//确认密码
	private Button btn_login;//注册按钮
	private TimeCount time;
	private String newyzm ;
	private int status;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		titleView.setText("快速注册");	
		init();
	}
	public void init(){
		
		text_yzm=(TextView)findViewById(R.id.text_yzm);
		edit_mobile=(EditText)findViewById(R.id.edit_mobile1);
		edit_yzm=(EditText)findViewById(R.id.edit_yzm);
		edit_pwd=(EditText)findViewById(R.id.edit_pwd);
		edit_pwdconfirm=(EditText)findViewById(R.id.edit_pwdconfirm);
		btn_login=(Button)findViewById(R.id.btn_signin);
		time=new TimeCount(60000,1000);
		text_yzm.setOnClickListener(this);
		btn_login.setOnClickListener(this);
	}	
	@Override
	public void onClick(View v) {
	   switch(v.getId()){
	   case R.id.text_yzm:
		   String textphone=edit_mobile.getText().toString();
		   if(UserUtil.isMobileNO(textphone)){
			   sendyzm(textphone);
		   }else{
			   showShortToastMessage("手机号码格式不正确");
		   }
		   break;
	   case R.id.btn_signin:
		   String phone=edit_mobile.getText().toString();
		   String verify=edit_yzm.getText().toString().trim();
		   String pwd=edit_pwd.getText().toString().trim();
		   String confirmpwd=edit_pwdconfirm.getText().toString().trim();
		   if(status==1){
			   showShortToastMessage("此手机号已注册");
				return;
		   }
		   if(!UserUtil.isMobileNO(phone)){
			   showShortToastMessage("手机号码格式不正确");
				return;
		   }
		   if(verify.equals("")){
			   showShortToastMessage("验证码不能为空");
			   return;
		   }
		   if(!verify.equals(newyzm)){
			   showShortToastMessage("验证码不正确");
			   return;
		   }
		   if(!pwd.equals(confirmpwd)){
			  showShortToastMessage("密码不一致"); 
			  return;
		   }
		   login(phone,verify,pwd);
		    break;
	   default:
		   break;
	   }
	}
	//提交手机号，验证码，密码--登陆
	public void login(final String phone,final String verify,final String pwd){
		new Thread(new Runnable(){
         JSONObject jsonobject;
         String code;
			@Override
			public void run() {
		  final  String Json = NetWorkUtil.GetDate(ConstantUtils.REGISTER
						+ "/mobile/" + phone+"/verify_code/"+verify+"/password/"+pwd+"/invite_code/" +
								"");
				Log.e("resulr", "用户注册" + Json);
				
				// 这儿是耗时操作，完成之后更新UI；
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Gson gson = new Gson();
						LogBean registerbean=gson.fromJson(Json,LogBean.class);
						if(registerbean.code.equals("0")){
								Intent intent=new Intent(SigninActivity.this,LoginActivity.class);
								SigninActivity.this.startActivity(intent);
								finish();
								Toast.makeText(SigninActivity.this,"注册完成",Toast.LENGTH_LONG).show();									
								return;
							}else{
								Toast.makeText(SigninActivity.this,"注册失败",Toast.LENGTH_LONG).show();	
							}
						}
					
					});		
			}			
		}).start();
		
	}
	public void sendyzm(final String mobile){		
		time.start();// 开始计时
		new Thread(new Runnable() {
			JSONObject jsonObject;
			String code;
			@Override
			public void run() {
				final String result = NetWorkUtil.GetDate(ConstantUtils.VERIFY
						+ "/mobile/" + mobile);
				Log.e("验证码", "获取验证码：" + result);
				runOnUiThread(new Runnable(){
					@Override
					public void run() {
						Gson gson = new Gson();
						final VerifyBean verifyBean = gson.fromJson(result, VerifyBean.class);
						if (verifyBean.code.equals("0")) {
							code = verifyBean.code;
							status=verifyBean.result.status;
							newyzm = verifyBean.result.verifycode;
						}else{
							Toast.makeText(SigninActivity.this,"获取验证码失败",Toast.LENGTH_LONG).show();	
						}
											}
					
				});				
			}
		}).start();
	}
	
	// 倒计时
			class TimeCount extends CountDownTimer {
				public TimeCount(long millisInFuture, long countDownInterval) {
					super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
				}
				@Override
				public void onFinish() {// 计时完毕时触发
					text_yzm.setText("重新发送");
					text_yzm.setTextColor(Color.argb(255, 17, 17, 17));
					text_yzm.setClickable(true);
				}

				@Override
				public void onTick(long millisUntilFinished) {// 计时过程显示
					text_yzm.setClickable(false);
					text_yzm.setTextColor(Color.argb(255, 187, 187, 187));
					text_yzm.setText(millisUntilFinished / 1000 + "秒后重发");
				}
			}

}
