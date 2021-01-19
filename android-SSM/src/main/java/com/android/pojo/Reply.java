package com.android.pojo;


public class Reply {

  private long id;
  private long discussId;
  private long author;
  private String content;
  private java.sql.Date date;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDiscussId() {
    return discussId;
  }

  public void setDiscussId(long discussId) {
    this.discussId = discussId;
  }


  public long getAuthor() {
    return author;
  }

  public void setAuthor(long author) {
    this.author = author;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

}
