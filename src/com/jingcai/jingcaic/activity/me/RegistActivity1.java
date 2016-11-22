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
	
	private TextView text_yzm;//����
	private EditText edit_mobile;//�ֻ���
	private EditText edit_yzm;//��֤��
	private EditText edit_pwd;//�ֻ�����
	private EditText edit_pwdconfirm;//ȷ������
	private Button btn_login;//ע�ᰴť
	private TimeCount time;
	private String newyzm ;
	private int status;
	private UserEntity userEntity;
	private CodeReceiver codeReceiver;
	
	private String textphone;//�ֻ���
	private String verify;//������
	private String pwd;//����
	private String confirmpwd;//ȷ������
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		titleView.setText("ע��");	
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
		//����ֻ�����
		textphone=edit_mobile.getText().toString();
	    verify=edit_yzm.getText().toString().trim();
	    pwd=edit_pwd.getText().toString().trim();
	    confirmpwd=edit_pwdconfirm.getText().toString().trim();
	
		switch (v.getId()) {
		case R.id.text_yzm:
			//������֤��
			sendcode();	 
			break;
			
		case R.id.btn_signin:
			//��¼
			signin();
			break;
			
		default:
			break;
		}
		
	}


	/**
	 * ע��
	 */
	private void signin() {
		if(!pwd.equals(confirmpwd)){
			  showShortToastMessage("������ȷ�����벻һ��");
		}
		if(textphone.equals("")){
			showShortToastMessage("�ֻ���Ϊ��");
		}
		if(verify.equals("")){
			showShortToastMessage("��֤��Ϊ��");
		}
		if(pwd.equals("")){
			showShortToastMessage("����Ϊ��");
		}
		if(confirmpwd.equals("")){
			showShortToastMessage("ȷ������Ϊ��");
		}
		if(!UserUtil.isMobileNO(textphone)){
			showShortToastMessage("�ֻ������ʽ����");
		}
		System.out.println("11111"+"ע��");
		userEntity=new UserEntity();
		//�ֻ���
		userEntity.setUsername(textphone);
		//����
		userEntity.setUserpassword(pwd);
		//��֤��
		userEntity.setVerifycode(verify);

		RegisterBiz registerBiz=new RegisterBiz();
		registerBiz.register(userEntity);
	}
	/**
	 * ���Ͷ�����֤��
	 */
	private void sendcode() {
		  //��ʼ��ʱ
		   time.start();
		  //��֤�ֻ��Ÿ�ʽ
		   if(UserUtil.isMobileNO(textphone)){
			   //��ʽ��ȷ,������֤��
			   userEntity=new UserEntity();
			   userEntity.setUsername(textphone);
			   CodeBiz codeBiz=new CodeBiz();
			   codeBiz.code(userEntity);   
		   }else{
			   //��ʽ����ȷ
			   showShortToastMessage("�ֻ������ʽ����ȷ");
		   }
		
	}

	// ����ʱ
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// ��������Ϊ��ʱ��,�ͼ�ʱ��ʱ����
		}
		@Override
		public void onFinish() {					// ��ʱ���ʱ����
			text_yzm.setText("���·���");
			text_yzm.setBackgroundColor(color.orange);
			text_yzm.setTextColor(Color.WHITE);
			text_yzm.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {// ��ʱ������ʾ
			text_yzm.setBackgroundColor(color.gray);
			text_yzm.setClickable(false);
			text_yzm.setTextColor(Color.argb(255, 187, 187, 187));
			text_yzm.setText(millisUntilFinished / 1000 + "����ط�");
		}
	}
	
	//��֤�����
	class CodeReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			
			int status = intent.getIntExtra(Const.KEY_DATA, 0);
			//�ɹ�����
			if (status == Const.STATUS_OK) {
				showShortToastMessage("��鿴�ֻ���֤��");
			}
		}
	}

	

}
