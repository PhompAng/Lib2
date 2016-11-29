package com.example.phompang.lib2.model;

import java.io.Serializable;

/**
 * Created by phompang on 11/27/2016 AD.
 */

public class Member implements Serializable {
    private int id;
    private String firstname;
    private String lastname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return getId() + ": " + getFirstname() + " " + getLastname();
    }
}
