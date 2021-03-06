package com.example.phompang.lib2.model;

import java.io.Serializable;

/**
 * Created by phompang on 11/27/2016 AD.
 */

public class Staff implements Serializable {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getId() + ": " + getUsername();
    }
}
