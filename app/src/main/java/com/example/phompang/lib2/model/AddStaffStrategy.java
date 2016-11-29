package com.example.phompang.lib2.model;

import com.example.phompang.lib2.utils.SqliteUtils;

/**
 * Created by phompang on 11/27/2016 AD.
 */

public class AddStaffStrategy implements Strategy {
    @Override
    public void update(SqliteUtils sqliteUtils, int id, String username, String password) {
        sqliteUtils.addStaff(username, password);
    }
}
