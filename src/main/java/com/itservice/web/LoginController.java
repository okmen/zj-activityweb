package com.itservice.web;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itservice.service.IUserService;
import com.utils_max.JsonUtil;

/*
 * 前端用户登陆模块
 */
@Controller
@RequestMapping(value = "/sso/")
public class LoginController {
	/**
	 * 用户模块 service api 服务
	 */
	@Resource(name = "userMgtService")
	private IUserService loginService;
	
	@ResponseBody
	@RequestMapping(value = "login")
	public String download(String username,String pwd) throws Exception {
		return  JsonUtil.objectToJsonStr(loginService.loginReturnResultMsg(username, pwd));
	}
}
