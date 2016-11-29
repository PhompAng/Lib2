package com.example.phompang.lib2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.phompang.lib2.model.Book;
import com.example.phompang.lib2.model.Member;
import com.example.phompang.lib2.utils.DateUtils;
import com.example.phompang.lib2.utils.SessionUtils;
import com.example.phompang.lib2.utils.SqliteUtils;

public class AddBorrowActivity extends AppCompatActivity {

    private SqliteUtils mSqliteUtils;
    private Spinner book;
    private Spinner member;
    private ArrayAdapter<Book> bookArrayAdapter;
    private ArrayAdapter<Member> memberArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_borrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Borrow Book");

        mSqliteUtils = new SqliteUtils(this);

        book = (Spinner) findViewById(R.id.book);
        member = (Spinner) findViewById(R.id.member);

        bookArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSqliteUtils.getBooks());
        memberArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSqliteUtils.getMembers());

        book.setAdapter(bookArrayAdapter);
        member.setAdapter(memberArrayAdapter);

        findViewById(R.id.borrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book b = (Book) book.getSelectedItem();
                Member m = (Member) member.getSelectedItem();
//                mSqliteUtils.addBorrow(b.getId(), m.getId(), SessionUtils.getInstance().getStaff().getId(), DateUtils.toString(new Date()), DateUtils.toString(DateUtils.nextSeven()));
                mSqliteUtils.addBorrow(b.getId(), m.getId(), SessionUtils.getInstance().getStaff().getId(), DateUtils.toString(DateUtils.lastTen()), DateUtils.toString(DateUtils.nextSeven(DateUtils.lastTen())));
                setResult(1);
                finish();
            }
        });
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
