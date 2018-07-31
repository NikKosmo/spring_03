package ru.otus.homework.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework.csv.CsvSource;
import ru.otus.homework.tester.TestSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Component
@ConfigurationProperties("test-source")
public class TestConfiguration {

    private String questionFilePath;

    public String getQuestionFilePath() {
        return questionFilePath;
    }

    public void setQuestionFilePath(String questionFilePath) {
        this.questionFilePath = questionFilePath;
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
