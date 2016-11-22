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
 * ע��ҵ����
 */
public class RegisterBiz {
	
	public void register(final UserEntity UserEntity){
		/**
		 * post����
		 */
		
		 HttpUtils httpUtils=new HttpUtils(60000);
		 
		 RequestParams requestParams=new RequestParams();
		//�ֻ���
		 String mobile=UserEntity.getUsername();
		//��֤��
		 String verify_code=UserEntity.getVerifycode();
		//����
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
							//���ؽ��,�ɴ�ӡ�鿴
							String jsonString = responseInfo.result;
							//�õ�״̬
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
