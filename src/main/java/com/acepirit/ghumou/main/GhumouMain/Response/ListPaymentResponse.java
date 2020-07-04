package com.acepirit.ghumou.main.GhumouMain.Response;

import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;

import java.util.List;

public class ListPaymentResponse {
    private String msg;
    private int statusCode;
    private long timestamp;
    private List<Payment> results;

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

    public List<Payment> getResults() {
        return results;
    }

    public void setResults(List<Payment> results) {
        this.results = results;
    }
}
