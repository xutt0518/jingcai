package com.jingcai.jingcaic.activity;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.util.MyActivityManager;
import com.jingcai.jingcaic.util.WaitDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class BaseActivity extends Activity{
	protected ViewGroup mainBody; // ������ʾ
    WaitDialog waitDialog; // �ȴ��M�ȿ�
	protected boolean isTemplate = false; // �Ƿ�ʹ��ģ��
	private View titleBar;// ������
	protected TextView titleView; // ����
	protected ImageButton templateButtonLeft; // ģ��������Button,��ʹ��ģ��ʱ��������ʾ;
	protected ImageButton templateButtonRight; // ģ��������Button,��ʹ��ģ��ʱ��������ʾ;
	protected TextView templateTextViewRight; // ģ���Ҳ�TextView
	private View reLoadView;// ���¼��ص� ����
	private MyActivityManager myActivityManager;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.template);
		mainBody = (ViewGroup) findViewById(R.id.view_mainBody);
		reLoadView = findViewById(R.id.reLoadView);
		reLoadView.setVisibility(View.GONE);
		titleBar = findViewById(R.id.titleBar);
		templateButtonLeft = (ImageButton) findViewById(R.id.title_but_left);
		templateButtonRight = (ImageButton) findViewById(R.id.title_but_right);
		templateTextViewRight = (TextView) findViewById(R.id.title_tv_right);

		waitDialog = new WaitDialog(this);
		templateButtonLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		titleView = (TextView) findViewById(R.id.title_text);
		if (!isTemplate) {// ��ʹ��ģ��Ͱѱ�����ȥ��
			titleBar.setVisibility(View.GONE);
		}
		// ��Activityѹ��ջ�й�������
		myActivityManager = MyActivityManager.getInstance();
		myActivityManager.pushOneActivity(this);
		
	}

	@Override
	public void setContentView(int layoutResID) {
		if (layoutResID == R.layout.template) {
			super.setContentView(layoutResID);
		} else {
			mainBody.removeAllViews();
			mainBody.addView(this.getLayoutInflater()
					.inflate(layoutResID, null));
		}
	}

	@Override
	public void setContentView(View view) {
		mainBody.removeAllViews();
		mainBody.addView(view);
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		mainBody.removeAllViews();
		mainBody.addView(view, params);
	}

	// ��ʾToast��Ϣ
	public void showShortToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ����
	 * 
	 * @param view
	 */
	public void back(View view) {
		finish();
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ����ʱ���쳣Activity
		myActivityManager.pushOneActivity(this);

		if (waitDialog != null) {
			if (waitDialog.isShowing()) {
				waitDialog.dismiss();
			}
		}
		System.gc();
	}
}
