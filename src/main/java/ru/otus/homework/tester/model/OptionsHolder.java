package ru.otus.homework.tester.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OptionsHolder {

    private int questionNumber;
    private Map<Character, String> options = new HashMap<>();

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void addOptions(Map<Character, String> options) {
        this.options.putAll(options);
    }

    public Map<Character, String> getOptions(){
        return Collections.unmodifiableMap(options);
    }

    @Override
    public String toString() {
        return options.toString();
    }
}
