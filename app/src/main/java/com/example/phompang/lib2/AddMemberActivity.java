package com.example.phompang.lib2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phompang.lib2.model.AddMemberStrategy;
import com.example.phompang.lib2.model.Member;
import com.example.phompang.lib2.model.Strategy;
import com.example.phompang.lib2.model.UpdateMemberStrategy;
import com.example.phompang.lib2.utils.SqliteUtils;

public class AddMemberActivity extends AppCompatActivity {

    private SqliteUtils mSqliteUtils;
    private Strategy strategy;

    private EditText fname;
    private EditText lname;

    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        mSqliteUtils = new SqliteUtils(this);

        fname  = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        Button add = (Button) findViewById(R.id.add);

        if (i.getExtras() != null) {
            setTitle("Update Member");
            Member s = (Member) i.getSerializableExtra("member");
            id = s.getId();
            fname.setText(s.getFirstname());
            lname.setText(s.getLastname());
            add.setText(R.string.update);
            strategy = new UpdateMemberStrategy();
        } else {
            setTitle("Add Member");
            id = -1;
            strategy = new AddMemberStrategy();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strategy.update(mSqliteUtils, id, fname.getText().toString(), lname.getText().toString());
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
