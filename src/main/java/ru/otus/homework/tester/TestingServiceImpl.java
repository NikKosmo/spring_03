package ru.otus.homework.tester;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.tester.model.OptionsHolder;
import ru.otus.homework.tester.model.Question;
import ru.otus.homework.tester.model.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class TestingServiceImpl implements TestingService {

    private Test test;
    private MessageSource messageSource;

    public TestingServiceImpl(TestSource testSource, MessageSource messageSource) {
        this.test = testSource.getTest();
        this.messageSource = messageSource;
    }

    @Override
    public Question getQuestion(int questionNumber) {
        return test.getQuestion(questionNumber);
    }

    @Override
    public Collection<Question> getQuestions() {
        return test.getQuestions();
    }

    @Override
    public OptionsHolder getOptions(int questionNumber) {
        return test.getVariant(questionNumber);
    }

    @Override
    public String submitAnswers(Map<Integer, Character> answers) {
        return test.submitAnswers(answers);
    }

    public void startTest() {
        System.out.println(messageSource.getMessage("user.start", null,  Locale.getDefault()));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean ready = checkReady(br);
            if (ready) {
                startTesting(br);
            } else {
                endTesting();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void endTesting() {
        System.out.println(messageSource.getMessage("user.bye", null,  Locale.getDefault()));
    }

    private void startTesting(BufferedReader br) throws IOException {
        String studentName = getStudentName(br);
        System.out.println(messageSource.getMessage("user.hello", new String[]{studentName}, Locale.getDefault()));
        runTest(br);
    }

    private void runTest(BufferedReader br) throws IOException {
        Map<Integer, Character> answers = new HashMap<>();
        for (Question question : getQuestions()) {
            System.out.println(question.getQuestion());
            System.out.println(getOptions(question.getQuestionNumber()));
            System.out.println(messageSource.getMessage("select.variant", null,  Locale.getDefault()));
            char answer = getAnswer(br);
            answers.put(question.getQuestionNumber(), answer);
        }
        System.out.println(submitAnswers(answers));
    }

    private char getAnswer(BufferedReader br) throws IOException {
        String answer = br.readLine();
        return answer.charAt(0);
    }

    private String getStudentName(BufferedReader br) throws IOException {
        System.out.println(messageSource.getMessage("user.name", null,  Locale.getDefault()));
        return br.readLine();
    }


    private boolean checkReady(BufferedReader br) throws IOException {
        boolean answeredCorrectly = false;
        boolean ready = false;
        do {
            System.out.println(messageSource.getMessage("user.ready", null,  Locale.getDefault()));
            String readyString = br.readLine();
            if (readyString.equalsIgnoreCase("y")) {
                answeredCorrectly = true;
                ready = true;
            } else if (readyString.equalsIgnoreCase("n")) {
                answeredCorrectly = true;
                ready = false;
            }
        } while (!answeredCorrectly);
        return ready;
    }

}
