package com.system.entity4Json;

public class Result4Json {

	private Object data;

	private boolean flag;

	private String msg;

	public Result4Json(Boolean flag) {
		this.flag = flag;
	}

	public Result4Json(Boolean flag, Object data) {
		this.flag = flag;
		this.data = data;
	}

	public Result4Json(Boolean flag, String msg, Object data) {
		this.flag = flag;
		this.data = data;
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
