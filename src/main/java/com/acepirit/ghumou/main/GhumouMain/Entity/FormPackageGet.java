package com.acepirit.ghumou.main.GhumouMain.Entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

public class FormPackageGet {
    private int id;
    private String packageTitle;

    private String packageDescription;

    private int offers;

    private int regularPrice;

    private int salePrice;

    private String duration;

    private String packageSellar;

    private MultipartFile thumnail;

    private String packageType;

    private MultipartFile[] images;

    private String icons;

    private Inclusions inclusions;

    private Exclusions exclusions;

    private Itenarys itenarys;

    public String getPackageTitle() {
        return packageTitle;
    }

    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public int getOffers() {
        return offers;
    }

    public void setOffers(int offers) {
        this.offers = offers;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(int regularPrice) {
        this.regularPrice = regularPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPackageSellar() {
        return packageSellar;
    }

    public void setPackageSellar(String packageSellar) {
        this.packageSellar = packageSellar;
    }

    public MultipartFile getThumnail() {
        return thumnail;
    }

    public void setThumnail(MultipartFile thumnail) {
        this.thumnail = thumnail;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public Inclusions getInclusions() {
        return inclusions;
    }

    public void setInclusions(Inclusions inclusions) {
        this.inclusions = inclusions;
    }

    public Exclusions getExclusions() {
        return exclusions;
    }

    public void setExclusions(Exclusions exclusions) {
        this.exclusions = exclusions;
    }

    public Itenarys getItenarys() {
        return itenarys;
    }

    public void setItenarys(Itenarys itenarys) {
        this.itenarys = itenarys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }
}
