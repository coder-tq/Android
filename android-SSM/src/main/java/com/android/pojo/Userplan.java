package com.android.pojo;


public class Userplan {

  private long userId;
  private long planId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private long lastDate;
  private String submitContent;
  private String note;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getPlanId() {
    return planId;
  }

  public void setPlanId(long planId) {
    this.planId = planId;
  }


  public java.sql.Date getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Date startDate) {
    this.startDate = startDate;
  }


  public java.sql.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


  public long getLastDate() {
    return lastDate;
  }

  public void setLastDate(long lastDate) {
    this.lastDate = lastDate;
  }


  public String getSubmitContent() {
    return submitContent;
  }

  public void setSubmitContent(String submitContent) {
    this.submitContent = submitContent;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
