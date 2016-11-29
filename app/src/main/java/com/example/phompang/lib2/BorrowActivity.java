package com.example.phompang.lib2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phompang.lib2.model.Borrow;
import com.example.phompang.lib2.utils.SqliteUtils;

import java.util.Date;

public class BorrowActivity extends AppCompatActivity {

    private SqliteUtils mSqliteUtils;

    private ArrayAdapter<Borrow> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        setTitle("Borrow List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSqliteUtils = new SqliteUtils(this);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String date = sdf.format(new Date());
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.DATE, 7);  // number of days to add
//        String end = sdf.format(c.getTime());
        //mSqliteUtils.addBorrow(1,1,1,date,end);

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSqliteUtils.getBorrows());
        adapter.setNotifyOnChange(true);
        listView.setAdapter(adapter);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(BorrowActivity.this, AddBorrowActivity.class), 2);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Borrow s = (Borrow) adapterView.getItemAtPosition(i);
                AlertDialog.Builder dialog = new AlertDialog.Builder(BorrowActivity.this);
                dialog.setMessage("Return ?");
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.setPositiveButton("Return", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String status = (new Date()).after(s.getEnd()) ? "OVERDUE":"RETURN";
                        mSqliteUtils.returnBook(s.getId(), status);
                        adapter.remove(s);
                        adapter.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
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

        if (requestCode == 2) {
            adapter.clear();
            adapter.addAll(mSqliteUtils.getBorrows());
            adapter.notifyDataSetChanged();
        }
    }
}
