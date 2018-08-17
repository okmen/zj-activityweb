package com.itservice.vo;

import java.io.Serializable;

public class UserLoginSuccessInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户登陆验证accessToken
	 */
	private String accessToken;
	/**
	 * 昵称
	 */
	private String userName;
	/***
	 * 用户手机号
	 */
	private String phone;
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
