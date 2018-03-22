package com.target.caseStudy.myRetail.utils;

import org.json.JSONObject;

public class ClientResponseEntity {
	
	private int responseStatus;
	private JSONObject responseObject;
	
	public JSONObject getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(JSONObject responseObject) {
		this.responseObject = responseObject;
	}
	public int getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

}
