package com.jingcai.jingcaic.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public abstract class BaseFragmentActivity extends FragmentActivity {

	//获取到前台可见的Activity
	private static Activity mForegroundActivity = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉系统标题栏 
		initView();
		initFindViewById();
		initDate();
		setLinstener();
	}
	
	/**
	 * 子类实现此抽象方法返回View进行展示 
	 */
	protected abstract void initView();
	 /**
     * 初始化控件
     */
    protected abstract void initFindViewById();
    /** 
     * 子类可以复写此方法初始化事件 (例如点击事件)
     */  
    protected  void setLinstener(){  
  
    } 
    
    /**
     * 初始化数据
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
