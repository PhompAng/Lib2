package com.example.phompang.lib2.model;

import com.example.phompang.lib2.utils.SqliteUtils;

/**
 * Created by phompang on 11/28/2016 AD.
 */

public class UpdateBookStrategy implements Strategy {
    @Override
    public void update(SqliteUtils sqliteUtils, int id, String text1, String text2) {
        sqliteUtils.updateBook(id, text1, text2);
    }
}
