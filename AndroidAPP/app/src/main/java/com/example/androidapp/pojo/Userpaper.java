package com.example.androidapp.pojo;


public class Userpaper {

  private long userId;
  private long paperId;
  private long lastTime;
  private long score;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getPaperId() {
    return paperId;
  }

  public void setPaperId(long paperId) {
    this.paperId = paperId;
  }


  public long getLastTime() {
    return lastTime;
  }

  public void setLastTime(long lastTime) {
    this.lastTime = lastTime;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

}
