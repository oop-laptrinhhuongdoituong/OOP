package com.example.baitaplonoop.model;

public class QuestionInQuiz {
    private String questionID;
    private String quizName;
    private double yourMark;

    public QuestionInQuiz() {
    }

    public QuestionInQuiz(String questionID, String quizName, double yourMark) {
        this.questionID = questionID;
        this.quizName = quizName;
        this.yourMark = yourMark;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public double getYourMark() {
        return yourMark;
    }

    public void setYourMark(double yourMark) {
        this.yourMark = yourMark;
    }
}
