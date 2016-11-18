package com.jingcai.jingcaic.util;

public class ConstantUtils {
   /**
    * 前缀
    */
	public final static String GLOBAL_URL="http://121.42.189.14:8082/Api";
	/**
	 * 获取验证码
	 */
	public final static String VERIFY=GLOBAL_URL+"/UserAPI/c_get_verifycode";
	/**
	 * 用户注册
	 */
	public final static String REGISTER=GLOBAL_URL+"/UserAPI/c_user_register";
    /**
     * 用户登陆
     */
	public final static String Log_in=GLOBAL_URL+"/UserAPI/c_user_login";
	/**
	 * 找回密码
	 */
	public final static String FIND_PWD=GLOBAL_URL+"method=Vegetable_user_findPassword";
	/**
	 * 退出登陆
	 */
	public final static String LOG_OUT=GLOBAL_URL+"/UserAPI/c_user_logout";
}
