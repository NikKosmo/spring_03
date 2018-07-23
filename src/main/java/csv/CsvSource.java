package csv;

import tester.TestSource;
import tester.model.OptionsHolder;
import tester.model.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${csv.source}")
    private String questionFilePath;

    public CsvSource(String questionFilePath) {
        this.questionFilePath = questionFilePath;
    }


    private List<Question> getQuestions() throws IOException {
        List<Question> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (Reader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(questionFilePath), "utf-8"))) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : parser.getRecords()) {
                Question question = new Question();
                question.setQuestionNumber(Integer.parseInt(record.get(0).trim()));
                question.setQuestion(record.get(1));
                question.setRightAnswer(record.get(2).charAt(0));
                result.add(question);
            }
        }
        return result;
    }

    private List<OptionsHolder> getVariants() throws IOException {
        List<OptionsHolder> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (Reader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(questionFilePath), "utf-8"))) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : parser.getRecords()) {
                OptionsHolder holder = new OptionsHolder();
                holder.setQuestionNumber(Integer.parseInt(record.get(0).trim()));
                String fieldValue = record.get(3);
                String[] questionsMap = fieldValue.split("\\.");
                Map<Character, String> questions = new HashMap<>();
                for (String option : questionsMap) {
                    String[] optionParts = option.split("\\)");
                    questions.put(optionParts[0].charAt(0), optionParts[1]);
                }
                holder.addOptions(questions);
                result.add(holder);
            }
        }
        return result;
    }

    @Override
    public Test getTest()  {
        Test result = null;
        try {
            result =new Test();
            List<Question> questions = getQuestions();
            List<OptionsHolder> variants = getVariants();
            if (questions.size() != variants.size()) {
                throw new IllegalArgumentException("Number of Questions and AnswerVariants differ");
            }
            result.addQuestions(questions);
            result.addVariants(variants);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
