package com.acepirit.ghumou.main.GhumouMain.Response;

import java.util.List;

import com.acepirit.ghumou.main.GhumouMain.Entity.AddToFavourites;
import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;

public class ListOrderResponse {
	private String msg;
	private int statusCode;
	private long timestamp;
	private List<Orderpackage> results;
	
	public ListOrderResponse() {}
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
	public List<Orderpackage> getResults() {
		return results;
	}
	public void setResults(List<Orderpackage> results) {
		this.results = results;
	}
	
	
}
