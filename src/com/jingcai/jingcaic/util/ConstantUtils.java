package com.jingcai.jingcaic.util;

public class ConstantUtils {
   /**
    * ǰ׺
    */
	public final static String GLOBAL_URL="http://121.42.189.14:8082/Api";
	/**
	 * ��ȡ��֤��
	 */
	public final static String VERIFY=GLOBAL_URL+"/UserAPI/c_get_verifycode";
	/**
	 * �û�ע��
	 */
	public final static String REGISTER=GLOBAL_URL+"/UserAPI/c_user_register";
    /**
     * �û���½
     */
	public final static String Log_in=GLOBAL_URL+"/UserAPI/c_user_login";
	/**
	 * �һ�����
	 */
	public final static String FIND_PWD=GLOBAL_URL+"method=Vegetable_user_findPassword";
	/**
	 * �˳���½
	 */
	public final static String LOG_OUT=GLOBAL_URL+"/UserAPI/c_user_logout";
}
