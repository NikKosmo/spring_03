package configuration;

import csv.CsvSource;
import tester.TestSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@Configuration
public class TestSourceConfiguration {

    @Value("${csv.source}")
    private String questionFilePath;

    @Bean
    public TestSource testSource(){
        return new CsvSource(questionFilePath);
    }
}
