package com.example.androidapp.pojo;

public class Paper {

  private long id;
  private java.sql.Timestamp startDate;
  private String note;
  private long userA;
  private long userB;
  private long userAScore;
  private long userBScore;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Timestamp startDate) {
    this.startDate = startDate;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }


  public long getUserA() {
    return userA;
  }

  public void setUserA(long userA) {
    this.userA = userA;
  }


  public long getUserB() {
    return userB;
  }

  public void setUserB(long userB) {
    this.userB = userB;
  }


  public long getUserAScore() {
    return userAScore;
  }

  public void setUserAScore(long userAScore) {
    this.userAScore = userAScore;
  }


  public long getUserBScore() {
    return userBScore;
  }

  public void setUserBScore(long userBScore) {
    this.userBScore = userBScore;
  }

}
