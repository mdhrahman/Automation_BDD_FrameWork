package com.ui.qa.commons;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class SharedContext {

	public RequestSpecification request;
	public Response response;
	public Map<String, Object> data = new HashMap<String, Object>();
	
}
