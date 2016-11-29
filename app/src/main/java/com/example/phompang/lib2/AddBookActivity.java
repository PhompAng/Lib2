package com.example.phompang.lib2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phompang.lib2.model.AddBookStrategy;
import com.example.phompang.lib2.model.Book;
import com.example.phompang.lib2.model.Strategy;
import com.example.phompang.lib2.model.UpdateBookStrategy;
import com.example.phompang.lib2.utils.SqliteUtils;

public class AddBookActivity extends AppCompatActivity {
    private SqliteUtils mSqliteUtils;
    private Strategy strategy;

    private EditText name;
    private EditText author;

    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        mSqliteUtils = new SqliteUtils(this);

        name  = (EditText) findViewById(R.id.name);
        author = (EditText) findViewById(R.id.author);
        Button add = (Button) findViewById(R.id.add);

        if (i.getExtras() != null) {
            setTitle("Update Book");
            Book s = (Book) i.getSerializableExtra("book");
            id = s.getId();
            name.setText(s.getName());
            author.setText(s.getAuthor());
            add.setText(R.string.update);
            strategy = new UpdateBookStrategy();
        } else {
            setTitle("Add Book");
            id = -1;
            strategy = new AddBookStrategy();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strategy.update(mSqliteUtils, id, name.getText().toString(), author.getText().toString());
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
