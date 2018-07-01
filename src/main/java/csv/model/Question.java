package csv.model;

public class Question {
    private int questionNumber;
    private String question;
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

    public char getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(char rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public boolean isAnswerRight(char givenAnswer) {
        return rightAnswer == givenAnswer;
    }
}
