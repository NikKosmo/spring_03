package ru.otus.homework.tester.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private Map<Integer, Question> questionMap = new HashMap<>();
    private Map<Integer, OptionsHolder> variantsMap = new HashMap<>();

    public void addQuestions(List<Question> questions) {
        questions.forEach(q -> questionMap.put(q.getQuestionNumber(), q));
    }

    public Collection<Question> getQuestions() {
        return questionMap.values();
    }

    public Question getQuestion(int questionNumber) {
        return questionMap.get(questionNumber);
    }

    public void addVariants(List<OptionsHolder> variants) {
        variants.forEach(v -> variantsMap.put(v.getQuestionNumber(), v));
    }

    public OptionsHolder getVariant(int number) {
        return variantsMap.get(number);
    }

    private boolean submitAnswer(Integer questionNumber, Character answer) {
        return questionMap.get(questionNumber).isAnswerRight(answer);
    }

    public String submitAnswers(Map<Integer, Character> answers) {
        long rightAnswersCount = answers.entrySet()
                .stream()
                .filter(e -> submitAnswer(e.getKey(), e.getValue()))
                .count();
        return String.format("Out of %d questions you've answered %d questions right",
                questionMap.size(), rightAnswersCount);
    }

}
