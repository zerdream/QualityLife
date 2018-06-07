package com.yinao.qualityLife.bean;
public class JsonResult {

	private String status = null;

	private Object result = null;

	public JsonResult status(String status) {
		this.status = status;
		return this;
	}

	public void setResult(Object o) {
		this.result = o;
		
	}

	public void setStatus(String string) {
		this.status = string;
		
	}

	public void setResult(String o) {
		this.result = o;
		
	}

	// Getter Setter
}