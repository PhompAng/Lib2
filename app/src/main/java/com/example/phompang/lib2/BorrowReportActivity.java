package com.example.phompang.lib2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phompang.lib2.model.Borrow;
import com.example.phompang.lib2.model.Member;
import com.example.phompang.lib2.utils.SqliteUtils;

import java.util.Date;

public class BorrowReportActivity extends AppCompatActivity {

    private SqliteUtils mSqliteUtils;

    private ArrayAdapter<Member> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_report);
        setTitle("Overdue Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSqliteUtils = new SqliteUtils(this);

        for (Borrow b: mSqliteUtils.getBorrows()) {
            if ((new Date()).after(b.getEnd())) {
                mSqliteUtils.returnBook(b.getId(), "OVERDUE");
            }
        }
        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSqliteUtils.getOverdue());
        adapter.setNotifyOnChange(true);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
