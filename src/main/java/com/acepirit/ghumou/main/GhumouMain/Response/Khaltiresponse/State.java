package com.acepirit.ghumou.main.GhumouMain.Response.Khaltiresponse;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class State {
    private String idx;

    private String name;

    private String template;

    public State() {
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
