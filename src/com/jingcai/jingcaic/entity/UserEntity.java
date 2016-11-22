package com.jingcai.jingcaic.entity;

import java.io.Serializable;

public class UserEntity implements Serializable{
	//用户名
	private String username;
	//密码
	private String userpassword;
	//验证码
	private String verifycode;
	//邀请码
	private String invitecode;
	
	
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	
	
	
}
