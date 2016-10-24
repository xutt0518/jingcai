package com.jingcai.jingcaic;
import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;
public class TApplication extends Application {
	
	/**
	 * release=true:软件发布     
	 * release=false:开发中
	 */
	public static boolean isRelease=false;	
	@Override
	public void onCreate() {
		super.onCreate();
		/* Bugly SDK初始化
	        * 参数1：上下文对象
	        * 参数2：APPID，平台注册时得到,注意替换成你的appId
	        * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志 (true开发调试模式)
	        */
	       CrashReport.initCrashReport(getApplicationContext(), "0a04f86363", true);
	}
	
}
