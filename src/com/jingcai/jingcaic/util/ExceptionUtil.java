package com.jingcai.jingcaic.util;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.jingcai.jingcaic.TApplication;
import com.tencent.bugly.crashreport.CrashReport;
/**
 * �쳣ͳһ����
 * @author tarena
 *
 */
public class ExceptionUtil {
	public static void handleException(Throwable e)
	{
		if (TApplication.isRelease==false)//������
		{
			//����쳣��Ϣ
			e.printStackTrace();
			
		    //���쳣��Ϣ����ַ���
			StringWriter stringWriter=new StringWriter();
			PrintWriter printWriter=new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			String string=stringWriter.toString();
		    LogUtil.i("�쳣", string);
		}
		if(TApplication.isRelease==true)//����
		{	
			//���쳣��Ϣ���͸�bugly
			CrashReport.postCatchedException(e);	
		}
		
	}

}
