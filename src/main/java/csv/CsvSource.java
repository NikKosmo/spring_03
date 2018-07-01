package csv;

import csv.model.OptionsHolder;
import csv.model.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tester.model.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvSource implements TestSource {

    private String questionFilePath;
    private String optionsFilePath;


    private List<Question> getQuestions() throws IOException {
        List<Question> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (Reader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(questionFilePath), "utf-8"))) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : parser.getRecords()) {
                Question question = new Question();
                question.setQuestionNumber(Integer.parseInt(record.get("questionNumber").trim()));
                question.setQuestion(record.get("question"));
                question.setRightAnswer(record.get("rightAnswer").charAt(0));
                result.add(question);
            }
        }
        return result;
    }

    private List<OptionsHolder> getVariants() throws IOException {
        List<OptionsHolder> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (Reader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(optionsFilePath), "utf-8"))) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : parser.getRecords()) {
                OptionsHolder holder = new OptionsHolder();
                holder.setQuestionNumber(Integer.parseInt(record.get("questionNumber").trim()));
                Map<String, String> questionsMap = record.toMap();
                questionsMap.remove("questionNumber");
                Map<Character, String> questions = new HashMap<>();
                questionsMap.forEach((key, value) -> questions.put(key.charAt(0), value));
                holder.addOptions(questions);
                result.add(holder);
            }
        }
        return result;
    }

    @Override
    public Test getTest() throws IOException {
        Test result = new Test();
        List<Question> questions = getQuestions();
        List<OptionsHolder> variants = getVariants();
        if (questions.size() != variants.size()) {
            throw new IllegalArgumentException("Number of Questions and AnswerVariants differ");
        }
        result.addQuestions(questions);
        result.addVariants(variants);
        return result;
    }

    public void setQuestionFilePath(String questionFilePath) {
        this.questionFilePath = questionFilePath;
    }

    public void setOptionsFilePath(String optionsFilePath) {
        this.optionsFilePath = optionsFilePath;
    }

}
