package com.acepirit.ghumou.main.GhumouMain.Entity;

public class ComparisonGet {
    private int userId;
    private int packageId;

    public ComparisonGet(int userId, int packageId) {
        this.userId = userId;
        this.packageId = packageId;
    }

    public ComparisonGet() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
