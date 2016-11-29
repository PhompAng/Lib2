package com.example.phompang.lib2.model;

import com.example.phompang.lib2.utils.SqliteUtils;

/**
 * Created by phompang on 11/27/2016 AD.
 */

public interface Strategy {
    void update(SqliteUtils sqliteUtils, int id, String text1, String text2);
}
