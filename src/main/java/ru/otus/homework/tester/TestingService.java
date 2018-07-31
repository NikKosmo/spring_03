package ru.otus.homework.tester;

import ru.otus.homework.tester.model.OptionsHolder;
import ru.otus.homework.tester.model.Question;

import java.util.Collection;
import java.util.Map;

public interface TestingService {

    Question getQuestion(int questionNumber);

    Collection<Question> getQuestions();

    OptionsHolder getOptions(int questionNumber);

    Map<Integer, Integer> submitAnswers(Map<Integer, Character> answers);
}
