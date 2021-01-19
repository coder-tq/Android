package com.android.pojo;


public class Problem {

  private long id;
  private long type;
  private String title;
  private String content;
  private String ans;
  private String note;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
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


  public String getAns() {
    return ans;
  }

  public void setAns(String ans) {
    this.ans = ans;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
