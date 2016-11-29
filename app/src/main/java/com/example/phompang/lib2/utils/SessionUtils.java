package com.example.phompang.lib2.utils;

import com.example.phompang.lib2.model.Staff;

/**
 * Created by phompang on 11/29/2016 AD.
 */
public class SessionUtils {
    private Staff staff;

    private static SessionUtils ourInstance = new SessionUtils();

    public static SessionUtils getInstance() {
        return ourInstance;
    }

    private SessionUtils() {
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
