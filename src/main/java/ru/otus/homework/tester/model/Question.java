package ru.otus.homework.tester.model;

public class Question {
    private int questionNumber;
    private String question;
    private String options;
    private char rightAnswer;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public char getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(char rightAnswer) {
        this.rightAnswer = Character.toLowerCase(rightAnswer);
    }

    public boolean isAnswerRight(char givenAnswer) {
        return rightAnswer == Character.toLowerCase(givenAnswer);
    }
}
