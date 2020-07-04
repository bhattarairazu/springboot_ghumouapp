package com.acepirit.ghumou.main.GhumouMain.Response;

import com.acepirit.ghumou.main.GhumouMain.Entity.Review;

import java.util.List;

public class ListSellarNameResponse {
    private String msg;
    private int statusCode;
    private long timestamp;
    private List<String> results;

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

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
