package com.jingcai.jingcaic.util;
import com.jingcai.jingcaic.TApplication;

import android.util.Log;
/**
 * ͳһ������־
 * baidu android log4j
 * @author tarena
 * 
 */
public class LogUtil {
	public static void i(String tag, Object msg) {
		if (TApplication.isRelease==true)
		{
			return;
		}
		if(TApplication.isRelease==false)
		{
			Log.i(tag, String.valueOf(msg));
		}
	}
}
