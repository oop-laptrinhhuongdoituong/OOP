package com.example.baitaplonoop.model;

import java.time.LocalDateTime;

public class Quiz {
    private String quizName;
    private String Description;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private int timeLimit;
    private double mark;

    public Quiz() {
    }

    public Quiz(String quizName, String description, LocalDateTime openTime, LocalDateTime closeTime, int timeLimit, double mark) {
        this.quizName = quizName;
        Description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.timeLimit = timeLimit;
        this.mark = mark;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
