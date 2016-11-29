package com.example.phompang.lib2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phompang.lib2.model.Book;
import com.example.phompang.lib2.model.Borrow;
import com.example.phompang.lib2.model.Member;
import com.example.phompang.lib2.model.Staff;

import java.util.ArrayList;

/**
 * Created by phompang on 11/27/2016 AD.
 */

public class SqliteUtils extends SQLiteOpenHelper {

    public static final String DB_NAME = "lib2";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STAFF = "staff";
    public static final String TABLE_MEMBER = "member";
    public static final String TABLE_BOOK = "book";
    public static final String TABLE_BORROW = "borrow";

    class TableStaff {
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
    }

    class TableMember {
        public static final String ID = "id";
        public static final String FNAME = "firstname";
        public static final String LNAME = "lastname";
    }

    class TableBook {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AUTHOR = "author";
    }

    class TableBorrow {
        public static final String ID = "id";
        public static final String B_ID = "b_id";
        public static final String M_ID = "m_id";
        public static final String S_ID = "s_id";
        public static final String BORROW_DATE = "borrow_date";
        public static final String RETURN_DATE = "return_date";
        public static final String STATUS = "status";
    }

    public SqliteUtils(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table staff(id integer primary key, username text, password text);");
        sqLiteDatabase.execSQL("create table member(id integer primary key, firstname text, lastname text);");
        sqLiteDatabase.execSQL("create table book(id integer primary key, name text, author text);");
        sqLiteDatabase.execSQL("create table borrow(id integer primary key, " +
                "b_id integer REFERENCES book(id) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "m_id integer REFERENCES member(id) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "s_id integer REFERENCES staff(id) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "borrow_date text, return_date text, status text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists staff");
        sqLiteDatabase.execSQL("drop table if exists member");
        sqLiteDatabase.execSQL("drop table if exists book");

        onCreate(sqLiteDatabase);
    }

    public void addStaff(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableStaff.USERNAME, username);
        values.put(TableStaff.PASSWORD, password);

        db.insert(TABLE_STAFF, null, values);
        db.close();
    }

    public void updateStaff(int id, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableStaff.USERNAME, username);
        values.put(TableStaff.PASSWORD, password);

        db.update(TABLE_STAFF, values, TableStaff.ID + "=" + id, null);
        db.close();
    }

    public void deleteStaff(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_STAFF, TableStaff.ID + "=" + id, null);
        db.close();
    }

    public ArrayList<Staff> getStaffs() {
        SQLiteDatabase database = this.getReadableDatabase();

        String sql = "select * from staff";

        Cursor cursor = database.rawQuery(sql, null);

        ArrayList<Staff> staffs = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Staff s = new Staff();
                s.setId(cursor.getInt(0));
                s.setUsername(cursor.getString(1));
                s.setPassword(cursor.getString(2));
                staffs.add(s);
                cursor.moveToNext();
            }
            cursor.close();
        }
        database.close();

        return staffs;
    }

    public ArrayList<Member> getMembers() {
        SQLiteDatabase database = this.getReadableDatabase();

        String sql = "select * from member";

        Cursor cursor = database.rawQuery(sql, null);

        ArrayList<Member> members = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Member s = new Member();
                s.setId(cursor.getInt(0));
                s.setFirstname(cursor.getString(1));
                s.setLastname(cursor.getString(2));
                members.add(s);
                cursor.moveToNext();
            }
            cursor.close();
        }
        database.close();

        return members;
    }

    public ArrayList<Book> getBooks() {
        SQLiteDatabase database = this.getReadableDatabase();

        String sql = "select * from book";

        Cursor cursor = database.rawQuery(sql, null);

        ArrayList<Book> books = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Book s = new Book();
                s.setId(cursor.getInt(0));
                s.setName(cursor.getString(1));
                s.setAuthor(cursor.getString(2));
                books.add(s);
                cursor.moveToNext();
            }
            cursor.close();
        }
        database.close();

        return books;
    }

    public void addMember(String fname, String lname) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableMember.FNAME, fname);
        values.put(TableMember.LNAME, lname);

        db.insert(TABLE_MEMBER, null, values);
        db.close();
    }

    public void updateMember(int id, String fname, String lname) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableMember.FNAME, fname);
        values.put(TableMember.LNAME, lname);

        db.update(TABLE_MEMBER, values, TableMember.ID + "=" + id, null);
        db.close();
    }

    public void deleteMember(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MEMBER, TableMember.ID + "=" + id, null);
        db.close();
    }

    public void addBook(String name, String author) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableBook.NAME, name);
        values.put(TableBook.AUTHOR, author);

        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    public void updateBook(int id, String name, String author) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableBook.NAME, name);
        values.put(TableBook.AUTHOR, author);

        db.update(TABLE_BOOK, values, TableBook.ID + "=" + id, null);
        db.close();
    }

    public void deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_BOOK, TableBook.ID + "=" + id, null);
        db.close();
    }

    public void addBorrow(int b, int m, int s, String start, String end) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableBorrow.B_ID, b);
        values.put(TableBorrow.M_ID, m);
        values.put(TableBorrow.S_ID, s);
        values.put(TableBorrow.BORROW_DATE, start);
        values.put(TableBorrow.RETURN_DATE, end);
        values.put(TableBorrow.STATUS, "BORROW");

        db.insert(TABLE_BORROW, null, values);
        db.close();
    }

    public void returnBook(int id, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableBorrow.STATUS, status);

        db.update(TABLE_BORROW, values, TableBorrow.ID + "=" + id, null);
        db.close();
    }

    public ArrayList<Borrow> getBorrows() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Borrow> borrows = new ArrayList<>();

        String sql = "select * from borrow where status = 'BORROW' or status = 'OVERDUE';";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Borrow b = new Borrow();
            b.setId(cursor.getInt(0));
            b.setStart(DateUtils.toDate(cursor.getString(4)));
            b.setEnd(DateUtils.toDate(cursor.getString(5)));
            b.setStatus(cursor.getString(6));

            Cursor getBook = db.rawQuery("select * from book where id = " + cursor.getInt(1) + ";", null);
            getBook.moveToFirst();
            if (getBook.getCount() > 0) {
                Book book = new Book();
                book.setId(getBook.getInt(0));
                book.setName(getBook.getString(1));
                book.setAuthor(getBook.getString(2));
                b.setBook(book);
            }

            Cursor getMember = db.rawQuery("select * from member where id = " + cursor.getInt(2) + ";", null);
            getMember.moveToFirst();
            if (getMember.getCount() > 0) {
                Member member = new Member();
                member.setId(getMember.getInt(0));
                member.setFirstname(getMember.getString(1));
                member.setLastname(getMember.getString(2));
                b.setMember(member);
            }

            Cursor getStaff = db.rawQuery("select * from staff where id = " + cursor.getInt(3) + ";", null);
            getStaff.moveToFirst();
            if (getStaff.getCount() > 0) {
                Staff staff = new Staff();
                staff.setId(getStaff.getInt(0));
                staff.setUsername(getStaff.getString(1));
                staff.setPassword(getStaff.getString(2));
                b.setStaff(staff);
            }

            borrows.add(b);
            getBook.close();
            getMember.close();
            getStaff.close();
            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return borrows;
    }

    public ArrayList<Member> getOverdue() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Member> borrows = new ArrayList<>();

        String sql = "select distinct m_id from borrow where status = 'OVERDUE';";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Cursor getMember = db.rawQuery("select * from member where id = " + cursor.getInt(0) + ";", null);
            getMember.moveToFirst();
            if (getMember.getCount() > 0) {
                Member member = new Member();
                member.setId(getMember.getInt(0));
                member.setFirstname(getMember.getString(1));
                member.setLastname(getMember.getString(2));
                borrows.add(member);
            }

            getMember.close();
            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return borrows;
    }

    public Staff login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from staff where username = '" + username + "' and password='" + password + "';";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (cursor.getCount() > 0) {
            Staff s = new Staff();
            s.setId(cursor.getInt(0));
            s.setUsername(cursor.getString(1));
            s.setPassword(cursor.getString(2));
            cursor.close();
            db.close();
            return s;
        }
        return null;
    }
}
