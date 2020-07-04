package com.acepirit.ghumou.main.GhumouMain.Entity;

import org.springframework.web.multipart.MultipartFile;

public class FormGetOurServices {
    private String name;

    private String description;

    private MultipartFile file;

    private String type;

    public FormGetOurServices() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
