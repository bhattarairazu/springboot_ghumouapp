package com.acepirit.ghumou.main.GhumouMain.Response;

import com.acepirit.ghumou.main.GhumouMain.Entity.ComparisonPackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;

import java.util.List;

public class ListComparisonResponse {
    private String msg;
    private int statusCode;
    private long timestamp;
    private List<ComparisonPackage> results;

    public ListComparisonResponse() {
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

    public List<ComparisonPackage> getResults() {
        return results;
    }

    public void setResults(List<ComparisonPackage> results) {
        this.results = results;
    }
}
