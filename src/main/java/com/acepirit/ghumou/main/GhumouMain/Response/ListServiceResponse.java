package com.acepirit.ghumou.main.GhumouMain.Response;

import com.acepirit.ghumou.main.GhumouMain.Entity.OurServices;

import java.util.List;

public class ListServiceResponse {
    private String msg;
    private int statusCode;
    private long timestamp;
    private List<OurServices> results;

    public ListServiceResponse() {
    }

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

    public List<OurServices> getResults() {
        return results;
    }

    public void setResults(List<OurServices> results) {
        this.results = results;
    }
}
