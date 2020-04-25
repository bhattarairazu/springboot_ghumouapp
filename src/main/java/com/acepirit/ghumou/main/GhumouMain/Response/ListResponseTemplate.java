package com.acepirit.ghumou.main.GhumouMain.Response;

import java.util.List;

import com.acepirit.ghumou.main.GhumouMain.Entity.User;

public class ListResponseTemplate {
	private String msg;
	private int statusCode;
	private long timestamp;
	private List<User> results;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public List<User> getResults() {
		return results;
	}
	public void setResults(List<User> results) {
		this.results = results;
	}
	
	
	
}
