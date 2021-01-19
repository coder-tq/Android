package com.example.androidapp.pojo;


public class Discuss {

  private long id;
  private String title;
  private String content;
  private long author;
  private java.sql.Date date;
  private String note;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getAuthor() {
    return author;
  }

  public void setAuthor(long author) {
    this.author = author;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "Discuss{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", author=" + author +
            ", date=" + date +
            ", note='" + note + '\'' +
            '}';
  }
}
