package com.example.phompang.lib2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phompang.lib2.model.AddStaffStrategy;
import com.example.phompang.lib2.model.Staff;
import com.example.phompang.lib2.model.Strategy;
import com.example.phompang.lib2.model.UpdateStaffStrategy;
import com.example.phompang.lib2.utils.SqliteUtils;

public class AddStaffActivity extends AppCompatActivity {

    private SqliteUtils mSqliteUtils;
    private Strategy strategy;

    private EditText username;
    private EditText password;

    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        mSqliteUtils = new SqliteUtils(this);

        username  = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button add = (Button) findViewById(R.id.add);

        if (i.getExtras() != null) {
            setTitle("Update Staff");
            Staff s = (Staff) i.getSerializableExtra("staff");
            id = s.getId();
            username.setText(s.getUsername());
            password.setText(s.getPassword());
            add.setText(R.string.update);
            strategy = new UpdateStaffStrategy();
        } else {
            setTitle("Add Staff");
            id = -1;
            strategy = new AddStaffStrategy();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strategy.update(mSqliteUtils, id, username.getText().toString(), password.getText().toString());
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
