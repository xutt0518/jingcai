package com.jingcai.jingcaic;
import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;
public class TApplication extends Application {
	
	/**
	 * release=true:�������     
	 * release=false:������
	 */
	public static boolean isRelease=false;	
	@Override
	public void onCreate() {
		super.onCreate();
		/* Bugly SDK��ʼ��
	        * ����1�������Ķ���
	        * ����2��APPID��ƽ̨ע��ʱ�õ�,ע���滻�����appId
	        * ����3���Ƿ�������ģʽ������ģʽ�»����'CrashReport'tag����־ (true��������ģʽ)
	        */
	       CrashReport.initCrashReport(getApplicationContext(), "0a04f86363", true);
	}
	
}
