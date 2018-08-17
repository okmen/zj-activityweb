package com.itservice.vo.param;

import java.io.Serializable;

public class UserRegisterParam  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String pwd;
	private String pwdConfirm;
	private String mobilephone;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwdConfirm() {
		return pwdConfirm;
	}
	public void setPwdConfirm(String pwdConfirm) {
		this.pwdConfirm = pwdConfirm;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	
}
