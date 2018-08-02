package com.itservice.web;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.itservice.entity.redistags.RedisUser;
import com.itservice.result.ResultMsg;
import com.itservice.result.enums.ResultStatusEnums;
import com.itservice.vo_param.user.UserRegisterParam;
import com.utils_max.JsonUtil;
import com.utils_max.ParseUtils;
import com.utils_max.encrypt.MD5Encrypt;
import com.utils_max.redis.RedisUtil;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	/**
	 * 用户注册
	 * @author lvxl
	 * @date:   2018年8月1日
	 * @Desc :
	 * @param registerParamJson
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "register")
	public String download(String registerParamJson) throws Exception {
		
		ResultMsg result=new ResultMsg();
		UserRegisterParam registerParam=(UserRegisterParam)JsonUtil.jsonStrToObject(registerParamJson,UserRegisterParam.class);
	
		if(registerParam!=null){
			if(ParseUtils.isEmpty(registerParam.getUserName())||ParseUtils.isEmpty(registerParam.getPwd())||ParseUtils.isEmpty(registerParam.getMobilephone())){
				result.setStatus(ResultStatusEnums.paramError);
				result.setMsg("参数不全");
				return JsonUtil.objectToJsonStr(result);
			}
			UserRegisterParam userJson= (UserRegisterParam)RedisUtil.getObject(RedisUser.user_base+registerParam.getUserName());
			if(ParseUtils.isEmpty(userJson)){
				registerParam.setPwd(MD5Encrypt.encrypt(registerParam.getPwd())); 
				RedisUtil.setObject(RedisUser.user_base+registerParam.getUserName(), registerParam);
				result.setStatus(ResultStatusEnums.success);
				result.setBackResult(registerParam);
				result.setMsg("成功");  
			}else{
				result.setStatus(ResultStatusEnums.sysError);
				result.setMsg("已经存在的用户名"); 
			}
		}else{
			result.setStatus(ResultStatusEnums.paramError);
			result.setMsg("参数错误");
			return JsonUtil.objectToJsonStr(result);
		}
		return  JsonUtil.objectToJsonStr(result);
	}
}
