package com.jingcai.jingcaic.util;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.jingcai.jingcaic.TApplication;
import com.tencent.bugly.crashreport.CrashReport;
/**
 * 异常统一处理
 * @author tarena
 *
 */
public class ExceptionUtil {
	public static void handleException(Throwable e)
	{
		if (TApplication.isRelease==false)//开发中
		{
			//输出异常信息
			e.printStackTrace();
			
		    //把异常信息变成字符串
			StringWriter stringWriter=new StringWriter();
			PrintWriter printWriter=new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			String string=stringWriter.toString();
		    LogUtil.i("异常", string);
		}
		if(TApplication.isRelease==true)//上线
		{	
			//讲异常信息发送给bugly
			CrashReport.postCatchedException(e);	
		}
		
	}

}
