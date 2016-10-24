package com.jingcai.jingcaic.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public abstract class BaseFragmentActivity extends FragmentActivity {

	//��ȡ��ǰ̨�ɼ���Activity
	private static Activity mForegroundActivity = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��ϵͳ������ 
		initView();
		initFindViewById();
		initDate();
		setLinstener();
	}
	
	/**
	 * ����ʵ�ִ˳��󷽷�����View����չʾ 
	 */
	protected abstract void initView();
	 /**
     * ��ʼ���ؼ�
     */
    protected abstract void initFindViewById();
    /** 
     * ������Ը�д�˷�����ʼ���¼� (�������¼�)
     */  
    protected  void setLinstener(){  
  
    } 
    
    /**
     * ��ʼ������
     */
    protected abstract void initDate();
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	this.mForegroundActivity = this;
    }
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	this.mForegroundActivity = null;
    }
    
    public static Activity getForegroundActivity() {
		return mForegroundActivity;
	}

}
