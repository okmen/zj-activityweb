package com.itservice.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itservice.result.ResultMsg;
import com.itservice.result.enums.ResultStatusEnums;
import com.itservice.service.IUserService;
import com.itservice.vo.param.UserRegisterParam;
import com.utils_max.JsonUtil;
import com.utils_max.ParseUtils;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	/**
	 * 用户模块 service api 服务
	 */
	@Resource(name = "userMgtService")
	private IUserService loginService;
	
	/**
	 * 用户注册
	 * @author max
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
			//调取用户注册服务
			result=loginService.register(registerParam);
		}else{
			result.setStatus(ResultStatusEnums.paramError);
			result.setMsg("参数错误");
			return JsonUtil.objectToJsonStr(result);
		}
		return  JsonUtil.objectToJsonStr(result);
	}
}
