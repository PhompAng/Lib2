package com.example.phompang.lib2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phompang.lib2.model.Book;
import com.example.phompang.lib2.utils.SqliteUtils;

public class BookActivity extends AppCompatActivity {
    private SqliteUtils mSqliteUtils;
    private ArrayAdapter<Book> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        setTitle("Book");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSqliteUtils = new SqliteUtils(this);

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSqliteUtils.getBooks());
        adapter.setNotifyOnChange(true);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book s = (Book) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(BookActivity.this, AddBookActivity.class);
                intent.putExtra("book", s);
                startActivityForResult(intent, 2);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Book s = (Book) adapterView.getItemAtPosition(i);
                AlertDialog.Builder dialog = new AlertDialog.Builder(BookActivity.this);
                dialog.setMessage("delete ?");
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mSqliteUtils.deleteBook(s.getId());
                        adapter.remove(s);
                        adapter.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
                return true;
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookActivity.this, AddBookActivity.class);
                startActivityForResult(intent, 2);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("aaa", requestCode + "");
        if (requestCode == 2) {
            adapter.clear();
            adapter.addAll(mSqliteUtils.getBooks());
            adapter.notifyDataSetChanged();
        }
    }
}
