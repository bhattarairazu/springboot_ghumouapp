package com.acepirit.ghumou.main.GhumouMain.Response;

import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;

import java.util.List;

public class SimilarPackagesResponse {
    private String msg;
    private int statusCode;
    private long timestamp;
    private Packagess results;
    private List<Packagess> similarPackages;

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

    public Packagess getResults() {
        return results;
    }

    public void setResults(Packagess results) {
        this.results = results;
    }

    public List<Packagess> getSimilarPackages() {
        return similarPackages;
    }

    public void setSimilarPackages(List<Packagess> similarPackages) {
        this.similarPackages = similarPackages;
    }
}
