package com.itservice.result;

import com.itservice.result.enums.ResultStatusEnums;

public class ResultMsg {

	private ResultStatusEnums status;
	private Object backResult;
	private String msg;
	
	
	
	public ResultStatusEnums getStatus() {
		return status;
	}
	public void setStatus(ResultStatusEnums status) {
		this.status = status;
	}
	public Object getBackResult() {
		return backResult;
	}
	public void setBackResult(Object backResult) {
		this.backResult = backResult;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
