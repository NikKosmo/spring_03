package ru.otus.homework.tester.shell;

import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.tester.TestSource;
import ru.otus.homework.tester.TestingService;
import ru.otus.homework.tester.model.OptionsHolder;
import ru.otus.homework.tester.model.Question;
import ru.otus.homework.tester.model.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ShellComponent
public class TestingByCommandsService implements TestingService {

    private MessageSource messageSource;
    private Test test;

    public TestingByCommandsService(TestSource testSource, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.test = testSource.getTest();
    }

    @ShellMethod("start")
    public String start(@ShellOption String name) {
        return messageSource.getMessage("test.hello",
                new String[]{name, String.valueOf(getQuestions().size())},
                Locale.getDefault());
    }

    @ShellMethod("rules")
    public String rules() {
        return messageSource.getMessage("test.rules", null, Locale.getDefault());
    }

    @ShellMethod("get question")
    public String question(@ShellOption int questionNumber) {
        return String.format("%d) %s \n%s", questionNumber,
                getQuestion(questionNumber).getQuestion(),
                getOptions(questionNumber));
    }

    @ShellMethod("submit answers")
    public String submit(@ShellOption String answers) {
        Map<Integer, Character> answersMap = new HashMap<>();
        int questionNumber = 1;
        for (char answer : answers.trim().toCharArray()) {
            answersMap.put(questionNumber++, answer);
        }
        Map<Integer, Integer> resultMap = submitAnswers(answersMap);
        Integer numberOfQuestions = resultMap.keySet().iterator().next();
        Integer numberOfRightAnswers = resultMap.values().iterator().next();
        return messageSource.getMessage("test.result",
                new String[]{numberOfQuestions.toString(), numberOfRightAnswers.toString()},
                Locale.ENGLISH);
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
    public Map<Integer, Integer> submitAnswers(Map<Integer, Character> answers) {
        return test.submitAnswers(answers);
    }
}
