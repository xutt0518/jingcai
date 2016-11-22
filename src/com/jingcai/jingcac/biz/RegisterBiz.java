package com.jingcai.jingcac.biz;

import org.json.JSONObject;

import android.content.Intent;

import com.jingcai.jingcaic.TApplication;
import com.jingcai.jingcaic.entity.UserEntity;
import com.jingcai.jingcaic.util.Const;
import com.jingcai.jingcaic.util.ConstantUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 注册业务类
 */
public class RegisterBiz {
	
	public void register(final UserEntity UserEntity){
		/**
		 * post请求
		 */
		
		 HttpUtils httpUtils=new HttpUtils(60000);
		 
		 RequestParams requestParams=new RequestParams();
		//手机号
		 String mobile=UserEntity.getUsername();
		//验证码
		 String verify_code=UserEntity.getVerifycode();
		//密码
		 String password=UserEntity.getUserpassword();
		 requestParams.addBodyParameter("mobile", mobile);
		 requestParams.addBodyParameter("verify_code", verify_code);
		 requestParams.addBodyParameter("password", password);
		 requestParams.addBodyParameter("invite_code", "");
		 
		 httpUtils.send(HttpMethod.POST, ConstantUtils.REGISTER, requestParams, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException error, String msg) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
			
					try {	
							//返回结果,可打印查看
							String jsonString = responseInfo.result;
							//得到状态
							JSONObject jsonObject = new JSONObject(jsonString);
							System.out.println("11111"+jsonObject.toString());
							System.out.println("11111"+jsonString.toString());
						
							
						} catch (Exception e) {
							
							e.printStackTrace();
						
						}finally{
					
						}
				}
			});
		}
}
