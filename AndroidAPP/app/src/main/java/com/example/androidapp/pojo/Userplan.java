package com.example.androidapp.pojo;


import java.sql.Date;

public class Userplan {

    private long user_id;
    private long plan_id;
    private String name;
    private java.sql.Date start_date;
    private java.sql.Date end_date;
    private long last_date;
    private String content;
    private String submit_content;
    private String note;


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(long plan_id) {
        this.plan_id = plan_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public long getLast_date() {
        return last_date;
    }

    public void setLast_date(long last_date) {
        this.last_date = last_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmit_content() {
        return submit_content;
    }

    public void setSubmit_content(String submit_content) {
        this.submit_content = submit_content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
