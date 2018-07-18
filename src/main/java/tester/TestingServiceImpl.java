package tester;

import tester.model.OptionsHolder;
import tester.model.Question;
import tester.model.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TestingServiceImpl implements TestingService {

    private Test test;

    public TestingServiceImpl(TestSource testSource) throws IOException {
        this.test = testSource.getTest();
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
        System.out.println("Prepare to take the test");
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
        System.out.println("Come back when you will be ready.\nGood bye!");
    }

    private void startTesting(BufferedReader br) throws IOException {
        System.out.println("Hello " + getStudentName(br));
        runTest(br);
    }

    private void runTest(BufferedReader br) throws IOException {
        Map<Integer, Character> answers = new HashMap<>();
        for (Question question : getQuestions()) {
            System.out.println(question.getQuestion());
            System.out.println(getOptions(question.getQuestionNumber()));
            System.out.println("Select your variant");
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
        System.out.println("Please enter your name");
        return br.readLine();
    }


    private boolean checkReady(BufferedReader br) throws IOException {
        boolean answeredCorrectly = false;
        boolean ready = false;
        do {
            System.out.println("Are you are ready? (y/n)");
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
