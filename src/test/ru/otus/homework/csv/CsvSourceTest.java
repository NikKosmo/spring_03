package ru.otus.homework.csv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.homework.configuration.TestConfiguration;
import ru.otus.homework.tester.model.Question;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvSourceTest {

    @Mock
    private TestConfiguration configuration;

    @Test
    public void fullTest() {
        given(configuration.getQuestionFilePath()).willReturn("questionsForTesting.csv");
        CsvSource source = new CsvSource(configuration);
        ru.otus.homework.tester.model.Test testFromSource = source.getTest();
        assertEquals(1, testFromSource.getQuestions().size());
        assertEquals(4, testFromSource.getVariant(1).getOptions().size());
        Question question = testFromSource.getQuestion(1);
        assertEquals("Количество примитивных типов в Java", question.getQuestion().trim());
        assertTrue(testFromSource.getQuestion(1).isAnswerRight('c'));
        assertFalse(testFromSource.getQuestion(1).isAnswerRight('b'));
        assertEquals(Collections.singletonMap(1, 1), testFromSource.submitAnswers(Collections.singletonMap(1, 'c')));
        assertEquals(Collections.singletonMap(1, 0), testFromSource.submitAnswers(Collections.singletonMap(1, 'b')));
    }
}