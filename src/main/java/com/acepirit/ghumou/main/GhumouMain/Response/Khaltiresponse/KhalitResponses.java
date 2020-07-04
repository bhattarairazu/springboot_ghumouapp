package com.acepirit.ghumou.main.GhumouMain.Response.Khaltiresponse;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;

@JsonDeserialize
public class KhalitResponses {
    private String idx;
    private double amount;
    private int fee_amount;
    private String reference;
    private boolean refunded;
    private String created_on;
    private String remarks;
    private Type type;
    private State state;
    private UserKhalti user;
    private Merchant merchant;

    public KhalitResponses() {
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFee_amount() {
        return fee_amount;
    }

    public void setFee_amount(int fee_amount) {
        this.fee_amount = fee_amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public UserKhalti getUser() {
        return user;
    }

    public void setUser(UserKhalti user) {
        this.user = user;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
