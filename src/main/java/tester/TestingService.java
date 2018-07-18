package tester;

import tester.model.OptionsHolder;
import tester.model.Question;

import java.util.Collection;
import java.util.Map;

public interface TestingService {

    Question getQuestion(int questionNumber);

    Collection<Question> getQuestions();

    OptionsHolder getOptions(int questionNumber);

    String submitAnswers(Map<Integer, Character> answers);

    void startTest();
}
