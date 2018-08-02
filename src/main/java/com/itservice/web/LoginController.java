package com.itservice.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itservice.entity.UUser;
import com.itservice.entity.redistags.RedisUser;
import com.itservice.mapper.UUserMapper;
import com.itservice.result.ResultMsg;
import com.itservice.result.enums.ResultStatusEnums;
import com.itservice.vo_param.user.UserRegisterParam;
import com.utils_max.JsonUtil;
import com.utils_max.ParseUtils;
import com.utils_max.encrypt.MD5Encrypt;
import com.utils_max.redis.RedisUtil;


@Controller
@RequestMapping(value = "/sso/")
public class LoginController {

	@Autowired
	private UUserMapper usermapper;
	
	@ResponseBody
	@RequestMapping(value = "login")
	public String download(String username,String pwd) throws Exception {
		ResultMsg result=new ResultMsg();
		UserRegisterParam userInfo= (UserRegisterParam)RedisUtil.getObject(RedisUser.user_base+username);
		if(!ParseUtils.isEmpty(userInfo)){
			UUser user= usermapper.selectByPrimaryKey(username);
//			
			if(MD5Encrypt.encrypt(pwd).equals(userInfo.getPwd())){
				result.setStatus(ResultStatusEnums.success);
				result.setMsg("登陆成功！");
			}else {
				result.setStatus(ResultStatusEnums.sysError);
				result.setMsg("密码错误！");
			}
		}else {
			result.setStatus(ResultStatusEnums.sysError);
			result.setMsg("用户不存在！");
		}
		return  JsonUtil.objectToJsonStr(result);
	}
}
