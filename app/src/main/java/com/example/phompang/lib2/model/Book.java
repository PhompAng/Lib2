package com.example.phompang.lib2.model;

import java.io.Serializable;

/**
 * Created by phompang on 11/28/2016 AD.
 */

public class Book implements Serializable {
    private int id;
    private String name;
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return getId() + ": " + getName() + " | " + getAuthor();
    }
}
