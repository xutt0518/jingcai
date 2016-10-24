package com.jingcai.jingcaic.util;
import java.util.Iterator;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
/**
 * ��Т�죺
 * Intent��ת������
 * ���ܣ���ת�����֮ǰActivity,�Լ�Я������
 *
 */
public class IntentUtil {
	// ת����һ��ҳ��
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
	
	// �ַ����Ƿ�Ϊ�գ�ȫ�ǲ��ɼ��ַ����ַ�����Ϊ�ǿգ�
		public static boolean isStrEmpty(Editable poStr) {
			String lsStr = poStr.toString();
			return isStrEmpty(lsStr);
		}

	// �ַ����Ƿ�Ϊ�գ�ȫ�ǲ��ɼ��ַ����ַ�����Ϊ�ǿգ�
	public static boolean isStrEmpty(String psStr) {
		return psStr == null || psStr.trim().length() == 0;
	}
	
	
}
