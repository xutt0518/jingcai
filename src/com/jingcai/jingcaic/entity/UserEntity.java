package com.jingcai.jingcaic.entity;

import java.io.Serializable;

public class UserEntity implements Serializable{
	//�û���
	private String username;
	//����
	private String userpassword;
	//��֤��
	private String verifycode;
	//������
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
