package tester;

import csv.TestSource;
import csv.model.Question;
import tester.model.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestingService {

    private TestSource testSource;

    public TestingService(TestSource testSource) {
        this.testSource = testSource;
    }

    public void startTest() throws IOException {
        System.out.println("Prepare to take the test");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean ready = checkReady(br);
        if (ready) {
            startTesting(br);
        } else {
            endTesting();
        }
    }

    private void endTesting() {
        System.out.println("Come back when you will be ready.\nGood bye!");
    }

    private void startTesting(BufferedReader br) throws IOException {
        Test test = testSource.getTest();
        test.setStudentName(getStudentName(br));
        runTest(test, br);
        System.out.println(prepareResultString(test));
    }

    private void runTest(Test test, BufferedReader br) throws IOException {
        for (Question question : test.getQuestions()) {
            System.out.println(question.getQuestion());
            System.out.println(test.getVariant(question.getQuestionNumber()));
            System.out.println("Select your variant");
            char answer = getAnswer(br);
            test.submitAnswer(question.getQuestionNumber(), answer);
        }
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

    private String prepareResultString(Test test) {
        return String.format("%s! out of %d questions you've answered %d questions right",
                test.getStudentName(), test.getQuestionsCount(), test.getRightAnswersCount());
    }
}
