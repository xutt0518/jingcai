package com.jingcai.jingcaic.util;
import java.util.Iterator;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
/**
 * 何孝天：
 * Intent跳转工具类
 * 功能：跳转后控制之前Activity,以及携带参数
 *
 */
public class IntentUtil {
	// 转向另一个页面
	public static void gotoActivity(Activity poFrom, Class<?> poTo,
			boolean pbFinish, Map<String, String> pmExtra) {
		Intent loIntent = new Intent(poFrom, poTo);
		if (pmExtra != null && !pmExtra.isEmpty()) {
			Iterator<String> loKeyIt = pmExtra.keySet().iterator();
			while (loKeyIt.hasNext()) {
				String lsKey = loKeyIt.next();
				loIntent.putExtra(lsKey, pmExtra.get(lsKey));
			}
		}
		if (pbFinish)
			poFrom.finish();
		poFrom.startActivity(loIntent);
	}
	
	// 字符串是否为空（全是不可见字符的字符串认为是空）
		public static boolean isStrEmpty(Editable poStr) {
			String lsStr = poStr.toString();
			return isStrEmpty(lsStr);
		}

	// 字符串是否为空（全是不可见字符的字符串认为是空）
	public static boolean isStrEmpty(String psStr) {
		return psStr == null || psStr.trim().length() == 0;
	}
	
	
}
