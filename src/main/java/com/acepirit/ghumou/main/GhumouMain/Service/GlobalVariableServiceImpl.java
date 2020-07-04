package com.acepirit.ghumou.main.GhumouMain.Service;

import org.springframework.stereotype.Service;

@Service
public class GlobalVariableServiceImpl implements GlobalVariableService {
    String uname = null;
    @Override
    public String getUsername() {
        return this.uname;
    }

    @Override
    public void setUsername(String username) {
        this.uname = username;

    }
}
