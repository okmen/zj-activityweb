package com.itservice.service;

import com.itservice.entity.UUsers;
import com.itservice.result.ResultMsg;

public interface IUserService {

	/**
	 * 用户注册
	 * @author max
	 * @date:   2018年8月1日
	 * @Desc :
	 * @param user
	 * @return
	 */
	public ResultMsg register(UUsers user);
	
}
