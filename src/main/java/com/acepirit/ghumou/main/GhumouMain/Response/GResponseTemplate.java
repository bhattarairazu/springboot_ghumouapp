package com.acepirit.ghumou.main.GhumouMain.Response;

public class GResponseTemplate {
	private String msg;
	private int statusCode;
	private long timestamp;
	private Object results;
	
	public GResponseTemplate() {}
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
	public Object getResults() {
		return results;
	}
	public void setResults(Object results) {
		this.results = results;
	}
	
	
	
	

}
