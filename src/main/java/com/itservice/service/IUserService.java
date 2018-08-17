package com.itservice.service;

import com.itservice.result.ResultMsg;
import com.itservice.vo.param.UserRegisterParam;

public interface IUserService {

	/**
	 * 用户注册
	 * @author max
	 * @date:   ResultMsg
	 * @Desc :
	 * @param user
	 * @return
	 */
	public ResultMsg register(UserRegisterParam user);
	
	/**
	 * 用户登陆
	 * @author max
	 * @date:   2018年8月17日
	 * @Desc :
	 * @param username
	 * @param pwd
	 * @return
	 */
	public ResultMsg loginReturnResultMsg(String username,String pwd);
}
