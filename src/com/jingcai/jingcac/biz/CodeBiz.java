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
 * ��֤��ҵ����
 */
public class CodeBiz {
	/**
	 * post����
	 */
	//������֤��
	public static void code(final UserEntity UserEntity){
		
		HttpUtils httpUtils=new HttpUtils(60000);
		RequestParams requestParams=new RequestParams();
		String mobile=UserEntity.getUsername();
		//requestParams.addQueryStringParameter("mobile",mobile);
		requestParams.addBodyParameter("mobile", mobile);
		httpUtils.send(HttpMethod.POST, ConstantUtils.VERIFY, requestParams, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				//����״̬0�ɹ�
				int status = Const.STATUS_OK;
				try {	
						//���ؽ��,�ɴ�ӡ�鿴
						String jsonString = responseInfo.result;
						//�õ�״̬
						JSONObject jsonObject = new JSONObject(jsonString);
						System.out.println("11111"+jsonObject.toString());
						System.out.println("11111"+jsonString.toString());
						String code = jsonObject.getString("code");
						if("0".equals(code)){
							status = Integer.parseInt(code);
						}			
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						// ���㲥
						Intent intent = new Intent(
								Const.ACTION__CODE);
						intent.putExtra(Const.KEY_DATA, status);
						TApplication.instance.sendBroadcast(intent);
					}
			}
		});
	}
	
}
