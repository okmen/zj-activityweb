package com.itservice.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itservice.entity.UUser;
import com.itservice.mapper.UUserMapper;
import com.itservice.result.ResultMsg;
import com.itservice.result.enums.ResultStatusEnums;
import com.itservice.service.IUserService;
import com.itservice.vo.UserLoginSuccessInfo;
import com.itservice.vo.param.UserRegisterParam;
import com.utils_max.ParseUtils;
import com.utils_max.encrypt.MD5Encrypt;
import com.utils_max.redis.RedisUtil;

@Service("userMgtService")
public class UserServiceImpl implements IUserService{
	//用户信息处理 dao
	@Autowired
	private UUserMapper usermapper;
	
	public ResultMsg  register(UserRegisterParam user){
		ResultMsg result=new ResultMsg();
		if(!ParseUtils.isEmpty(user)){
			UUser userInfo=usermapper.getUserByUsername(user.getUserName());
			if(!ParseUtils.isEmpty(userInfo)){
				result.setStatus(ResultStatusEnums.sysError);
				result.setMsg("用户已经存在");
				return result;
			}
			 userInfo=new UUser();
			 userInfo.setUsername(user.getUserName());
			 userInfo.setPwd(MD5Encrypt.encrypt(user.getPwd()));
			 userInfo.setPhone(user.getMobilephone());
			 if(usermapper.insert(userInfo)>0){
				 result.setStatus(ResultStatusEnums.success);
				 result.setMsg("注册成功");
			 }else{
				 result.setStatus(ResultStatusEnums.sysError);
				 result.setMsg("注册失败");
			 }
		}
		return result;
	}
	
	public ResultMsg loginReturnResultMsg(String username,String pwd){
		ResultMsg result=new ResultMsg();
		UUser user= usermapper.getUserByUsername(username);
		if(!ParseUtils.isEmpty(user)){
			if(!MD5Encrypt.encrypt(pwd).equals(user.getPwd())){
				result.setStatus(ResultStatusEnums.sysError);
				result.setMsg("密码错误！");
			}else{
				result.setStatus(ResultStatusEnums.success);
				//登陆成功 返回给前端的model
				UserLoginSuccessInfo resultModel=new UserLoginSuccessInfo();
				//用户验证token
				String accessToken=UUID.randomUUID().toString().replace("-", "").toLowerCase();
				resultModel.setAccessToken(accessToken);
				resultModel.setUserName(user.getUsername());
				resultModel.setPhone(user.getPhone());
				
				// 通过redis缓存用户登陆信息 2小时过期
				RedisUtil.setObject(accessToken, resultModel, 7200);
				result.setBackResult(resultModel);
			}
		}else{
			result.setStatus(ResultStatusEnums.sysError);
			result.setMsg("用户不存在！");
		}
		return result;
	}
}
