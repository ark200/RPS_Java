package com.example.rpsjava;

public class GameLog {
    private long date;
    private String result;
    private int myScore;
    private int oppScore;

    public GameLog(long date, String result, int myScore, int oppScore) {
        this.date = date;
        this.result = result;
        this.myScore = myScore;
        this.oppScore = oppScore;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getMyScore() {
        return myScore;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public int getOppScore() {
        return oppScore;
    }

    public void setOppScore(int oppScore) {
        this.oppScore = oppScore;
    }
}
