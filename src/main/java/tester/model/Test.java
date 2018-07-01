package tester.model;

import csv.model.OptionsHolder;
import csv.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private String studentName;
    private int questionsCount;
    private int rightAnswersCount;
    private Map<Integer, Question> questionMap = new HashMap<>();
    private Map<Integer, OptionsHolder> variantsMap = new HashMap<>();

    public void addQuestions(List<Question> questions) {
        questions.forEach(q -> questionMap.put(q.getQuestionNumber(), q));
        questionsCount = questionMap.size();
    }

    public Iterable<Question> getQuestions() {
        return questionMap.values();
    }

    public void addVariants(List<OptionsHolder> variants) {
        variants.forEach(v -> variantsMap.put(v.getQuestionNumber(), v));
    }

    public OptionsHolder getVariant(int number) {
        return variantsMap.get(number);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getRightAnswersCount() {
        return rightAnswersCount;
    }

    private void addOneToRightAnswersCount() {
        this.rightAnswersCount++;
    }

    public void submitAnswer(int questionNumber, char answer) {
        if (questionMap.get(questionNumber).isAnswerRight(answer)) {
            addOneToRightAnswersCount();
        }
    }

}
