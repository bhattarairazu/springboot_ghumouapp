package com.acepirit.ghumou.main.GhumouMain.Response;

import com.acepirit.ghumou.main.GhumouMain.Entity.Orderpackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Payment;

import java.util.List;

public class OrderWithPaymentResponse {
    private String msg;
    private int statusCode;
    private long timestamp;
    private Orderpackage results;
    private List<Payment> payments;

    public OrderWithPaymentResponse(){}

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

    public Orderpackage getResults() {
        return results;
    }

    public void setResults(Orderpackage results) {
        this.results = results;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
