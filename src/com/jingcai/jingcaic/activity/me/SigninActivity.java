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

	private TextView text_yzm;//����
	private EditText edit_mobile;//�ֻ���
	private EditText edit_yzm;//��֤��
	private EditText edit_pwd;//�ֻ�����
	private EditText edit_pwdconfirm;//ȷ������
	private Button btn_login;//ע�ᰴť
	private TimeCount time;
	private String newyzm ;
	private int status;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		titleView.setText("����ע��");	
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
			   showShortToastMessage("�ֻ������ʽ����ȷ");
		   }
		   break;
	   case R.id.btn_signin:
		   String phone=edit_mobile.getText().toString();
		   String verify=edit_yzm.getText().toString().trim();
		   String pwd=edit_pwd.getText().toString().trim();
		   String confirmpwd=edit_pwdconfirm.getText().toString().trim();
		   if(status==1){
			   showShortToastMessage("���ֻ�����ע��");
				return;
		   }
		   if(!UserUtil.isMobileNO(phone)){
			   showShortToastMessage("�ֻ������ʽ����ȷ");
				return;
		   }
		   if(verify.equals("")){
			   showShortToastMessage("��֤�벻��Ϊ��");
			   return;
		   }
		   if(!verify.equals(newyzm)){
			   showShortToastMessage("��֤�벻��ȷ");
			   return;
		   }
		   if(!pwd.equals(confirmpwd)){
			  showShortToastMessage("���벻һ��"); 
			  return;
		   }
		   login(phone,verify,pwd);
		    break;
	   default:
		   break;
	   }
	}
	//�ύ�ֻ��ţ���֤�룬����--��½
	public void login(final String phone,final String verify,final String pwd){
		new Thread(new Runnable(){
         JSONObject jsonobject;
         String code;
			@Override
			public void run() {
		  final  String Json = NetWorkUtil.GetDate(ConstantUtils.REGISTER
						+ "/mobile/" + phone+"/verify_code/"+verify+"/password/"+pwd+"/invite_code/" +
								"");
				Log.e("resulr", "�û�ע��" + Json);
				
				// ����Ǻ�ʱ���������֮�����UI��
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Gson gson = new Gson();
						LogBean registerbean=gson.fromJson(Json,LogBean.class);
						if(registerbean.code.equals("0")){
								Intent intent=new Intent(SigninActivity.this,LoginActivity.class);
								SigninActivity.this.startActivity(intent);
								finish();
								Toast.makeText(SigninActivity.this,"ע�����",Toast.LENGTH_LONG).show();									
								return;
							}else{
								Toast.makeText(SigninActivity.this,"ע��ʧ��",Toast.LENGTH_LONG).show();	
							}
						}
					
					});		
			}			
		}).start();
		
	}
	public void sendyzm(final String mobile){		
		time.start();// ��ʼ��ʱ
		new Thread(new Runnable() {
			JSONObject jsonObject;
			String code;
			@Override
			public void run() {
				final String result = NetWorkUtil.GetDate(ConstantUtils.VERIFY
						+ "/mobile/" + mobile);
				Log.e("��֤��", "��ȡ��֤�룺" + result);
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
							Toast.makeText(SigninActivity.this,"��ȡ��֤��ʧ��",Toast.LENGTH_LONG).show();	
						}
											}
					
				});				
			}
		}).start();
	}
	
	// ����ʱ
			class TimeCount extends CountDownTimer {
				public TimeCount(long millisInFuture, long countDownInterval) {
					super(millisInFuture, countDownInterval);// ��������Ϊ��ʱ��,�ͼ�ʱ��ʱ����
				}
				@Override
				public void onFinish() {// ��ʱ���ʱ����
					text_yzm.setText("���·���");
					text_yzm.setTextColor(Color.argb(255, 17, 17, 17));
					text_yzm.setClickable(true);
				}

				@Override
				public void onTick(long millisUntilFinished) {// ��ʱ������ʾ
					text_yzm.setClickable(false);
					text_yzm.setTextColor(Color.argb(255, 187, 187, 187));
					text_yzm.setText(millisUntilFinished / 1000 + "����ط�");
				}
			}

}
