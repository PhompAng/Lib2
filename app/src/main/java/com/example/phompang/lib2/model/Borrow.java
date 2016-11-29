package com.example.phompang.lib2.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by phompang on 11/29/2016 AD.
 */

public class Borrow implements Serializable {
    private int id;
    private Book book;
    private Staff staff;
    private Member member;
    private Date start;
    private Date end;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + ". Book: " + getBook().getName() + "\n" +
                "Member: " + getMember().getFirstname() + "\n" +
                "Staff: " +  getStaff().getUsername() + "\n" +
                "Borrow: " + getStart().toString() + "\n" +
                "Due: " + getEnd().toString();
    }
}
