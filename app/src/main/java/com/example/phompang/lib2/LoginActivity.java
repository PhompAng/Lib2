package com.example.phompang.lib2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phompang.lib2.model.Staff;
import com.example.phompang.lib2.utils.SessionUtils;
import com.example.phompang.lib2.utils.SqliteUtils;

public class LoginActivity extends AppCompatActivity {

    private SqliteUtils mSqliteUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSqliteUtils = new SqliteUtils(this);
//        mSqliteUtils.addStaff("admin", "admin");

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();

                Staff s = mSqliteUtils.login(username, password);
                if (s != null) {
                    SessionUtils.getInstance().setStaff(s);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Credential", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
